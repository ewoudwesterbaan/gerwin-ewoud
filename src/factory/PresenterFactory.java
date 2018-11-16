package factory;

import model.PresentationManager;
import model.Presenter;

/**
 * Verantwoordelijk voor het initialiseren en imlementeren van de Presenter.
 */
public class PresenterFactory {
	
	private static PresenterFactory instance = null;
	private Presenter presenter;
	
	/**
	 * Maakt een instantie van de klasse aan indien dit nodig is.
	 * @return Een PresenterFactory object.
	 */
	public static PresenterFactory getInstance() {
		if (instance == null) {
			instance = new PresenterFactory();
		}
		return instance;
	}
	
	/**
	 * Constructor. De Presenter ge�mplementeerd
	 * door de PresentationManager (nu is er maar ��n concrecte klasse
	 * die de Presenter implementeerd).<br>
	 * Er mag maar ��n Presenter zijn, daarom initialiseert de constructor de Presenter.
	 */
	private PresenterFactory() {
		presenter = new PresentationManager();
	}
	
	/**
	 * 
	 * @return De Presenter.
	 */
	public Presenter getPresenter() {
		return presenter;
	}

}
