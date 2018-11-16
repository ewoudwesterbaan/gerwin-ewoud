package factory;

import java.util.List;

import model.Slide;
import model.SlideDeckSlide;
import model.SlideItem;

public class SlideFactory {
	
private static SlideFactory instance;
	
	public static SlideFactory getInstance() {
		if (instance == null) {
			instance = new SlideFactory();
		}
		return instance;
	}
	
	private SlideFactory() {
		
	}
	
	public Slide getSlide(String title) {
		Slide slide = new SlideDeckSlide();		
		SlideItem titleSlideItem = SlideItemFactory.getInstance().getSlideItem(0, "TEXT", title);		
		slide.append(titleSlideItem);
		return slide;
	}

}
