package com.uiafw.cn.pn.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiafw.cn.pn.helper.assertion.VerificationHelper;
import com.uiafw.cn.pn.helper.browserconfiguration.config.ObjectReader;
import com.uiafw.cn.pn.helper.javascript.JavaScriptHelper;
import com.uiafw.cn.pn.helper.logger.LoggerHelper;
import com.uiafw.cn.pn.helper.wait.WaitHelper;
import com.uiafw.cn.pn.testbase.TestBase;

public class LoginPage {
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	public WaitHelper waitHelper;
	
	@FindBy(xpath = "//a[contains(text(),'Sign in')]")
	public WebElement signin;
	
	@FindBy(xpath = "//*[@id=\"email\"]")
	public WebElement email;
	
	@FindBy(xpath = "//*[@id=\"passwd\"]")
	public WebElement passwd;
	
	@FindBy(xpath = "//*[@id=\"SubmitLogin\"]/span")
	public WebElement submitLogin;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]/p")
	public WebElement successMessageObject;
	
	@FindBy(xpath = "//*[@id=\"email_create\"]")
	public WebElement createNewAccountEmailAddress;
	
	@FindBy(xpath = "//*[@id=\"SubmitCreate\"]")
	public WebElement createNewAccount;
	
	@FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")
	public WebElement logout;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(signin, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
	}
	
	public void clickOnSignInLink() {
		log.info("clicking on sign in link");
		TestBase.logExtentReport("clicking on sign in link");
		signin.click();
	}
	
	public void enterEmailAddress(String emailAddress) {
		log.info("Entering email address");
		TestBase.logExtentReport("Entering email address");
		this.email.sendKeys(emailAddress);
	}
	
	public void enterPassword(String password) {
		log.info("Entering password");
		TestBase.logExtentReport("Entering password");
		this.passwd.sendKeys(password);
	}
	
	public MyAccountPage clickOnSignInButton() {
		log.info("clicking on signin button");
		TestBase.logExtentReport("clicking on signin button");
		new JavaScriptHelper(driver).scrollDownVertically();
		submitLogin.click();
		return new MyAccountPage(driver);
	}
	
	public boolean verifySuccessfulLogin() {
		log.info("element is displaying "+successMessageObject.getText());
		TestBase.logExtentReport("element is displayings "+successMessageObject.getText());
		return new VerificationHelper(driver).isDisplayed(successMessageObject);
	}
	
	public void enterRegistrationEmail() {
		String email = System.currentTimeMillis()+"@gmail.com";
		log.info("entering registration email");
		TestBase.logExtentReport("entering registration email");
		createNewAccountEmailAddress.sendKeys(email);
	}
	
	public RegistrationPage clickOnCreateNewAccount() {
		createNewAccount.click();
		return new RegistrationPage(driver);
	}
	
	public void loginToApplication(String emailAddress, String password) {
		clickOnSignInLink();
		enterEmailAddress(emailAddress);
		enterPassword(password);
		clickOnSignInButton();
//		verifySuccessfulLogin();
		log.info("login to application completed and landed on account page");
		TestBase.logExtentReport("login to application completed and landed on account page");
	}

	public void logoutUser() {
		logout.click();
		log.info("logging out user");
		TestBase.logExtentReport("logging out user");
		waitHelper.waitForElement(signin,ObjectReader.reader.getExplicitWait());
	}
}
