package com.uiafw.cn.pn.helper.wait;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.uiafw.cn.pn.helper.browserconfiguration.config.ObjectReader;
import com.uiafw.cn.pn.helper.logger.LoggerHelper;

public class WaitHelper {
	private WebDriver driver;
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		log.info("WaitHelper object created");
	}
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);
	//This is ImplicitWait method
	public void setImplicitWait(Duration duration) {
		driver.manage().timeouts().implicitlyWait(duration);
		log.info("Implicit Wait has been set to: "+ ObjectReader.reader.getImplicitWait());
	}
	
	public FluentWait<WebDriver> fluentWait(int timeOutInSeconds, int pollingEveryInMilliSec) {
		return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds)).pollingEvery(Duration.ofMillis(pollingEveryInMilliSec)).ignoring(NoSuchElementException.class);
	}
	
	public FluentWait<WebDriver> fluentWait(int timeOutInSeconds) {
		return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds)).ignoring(NoSuchElementException.class);
	}
	
	private FluentWait<WebDriver> getfWait(int timeOutInSeconds, int pollingEveryInMilliSec) {
		FluentWait<WebDriver> fwait = fluentWait(timeOutInSeconds, pollingEveryInMilliSec);
		fwait.pollingEvery(Duration.ofMillis(pollingEveryInMilliSec));
		fwait.ignoring(NoSuchElementException.class);
		fwait.ignoring(ElementNotVisibleException.class);
		fwait.ignoring(StaleElementReferenceException.class);
		fwait.ignoring(NoSuchFrameException.class);
		return fwait;
	}
	
	public void waitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds, int pollingEveryMilliSec) {
		log.info("Waiting for: "+element.toString()+ " for: " +timeOutInSeconds+" seconds");
		FluentWait<WebDriver> fwait = getfWait(timeOutInSeconds, pollingEveryMilliSec);
		fwait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element is visible now");
	}
	
	public void waitForElementClickable(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for: "+element.toString()+ " for: " +timeOutInSeconds+" seconds");
		FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds));
		fwait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Element is clickable now");
	}
	
	public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
		log.info("Waiting for: "+element.toString()+ " for: " +timeOutInSeconds+" seconds");
		FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds));
		boolean status = fwait.until(ExpectedConditions.invisibilityOf(element));
		log.info("Element is invisible now");
		return status;
	}
	
	public void waitFrameToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
		log.info("Waiting for: "+element.toString()+ " for: " +timeOutInSeconds+" seconds");
		FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds));
		fwait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("frame is available and switiched");
	}
	
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
		FluentWait<WebDriver> fwait = fluentWait(timeOutInSeconds, pollingEveryInMilliSec);
		fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	public WebElement waitForElement(WebElement element, int timeOutInSeconds) {
		FluentWait<WebDriver> fwait = fluentWait(timeOutInSeconds);
		fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	public void pageLoadTime(Duration duration) {
		log.info("Waiting for loading a page for: "+ObjectReader.reader.getPageLoadTime());
		driver.manage().timeouts().pageLoadTimeout(duration);
		log.info("Page is loaded");
	}
}
