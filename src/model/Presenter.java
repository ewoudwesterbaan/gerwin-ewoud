package model;

public interface Presenter {

	void selectPresentation(int number);
	void next();
	void previous();
	void attach(PresenterObserver presenterObserver);
	void detach(PresenterObserver presenterObserver);
	void loadFile(String fileName);
	void saveFile(String fileName);
}
