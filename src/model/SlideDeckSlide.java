package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

/**
 * <p>Verantwoordelijk voor het correct laten tekenen van de {@link SlideItem}s.
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public class SlideDeckSlide implements Slide {
	private int width = 1200;
	private int height = 800;
	protected Vector<SlideItem> items; // de slide-items worden in een Vector bewaard

	/**
	 * Constructor. Initialiseert de items.
	 */
	public SlideDeckSlide() {
		items = new Vector<SlideItem>();
	}

	/**
	 * {@inheritDoc}
	 */
	public void append(SlideItem slideItem) {
		items.addElement(slideItem);
	}

	/**
	 * Geeft een {@link SlideItem} terug van de items.
	 * @param number Number van de {@link SlideItem} om terug te geven.
	 * @return {@link SlideItem}
	 */
	public SlideItem getSlideItem(int number) {
		return (SlideItem)items.elementAt(number);
	}

	/**
	 * Geeft alle {@link SlideItem}s terug.
	 * @return {@link Vector} met {@link SlideItem}s.
	 */
	public Vector<SlideItem> getSlideItems() {
		return items;
	}

	/**
	 * {@inheritDoc}
	 */
	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);
	    int y = area.y;
	    for (int number=0; number<items.size(); number++) {
	    	SlideItem slideItem = getSlideItems().elementAt(number);
	    	slideItem.draw(area.x, y, scale, g, view, this);
	    	y += slideItem.getBoundingBox(g, view, scale, this).height;
	    }
	  }

	/**
	 * Berekent de schaal.
	 * @param area {@link Rectangle}
	 * @return De schaal.
	 */
	private float getScale(Rectangle area) {
		return Math.min(((float)area.width) / ((float)width), ((float)area.height) / ((float)height));
	}

	/**
	 * {@inheritDoc}
	 */
	public int getWidth() {
		return width;
	}
}