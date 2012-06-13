

public class Name {
	private final String name;

	public static Name name(String name) {
		return (name == null) ? null : new Name(name);
	}

	public Name(String name) {
		if (name == null)
			throw new IllegalArgumentException("Cannot wrap null.");
		this.name = name;
	}

	public String stringValue() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Name other = (Name) obj;
		return (name.equals(other.name));
	}

	@Override
	public String toString() {
		return name.toString();
	}
}