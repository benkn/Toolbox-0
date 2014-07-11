package ben.kn.toolbox.services;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JSoupService {

	private static final String URL = "http://m.si.com/scores/?sport=nba";
	
	public static void main (String [ ]args0) throws IOException {
		Document doc = Jsoup.connect(URL).get();
		String title = doc.title();
		
		System.out.println ("Title: " + title);
		

		Element scoresTable = doc.getElementById("scores-widget");
		
		Element unorderedScoreList = scoresTable.child(0);
		
		// go through each line item, constructing the score
		for ( Element scoreLineItem : unorderedScoreList.children() ) {
			System.out.println ("Total items in line: " + scoreLineItem.children().size());
		}
		
		System.out.println ("Finished");

	}
}
