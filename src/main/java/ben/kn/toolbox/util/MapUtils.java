package ben.kn.toolbox.util;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This utility class provides numerous methods for simple functional operations
 * with a map.
 * 
 * @author Ben Knear
 * 
 * @param <T> The required class type to use for some methods
 */
public class MapUtils<T> {
	/**
	 * With the given List of Maps, get a unique collection of the keys
	 * 
	 * @param mapList List of Maps
	 * @return Set of unique T keys
	 */
	public Set<T> getKeys(List<Map<T, ? extends Object>> mapList) {
		Set<T> keys = new HashSet<T>();
		for ( Map<T, ? extends Object> map : mapList ) {
			keys.addAll(map.keySet());
		}
		return keys;
	}
}