package com.hiveworkshop.symbol.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		/* PersonTest.class, */
		PrivilegeTest.class, SymbolTest.class, SignatureTest.class,

		QueryTest.class,})
public class AllTests
{

}
