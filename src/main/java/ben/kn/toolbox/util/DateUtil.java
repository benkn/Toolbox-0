package ben.kn.toolbox.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * Take the given date and translate it to just a yyyy-MM-dd date, with no
	 * other date elements.
	 * 
	 * @param date Date
	 * @return Date
	 */
	public static Date getYyyyMmDdDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// wipe the hour, minute, second, millisecond
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);

		return cal.getTime();
	}

	public static Date getTomorrowFrom(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}

}
