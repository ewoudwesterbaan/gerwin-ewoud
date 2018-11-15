package model;

import java.util.ArrayList;
import java.util.List;

public class SlideSequence implements Presentation {
	private String title; // de titel van de presentatie
	private List<Slide> slides;
	private Slide currentSlide;
	private int currentSlideIndex; // wel zo handig...

	private List<PresentationObserver> observers;

	public SlideSequence() {
		slides = new ArrayList<Slide>();
		observers = new ArrayList<PresentationObserver>();

		// initially, no slide selected
		currentSlide = null;
		currentSlideIndex = -1;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int getSize() {
		return slides.size();
	}

	@Override
	public void append(Slide slide) {
		slides.add(slide);
	}

	@Override
	public Slide getCurrentSlide() {
		return currentSlide;
	}

	@Override
	public int getSlideNumber() {
		return currentSlideIndex;
	}

	@Override
	public boolean hasNext() {
		if (slides.size() == 0)
			return false;
		return (currentSlideIndex < (slides.size() - 1));
	}

	@Override
	public boolean hasPrevious() {
		if (slides.size() == 0)
			return false;
		return (currentSlideIndex > 0);
	}

	@Override
	// assumption: zero-based indexing
	public void selectSlide(int number) {
		if ((number < 0) || (number >= slides.size()))
			return;

		currentSlide = slides.get(number);
		currentSlideIndex = number;

		// state has changed, notify all observers
		notifyAllObservers();
	}

	@Override
	public void next() {
		selectSlide(currentSlideIndex + 1);
	}

	@Override
	public void previous() {
		selectSlide(currentSlideIndex - 1);
	}

	@Override
	public void attach(PresentationObserver presentationObserver) {
		// add observer to observer list
		if (!observers.contains(presentationObserver))
			observers.add(presentationObserver);
	}

	@Override
	public void detach(PresentationObserver presentationObserver) {
		// remove observer from observer list
		if (observers.contains(presentationObserver))
			observers.remove(presentationObserver);
	}

	private void notifyAllObservers() {
		// update all presentation observers
		for (PresentationObserver observer : observers) {
			observer.update(currentSlide);
		}
	}
}