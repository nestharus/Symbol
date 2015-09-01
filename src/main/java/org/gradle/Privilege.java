package org.gradle;

import com.google.common.collect.ImmutableSet;

public class Privilege {
	ImmutableSet<Privilege> set;
	
	public Privilege() {
		this.set = ImmutableSet.of(this);
	}
	
	/**
	 *	Test if the fiven privilege satesfies current privilege
	 *  ()
	 */
	public boolean test(Privilege other){
		return false;
	}

	public Privilege intersect(Privilege other){
		return null;
	}

	
	public Privilege union(Privilege other){
		return null;
	}
}
