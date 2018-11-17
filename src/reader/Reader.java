package reader;

import java.util.List;
import model.Presentation;

/**
 * Verantwoordelijk voor het inlezen van {@link Presentation}s.
 * 
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 */
public interface Reader {

	/**
	 * Laad de presentaties.
	 * 
	 * @return {@link List} van {@link Presentation}.
	 */
	List<Presentation> load();
}