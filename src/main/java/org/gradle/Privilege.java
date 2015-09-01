package org.gradle;

import com.google.common.collect.ImmutableSet;

public class Privilege {
	ImmutableSet<Privilege> set;
	
	public Privilege() {
		this.set = ImmutableSet.of(this);
	}
	
	public Privilege(Privilege p1, Privilege p2) {
		this.set = ImmutableSet.<Privilege>builder().addAll(p1.set).addAll(p2.set).build();
	}
	
	/**
	 *	Test if the fiven privilege satesfies current privilege
	 *  ()
	 */
	public boolean test(Privilege other){
		return this.set.containsAll(other.set);
	}

	public Privilege intersect(Privilege other){
		return null;
	}

	
	public Privilege union(Privilege other){
		return new Privilege(this, other);
	}
}
