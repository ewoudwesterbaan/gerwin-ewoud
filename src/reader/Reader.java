package reader;

import java.util.List;

import model.Presentation;

public interface Reader {
	
	/**
	 * Laad de presentaties.
	 * @return {@link List} van {@link Presentation}.
	 */
	List<Presentation> load();

}
