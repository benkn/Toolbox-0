package ben.kn.toolbox.to;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

public abstract class CloneableObject<T> {
	@Override
	public T clone() {
		T duplicate = null;
		try {
			@SuppressWarnings("unchecked")
			Constructor<T> constructor = (Constructor<T>) getClass().getDeclaredConstructor();
			// System.out.println("Constructing new " +
			// getClass().getCanonicalName() + " using " +
			// constructor.getName());
			duplicate = constructor.newInstance();

			Field[] fields = getClass().getDeclaredFields();
			for ( Field f : fields ) {
				try {
					String fieldName = StringUtils.capitalize(f.getName());
					Method getter = getClass().getMethod("get" + fieldName);
					Method setter = getClass().getMethod("set" + fieldName, f.getType());
					// System.out.println("Running " + getter.getName() +
					// " on this, and "
					// + setter.getName() + " on the duplicate.");
					setter.invoke(duplicate, getter.invoke(this));
				} catch (NoSuchMethodException nsme) {
					/* do nothing in these situations */
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duplicate;
	}
}