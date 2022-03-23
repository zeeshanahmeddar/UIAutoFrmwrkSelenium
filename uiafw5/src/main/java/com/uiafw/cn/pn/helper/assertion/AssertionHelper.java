package com.uiafw.cn.pn.helper.assertion;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.uiafw.cn.pn.helper.logger.LoggerHelper;

public class AssertionHelper {

	private static Logger log = LoggerHelper.getLogger(AssertionHelper.class);
	
	public static void assertTwoStringsIsEqual(String s1, String s2) {
		log.info("compare two strings");
		Assert.assertEquals(s1, s2);
	}
	
	public static void verifyText(String s1, String s2) {
		log.info("Verifying text one with text two");
		Assert.assertEquals(s1, s2);
	}
	
	public static void makeTrue() {
		log.info("Making script pass");
		Assert.assertTrue(true);
	}
	
	public static void makeTrue(String message) {
		Assert.assertTrue(true, message);
	}
	
	public static void makeFalse() {
		log.info("Making script false");
		Assert.assertFalse(true);
	}
	
	public static void makeFalse(String message) {
		Assert.assertFalse(true, message);
	}
	
	public static void verifyTrue(boolean status) {
		Assert.assertTrue(status);
	}
	
	public static void verifyFalse(boolean status) {
		Assert.assertFalse(status);
	}
	
	public static void verifyNull(String s1) {
		log.info("Verify object is null");
		Assert.assertNull(s1);
	}
	
	public static void verifyNotNull(String s1) {
		log.info("Verify object is not null");
		Assert.assertNotNull(s1);
	}

	public static void updateTestStatus(boolean status) {
		if (status) {
			pass();
		} else {
			fail();
		}
		
	}

	private static void fail() {
		Assert.assertTrue(false);
		
	}

	private static void pass() {
		Assert.assertTrue(true);
		
	}

}
