package factory;

import model.BitmapItem;
import model.Item;
import model.SlideItem;
import model.TextItem;

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
