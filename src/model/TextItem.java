package model;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/** <p>Een tekst item.</p>
 * <p>Een TextItem heeft tekenfunctionaliteit en is verantwoordelijk om zichzelf te tekenen.</p>
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
 * @version 2.0 Gerwin van Dijken en Ewoud Westerbaan
 */
public class TextItem implements Item {
	private String text;
	
	/**
	 * Aanmaken van het object.
	 * @param string Tekst doe weergegeven moet worden.
	 */
	public TextItem(String string) {
		text = string;
	}

	/**
	 * Geeft de tekst terug.
	 * @return {@link String} De tekst die weergegeven moet worden.
	 */
	public String getText() {
		return text == null ? "" : text;
	}

	/**
	 * 
	 * @param style {@link Style}
	 * @param scale Schaalgrootte.
	 * @return {@link AttributedString}
	 */
	private AttributedString getAttributedString(Style style, float scale) {
		AttributedString attrStr = new AttributedString(getText());
		attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
		return attrStr;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, 
			float scale, Style style, Slide slide) {
		List<TextLayout> layouts = getLayouts(g, scale, style, slide);
		int xsize = 0, ysize = (int) (style.leading * scale);
		Iterator<TextLayout> iterator = layouts.iterator();
		while (iterator.hasNext()) {
			TextLayout layout = iterator.next();
			Rectangle2D bounds = layout.getBounds();
			if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			}
			if (bounds.getHeight() > 0) {
				ysize += bounds.getHeight();
			}
			ysize += layout.getLeading() + layout.getDescent();
		}
		return new Rectangle((int) (style.indent*scale), 0, xsize, ysize );
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(int x, int y, float scale, Graphics g, 
			ImageObserver o, Style style, Slide slide) {
		if (text == null || text.length() == 0) {
			return;
		}
		List<TextLayout> layouts = getLayouts(g, scale, style, slide);
		Point pen = new Point(x + (int)(style.indent * scale), 
				y + (int) (style.leading * scale));
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(style.color);
		Iterator<TextLayout> it = layouts.iterator();
		while (it.hasNext()) {
			TextLayout layout = it.next();
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		} 
	  }
	
	/**
	 * 
	 * @param graphics
	 * @param scale De schaalgrootte.
	 * @param style {@link Style}
	 * @param slide {@link Slide}
	 * @return Een lijst van {@link TextLayout}s
	 */
	private List<TextLayout> getLayouts(Graphics graphics, float scale, Style style, Slide slide) {
		List<TextLayout> layouts = new ArrayList<TextLayout>();
		AttributedString attrStr = getAttributedString(style, scale);
    	Graphics2D g2d = (Graphics2D) graphics;
    	FontRenderContext frc = g2d.getFontRenderContext();
    	LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
    	float wrappingWidth = (slide.getWidth() - style.indent) * scale;
    	while (measurer.getPosition() < getText().length()) {
    		TextLayout layout = measurer.nextLayout(wrappingWidth);
    		layouts.add(layout);
    	}
    	return layouts;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "TextItem[" + getText() + "]";
	}
}
