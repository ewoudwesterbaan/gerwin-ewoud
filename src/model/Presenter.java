package model;

public interface Presenter {

	int getSize();
	void append(Presentation presentation);
	boolean hasNext();
	boolean hasPrevious();
	void next();
	void previous();
	void selectPresentation(int number);
	Presentation getCurrentPresentation();
	void attach(PresenterObserver presenterObserver);
	void detach(PresenterObserver presenterObserver);
	void loadFile(String fileName);
	void saveFile(String fileName);
}