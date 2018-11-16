package model;

public interface Presenter {

	int getSize();
	void append(Presentation presentation);
	void selectPresentation(int number);
	Presentation getCurrentPresentation();
	
	int getNumberOfSlides();
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