import factory.PresenterFactory;
import model.Presenter;
import view.SlideViewerFrame;

/**
 * JabberPoint Main Programma
 * <p>
 * This program is distributed under the terms of the accompanying COPYRIGHT.txt
 * file (which is NOT the GNU General Public License). Please read it. Your use
 * of the software constitutes acceptance of the terms in the COPYRIGHT.txt
 * file.
 * </p>
 * 
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
public class JabberPoint {
	protected static final String JABVERSION = "Jabberpoint 2.0 - OU version";

	/** Het Main Programma */
	public static void main(String argv[]) {

		Presenter presenter = PresenterFactory.getInstance().getPresenter();

		new SlideViewerFrame(JABVERSION, presenter);

		if (argv.length == 0) { // een demo presentatie
			presenter.loadFile("");
		} else {
			presenter.loadFile(argv[0]);
		}

		// by default start with first presentation
		presenter.selectPresentation(0);
	}
}