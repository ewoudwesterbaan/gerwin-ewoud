package factory;

import model.PresentationManager;
import model.Presenter;

/**
 * Verantwoordelijk voor het aanmaken van {@link Presenter} objecten.
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public class PresenterFactory {
	
	private static PresenterFactory instance = null;
	private Presenter presenter;
	
	/**
	 * Geeft een object van {@link PresenterFactory} terug. Maakt het object aan indien deze nog niet is geïnitialiseerd.
	 * @return De {@link PresenterFactory} om {@link Presenter} objecten mee te maken.
	 */
	public static PresenterFactory getInstance() {
		if (instance == null) {
			instance = new PresenterFactory();
		}
		return instance;
	}
	
	/**
	 * Constructor is {@code private} omdat alleen deze klasse een object van zichzelf mag maken.<br>
	 * Er mag maar één {@link Presenter} zijn, daarom initialiseert de constructor de Presenter.
	 */
	private PresenterFactory() {
		presenter = new PresentationManager();
	}
	
	/**
	 * 
	 * @return Een implementatie van de {@link Presenter}.
	 */
	public Presenter getPresenter() {
		return presenter;
	}

}
