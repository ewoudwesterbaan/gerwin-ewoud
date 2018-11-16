package model;

/**
 * <p>
 * Presentation houdt de slides in de presentatie bij.
 * </p>
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
public interface Presentation {
	/**
	 * 
	 * @return De titel van de presentatie.
	 */
	 String getTitle();
	 /**
	  * 
	  * @return Het aantal slides dat de presentatie bevat.
	  */
	 int getNumberOfSlides();
	 /**
	  * 
	  * @return De actieve (getoonde) {@link Slide}.
	  */
	 Slide getCurrentSlide();
	 
	 /**
	  * Geeft het nummer terug van de actieve {@link Slide}.
	  * @return Nummer van de actieve {@link Slide}.
	  */
	 int getSlideNumber();
	 /**
	  * Geeft aan of er een volgende {@link Slide} is.
	  * @return True als er een volgende slide is, anders False.
	  */
	 boolean hasNext();
	 /**
	  * Geeft aan of er een vorige {@link Slide} is.
	  * @return True als er een vorige slide is, anders False.
	  */
	 boolean hasPrevious();
	 /**
	  * Selecteert de volgende {@link Slide} als actieve slide.
	  */
	 void next();
	 /**
	  * Selecteert de vorige {@link Slide} als actieve slide.
	  */
	 void previous();
	 /**
	  * Selecteert een {@link Slide} als actieve slide.
	  * @param number Nummer van de slide.
	  */
	 void selectSlide(int number);
	 /**
	  * 
	  * @param slide De {@link Slide} om toe te voegen.
	  */
	 void append(Slide slide);
}