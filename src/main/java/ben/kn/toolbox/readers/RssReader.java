package ben.kn.toolbox.readers;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * This is an example of the Rome RSS Reader.
 * @requires rome-1.0.jar
 * @requires jdom-1.1.jar
 * 
 * @author Ben (bknear@gmail.com)
 * @since Nov 19, 2012
 */
public class RssReader {
	public static void main(String[] args) throws IllegalArgumentException, FeedException,
			IOException {
		URL feedSource = new URL("http://techtrailblazer.wordpress.com/feed/rss/");
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(feedSource));
		@SuppressWarnings("unchecked")
        List<SyndEntry> entries = feed.getEntries();
		if ( entries != null ) {
			for ( SyndEntry entry : entries ) {
				System.out.println(entry.getTitle());
			}
		}
	}
}
