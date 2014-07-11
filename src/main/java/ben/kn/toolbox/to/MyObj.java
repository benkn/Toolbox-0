package ben.kn.toolbox.to;

public class MyObj {

	private String name;
	private String value;

	public MyObj(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return "MyObj [name=" + name + ", value=" + value + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		System.out.println("HASHING : " + name);
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("Equals checking with " + name);
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		MyObj other = (MyObj) obj;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals(other.name) )
			return false;
		if ( value == null ) {
			if ( other.value != null )
				return false;
		} else if ( !value.equals(other.value) )
			return false;
		return true;
	}

}
