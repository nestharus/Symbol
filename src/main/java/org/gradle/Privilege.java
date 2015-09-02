package org.gradle;

import com.google.common.collect.ImmutableSet;

public class Privilege {
	public static Privilege union(Privilege... privileges) {
		ImmutableSet.Builder<Privilege> builder = ImmutableSet.builder();

		for (Privilege privilege : privileges) {
			builder.addAll(privilege.privileges.iterator());
		} // if

		return new Privilege(builder.build());
	} // union

	private final ImmutableSet<Privilege> privileges;

	public Privilege() {
		this.privileges = ImmutableSet.of(this);
	} // Privilege

	private Privilege(ImmutableSet<Privilege> privileges) {
		this.privileges = privileges;
	} // Privilege

	/**
	 * Test if the given privilege satisfies current privilege
	 *
	 * @param providedPrivileges
	 * @return whether provided privileges are accepted
	 */
	public boolean accepts(Privilege providedPrivileges) {
		return providedPrivileges.privileges.contains(this);
	} // accepts
} // Privilege
