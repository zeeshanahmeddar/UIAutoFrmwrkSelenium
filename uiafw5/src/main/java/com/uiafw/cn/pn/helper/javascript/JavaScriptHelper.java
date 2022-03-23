package com.uiafw.cn.pn.helper.javascript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.uiafw.cn.pn.helper.logger.LoggerHelper;

public class JavaScriptHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);
	
	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
		log.info("JavaScriptHelper has been initialised");
	}
	
	public Object executeScript(String script) {
		JavascriptExecutor jsexe = (JavascriptExecutor)driver;
		return jsexe.executeScript(script);
	}
	
	public Object executeScript(String script, Object...args) {
		JavascriptExecutor jsexe = (JavascriptExecutor)driver;
		return jsexe.executeScript(script, args);
	}
	
	public void scrollToElement(WebElement element) {
		log.info("scrolling to element");
		executeScript("window.scrollTo(arguments[0],arguments[1])",element.getLocation().x,element.getLocation().y);
	}
	
	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		log.info("Element is clicked"+element.toString());
	}
	
	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()",element);
		log.info("Scroll into view to Element"+element.toString());
	}
	
	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info("Scroll into view to Element"+element.toString()+" and clicked");
	}
	
	public void scrollDownVertically() {
		executeScript("window.scrollTo(0,document.body.scrollHeight)");
		log.info("Scrolling down vertically");
	}
	
	public void scrollUpVertically() {
		executeScript("window.scrollTo(0,-document.body.scrollHeight)");
		log.info("Scrolling up vertically");
	}
	
	public void scrollDownByPixel(int pixel) {
		executeScript("window.scrollBy(0,"+pixel+")");
	}
	
	public void scrollUpByPixel(int pixel) {
		executeScript("window.scrollBy(0,-"+pixel+")");
	}
	
	public void zoomInBy100Percent() {
		executeScript("document.body.style.zoom='100'");
	}
	
	public void zoomInBy50Percent() {
		executeScript("document.body.style.zoom='50'");
	}
	
	public void clickElement(WebElement element) {
		executeScript("arguments[0].click();",element);
	}

}
