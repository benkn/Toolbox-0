package ben.kn.toolbox.logging;

public class SimpleLogger {

	private boolean debugEnabled = false;
	private boolean detailEnabled = false;
	private static final String LINE_DELIM = "----------------------------------------------------------";

	private SimpleLogger() {

	}

	public static SimpleLogger getLogger(Class<?> clazz) {
		// this logic can change later
		return new SimpleLogger();
	}

	public void info(Object message) {
		System.out.println("INFO: " + message);
	}

	public void debug(Object message) {
		System.out.println("DEBUG: " + message);
	}

	public void warn(Object message) {
		System.out.println("WARN: " + message);
	}

	public void error(Object message) {
		System.out.println("ERROR: " + message);
	}

	public boolean isDebugEnabled() {
		return debugEnabled;
	}

	public boolean isDetailEnabled() {
		return detailEnabled;
	}

	public void setDetailEnabled(boolean detailEnable) {
		detailEnabled = detailEnable;
	}

	public void important(Object message) {
		info(LINE_DELIM);
		info(message);
		info(LINE_DELIM);
	}
}
