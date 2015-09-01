package org.gradle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	PersonTest.class,
	PrivilegeTest.class,
	SignatureTest.class
})
public class AllTests {

}
