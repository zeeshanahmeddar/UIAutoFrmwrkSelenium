package com.uiafw.cn.pn.testscripts;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiafw.cn.pn.helper.assertion.AssertionHelper;
import com.uiafw.cn.pn.helper.browserconfiguration.config.ObjectReader;
import com.uiafw.cn.pn.helper.logger.LoggerHelper;
import com.uiafw.cn.pn.pageobject.LoginPage;
import com.uiafw.cn.pn.testbase.TestBase;

public class LoginDataDriverTest extends TestBase{
	
	private Logger log = LoggerHelper.getLogger(LoginDataDriverTest.class);
	LoginPage login;
	@DataProvider(name = "testDataDrivendp")
	public Object[][] testDataDriven(){
		return getExcelDD("testDataDriven.xlsx", "LoginDataDriven");
	}
	
	@BeforeClass
	public void beforeClass() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		login = new LoginPage(driver);
	}
	
	@Test(dataProvider = "testDataDrivendp")
	public void testLoginDD(String username, String password, String runmode) {
		if(runmode.equalsIgnoreCase("no")) {
			throw new SkipException("Run mode for this set of data is marked as N");
		}
		login.loginToApplication(username, password);
		boolean status = login.verifySuccessfulLogin();
		AssertionHelper.updateTestStatus(status);
		login.logoutUser();
		log.info("Logout completed");
	}
	
	
}
