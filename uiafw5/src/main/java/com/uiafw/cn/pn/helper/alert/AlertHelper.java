package com.uiafw.cn.pn.helper.alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.uiafw.cn.pn.helper.logger.LoggerHelper;

public class AlertHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(AlertHelper.class);
	
	public AlertHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public Alert getAlert() {
		log.info("Alert test: "+driver.switchTo().alert().getText());
		return driver.switchTo().alert();
	}
	
	public void acceptAlert() {
		log.info("Acceting alert");
		getAlert().accept();
	}
	
	public void dismissAlert() {
		getAlert().dismiss();
	}
	
	public String getAlertText() {
		log.info("Getting alert text");
		return getAlert().getText();
	}
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			log.info("Alert is present");
			return true;
		} catch (NoAlertPresentException e) {
			log.info(e.getCause());
			return false;
		}
	}
	
	public void acceptAlertIfPresent() {
		if (isAlertPresent()) {
			acceptAlert();
		} else {
			log.info("Alergt is not present");
		}
	}
	
	public void dismissAlertIfPresent() {
		if (isAlertPresent()) {
			dismissAlert();
		} else {
			log.info("Alergt is not present");
		}
	}
	
	public void acceptPrompt(String text) {
		if(isAlertPresent()) {
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			log.info("Text enter and accept the prompt");
		}
	}

}
