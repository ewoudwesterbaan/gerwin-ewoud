package factory;

import model.SlideItem;

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
		SlideItem slideItem = new SlideItem();
		return null; 
	}

}
