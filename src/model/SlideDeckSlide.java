package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

public class SlideDeckSlide implements Slide {
	private int width = 1200;
	private int height = 800;
	protected Vector<SlideItem> items; // de slide-items worden in een Vector bewaard

	public SlideDeckSlide() {
		items = new Vector<SlideItem>();
	}

	// Voeg een SlideItem toe
	public void append(SlideItem slideItem) {
		items.addElement(slideItem);
	}

	// geef het betreffende SlideItem
	public SlideItem getSlideItem(int number) {
		return (SlideItem)items.elementAt(number);
	}

	// geef alle SlideItems in een Vector
	public Vector<SlideItem> getSlideItems() {
		return items;
	}

	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);
	    int y = area.y;
	    for (int number=0; number<items.size(); number++) {
	    	SlideItem slideItem = getSlideItems().elementAt(number);
	    	slideItem.draw(area.x, y, scale, g, view, this);
	    	y += slideItem.getBoundingBox(g, view, scale, this).height;
	    }
	  }

	// geef de schaal om de slide te kunnen tekenen
	private float getScale(Rectangle area) {
		return Math.min(((float)area.width) / ((float)width), ((float)area.height) / ((float)height));
	}

	@Override
	public int getWidth() {
		return width;
	}
}
