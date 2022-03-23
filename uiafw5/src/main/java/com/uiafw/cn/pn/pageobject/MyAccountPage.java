package com.uiafw.cn.pn.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiafw.cn.pn.helper.assertion.VerificationHelper;
import com.uiafw.cn.pn.helper.browserconfiguration.config.ObjectReader;
import com.uiafw.cn.pn.helper.logger.LoggerHelper;
import com.uiafw.cn.pn.helper.wait.WaitHelper;
import com.uiafw.cn.pn.testbase.TestBase;

public class MyAccountPage {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(MyAccountPage.class);
	public WaitHelper waitHelper;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]/p")
	public WebElement yourAccountMessage;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]/div/div[1]/ul/li[1]/a")
	public WebElement orderHistoryAndDetails;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]/div/div[1]/ul/li[4]/a")
	public WebElement myPersonalInformation;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]/div/div[2]/ul/li/a")
	public WebElement myWishList;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(yourAccountMessage, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
	}
	
	public void clickOnMyWishList() {
		myWishList.click();
		log.info("clicked on "+myWishList.getText());
		TestBase.logExtentReport("clicked on "+myWishList.getText());
	}
	
	public void clickOnMyPersonalInfo() {
		myPersonalInformation.click();
		log.info("clicked on "+myPersonalInformation.getText());
		TestBase.logExtentReport("clicked on "+myPersonalInformation.getText());
	}
	
	public void clickOnOrderHistoryAndDetails() {
		orderHistoryAndDetails.click();
		log.info("clicked on "+orderHistoryAndDetails.getText());
		TestBase.logExtentReport("clicked on "+orderHistoryAndDetails.getText());
	}

	public boolean yourAccountPageMessageDisplayed() {
		return new VerificationHelper(driver).isDisplayed(yourAccountMessage);
	}

}
