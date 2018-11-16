package factory;

import reader.DemoReader;
import reader.Reader;
import reader.XMLReader;

/**
 * 
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public class ReaderFactory {

	private static ReaderFactory instance = null;

	public static ReaderFactory getInstance() {
		if (instance == null) {
			instance = new ReaderFactory();
		}
		return instance;
	}

	private ReaderFactory() {
	}

	/**
	 * Bepaalt het type Reader dat aangemaakt moet worden.
	 * @param fn Bestandsnaam.
	 * @return Implementatie van een Reader.
	 */
	public Reader getReader(String fn) {
		Reader reader = null;
		if (fn.endsWith(".xml")) {
			reader = new XMLReader(fn);
		} else {
			reader = new DemoReader();
		}
		return reader;
	}
}