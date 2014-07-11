package ben.kn.toolbox.util.collections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ArrayUtils<T> {
	public void swap(Object[] array, int indexOne, int indexTwo) {
		if ( array != null && indexOne < array.length && indexTwo < array.length ) {
			Object one = array[indexOne];
			array[indexOne] = array[indexTwo];
			array[indexTwo] = one;
		}
	}

	/**
	 * Reverse the order of the elements in the given array
	 * 
	 * @param array Object []
	 */
	public void reverseOrder(Object[] array) {
		for ( int i = 0, j = array.length - 1; i < j; i++, j-- ) {
			Object o = array[i];
			array[i] = array[j];
			array[j] = o;
		}
	}

	/**
	 * Create a comma-delimited string of the given array or series of objects.
	 * 
	 * @param array Objects
	 * @return String
	 */
	public String arrayToString(Object... array) {
		StringBuilder sb = new StringBuilder();

		if ( array != null ) {
			for ( Object o : array ) {
				sb.append(o.toString() + ", ");
			}
		}
		return sb.toString();
	}

	/**
	 * Get the union of all values in arrays a and b.
	 * 
	 * @param a T array
	 * @param b T array
	 * @param uniqueIdentificationMethod Method to invoke to uniquely identify
	 *            each T
	 * @return Collection of T without duplicates, or an empty collection if no
	 *         values are provided
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public Collection<T> union(T[] a, T[] b, Method uniqueIdentificationMethod)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map<Object, T> results = new HashMap<Object, T>();

		if ( a != null ) {
			for ( T t : a ) {
				Object id = uniqueIdentificationMethod.invoke(t);
				results.put(id, t);
			}
		}

		if ( b != null ) {
			for ( T t : b ) {
				Object id = uniqueIdentificationMethod.invoke(t);
				results.put(id, t);
			}
		}

		return results.values();
	}

	/**
	 * Get the T values which are only in a and not in b.
	 * 
	 * @param a T array
	 * @param b T array
	 * @param uniqueIdentificationMethod Method to invoke to uniquely identify
	 *            each T
	 * @return Collection of T without duplicates, or an empty collection if no
	 *         values are provided
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public Collection<T> justInFirstArray(T[] a, T[] b, Method uniqueIdentificationMethod)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map<Object, T> results = new HashMap<Object, T>();

		if ( a != null ) {
			for ( T t : a ) {
				Object id = uniqueIdentificationMethod.invoke(t);
				results.put(id, t);
			}
		}

		if ( b != null ) {
			for ( T t : b ) {
				Object id = uniqueIdentificationMethod.invoke(t);
				if ( results.containsKey(id) ) {
					results.remove(id);
				}
			}
		}

		return results.values();
	}

}