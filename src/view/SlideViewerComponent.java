package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;

import model.Presentation;
import model.PresentationObserver;
import model.Presenter;
import model.PresenterObserver;
import model.Slide;

/**
 * <p>
 * SlideViewerComponent is een grafische component die Slides kan laten zien.
 * </p>
 * 
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent implements PresentationObserver {

	private Slide slide; // de huidige slide
	private Font labelFont = null; // het font voor labels
	private Presenter presenter = null;

	private static final long serialVersionUID = 227L;

	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;

	public SlideViewerComponent(Presenter presenter) {
		setBackground(BGCOLOR);
		
		// if another slide is selected, we want to be notified
		this.presenter = presenter;
		this.presenter.attach(this);

		labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
	}

	public Dimension getPreferredSize() {
		return new Dimension(Slide.WIDTH, Slide.HEIGHT);
	}

	// teken de slide
	public void paintComponent(Graphics g) {
		g.setColor(BGCOLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);
		if (presenter.getCurrentSlide() == null) {
			return;
		}
		g.setFont(labelFont);
		g.setColor(COLOR);
		g.drawString("Slide " + (1 + presenter.getSlideNumber()) + " of " + presenter.getNumberOfSlides(), XPOS, YPOS);
		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
		slide.draw(g, area, this);
	}

	@Override
	public void update(Slide slide) {
		this.slide = slide;
		repaint();
	}
}