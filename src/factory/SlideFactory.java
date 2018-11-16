package factory;

import model.Slide;
import model.SlideDeckSlide;
import model.SlideItem;

/**
 * 
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
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
