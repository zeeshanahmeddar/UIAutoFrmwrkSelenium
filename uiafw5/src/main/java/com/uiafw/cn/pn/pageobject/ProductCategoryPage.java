package com.uiafw.cn.pn.pageobject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.uiafw.cn.pn.helper.assertion.AssertionHelper;
import com.uiafw.cn.pn.helper.browserconfiguration.config.ObjectReader;
import com.uiafw.cn.pn.helper.javascript.JavaScriptHelper;
import com.uiafw.cn.pn.helper.logger.LoggerHelper;
import com.uiafw.cn.pn.helper.select.DropdownHelper;
import com.uiafw.cn.pn.helper.wait.WaitHelper;
import com.uiafw.cn.pn.testbase.TestBase;

public class ProductCategoryPage {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(ProductCategoryPage.class);
	public WaitHelper waitHelper;
	
	@FindBy(xpath = "//*[@id=\"layered_block_left\"]")
	WebElement catalogText;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[1]")
	WebElement addToCart;
	
	@FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")
	WebElement productAddedSuccessfully;
	
	@FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")
	WebElement proceedToCheckout;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]/ul/li")
	List<WebElement> totalProducts;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]/ul/li/div/div[2]/div[1]/span[1]")
	List<WebElement> allPriceElements;
	
	@FindBy(xpath = "//*[@id=\"selectProductSort\"]")
	WebElement sortBy;
	
	public ProductCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(catalogText, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		TestBase.logExtentReport("product category page is loaded");
		log.info("product category page is created");
	}
	
	public void mouseOverOnProduct(int number) {
		String firstPart = "//*[@id=\"center_column\"]/ul/li[";
		String secondPart = "]/div/div[2]/div[2]/a[1]";
		Actions action = new Actions(driver);
		TestBase.logExtentReport("Mouse Over On a Product");
		log.info("Mouse Over On a Product");
		action.moveToElement(driver.findElement(By.xpath(firstPart+number+secondPart))).build().perform();
	}
	
	public void addToCart() {
		addToCart.click();
		TestBase.logExtentReport("add to cart executed");
		log.info("add to cart executed");
	}
	
	public ShoppingCartSummaryPage clickOnProceedToCheckout() {
		proceedToCheckout.click();
		TestBase.logExtentReport("clicking On Proceed To Checkout");
		log.info("clicking On Proceed To Checkout");
		return new ShoppingCartSummaryPage(driver);
	}
	
	public void selectColor(String colorName) {
		new JavaScriptHelper(driver).scrollIntoView(driver.findElement(By.xpath("//a[contains(text(),\""+colorName+"\")]/parent::*/preceding-sibling::input[1]")));
		driver.findElement(By.xpath("//a[contains(text(),\""+colorName+"\")]/parent::*/preceding-sibling::input[1]")).click();
		TestBase.logExtentReport("checking the checkbox before color");
		log.info("checking the checkbox before color");
	}
	
	public void selectSmallSize() {
		log.info("selecting small size");
		TestBase.logExtentReport("selecting small size");
		driver.findElement(By.xpath("//*[@id=\"layered_id_attribute_group_1\"]")).click();
		try {
			boolean selected = driver.findElement(By.xpath("//*[@id=\"layered_id_attribute_group_1\"]")).isSelected();
			if(!selected) {
				driver.findElement(By.xpath("//*[@id=\"layered_id_attribute_group_1\"]")).click();
				log.info("small size is selected");
				TestBase.logExtentReport("small size is selected");
			}
		} catch (Exception e) {
			log.info("small size is already selected");
			TestBase.logExtentReport("small size is already selected");
		}
	}
	
	public void selectMediumSize() {
		log.info("selecting medium size");
		TestBase.logExtentReport("selecting medium size");
		try {
			boolean selected = driver.findElement(By.xpath("//*[@id=\"layered_id_attribute_group_2\"]")).isSelected();
			if(!selected) {
				driver.findElement(By.xpath("//*[@id=\"layered_id_attribute_group_2\"]")).click();
				log.info("medium size is selected");
				TestBase.logExtentReport("medium size is selected");
			}
		} catch (Exception e) {
			log.info("medium size is already selected");
			TestBase.logExtentReport("medium size is already selected");
		}
	}
	
	public void selectFirstProduct() {
		Actions action = new Actions(driver);
		action.moveToElement(totalProducts.get(0)).build().perform();
		addToCart.click();
		log.info("added first product to cart");
		TestBase.logExtentReport("added first product to cart");
	}
	
	public int getTotalProductNumber() {
		return totalProducts.size();
	}
	
	public List<WebElement> getAllProductsPrices(){
		return allPriceElements;
	}
	
	public void selectSortByDropDown(String valueToSelect) {
		DropdownHelper dropdown = new DropdownHelper(driver);
		dropdown.selectUsingVisibleText(sortBy, valueToSelect);
	}
	
	public void lowToHighPrices() {
		boolean status = false;
		List<WebElement> pricesx = getAllProductsPrices();
		ArrayList<Integer> priceArray = new ArrayList<Integer>();
		Iterator<WebElement> itr = pricesx.iterator();
		
		while(itr.hasNext()) {
			String p = itr.next().getText();
			if(p.contains("$")) {
				String actualData = p.substring(1);
				log.info(actualData);
				double p1 = Double.parseDouble(actualData);
				int productprice = (int)p1;
				priceArray.add(productprice);
			}
		}
		log.info(priceArray);
		for(int i = 0; i < (priceArray.size()-1);i++) {
			if(priceArray.get(i)<=priceArray.get(i+1)) {
				log.info(priceArray.get(i)+" <= "+priceArray.get(i+1));
				status = true;
			} else {
				Assert.assertTrue(false, "Price filter not working");
				status = false;
			}
		}
		AssertionHelper.updateTestStatus(status);
	}

}
