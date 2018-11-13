/**
 * 
 */
package factory;

import java.awt.Color;

import model.Style;

/**
 * @author 
 *
 */
public class StyleFactory {
	
	private static StyleFactory instance;
	
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
	 * 
	 * @return Factory to create Style objects.
	 */
	public static StyleFactory getInstance() {
		if (instance == null) {
			instance = new StyleFactory();
		}
		return instance;
	}
	
	/**
	 * 
	 * @param level The level of the SlideItem to create a Style for.
	 * @return A style
	 */
	public Style getStyle(int level) {
		if (level >= styles.length) {
			level = styles.length - 1;
		}
		return styles[level];
	}

}
