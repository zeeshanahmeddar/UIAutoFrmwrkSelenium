package com.uiafw.cn.pn.testscripts;

import org.testng.annotations.Test;

import com.uiafw.cn.pn.helper.assertion.AssertionHelper;
import com.uiafw.cn.pn.testbase.TestBase;

public class FirstTest extends TestBase{

	@Test
	public void testLoginP() {
		AssertionHelper.makeTrue();
	}
	@Test
	public void testLoginF() {
		AssertionHelper.makeFalse();
	}
}
