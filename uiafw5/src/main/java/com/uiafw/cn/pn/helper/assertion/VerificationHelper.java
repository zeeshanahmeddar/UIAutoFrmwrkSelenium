package com.uiafw.cn.pn.helper.assertion;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiafw.cn.pn.helper.logger.LoggerHelper;

public class VerificationHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(VerificationHelper.class);
	
	public VerificationHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("Element is displayed "+element.getText());
			return true;
		} catch (Exception e) {
			log.error("Element is not displayed ",e.getCause());
			return false;
		}
	}
	
	public boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("Element is displayed "+element.getText());
			return false;
		} catch (Exception e) {
			log.error("Element is not displayed "+element.getText());
			return true;
		}
	}
	
	public String getText(WebElement element) {
		if(element == null) {
			log.info("Element is null");
			return null;
		}
		boolean status = isDisplayed(element);
		if(status) {
			log.info("Element text is "+element.getText());
			return element.getText();
		} else {
			return null;
		}
	}
	
	public String getTitle() {
		log.info("Getting page title");
		return driver.getTitle();
	}
}
