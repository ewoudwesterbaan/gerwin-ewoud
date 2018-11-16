package controller;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.JOptionPane;

import model.AboutBox;
import model.Presenter;

/**
 * <p>
 * De controller voor het menu
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
public class MenuController extends MenuBar {

	private Frame parent; // het frame, alleen gebruikt als ouder voor de Dialogs

	private static final long serialVersionUID = 227L;

	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to slide";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXTSLIDE = "Next slide";
	protected static final String OPEN = "Open";
	protected static final String PAGENR = "Page number?";
	protected static final String PREVSLIDE = "Previous slide";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";
	protected static final String GOTO_PRESENTATION = "Go to presentation";
	protected static final String PRESENTATIONNR = "Presentation number?";

	// TODO: add

	protected static final String TESTFILE = "test.xml";
	protected static final String SAVEFILE = "dump.xml";

	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";

	public MenuController(Frame frame, final Presenter presenter) {
		parent = frame;

		MenuItem menuItem;
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(menuItem = mkMenuItem(OPEN));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// presentation.clear();
				//Accessor xmlAccessor = new XMLAccessor();
				//try {
					//xmlAccessor.loadFile(presentation, TESTFILE);
					//presentation.selectSlide(0); // .setSlideNumber(0);
				//} catch (IOException exc) {
			//		JOptionPane.showMessageDialog(parent, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
				//}
				// parent.repaint(); => gaat nu via observer!
			}
		});
		fileMenu.add(menuItem = mkMenuItem(NEW));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// presentation.clear();
				// parent.repaint(); => gaat nu via observer!
			}
		});
		fileMenu.add(menuItem = mkMenuItem(SAVE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Accessor xmlAccessor = new XMLAccessor();
				//try {
					//xmlAccessor.saveFile(presentation, SAVEFILE);
				//} catch (IOException exc) {
				//	JOptionPane.showMessageDialog(parent, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
				//}
			}
		});
		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem(EXIT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// presentation.exit(0);
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
				// String presentationNumberStr = JOptionPane.showInputDialog((Object)
				// PRESENTATIONNR);
				// int presentationNumber = Integer.parseInt(presentationNumberStr);
				// presenter.selectPresentation(presentationNumber - 1);

				// TODO: make this dynamic...
				String[] choices = { "presentatie 1", "presentatie 2", "presentatie 3" };
				String input = (String) JOptionPane.showInputDialog(null, "Choose presentation...",
						"Presentation selector", JOptionPane.QUESTION_MESSAGE, null, choices, // Array of choices
						null);
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

	// een menu-item aanmaken
	private MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}