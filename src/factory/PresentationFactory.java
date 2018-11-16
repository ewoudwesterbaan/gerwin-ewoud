package factory;

import model.Presentation;
import model.SlideSequence;

/**
 * Verantwoordelijk voor het aanmaken van {@link Presentation} objecten.
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public class PresentationFactory {
	
	private static PresentationFactory instance;
	
	/**
	 * 
	 * @return De {@link PresentationFactory} om {@link Presentation} objecten mee te maken.
	 */
	public static PresentationFactory getInstance() {
		if (instance == null) {
			instance = new PresentationFactory();
		}
		return instance;
	}
	
	/**
	 * Constructor is {@code private} omdat alleen deze klasse een object van zichzelf mag maken.
	 */
	private PresentationFactory() {
		
	}
	
	/**
	 * Geeft een implementatie terug van {@link Presentation}.
	 * @param showTitle Title van de presentatie.
	 * @param subTitle Subtitel van de presentie (bijvoorbeeld de titel van een aparte volgorde).
	 * @return Presentation object.
	 */
	public Presentation getPresentation(String showTitle, String subTitle) {
		// concatenate sequence title with presentation title
		String title = showTitle + " - " + subTitle;
		Presentation presentation = new SlideSequence(title); // De enige veriant die tot nu toe is geïmplementeerd.
		return presentation;
	}

}
