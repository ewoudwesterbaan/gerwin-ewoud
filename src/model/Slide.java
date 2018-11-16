package model;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/** <p>Een slide. Verantwoordelijk voor het tekenen van zichzelf.</p>
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
public interface Slide {
	/**
	 * Tekent de slide.
	 * @param graphics {@link Graphics}
	 * @param area {@link Rectangle}
	 * @param imageObserver {@link ImageObserver}
	 */
	abstract void draw(Graphics graphics, Rectangle area, ImageObserver imageObserver);
	
	/**
	 * Voegt een {@link SlideItem} toe aan de slide.
	 * @param slideItem {@link SlideItem} om toe te voegen.
	 */
	abstract void append(SlideItem slideItem);
	
	/**
	 * Geeft de breedte van de slide terug.
	 * @return De breedte van de slide.
	 */
	abstract int getWidth();
}
