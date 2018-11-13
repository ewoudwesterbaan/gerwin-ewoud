package factory;

import reader.DemoReader;
import reader.Reader;
import reader.XMLReader;

public class ReaderFactory {
	
	private static ReaderFactory instance;
	
	public static ReaderFactory getInstance() {
		if (instance == null) {
			instance = new ReaderFactory();
		}
		return instance;
	}
	
	private ReaderFactory() {
	}
	
	public Reader getReader(String fn) {
		Reader reader = null;
		if (fn.endsWith(".xml")) {
			reader = new XMLReader(fn);
		} else if (fn == null) {
			reader = new DemoReader();
		}
		return reader;
	}

}
