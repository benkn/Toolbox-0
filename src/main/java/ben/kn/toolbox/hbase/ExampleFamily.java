package ben.kn.toolbox.hbase;

/**
 * Family enum representing the Column Families for specific Tables.
 * 
 * @author Ben (bknear@gmail.com)
 * @since Nov 6, 2012
 */
public enum ExampleFamily {
	METADATA("metadata", ExampleTable.EXAMPLE);

	String family;
	ExampleTable table;

	ExampleFamily(String family, ExampleTable table) {
		this.family = family;
	}

	public String getFamily() {
		return family;
	}

	public ExampleTable getTable() {
		return table;
	}
}
