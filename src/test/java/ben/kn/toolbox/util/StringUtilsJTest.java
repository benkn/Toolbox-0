package ben.kn.toolbox.util;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class StringUtilsJTest {
    //@formatter:off
	String[] testCases = {
			// 0
			"just a simple test",
			// 1
			"<span>another test</span>",
			// 2
			"<a href='link'><span class=\"junk\">and this</span></a>",
			// 3
			"<a href='link'><span class=\"junk\">this</span></a><a href='link'><span class=\"junk\">and this</span></a>",
			// 4
			"<div class=\"text_bg\"><a href=\"/nba/game_summary/2012042030;jsessionid=4DE8EACA99337B506A76BE7C9C1EBF72.cnnsi2b\"><span class=\"b\">MEM</span>&nbsp;85, CHA&nbsp;80 - Final</a></div>",
			// 5
			"<div class=\"text_bg1\"><a href=\"/nba/game_summary/2012042001;jsessionid=4DE8EACA99337B506A76BE7C9C1EBF72.cnnsi2b\">BOS&nbsp;92, <span class=\"b\">ATL</span>&nbsp;97 - Final</a></div>"
	};

	String[][] expectedResults = { 
			// 0
			{ 
				"just a simple test" 
			},
			// 1
			{ 
				"another test" 
			},
			// 2
			{ 
				"and this" 
			},
			// 3
			{ 
				"this",
				"and this" 
			},
			// 4
			{
				"MEM",
				"&nbsp;85, CHA&nbsp;80 - Final"
			},
			// 5
			{
				"BOS&nbsp;92, ",
				"ATL",
				"&nbsp;97 - Final"
			}
	};

	//@formatter:on

    @Test
    public void testScrubHTML() {
        for ( int i = 0; i < testCases.length; i++ ) {
            List<String> results = StringUtils.scrubHTML(testCases[i]);

            printArray(results.toArray(new String[0]));

            assertTrue("Uneven results for test " + i + ", expected " + expectedResults[i].length + " but received "
                    + results.size(), expectedResults[i].length == results.size());

            for ( int j = 0; j < expectedResults[i].length; j++ ) {
                assertTrue(
                        "Expected \"" + expectedResults[i][j] + "\" but received \""
                                + results.toArray(new String[0])[j] + "\"",
                        expectedResults[i][j].equals(results.toArray(new String[0])[j].trim()));
            }
        }
    }

    private void printArray(String[] array) {
        System.out.println("Printing Array");
        for ( String s : array ) {
            System.out.println(s);
        }
        System.out.println("Finished.");
    }
}