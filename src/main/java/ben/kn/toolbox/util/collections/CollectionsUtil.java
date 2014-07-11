package ben.kn.toolbox.util.collections;

import java.util.Collection;

/**
 * Utility class for performing operations on Collections.
 * 
 * @author Ben (bknear@gmail.com)
 * @since Feb 28, 2013
 */
public class CollectionsUtil {
	/**
	 * Create a comma-delimited string of the given array or series of objects.
	 * 
	 * @param array Objects
	 * @return String
	 */
	public static String arrayToString(Object... array) {
		StringBuilder sb = new StringBuilder();

		if ( array != null ) {
			for ( Object o : array ) {
				sb.append(o.toString() + ", ");
			}
		}
		return sb.toString();
	}

	public static String collectionToString(Collection<? extends Object> c) {
		StringBuilder sb = new StringBuilder();

		if ( c != null ) {
			for ( Object o : c ) {
				sb.append(o.toString() + ", ");
			}
		}
		return sb.toString();
	}
}
