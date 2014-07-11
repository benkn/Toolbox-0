package ben.kn.toolbox.readers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class WebsiteReader {
	public static void printSite(String urlString) throws Exception {
		URL yahoo = new URL(urlString);
		BufferedReader in = new BufferedReader(new InputStreamReader(yahoo.openStream()));

		String inputLine;

		while ( (inputLine = in.readLine()) != null )
			System.out.println(inputLine);

		in.close();
	}

	public static void main(String[] args) throws Exception {
		printSite("http://m.si.com/nba/schedule/20120420");
	}
}
