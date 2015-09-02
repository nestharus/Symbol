package org.gradle;

public class Signature {
	public static final int INFINITY = Integer.MAX_VALUE;

	public int distance(Signature signature) {
		return this == signature ? 0 : INFINITY;
	}

	/*
	 * Function Signature Example
	 *
	 * hashCode() return name.hashCode()*argCount // bad hash, but use these two
	 * things
	 *
	 * equals() // Fuzzy Equality! return getClass() == other.getClass() &&
	 * this.name = other.name && this.argCount == other.argCount
	 */

	@Override
	public boolean equals(Object object) {
		return this == object;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}