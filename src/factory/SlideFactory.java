package factory;

import model.Slide;
import model.SlideDeckSlide;
import model.SlideItem;

/**
 * Verantwoordelijk voor het aanmaken van {@link Slide} objecten.
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public class SlideFactory {
	
	private static SlideFactory instance;
	
	/**
	 * Geeft een object van {@link SlideFactory} terug. Maakt het object aan indien deze nog niet is geïnitialiseerd.
	 * @return De {@link SlideFactory} om {@link Slide} objecten mee te maken.
	 */
	public static SlideFactory getInstance() {
		if (instance == null) {
			instance = new SlideFactory();
		}
		return instance;
	}
	
	/**
	 * Constructor is {@code private} omdat alleen deze klasse een object van zichzelf mag maken.
	 */
	private SlideFactory() {
		// no code
	}
	
	/**
	 * Maakt een object aan dat {@link Slide} implementeerd en geeft deze terug.<br>
	 * Zet de titel om in een {@link SlideItem} en voegt deze toe aan de {@link Slide}.
	 * @param title Titel van de slide.
	 * @return Een implementatie van een {@link Slide}.
	 */
	public Slide getSlide(String title) {
		Slide slide = new SlideDeckSlide();		
		SlideItem titleSlideItem = SlideItemFactory.getInstance().getSlideItem(0, "TEXT", title);		
		slide.append(titleSlideItem);
		return slide;
	}

}
