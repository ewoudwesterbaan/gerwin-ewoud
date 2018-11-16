package model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Verantwoordelijk voor de correcte volgorde van de {@link Slide}s.
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public class SlideSequence implements Presentation {
	private String title; // de titel van de presentatie
	private List<Slide> slides;
	private Slide currentSlide;
	private int currentSlideIndex; // wel zo handig...

	/**
	 * Constructor. Vult de titel en initialiseert de lijst met {@link Slide}s. Zet de {@code currentSlide} en {@code currentSlideIndex} op een niet-geselecteerde {@link Slide}.
	 * @param title Titel van deze vorm van presentatie.
	 */
	public SlideSequence(String title) {
		this.title = title;
		slides = new ArrayList<Slide>();

		// initially, no slide selected
		currentSlide = null;
		currentSlideIndex = -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfSlides() {
		return slides.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void append(Slide slide) {
		slides.add(slide);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Slide getCurrentSlide() {
		return currentSlide;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSlideNumber() {
		return currentSlideIndex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasNext() {
		if (slides.size() == 0)
			return false;
		return (currentSlideIndex < (slides.size() - 1));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasPrevious() {
		if (slides.size() == 0)
			return false;
		return (currentSlideIndex > 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void next() {
		selectSlide(currentSlideIndex + 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void previous() {
		selectSlide(currentSlideIndex - 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void selectSlide(int number) {
		if ((number < 0) || (number >= slides.size()))
			return;

		currentSlide = slides.get(number);
		currentSlideIndex = number;
	}
}