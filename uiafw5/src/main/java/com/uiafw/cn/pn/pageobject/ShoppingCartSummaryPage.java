package com.uiafw.cn.pn.pageobject;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiafw.cn.pn.helper.assertion.VerificationHelper;
import com.uiafw.cn.pn.helper.browserconfiguration.config.ObjectReader;
import com.uiafw.cn.pn.helper.logger.LoggerHelper;
import com.uiafw.cn.pn.helper.wait.WaitHelper;
import com.uiafw.cn.pn.testbase.TestBase;

public class ShoppingCartSummaryPage {
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(ShoppingCartSummaryPage.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath = "//*[@id=\"cart_title\"]/span")
	public WebElement yourShoppingCart;
	
	@FindBy(xpath = "//*[@title=\"Delete\"]")
	public List<WebElement> allDeleteableProducts;
	
	@FindBy(xpath = "//*[contains(text(),'Your shopping cart is empty')]")
	public WebElement emptyShoppingCartMessage;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]/p[2]/a[1]/span")
	public WebElement proceedToCheckout;	
	
	public ShoppingCartSummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(yourShoppingCart, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		TestBase.logExtentReport("ShoppingCartSummaryPage created");
		log.info("ShoppingCartSummaryPage created");
	}
	
	public boolean verifyProduct(String product) {
		WebElement productElement = driver.findElement(By.xpath("//*[contains(text(),'"+product+"')]"));
		return new VerificationHelper(driver).isDisplayed(productElement);
	}
	
	public void deleteProductFromShoppingCart() {
		List<WebElement> allDeleteables = allDeleteableProducts;
		Iterator<WebElement> itr = allDeleteables.iterator();
		while(itr.hasNext()) {
			itr.next().click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean verifyShoppingCartMessage() {
		return new VerificationHelper(driver).isDisplayed(emptyShoppingCartMessage);
	}
}
