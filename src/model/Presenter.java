package model;

import observer.SlideObserver;
import observer.PresentationObserver;

/**
 * Presenter houdt de verschillende presentaties bij. Een presentatie is een
 * variatie op een set van slides.
 * 
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public interface Presenter {

	/**
	 * Geeft het aantal presentaties terug.
	 * 
	 * @return Het aantal presentaties.
	 */
	int getSize();

	/**
	 * Voegt een {@link Presentation} toe.
	 * 
	 * @param presentation
	 *            De {@link Presentation} om toe te voegen.
	 */
	void append(Presentation presentation);

	/**
	 * Selecteert een {@link Presentation} die getoond moet worden. Aangemelde
	 * {@link PresentationObserver}s worden genotified.
	 * 
	 * @param number
	 *            Het nummer van de {@link Presentation}.
	 */
	void selectPresentation(int number);

	/**
	 * Geeft de huidige actieve {@link Presentation} terug.
	 * 
	 * @return De huidige actieve {@link Presentation}.
	 */
	Presentation getCurrentPresentation();

	/**
	 * 
	 * Geeft het aantal slides terug van de actieve {@link Presentation}.
	 * 
	 * @return Het aantal slides dat de actieve {@link Presentation} bevat.
	 */
	int getNumberOfSlides();

	/**
	 * Geeft de actieve {@link Slide} terug van de actieve {@link Presentation}.
	 * 
	 * @return De actieve {@link Slide}.
	 */
	Slide getCurrentSlide();

	/**
	 * Geeft het nummer van de actieve {@link Slide} terug van de actieve
	 * {@link Presentation}.
	 * 
	 * @return Het nummer van de actieve {@link Slide}.
	 */
	int getSlideNumber();

	/**
	 * Selecteert de volgende {@link Slide} van de actieve {@link Presentation}.
	 * Aangemelde {@link SlideObserver}s worden genotified.
	 */
	void nextSlide();

	/**
	 * Selecteert de vorige {@link Slide} van de actieve {@link Presentation}.
	 * Aangemelde {@link SlideObserver}s worden genotified.
	 */
	void previousSlide();

	/**
	 * Selecteert de {@link Slide} van de actieve {@link Presentation}.
	 * Aangemelde {@link SlideObserver}s worden genotified.
	 * 
	 * @param number
	 *            Nummer van de slide die geselecteerd moet worden.
	 */
	void selectSlide(int number);

	/**
	 * Voegt een observer toe aan de lijst met {@link PresentationObserver} objecten.
	 * 
	 * @param presenterObserver
	 *            Observer die toegevoegd moet worden.
	 */
	void attach(PresentationObserver presenterObserver);

	/**
	 * 
	 * Verwijdert een observer uit de lijst met {@link PresentationObserver} objecten.
	 * 
	 * @param presenterObserver
	 *            Observer die verwijderd moet worden.
	 */
	void detach(PresentationObserver presenterObserver);

	/**
	 * Voegt een observer toe aan de lijst met {@link SlideObserver} objecten.
	 * 
	 * @param presentationObserver De observer die zich abboneert op wijzigingen van de actieve {@link Presentation}.
	 */
	void attach(SlideObserver presentationObserver);

	/**
	 * Verwijdert een observer uit de lijst met {@link SlideObserver}
	 * objecten.
	 * 
	 * @param presentationObserver
	 *            Observer die verwijderd moet worden.
	 */
	void detach(SlideObserver presentationObserver);

	/**
	 * Laad de {@link Presentation}s uit een bestand en selecteert de eerste
	 * presentatie.
	 * 
	 * @param fileName Het bestand dat geladen moet worden.
	 */
	void loadFile(String fileName);
}