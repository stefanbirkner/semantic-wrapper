package com.github.stefanbirkner.semanticwrapper.generator;

public class FirstLetter {
	private final Character firstLetter;

	public static FirstLetter firstLetter(Character firstLetter) {
		return (firstLetter == null) ? null : new FirstLetter(firstLetter);
	}

	public FirstLetter(Character firstLetter) {
		if (firstLetter == null)
			throw new IllegalArgumentException("Cannot wrap null.");
		this.firstLetter = firstLetter;
	}

	public char charValue() {
		return firstLetter;
	}

	@Override
	public int hashCode() {
		return firstLetter.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FirstLetter other = (FirstLetter) obj;
		return (firstLetter.equals(other.firstLetter));
	}

	@Override
	public String toString() {
		return firstLetter.toString();
	}
}