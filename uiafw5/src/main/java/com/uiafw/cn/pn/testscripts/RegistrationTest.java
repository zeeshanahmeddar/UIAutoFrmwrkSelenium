package com.uiafw.cn.pn.testscripts;

import org.testng.annotations.Test;

import com.uiafw.cn.pn.helper.assertion.AssertionHelper;
import com.uiafw.cn.pn.helper.browserconfiguration.config.ObjectReader;
import com.uiafw.cn.pn.pageobject.LoginPage;
import com.uiafw.cn.pn.pageobject.MyAccountPage;
import com.uiafw.cn.pn.pageobject.RegistrationPage;
import com.uiafw.cn.pn.testbase.TestBase;

public class RegistrationTest extends TestBase {

	LoginPage loginPage;
	RegistrationPage registrationPage;
	MyAccountPage myAccountPage;
	
	@Test
	public void testLoginApplication() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		
		loginPage = new LoginPage(driver);
		loginPage.clickOnSignInLink();
		loginPage.enterRegistrationEmail();
		loginPage.clickOnCreateNewAccount();
		
		registrationPage = new RegistrationPage(driver);
		registrationPage.setFirstName("firstname");
		registrationPage.setLastName("lastname");
		registrationPage.setPassword("123456");
		registrationPage.setAddress("aas sfdlk");
		registrationPage.setDay("5");
		registrationPage.setMonth("July");
		registrationPage.setYear("1998");
		registrationPage.setCity("lahore");
		registrationPage.setState("California");
		registrationPage.setZipcode("99051");
		registrationPage.setPhoneNumber("0987654321");
		registrationPage.clickRegistrationButton();
		
		myAccountPage = new MyAccountPage(driver);
		boolean status = myAccountPage.yourAccountPageMessageDisplayed();
		AssertionHelper.updateTestStatus(status);
		
		
	}
}
