package model;

public interface Presenter {

	/**
	 * 
	 * @return Het aantal presentaties.
	 */
	int getSize();
	/**
	 * Voegt een presentatie toe.
	 * @param presentation De presentatie om toe te voegen.
	 */
	void append(Presentation presentation);
	/**
	 * Selecteert een presentatie die getoont moet worden.
	 * @param number Het nummer van de presentatie.
	 */
	void selectPresentation(int number);
	/**
	 * Geeft de huidige actieve presentatie terug.
	 * @return De getoonde presentatie.
	 */
	Presentation getCurrentPresentation();
	
	/**
	 * 
	 * @return Het aantal slides dat de actieve presentatie bevat.
	 */
	int getNumberOfSlides();
	/**
	 * 
	 * @return De getoonde slide.
	 */
	Slide getCurrentSlide();
	int getSlideNumber();
	void nextSlide();
	void previousSlide();
	void selectSlide(int number);

	void attach(PresenterObserver presenterObserver);
	void detach(PresenterObserver presenterObserver);
	void attach(PresentationObserver presentationObserver);
	void detach(PresentationObserver presentationObserver);
	void loadFile(String fileName);
	void saveFile(String fileName);
}