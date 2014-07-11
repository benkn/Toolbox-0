package ben.kn.toolbox.services;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFinder {
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static String createDate(long millis) {
		Date d = new Date(millis);

		return (dateFormatter.format(d));
	}

	public static void main(String[] args) {
		String result = createDate(1046476800000L);
		System.out.println(result);
	}
}