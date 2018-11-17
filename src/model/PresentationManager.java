package model;

import java.util.ArrayList;
import java.util.List;

import factory.ReaderFactory;
import observer.PresentationObserver;
import observer.PresenterObserver;
import reader.Reader;

/**
 * Verantwoordelijk voor het aansturen van {@link Presentation}s en
 * {@link Slide}s. Bij het activeren van een andere presentatie en/of andere
 * slide worden aangemelde observers genotified.
 * 
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public class PresentationManager implements Presenter {
	private List<Presentation> presentations;
	private List<PresenterObserver> observers;
	private List<PresentationObserver> presentationObservers;
	private Presentation currentPresentation;

	/**
	 * Constructor. Initialiseert de lijst met {@link Presentation}s en
	 * initialiseert de observer lijsten. Zet de {@code currentPresentation} op een
	 * niet-geselecteerde {@link Presentation}.
	 */
	public PresentationManager() {
		presentations = new ArrayList<Presentation>();
		observers = new ArrayList<PresenterObserver>();
		presentationObservers = new ArrayList<PresentationObserver>();

		// initially, no presentation selected
		currentPresentation = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize() {
		return presentations.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void append(Presentation presentation) {
		presentations.add(presentation);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Presentation getCurrentPresentation() {
		return currentPresentation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfSlides() {
		int numberOfSlides = 0;
		if (currentPresentation != null) {
			numberOfSlides = currentPresentation.getNumberOfSlides();
		}
		return numberOfSlides;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Slide getCurrentSlide() {
		Slide currentSlide = null;
		if (currentPresentation != null) {
			currentSlide = currentPresentation.getCurrentSlide();
		}
		return currentSlide;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSlideNumber() {
		int currentSlideNumber = -1;
		if (currentPresentation != null) {
			currentSlideNumber = currentPresentation.getSlideNumber();
		}
		return currentSlideNumber;
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void selectSlide(int number) {
		if (currentPresentation != null) {
			currentPresentation.selectSlide(number);

			// state has changed, notify all observers
			// todo: we weten niet zeker of er een andere slide actief is nu...
			notifyAllPresentationObservers();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void attach(PresenterObserver presenterObserver) {
		// add observer to observer list
		if (!observers.contains(presenterObserver))
			observers.add(presenterObserver);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void detach(PresenterObserver presenterObserver) {
		// remove observer from observer list
		if (observers.contains(presenterObserver))
			observers.remove(presenterObserver);
	}

	/**
	 * Notified alle {@link Presenter} observers zodra een andere
	 * {@link Presentation} geactiveerd is.
	 */
	private void notifyAllPresenterObservers() {
		// update all presenter observers
		for (PresenterObserver observer : observers) {
			observer.update(currentPresentation);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void attach(PresentationObserver presentationObserver) {
		// add observer to observer list
		if (!presentationObservers.contains(presentationObserver))
			presentationObservers.add(presentationObserver);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void detach(PresentationObserver presentationObserver) {
		// remove observer from observer list
		if (presentationObservers.contains(presentationObserver))
			presentationObservers.remove(presentationObserver);
	}

	/**
	 * Notified alle {@link Presentation} observers zodra een andere {@link Slide}
	 * geactiveerd is.
	 */
	private void notifyAllPresentationObservers() {
		Slide currentSlide = null;
		if (this.currentPresentation != null)
			currentSlide = currentPresentation.getCurrentSlide();

		// update all presentation observers
		for (PresentationObserver observer : presentationObservers) {
			observer.update(currentSlide);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void loadFile(String fileName) {
		Reader reader = ReaderFactory.getInstance().getReader(fileName);
		presentations = reader.load();

		// after loading of presentations, the first one is automatically selected
		if (presentations.size() > 0)
			selectPresentation(0);
		else {
			currentPresentation = null;

			// state has changed, notify all observers
			notifyAllPresenterObservers();
		}
	}
}