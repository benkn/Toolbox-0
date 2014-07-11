package ben.kn.toolbox.services;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SimpleSaxHandler extends DefaultHandler {
	public SimpleSaxHandler() {
		super();
	}

	public void startElement(String uri, String name,
		      String qName, Attributes atts) {
		System.out.println( name);
	}
	
	public static void main (String [] args ) {
		System.out.println ("YES!");
	}
}