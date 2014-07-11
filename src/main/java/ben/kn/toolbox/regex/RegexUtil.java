package ben.kn.toolbox.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

	public static void main(String[] args) {
		// setup the regular expression and list
		String regex = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$";
		List<RegexInputTest> rits = setupList();

		// Create a pattern to match breaks
		Pattern p = Pattern.compile(regex);
		Matcher matcher;

		for (RegexInputTest rit : rits) {
			matcher = p.matcher(rit.text);
			if (matcher.matches() != rit.shouldMatch) {
				System.out
						.println("Text: "
								+ rit.text
								+ " failed ("
								+ (rit.shouldMatch ? "should match"
										: "shouldn't match") + ")");
			}
		}

		System.out.println("Operation complete for");
		printRegex(regex);
	}

	public static List<RegexInputTest> setupList() {
		List<RegexInputTest> rits = new ArrayList<RegexInputTest>();

		// valid tests
		rits.add(new RegexInputTest("1985-04-19", true));
		rits.add(new RegexInputTest("1999-01-31", true));
		rits.add(new RegexInputTest("2011-05-23", true));
		rits.add(new RegexInputTest("2010-12-18", true));

		// invalid tests
		rits.add(new RegexInputTest("1845-04-19", false));
		rits.add(new RegexInputTest("2204-04-04", false));
		rits.add(new RegexInputTest("2001-13-12", false));
		rits.add(new RegexInputTest("2001-10-90", false));

		return rits;
	}
	
	/**
	 * Print the regular expression as standard notation (not Java String notation)
	 * @param regex String
	 */
	public static void printRegex(String regex) {
		// print each char one at a time to avoid escape characters
		for ( int i = 0; i< regex.length(); i++) {
			System.out.print(regex.charAt(i));
		}
		System.out.print("\n");
	}

}
