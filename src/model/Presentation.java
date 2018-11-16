package model;

/**
 * <p>
 * Presentation houdt de slides in de presentatie bij.
 * </p>
 * <p>
 * Er is slechts ��n instantie van deze klasse aanwezig.
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

public interface Presentation {
	 String getTitle();
	 int getNumberOfSlides();
	 Slide getCurrentSlide();
	 int getSlideNumber();
	 boolean hasNext();
	 boolean hasPrevious();
	 void next();
	 void previous();
	 void selectSlide(int number);
	 void append(Slide slide);
}