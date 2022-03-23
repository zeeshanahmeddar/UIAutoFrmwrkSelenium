package com.uiafw.cn.pn.helper.select;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uiafw.cn.pn.helper.logger.LoggerHelper;

public class DropdownHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(DropdownHelper.class);
	
	public DropdownHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
		log.info("Select by value");
	}
	
	public void selectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		log.info("Select by index");
	}
	
	public void selectUsingVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
		log.info("Select by visible text");
	}
	
	public void deselectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		select.deselectByValue(value);
		log.info("Deselect by value");
	}
	
	public void deselectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.deselectByIndex(index);
		log.info("Deselect by index");
	}
	
	public void deselectUsingVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		select.deselectByVisibleText(visibleText);
		log.info("Deselect by visible text");
	}
	
	public List<String> getAllDropdownValues(WebElement element){
		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for(WebElement webElement : elementList) {
			log.info(webElement.getText());
			valueList.add(webElement.getText());
		}
		return valueList;
		
	}

}
