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
	private List<PresentationObserver> presentationObservers;
	private Presentation currentPresentation;

	public PresentationManager() {
		presentations = new ArrayList<Presentation>();
		observers = new ArrayList<PresenterObserver>();
		presentationObservers = new ArrayList<PresentationObserver>();

		// initially, no presentation selected
		reset();
	}

	private void reset() {
		currentPresentation = null;

		// state has changed, notify all observers
		notifyAllPresenterObservers();
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
	// assumption: zero-based indexing
	public void selectPresentation(int number) {
		if ((number < 0) || (number >= presentations.size()))
			return;

		// switch (internally) to other presentation
		currentPresentation = presentations.get(number);

		// restart presentation, select first slide
		selectSlide(0);

		// state has changed, notify all observers
		notifyAllPresenterObservers();
	}

	@Override
	public int getNumberOfSlides() {
		int numberOfSlides = 0;
		if (currentPresentation != null) {
			numberOfSlides = currentPresentation.getNumberOfSlides();
		}
		return numberOfSlides;
	}

	@Override
	public Slide getCurrentSlide() {
		Slide currentSlide = null;
		if (currentPresentation != null) {
			currentSlide = currentPresentation.getCurrentSlide();
		}
		return currentSlide;
	}

	@Override
	public int getSlideNumber() {
		int currentSlideNumber = -1;
		if (currentPresentation != null) {
			currentSlideNumber = currentPresentation.getSlideNumber();
		}
		return currentSlideNumber;
	}

	@Override
	public void nextSlide() {
		if (currentPresentation != null) {
			if (currentPresentation.hasNext()) {
				currentPresentation.next();

				// presentation state has changed, notify all observers
				notifyAllPresentationObservers();
			}
		}
	}

	@Override
	public void previousSlide() {
		if (currentPresentation != null) {
			if (currentPresentation.hasPrevious()) {
				currentPresentation.previous();

				// presentation state has changed, notify all observers
				notifyAllPresentationObservers();
			}
		}
	}

	@Override
	public void selectSlide(int number) {
		if (currentPresentation != null) {
			currentPresentation.selectSlide(number);

			// state has changed, notify all observers
			// todo: we weten niet zeker of er een andere slide actief is nu...
			notifyAllPresentationObservers();
		}
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

	private void notifyAllPresenterObservers() {
		// update all presenter observers
		for (PresenterObserver observer : observers) {
			observer.update(currentPresentation);
		}
	}

	@Override
	public void attach(PresentationObserver presentationObserver) {
		// add observer to observer list
		if (!presentationObservers.contains(presentationObserver))
			presentationObservers.add(presentationObserver);
	}

	@Override
	public void detach(PresentationObserver presentationObserver) {
		// remove observer from observer list
		if (presentationObservers.contains(presentationObserver))
			presentationObservers.remove(presentationObserver);
	}

	private void notifyAllPresentationObservers() {
		Slide currentSlide = null;
		if (this.currentPresentation != null)
			currentSlide = currentPresentation.getCurrentSlide();

		// update all presentation observers
		for (PresentationObserver observer : presentationObservers) {
			observer.update(currentSlide);
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