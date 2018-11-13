package model;

import java.util.List;

import factory.ReaderFactory;
import reader.Reader;

public class PresentationManager implements Presenter {
	
	private List<Presentation> presentations;

	@Override
	public void selectPresentation(int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void previous() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attach(PresenterObserver presenterObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(PresenterObserver presenterObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadFile(String fileName) {
		Reader reader = ReaderFactory.getInstance().getReader(fileName);
		presentations = reader.load();
	}

	@Override
	public void saveFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

}
