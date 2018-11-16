package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * <p>Abstracte representatie van een object dat op een slide getekend kan worden.</p>
 * <p>Verantwoordelijk voor het tekenen van zichzelf.</p>
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public interface Item {
	
	/**
	 * Tekent het item.
	 * @param x X-as waar het object getekend moet worden.
	 * @param y Y-as waar het object getekend moet worden.
	 * @param scale De schaal waarop het object getekend moet worden.
	 * @param graphics {@link Graphics}
	 * @param imageObserver {@link ImageObserver}
	 * @param style {@link Style} waarmee het object getekend moet worden.
	 * @param slide {@link Slide} waarop het object getekend moet worden.
	 */
	void draw(int x, int y, float scale, Graphics graphics, 
			ImageObserver imageObserver, Style style, Slide slide);

	/**
	 * Berekent de bounding box en geeft deze terug.
	 * @param graphics {@link Graphics}
	 * @param imageObserver {@link ImageObserver}
	 * @param scale De schaal waarop het object getekend moet worden.
	 * @param style {@link Style} waarmee het object getekend moet worden.
	 * @param slide {@link Slide} waarop het object getekend moet worden.
	 * @return {@link Rectangle} De BoudingBox van het Item.
	 */
	Rectangle getBoundingBox(Graphics graphics, ImageObserver imageObserver, 
			float scale, Style style, Slide slide);
}
