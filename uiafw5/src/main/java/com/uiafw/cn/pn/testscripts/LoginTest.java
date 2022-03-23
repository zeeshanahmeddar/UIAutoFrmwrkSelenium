package com.uiafw.cn.pn.testscripts;

import org.testng.annotations.Test;

import com.uiafw.cn.pn.helper.assertion.AssertionHelper;
import com.uiafw.cn.pn.helper.browserconfiguration.config.ObjectReader;
import com.uiafw.cn.pn.pageobject.LoginPage;
import com.uiafw.cn.pn.testbase.TestBase;

public class LoginTest extends TestBase{
	
	@Test(description="Login test with valid credentials")
	public void testLoginToApplication() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUsername(), ObjectReader.reader.getPassword());
		boolean status = loginPage.verifySuccessfulLogin();
		AssertionHelper.updateTestStatus(status);
	}
	
	
}
