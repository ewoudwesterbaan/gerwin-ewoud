package model;
import java.awt.Color;
import java.awt.Font;

/** <p>Style staat voor Indent, Color, Font and Leading.</p>
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
public class Style {
	
	private static final String FONTNAME = "Helvetica";
	int indent;
	Color color;
	Font font;
	int fontSize;
	int leading;

	/**
	 * Maakt het object aan.
	 * @param indent Niveau
	 * @param color {@link Color}
	 * @param points Tekstgrootte.
	 * @param leading Leading
	 */
	public Style(int indent, Color color, int points, int leading) {
		this.indent = indent;
		this.color = color;
		font = new Font(FONTNAME, Font.BOLD, fontSize=points);
		this.leading = leading;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return "["+ indent + "," + color + "; " + fontSize + " on " + leading +"]";
	}

	/**
	 * Bepaalt de Font gegeven de schaal en fontsize.
	 * @param scale De schaal.
	 * @return {@link Font} waarvan de grootte bepaalt is met de gegeven schaal.
	 */
	public Font getFont(float scale) {
		return font.deriveFont(fontSize * scale);
	}
}
