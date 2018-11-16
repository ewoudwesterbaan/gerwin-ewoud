package factory;

import reader.DemoReader;
import reader.Reader;
import reader.XMLReader;

/**
 * Verantwoordelijk voor het aanmaken van {@link Reader} objecten.
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public class ReaderFactory {

	private static ReaderFactory instance = null;

	/**
	 * Geeft een object van {@link ReaderFactory} terug. Maakt het object aan indien deze nog niet is geïnitialiseerd.
	 * @return De {@link ReaderFactory} om {@link Reader} objecten mee te maken.
	 */
	public static ReaderFactory getInstance() {
		if (instance == null) {
			instance = new ReaderFactory();
		}
		return instance;
	}

	/**
	 * Constructor is {@code private} omdat alleen deze klasse een object van zichzelf mag maken.
	 */
	private ReaderFactory() {
		// no code
	}

	/**
	 * Bepaalt het type {@link Reader} dat aangemaakt moet worden en geeft deze terug.
	 * @param fn Bestandsnaam.
	 * @return Implementatie van een {@link Reader}.
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