package ben.kn.toolbox.hbase;

/**
 * Enum for defining tables existing in the HBase datastore.
 * 
 * @author Ben (bknear@gmail.com)
 * @since Nov 6, 2012
 */
public enum ExampleTable {

	EXAMPLE("example");

	String table;

	ExampleTable(String table) {
		this.table = table;
	}

	public String getTable() {
		return table;
	}
}