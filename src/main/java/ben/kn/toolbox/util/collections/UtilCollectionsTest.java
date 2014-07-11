package ben.kn.toolbox.util.collections;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class UtilCollectionsTest {
	@Test
	public void testMapUtil() {
		// set up
		List<Map<String, ? extends Object>> col = new ArrayList<Map<String, ? extends Object>>();
		char letter = 'a';
		for ( int i = 0; i < 3; i++ ) {
			Map<String, String> map = new HashMap<String, String>();
			for ( int j = 0; j < 3; j++ ) {
				map.put("" + j, "" + letter++);
			}
			col.add(map);
		}

		// test
		MapUtils<String> util = new MapUtils<String>();
		Set<String> keys = util.getKeys(col);
		assertTrue(keys.size() == 3);
	}
}
