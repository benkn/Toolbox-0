package ben.kn.toolbox.hbase;

/**
 * Qualifier enum defining the qualifiers used for specific Column Families.
 * 
 * @author Ben (bknear@gmail.com)
 * @since Nov 6, 2012
 */
public enum ExampleQualifier {
	//@formatter:off
	MESSAGE("message", ExampleFamily.METADATA), 
	USERNAME("username", ExampleFamily.METADATA), 
	POST_DATE("postDate", ExampleFamily.METADATA)
	;
	//@formatter:on

	private String qualifer;
	private ExampleFamily family;

	ExampleQualifier(String qualifer, ExampleFamily family) {
		this.qualifer = qualifer;
		this.family = family;
	}

	public String getQualifer() {
		return qualifer;
	}

	public ExampleFamily getFamily() {
		return family;
	}

	public byte[] getQualiferBytes() {
		return qualifer.getBytes();
	}
}