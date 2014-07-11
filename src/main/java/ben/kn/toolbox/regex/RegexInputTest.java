package ben.kn.toolbox.regex;

/**
 * Text input to a regular expression, including the text and a boolean
 * (shouldMatch) for whether it should match the regular expression.
 * 
 * @author Ben
 */
public class RegexInputTest {
	public String text;
	public boolean shouldMatch;

	public RegexInputTest(String text, boolean shouldMatch) {
		this.text = text;
		this.shouldMatch = shouldMatch;
	}
}