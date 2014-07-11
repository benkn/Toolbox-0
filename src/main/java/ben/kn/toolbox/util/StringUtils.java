package ben.kn.toolbox.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringUtils {

	private static final String HTML_SCRUBBING_REGEX = "<[/]?[\\w'\"=\\s;_0-9\\./]++>";

	/**
	 * Assumes the incoming string contain is ["x","y","z"]
	 * 
	 * @param json String
	 * @return String array
	 */
	public static String[] jsonStringToArray(String json) {
		String[] result = null;
		if ( json != null && json.trim().length() > 0 ) {
			json = json.trim();
			// remove the opening and closing []
			if ( json.substring(0, 2).equals("[\"") ) {
				json = json.substring(2);
			}
			if ( json.substring(json.length() - 2).equals("\"]") ) {
				json = json.substring(0, json.length() - 2);
			}

			// proceed to split by ","
			result = json.split("\",\"");
		}
		return result;
	}

	/**
	 * Assumes the incoming string contain is [1,2,4]
	 * 
	 * @param json String
	 * @return int array
	 */
	public static int[] jsonStringToIntArray(String json) {
		int[] result = null;
		if ( json != null && json.trim().length() > 0 ) {
			json = json.trim();
			// remove the opening and closing []
			if ( json.substring(0, 1).equals("[") ) {
				json = json.substring(1);
			}
			if ( json.substring(json.length() - 1).equals("]") ) {
				json = json.substring(0, json.length() - 1);
			}

			// proceed to split by ","
			String[] strArray = json.split(",");
			result = new int[strArray.length];
			for ( int i = 0; i < strArray.length; i++ ) {
				String intAsString = strArray[i];
				result[i] = Integer.parseInt(intAsString);
			}
		}
		return result;
	}

	/**
	 * Parse the given query into the tokens which DSMS searches with. This
	 * requires intimate knowledge of the technology used in DSMS, and probably
	 * could be a service method found within DSMS, but only Thunderhead may
	 * need this utility.
	 * 
	 * The returned array will contain multiTokens (search terms containing
	 * multiple tokens, such as "billy arnold") first, then the single tokens.
	 * 
	 * @param query String of the user's query
	 * @return String[] of the unique individual tokens used in the search. An
	 *         empty array if there is an error.
	 */
	public static String[] parseLuceneSyntaxQuery(String query) {
		List<String> tokens = new ArrayList<String>();

		// cannot think of how using a regular expression would be easier than
		// this
		/*
		 * If the query contains a ", it may contain a multi-token clause. Loop
		 * through the contents of the string to find all clauses of this
		 * nature.
		 * 
		 * eg. "billy jones" and "steve jones"
		 */
		if ( query.contains("\"") ) {
			String tQuery = query;
			while ( tQuery.contains("\"") ) {
				int indexOfOpeningQualifier = tQuery.indexOf("\"");
				tQuery = tQuery.substring(indexOfOpeningQualifier + 1);

				// verify a closing qualifer exists
				if ( !tQuery.contains("\"") ) {
					break;
				}

				// get the closing qualifier
				int indexOfClosingQualifer = tQuery.indexOf("\"");

				// get the token for later use
				String multiToken = tQuery.substring(0, indexOfClosingQualifer);

				// add the multiToken clause to the list
				tokens.add(multiToken);

				// remove the multiToken from the original query
				query = query.replaceAll("\"" + multiToken + "\"", "");

				// move tQuery to past the closing qualifier
				tQuery = tQuery.substring(indexOfClosingQualifer + 1);
			}
		}

		if ( query != null && query.trim().length() > 0 ) {
			// split by whitespace
			String[] remainingTokens = query.split("\\s");
			for ( String t : remainingTokens ) {
				if ( !(t.equalsIgnoreCase("and") || t.equalsIgnoreCase("or")) && t.length() > 0 ) {
					tokens.add(t);
				}
			}
		}

		return tokens.toArray(new String[0]);
	}

	/**
	 * Check that the given String is not null and has a value other than
	 * whitespace.
	 * 
	 * {@code 
	 *  hasValue(null)      = false
	 *  hasValue("")        = false
	 *  hasValue(" ")       = false
	 *  hasValue("bob")     = true
	 *  hasValue("  bob  ") = true
	 *  }
	 * 
	 * @param input String
	 * @return true if the input has a value, and false if not.
	 */
	public static boolean hasValue(String input) {
		if ( input != null && input.trim().length() > 0 ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Remove all takes in HTML mark-up
	 * 
	 * @param input String
	 * @return List of String tokens found, none empty
	 */
	public static List<String> scrubHTML(String input) {
		List<String> tokens = new ArrayList<String>();
		String[] temp = input.split(HTML_SCRUBBING_REGEX);
		for ( String t : temp ) {
			if ( hasValue(t) ) {
				tokens.add(t);
			}
		}
		return tokens;
	}

	/**
	 * @param input String to work with
	 * @return String of the value of the input without the first and the last
	 *         character.
	 */
	public static String dropFirstAndLastChar(String input) {
		if ( input != null ) {
			int length = input.length();
			input = input.substring(1, length - 1);
		}

		return input;
	}

	private static final char COMMA = ',';

	/**
	 * Create a comma separated String of the values in the given Collection. If
	 * the <code>surroundCharacter</code> is given, then the values will be
	 * preceded and succeeded by it.
	 * 
	 * @param col Collection of Objects
	 * @param surroundingCharacter Character, or null to not use
	 * @return String
	 */
	public static String toCommaDelimited(Collection<? extends Object> col,
			Character surroundingCharacter) {
		StringBuilder sb = new StringBuilder();
		boolean added = false;
		for ( Object o : col ) {
			if ( added ) {
				sb.append(COMMA);
			}
			if ( surroundingCharacter != null ) {
				sb.append(surroundingCharacter).append(o).append(surroundingCharacter);
			} else {
				sb.append(o);
			}
			added = true;
		}
		return sb.toString();
	}
}
