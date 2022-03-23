package com.uiafw.cn.pn.pageobject;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiafw.cn.pn.helper.browserconfiguration.config.ObjectReader;
import com.uiafw.cn.pn.helper.logger.LoggerHelper;
import com.uiafw.cn.pn.helper.wait.WaitHelper;
import com.uiafw.cn.pn.testbase.TestBase;

public class RegistrationPage {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(RegistrationPage.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath = "//*[@id=\"id_gender1\"]")
	public WebElement mrRadioButton;
	
	@FindBy(xpath = "//*[@id=\"customer_firstname\"]")
	public WebElement firstName;
	
	@FindBy(xpath = "//*[@id=\"customer_lastname\"]")
	public WebElement lastName;
	
	@FindBy(xpath = "//*[@id=\"passwd\"]")
	public WebElement password;
	
	@FindBy(xpath = "//*[@id=\"months\"]")
	public WebElement month;
	
	@FindBy(xpath = "//*[@id=\"days\"]")
	public WebElement day;
	
	@FindBy(xpath = "//*[@id=\"years\"]")
	public WebElement year;
	
	@FindBy(xpath = "//*[@id=\"address1\"]")
	public WebElement address;
	
	@FindBy(xpath = "//*[@id=\"city\"]")
	public WebElement city;
	
	@FindBy(xpath = "//*[@id=\"id_state\"]")
	public WebElement state;
	
	@FindBy(xpath = "//*[@id=\"postcode\"]")
	public WebElement zipcode;
	
	@FindBy(xpath = "//*[@id=\"id_country\"]")
	public WebElement country;
	
	@FindBy(xpath = "//*[@id=\"phone_mobile\"]")
	public WebElement mobilePhone;

	@FindBy(xpath = "//*[@id=\"submitAccount\"]")
	public WebElement registrationButto;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(firstName, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		TestBase.logExtentReport("RegistrationPage created");
		log.info("RegistrationPage created");
	}
	
	public void setRadioButton() {
		mrRadioButton.click();
		TestBase.logExtentReport("mrRadioButton clicked");
		log.info("mrRadioButton clicked");
	}
	
	public void setFirstName(String fName) {
		firstName.sendKeys(fName);
		TestBase.logExtentReport("firstName entered");
		log.info("firstName entered");
	}
	
	public void setLastName(String lName) {
		lastName.sendKeys(lName);
		TestBase.logExtentReport("lastName entered");
		log.info("lastName entered");
	}
	
	public void setPassword(String password) {
		this.password.sendKeys(password);
		TestBase.logExtentReport("password entered");
		log.info("password entered");
	}
	
	public void setDay(String day) {
		List<WebElement> days = driver.findElements(By.xpath("//*[@id=\"days\"]/option"));
		Iterator<WebElement> itr = days.iterator();
		while(itr.hasNext()) {
			WebElement currentDay = itr.next();
			String currentDayText = itr.next().getText().trim();
			if(currentDayText.equals(day)) {
				System.out.println(itr.toString());
				currentDay.click();
				break;
			}
		}
		TestBase.logExtentReport("day entered");
		log.info("day entered");
	}
	
	public void setMonth(String month) {
		List<WebElement> months = driver.findElements(By.xpath("//*[@id=\"months\"]/option"));
		Iterator<WebElement> itr = months.iterator();
		while(itr.hasNext()) {
			WebElement currentMonth = itr.next();
			String currentMonthText = itr.next().getText().trim();
			if(currentMonthText.equals(month)) {
				System.out.println(itr.toString());
				currentMonth.click();
				break;
			}
		}
		TestBase.logExtentReport("month entered");
		log.info("month entered");
	}
	
	public void setYear(String month) {
		List<WebElement> years = driver.findElements(By.xpath("//*[@id=\"years\"]/option"));
		Iterator<WebElement> itr = years.iterator();
		while(itr.hasNext()) {
			WebElement currentYear = itr.next();
			String currentYearText = itr.next().getText().trim();
			if(currentYearText.equals(month)) {
				System.out.println(itr.toString());
				currentYear.click();
				break;
			}
		}
		TestBase.logExtentReport("year entered");
		log.info("year entered");
	}
	
	public void setAddress(String address) {
		this.address.sendKeys(address);
		TestBase.logExtentReport("address entered");
		log.info("address entered");
	}
	
	public void setCity(String city) {
		this.city.sendKeys(city);
		TestBase.logExtentReport("city entered");
		log.info("city entered");
	}
	
	public void setState(String state) {
		List<WebElement> states = driver.findElements(By.xpath("//*[@id=\"id_state\"]/option"));
		Iterator<WebElement> itr = states.iterator();
		while(itr.hasNext()) {
			WebElement currentState = itr.next();
			String currentStateText = itr.next().getText().trim();
			if(currentStateText.equals(state)) {
				System.out.println(itr.toString());
				currentState.click();
				break;
			}
		}
		TestBase.logExtentReport("state entered "+state);
		log.info("state entered "+state);
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode.sendKeys(zipcode);
		TestBase.logExtentReport("zipcode entered");
		log.info("zipcode entered");
	}
	
	public void setPhoneNumber(String phonenumber) {
		this.mobilePhone.sendKeys(phonenumber);
		TestBase.logExtentReport("phonenumber entered");
		log.info("phonenumber entered");
	}

	public MyAccountPage clickRegistrationButton() {
		registrationButto.click();
		TestBase.logExtentReport("registrationButto clicked");
		log.info("registrationButto clicked");
		return new MyAccountPage(driver);
	}

}
