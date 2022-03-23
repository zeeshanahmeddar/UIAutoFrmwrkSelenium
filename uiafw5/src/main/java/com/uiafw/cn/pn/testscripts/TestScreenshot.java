package com.uiafw.cn.pn.testscripts;

import org.testng.annotations.Test;

import com.uiafw.cn.pn.testbase.TestBase;

public class TestScreenshot extends TestBase{
	@Test
	public void takeScreenshot() {
		driver.get("https://www.saucedemo.com/");
		captureScreen("firstscreen", driver);
	}
}
