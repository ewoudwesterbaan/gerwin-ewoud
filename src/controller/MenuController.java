package controller;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import factory.PresenterFactory;
import model.AboutBox;
import model.Presenter;
import view.SlideViewerFrame;

/**
 * De controller voor het menu. Verantwoordelijk voor het uitvoeren van akties
 * voor diverse menu items.
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
public class MenuController extends MenuBar {

	private Frame parent; // het frame, alleen gebruikt als ouder voor de Dialogs

	private static final long serialVersionUID = 227L;

	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to slide";
	protected static final String HELP = "Help";
	protected static final String NEXTSLIDE = "Next slide";
	protected static final String OPEN = "Open";
	protected static final String PAGENR = "Page number?";
	protected static final String PREVSLIDE = "Previous slide";
	protected static final String VIEW = "View";
	protected static final String GOTO_PRESENTATION = "Go to presentation";
	protected static final String PRESENTATIONNR = "Presentation number?";

	protected static final String TESTFILE = "test.xml";
	protected static final String SAVEFILE = "dump.xml";

	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";

	/**
	 * 
	 * Constructor van de MenuController. Maakt een menu aan met diverse menu items
	 * en koppelt eventhandlers aan de menu item.
	 * 
	 * @param frame
	 *            Parent object dat gebruikt kan worden voor dialogs.
	 * @param presenter
	 *            {@link Presenter} object dat aangestuurd wordt.
	 */
	public MenuController(Frame frame, final Presenter presenter) {
		parent = frame;

		MenuItem menuItem;
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(menuItem = mkMenuItem(OPEN));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(parent);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					String filename = file.getPath();

					Presenter presenter = PresenterFactory.getInstance().getPresenter();
					presenter.loadFile(filename);

					// start met eerste presentatie
					presenter.selectPresentation(0);
				}
			}
		});
		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem(EXIT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		});
		add(fileMenu);
		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(menuItem = mkMenuItem(NEXTSLIDE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presenter.nextSlide();
			}
		});
		viewMenu.add(menuItem = mkMenuItem(PREVSLIDE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presenter.previousSlide();
			}
		});
		viewMenu.add(menuItem = mkMenuItem(GOTO));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String pageNumberStr = JOptionPane.showInputDialog((Object) PAGENR);
				int pageNumber = Integer.parseInt(pageNumberStr);
				presenter.selectSlide(pageNumber - 1);
			}
		});
		viewMenu.addSeparator();
		viewMenu.add(menuItem = mkMenuItem(GOTO_PRESENTATION));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// display list of presentations to choose from
				int nrOfPresentations = presenter.getSize();
				String[] choices = new String[nrOfPresentations];
				for (int i = 1; i <= nrOfPresentations; i++) {
					choices[i - 1] = "presentation " + i;
				}
				String input = (String) JOptionPane.showInputDialog(null, "Choose presentation...",
						"Presentation selector", JOptionPane.QUESTION_MESSAGE, null, choices, null);
				int index = Arrays.asList(choices).indexOf(input);
				if (index >= 0)
					presenter.selectPresentation(index);
			}
		});
		add(viewMenu);
		Menu helpMenu = new Menu(HELP);
		helpMenu.add(menuItem = mkMenuItem(ABOUT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AboutBox.show(parent);
			}
		});
		setHelpMenu(helpMenu); // nodig for portability (Motif, etc.).
	}

	/**
	 * Maakt een nieuw menu item aan.
	 * 
	 * @param name
	 *            Naam van het menu item.
	 * @return Een {link MenuItem} object.
	 */
	private MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}