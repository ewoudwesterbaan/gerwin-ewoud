package view;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

import controller.KeyController;
import controller.MenuController;
import model.Presentation;
import model.Presenter;
import model.PresenterObserver;
import model.Slide;

/**
 * <p>
 * Het applicatiewindow voor een slideviewcomponent
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

public class SlideViewerFrame extends JFrame implements PresenterObserver {
	private static final long serialVersionUID = 3227L;

	private static final String JABTITLE = "Jabberpoint 1.6 - OU";
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;

	private Presenter presenter;
	private KeyController keyController;
	private MenuController menuController;
	
	public SlideViewerFrame(String title, Presenter presenter) {
		super(title);

		// if another presentation is selected, we want to be notified
		this.presenter = presenter;
		presenter.attach(this);

		Presentation presentation = presenter.getCurrentPresentation();
		SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation);
		presenter.attach(slideViewerComponent);
		setupWindow(slideViewerComponent, presentation);
	}

	// De GUI opzetten
	private void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation) {
		setTitle(JABTITLE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		getContentPane().add(slideViewerComponent);
		
		keyController = new KeyController(presentation);
		addKeyListener(keyController); // een controller toevoegen

		menuController = new MenuController(this, presenter, presentation);
		setMenuBar(menuController); // nog een controller toevoegen
		
		setSize(new Dimension(WIDTH, HEIGHT)); // Dezelfde maten als Slide hanteert.
		setVisible(true);
	}

	@Override
	public void update(Presentation presentation) // , Slide slide)
	{
		// moet presentation nog als (private) member bewaard worden, of is dit genoeg?
		this.setTitle(presentation.getTitle());
		
		keyController.switchPresentation(presentation);
		menuController.switchPresentation(presentation);
	}
}