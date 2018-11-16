package factory;

import model.BitmapItem;
import model.Item;
import model.SlideItem;
import model.TextItem;

/**
 * Verantwoordelijk voor het aanmaken van {@link SlideItem} objecten.
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public class SlideItemFactory {
	
	private static SlideItemFactory instance;
	
	/**
	 * Geeft een object van {@link SlideItemFactory} terug. Maakt het object aan indien deze nog niet is geïnitialiseerd.
	 * @return De {@link SlideItemFactory} om {@link SlideItem} objecten mee te maken.
	 */
	public static SlideItemFactory getInstance() {
		if (instance == null) {
			instance = new SlideItemFactory();
		}
		return instance;
	}
	
	/**
	 * Constructor is {@code private} omdat alleen deze klasse een object van zichzelf mag maken.
	 */
	private SlideItemFactory() {
		// no code
	}
	
	/**
	 * Maakt een object aan dat {@link SlideItem} implementeerd en geeft deze terug.<br>
	 * @param level Level van de {@link SlideItem}. Geeft aan welke style gebruikt moet worden.
	 * @param type Type van de {@link SlideItem}. Wordt gebruikt om de correcte implementatie terug te geven.
	 * @param content Inhoud van de {@link SlideItem}. Gebruik is afhankelijk van {@code type}.
	 * @return Een implementatie van een {@link SlideItem}.
	 */
	public SlideItem getSlideItem(int level, String type, String content) {
		Item item;
		if (type.equalsIgnoreCase("text")) {
			item = new TextItem(content);
		} else if (type.equalsIgnoreCase("image")) {
			item = new BitmapItem(content);
		} else { // Een type dat nog niet geïmplementeerd is.
			item = new TextItem("Invalid type of item");
		}
		SlideItem slideItem = new SlideItem(level, item);
		return slideItem; 
	}

}
