package org.gradle;


import org.apache.commons.collections.list.GrowthList;
import com.google.common.collect.ImmutableMap;

public class Person {
    ImmutableMap<Integer, Integer> map;

    private final String name;

    public Person(String name) {
	map = ImmutableMap.of();
	
	this.name = name;
	new GrowthList();
    }

    public String getName() {
	return name;
    }
}
