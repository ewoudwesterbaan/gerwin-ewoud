/**
 * 
 */
package factory;

import java.awt.Color;

import model.Item;
import model.Style;

/**
 * Verantwoordelijk voor het teruggeven van {@link Style} objecten.
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public class StyleFactory {
	
	private static StyleFactory instance = null;
	
	private Style[] styles;
	
	/**
	 * Default constructor.
	 */
	private StyleFactory() {
		styles = new Style[5];    
		// De styles zijn vast ingecodeerd.
		styles[0] = new Style(0, Color.red,   48, 20);	// style voor item-level 0
		styles[1] = new Style(20, Color.blue,  40, 10);	// style voor item-level 1
		styles[2] = new Style(50, Color.black, 36, 10);	// style voor item-level 2
		styles[3] = new Style(70, Color.black, 30, 10);	// style voor item-level 3
		styles[4] = new Style(90, Color.black, 24, 10);	// style voor item-level 4
	}
	
	/**
	 * Geeft een object van {@link StyleFactory} terug. Maakt het object aan indien deze nog niet is geïnitialiseerd.
	 * @return De {@link StyleFactory} om {@link Style} objecten mee op te vragen.
	 */
	public static StyleFactory getInstance() {
		if (instance == null) {
			instance = new StyleFactory();
		}
		return instance;
	}
	
	/**
	 * Geeft een stijl terug afhankelijk van het level.
	 * @param level Level waar de stijl voor opgevraagd wordt.
	 * @return Een stijl om toe te passen op een {@link Item}.
	 */
	public Style getStyle(int level) {
		if (level >= styles.length) {
			level = styles.length - 1;
		}
		return styles[level];
	}

}
