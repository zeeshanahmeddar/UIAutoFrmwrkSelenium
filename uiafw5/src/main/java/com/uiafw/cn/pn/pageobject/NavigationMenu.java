package com.uiafw.cn.pn.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiafw.cn.pn.helper.browserconfiguration.config.ObjectReader;
import com.uiafw.cn.pn.helper.logger.LoggerHelper;
import com.uiafw.cn.pn.helper.wait.WaitHelper;
import com.uiafw.cn.pn.testbase.TestBase;

public class NavigationMenu {
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(NavigationMenu.class);
	public WaitHelper waitHelper;
	
	@FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[1]")
	public WebElement womenMenu;
	
	@FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[2]/a")
	public WebElement dressesMenu;
	
	@FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[3]/a")
	public WebElement tshirtMenu;
	
	public NavigationMenu(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(womenMenu, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		TestBase.logExtentReport("NavigationMenu object created");
	}
	
	public void mouseOver(String data) {
		log.info("Mouse over");
		TestBase.logExtentReport("Mouse over");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]"))).build().perform();
	}
	
	public ProductCategoryPage clickOnItem(String data) {
		log.info("click on Product Category Items");
		TestBase.logExtentReport("click on Product Category Items");
		driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]")).click();
		return new ProductCategoryPage(driver);
	}
	
	public ProductCategoryPage clickOnMenu(WebElement element) {
		log.info("click on Product Category Menu");
		TestBase.logExtentReport("click on Product Category Menu");
		element.click();
		return new ProductCategoryPage(driver);
	}
	
}
