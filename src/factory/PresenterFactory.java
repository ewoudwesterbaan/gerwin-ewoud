package factory;

import model.PresentationManager;
import model.Presenter;

public class PresenterFactory {
	
	private static PresenterFactory instance;
	private Presenter presenter;
	
	public static PresenterFactory getInstance() {
		if (instance == null) {
			instance = new PresenterFactory();
		}
		return instance;
	}
	
	private PresenterFactory() {
		presenter = new PresentationManager();
	}
	
	public Presenter getPresenter() {
		return presenter;
	}

}
