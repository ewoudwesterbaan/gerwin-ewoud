package factory;

import model.BitmapItem;
import model.Item;
import model.SlideItem;
import model.TextItem;

/**
 * 
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public class SlideItemFactory {
	
private static SlideItemFactory instance;
	
	public static SlideItemFactory getInstance() {
		if (instance == null) {
			instance = new SlideItemFactory();
		}
		return instance;
	}
	
	private SlideItemFactory() {
		
	}
	
	public SlideItem getSlideItem(int level, String kind, String content) {
		Item item;
		if (kind.equalsIgnoreCase("text")) {
			item = new TextItem(content);
		} else if (kind.equalsIgnoreCase("image")) {
			item = new BitmapItem(content);
		} else {
			item = new TextItem("Invalid kind of item");
		}
		SlideItem slideItem = new SlideItem(level, item);
		return slideItem; 
	}

}
