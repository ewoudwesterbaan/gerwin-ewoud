package model;

import java.util.ArrayList;
import java.util.List;

import factory.ReaderFactory;
import reader.Reader;

public class PresentationManager implements Presenter {

	// Java kent ook (class) java.util.Observable en (interface) Observer
	// de PresentationManager implementeert de Observer pattern nu 'zelf'
	// (zo laten?)

	private List<Presentation> presentations;
	private List<PresenterObserver> observers;
	private Presentation currentPresentation;
	private int currentPresentationIndex; // wel zo handig...

	public PresentationManager() {
		presentations = new ArrayList<Presentation>();
		observers = new ArrayList<PresenterObserver>();

		// initially, no presentation selected
		reset();
	}

	private void reset() {
		currentPresentation = null;
		currentPresentationIndex = -1;

		// state has changed, notify all observers
		notifyAllObservers();
	}
	
	@Override
	public int getSize() {
		return presentations.size();
	}
	
	@Override
	public void append(Presentation presentation) {
		presentations.add(presentation);
	}

	@Override
	public Presentation getCurrentPresentation() {
		return currentPresentation;
	}

	@Override
	public boolean hasNext() {
		if (presentations.size() == 0)
			return false;
		return (currentPresentationIndex < (presentations.size() - 1));
	}

	@Override
	public boolean hasPrevious() {
		if (presentations.size() == 0)
			return false;
		return (currentPresentationIndex > 0);
	}

	@Override
	// assumption: zero-based indexing
	public void selectPresentation(int number) {
		if ((number < 0) || (number >= presentations.size()))
			return;

		currentPresentation = presentations.get(number);
		currentPresentationIndex = number;

		// restart presentation at first slide
		currentPresentation.selectSlide(0);

		// state has changed, notify all observers
		notifyAllObservers();
	}

	@Override
	public void next() {
		selectPresentation(currentPresentationIndex + 1);
	}

	@Override
	public void previous() {
		selectPresentation(currentPresentationIndex - 1);
	}

	@Override
	public void attach(PresenterObserver presenterObserver) {
		// add observer to observer list
		if (!observers.contains(presenterObserver))
			observers.add(presenterObserver);
	}

	@Override
	public void detach(PresenterObserver presenterObserver) {
		// remove observer from observer list
		if (observers.contains(presenterObserver))
			observers.remove(presenterObserver);
	}

	private void notifyAllObservers() {
		// update all presenter observers
		for (PresenterObserver observer : observers) {
			observer.update(currentPresentation);
		}
	}

	@Override
	public void loadFile(String fileName) {
		Reader reader = ReaderFactory.getInstance().getReader(fileName);
		presentations = reader.load();

		// after loading of presentations, the first one is automatically selected
		if (presentations.size() > 0)
			selectPresentation(0);
		else
			reset();
	}

	@Override
	public void saveFile(String fileName) {
		// TODO Auto-generated method stub
		// ...
	}
}