package model;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import factory.StyleFactory;

/** <p> Verantwoordelijk voor het zorgen dat een {@link Item} getekend kan worden met een bepaalde {@link Style}.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @author Gert Florijn
 * @author Sylvia Stuurman
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
*/
public class SlideItem {

	private Style style;
	private Item item;

	/**
	 * Constructor. Maakt het opject aan gegeven het level en de item.
	 * @param lev Level. Wordt gebruikt om de stijl op te halen.
	 * @param item {@link Item}
	 */
	public SlideItem(int lev, Item item) {
		style = StyleFactory.getInstance().getStyle(lev);
		this.item = item;
	}

	/**
	 * Delegeert het berekenen van de BoudingBox aan het {@link Item} en geeft de {@link Style} mee.
	 * @param graphics {@link Graphics}
	 * @param imageObserver {@link ImageObserver}
	 * @param scale De schaal waarop het object getekend moet worden.
	 * @param slide {@link Slide} waarop het object getekend moet worden.
	 * @return {@link Rectangle} De BoudingBox van het Item.
	 */
	public Rectangle getBoundingBox(Graphics graphics, 
			ImageObserver imageObserver, float scale, Slide slide) {
		return item.getBoundingBox(graphics, imageObserver, scale, style, slide);
	}

	/**
	 * Delegeert het tekenen aan het {@link Item} en geeft de {@link Style} mee.
	 * @param x X-as waar het object getekend moet worden.
	 * @param y Y-as waar het object getekend moet worden.
	 * @param scale De schaal waarop het object getekend moet worden.
	 * @param graphics {@link Graphics}
	 * @param imageObserver {@link ImageObserver}
	 * @param slide {@link Slide} waarop het object getekend moet worden.
	 */
	public void draw(int x, int y, float scale, 
			Graphics graphics, ImageObserver imageObserver, Slide slide) {
		item.draw(x, y, scale, graphics, imageObserver, style, slide);
	}
}
