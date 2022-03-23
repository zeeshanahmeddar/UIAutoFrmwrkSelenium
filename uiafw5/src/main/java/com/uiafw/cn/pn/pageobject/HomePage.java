package com.uiafw.cn.pn.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.uiafw.cn.pn.helper.browserconfiguration.config.ObjectReader;
import com.uiafw.cn.pn.helper.logger.LoggerHelper;
import com.uiafw.cn.pn.helper.wait.WaitHelper;
import com.uiafw.cn.pn.testbase.TestBase;

public class HomePage {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(HomePage.class);
	WaitHelper waitHelper;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
//		waitHelper.waitForElement(SubmitLogin, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
	}

}
