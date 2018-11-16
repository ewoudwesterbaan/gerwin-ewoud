package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public interface Item {
	
	void draw(int x, int y, float scale, Graphics g, 
			ImageObserver o, Style style, Slide slide);

	Rectangle getBoundingBox(Graphics g, ImageObserver observer, 
			float scale, Style style, Slide slide);
}
