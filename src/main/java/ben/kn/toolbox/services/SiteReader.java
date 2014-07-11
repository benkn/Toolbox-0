package ben.kn.toolbox.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class SiteReader extends BufferedReader {

	public SiteReader(URL url) throws IOException {
		super (new InputStreamReader(url.openStream()));
	}

	public static void main(String[] args) throws Exception {
//		URL yahoo = new URL("http://www.wund.com/cgi-bin/findweather/getForecast?query=22181");
		URL yahoo = new URL("http://m.si.com/scores/http://m.si.com/scores/");
		SiteReader in = new SiteReader(yahoo);

		String inputLine;

		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);

		in.close();
	}
}
