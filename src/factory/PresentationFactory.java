package factory;

import model.Presentation;
import model.SlideSequence;

/**
 * 
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public class PresentationFactory {
	
	private static PresentationFactory instance;
	
	public static PresentationFactory getInstance() {
		if (instance == null) {
			instance = new PresentationFactory();
		}
		return instance;
	}
	
	private PresentationFactory() {
		
	}
	
	// concatenate sequence title with presentation title
	public Presentation getPresentation(String showTitle, String sequenceTitle) {
		String title = showTitle + " - " + sequenceTitle;
		Presentation presentation = new SlideSequence(title);
		return presentation;
	}

}
