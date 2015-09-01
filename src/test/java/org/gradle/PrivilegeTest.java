package org.gradle;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrivilegeTest {

	@Test
	public void testTrivialConstruction() {
		Privilege priv = new Privilege();
		assertNotEquals(null, priv);
	}
	
	@Test
	public void test2PrivilegesNotEqual() {
		Privilege priv = new Privilege();
		Privilege priv2 = new Privilege();
		assertNotEquals(priv, priv2);
	}
	
	@Test
	public void testNulldoesNotSatesfyPrivilegeTest() {
		Privilege priv = new Privilege();
		Privilege priv2 = null;
		assertFalse(priv.test(priv2));
	}

	@Test
	public void testDistinctPrivilegeDoesNotSatesfyPrivilegeTest() {
		Privilege priv = new Privilege();
		Privilege priv2 = new Privilege();
		assertFalse(priv.test(priv2));
	}
	
	@Test
	public void testSamePrivilegeDoesSatesfyTest() {
		Privilege priv = new Privilege();
		Privilege priv2 = priv;
		assertTrue(priv.test(priv2));
	}
	
	@Test
	public void testUnionSatesfiesChildTest() {
		Privilege priv = new Privilege();
		Privilege priv2 = priv.union(new Privilege());
		assertTrue(priv.test(priv2));
	}
}
