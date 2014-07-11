package ben.kn.toolbox.lang;

public class DataAccessException extends RuntimeException {
	private static final long serialVersionUID = -4598339694077023661L;

	public DataAccessException(Exception e) {
		super(e);
	}

	public DataAccessException(String message, Exception e) {
		super(message, e);
	}

	public DataAccessException(String string) {
		super(string);
	}
}