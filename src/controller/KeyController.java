package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Presenter;

/**
 * De KeyController (KeyListener). Verantwoordelijk voor het uitvoeren van akties
 * voor diverse keyboard toetsen.
 * 
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 */

public class KeyController extends KeyAdapter {
	private Presenter presenter; // Er worden commando's gegeven aan de presentatie

	/**
	 * 
	 * Constructor van de KeyController.
	 * 
	 * @param presenter
	 *            {@link Presenter} object dat aangestuurd wordt.
	 */
	public KeyController(Presenter presenter) {
		this.presenter = presenter;
	}

	/**
	 * Afhandeling van een toetsaanslag. De {@link Presenter} wordt aangestuurd.
	 * 
	 * @param keyEvent De specifieke toetsaanslag.
	 */
	public void keyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
		case KeyEvent.VK_PAGE_DOWN:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_ENTER:
		case '+':
			if (presenter != null)
				presenter.nextSlide();
			break;
		case KeyEvent.VK_PAGE_UP:
		case KeyEvent.VK_UP:
		case '-':
			if (presenter != null)
				presenter.previousSlide();
			break;
		case 'q':
		case 'Q':
			System.exit(0);
			break;
		default:
			break;
		}
	}
}