package com.uiafw.cn.pn.testbase;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.uiafw.cn.pn.helper.browserconfiguration.BrowserType;
import com.uiafw.cn.pn.helper.browserconfiguration.ChromeBrowser;
import com.uiafw.cn.pn.helper.browserconfiguration.InternetExplorerBrowser;
import com.uiafw.cn.pn.helper.browserconfiguration.config.ObjectReader;
import com.uiafw.cn.pn.helper.browserconfiguration.config.PropertyReader;
import com.uiafw.cn.pn.helper.excel.ExcelDataDriverHelper;
import com.uiafw.cn.pn.helper.logger.LoggerHelper;
import com.uiafw.cn.pn.helper.resource.ResourceHelper;
import com.uiafw.cn.pn.helper.wait.WaitHelper;
import com.uiafw.cn.pn.helper.browserconfiguration.FirefoxBrowser;
import com.uiafw.cn.pn.utils.ExtentManager;

public class TestBase {
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public File reportDirectory; 
	
	@BeforeSuite
	public void beforeSuite() {
		extentReports = ExtentManager.getInstance();
	}
	
	@BeforeTest
	public void beforeTest() {
		ObjectReader.reader= new PropertyReader();
		reportDirectory = new File(ResourceHelper.getResourcePath("\\src\\main\\resources\\screenshots"));
		setupDriver(ObjectReader.reader.getBrowserType());
		extentTest = extentReports.createTest(getClass().getSimpleName());
	}
	
//	@BeforeClass
//	public void beforeClass() {
////		extentTest = extentReports.createTest(getClass().getSimpleName());
//	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		extentTest.log(Status.INFO, method.getName()+" test started");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, result.getThrowable());
//			String imagePath = captureScreen(result.getName(), driver);
//			extentTest.addScreenCaptureFromPath(imagePath);
		} else if(result.getStatus()==ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, result.getName()+" is passed");
//			String imagePath = captureScreen(result.getName(), driver);
//			extentTest.addScreenCaptureFromPath(imagePath);
		} else if(result.getStatus()==ITestResult.SKIP) {
			extentTest.log(Status.PASS, result.getThrowable());
//			String imagePath = captureScreen(result.getName(), driver);
//			extentTest.addScreenCaptureFromPath(imagePath);
		}
		extentReports.flush();
	}
	
	@AfterClass
	public void afterClass() {
		
	}
	
	@AfterTest
	public void afterTest() {
		if(driver!=null) {
			driver.quit();
		}
	}
	@AfterSuite
	public void afterSuite() {
		
	}
	
	public WebDriver getBrowserObject(BrowserType browserType) {
		try {
			switch(browserType) {
			case CHROME:
				ChromeBrowser chrome = new ChromeBrowser();
				ChromeOptions cOptions = chrome.getChromeOptions();
				return chrome.getChromeDriver(cOptions);
			case INTERNETEXPLORER:
				InternetExplorerBrowser internetExplorer = new InternetExplorerBrowser();
				InternetExplorerOptions ieOptions = internetExplorer.getIECapabilites();
				return internetExplorer.getEdgeDriver(ieOptions);
			case FIREFOX:
				FirefoxBrowser firefox = new FirefoxBrowser();
				FirefoxOptions fOptions = firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(fOptions);
			default:
				throw new Exception("Driver not found"+browserType.name());			
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}
	
	public void setupDriver(BrowserType browserType) {
		driver = getBrowserObject(browserType);
		log.info("initialze webdriver: "+driver.toString());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(Duration.ofSeconds(ObjectReader.reader.getImplicitWait()));
		wait.pageLoadTime(Duration.ofSeconds(ObjectReader.reader.getPageLoadTime()));
	}
	
	public String captureScreen(String fileName, WebDriver driver) {
		if(driver == null) {
			log.info("driver is null");
		}
		if(fileName=="") {
			fileName="Blank";
		}
		File destinationFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("_dd_MM_yyyy_hh_mm_ss");
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			destinationFile = new File(reportDirectory+"\\"+fileName+formatter.format(calendar.getTime())+".png");
			Files.copy(sourceFile.toPath(),destinationFile.toPath());
			Reporter.log("<a href='"+destinationFile.getAbsolutePath()+"'><img src='"+destinationFile.getAbsolutePath()+"' style='width:50px;height:50px;'/></a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public void getNavigationScreen(WebDriver driver) {
		log.info("Capturing ui navigation screen");
//		new JavaScriptHelper(driver).zoomInBy50Percent();
		String screen = captureScreen("",driver);
//		new JavaScriptHelper(driver).zoomInBy100Percent();
//		extentTest.addScreenCaptureFromPath(screen);
		
	}
	
	public static void logExtentReport(String s1){
		extentTest.log(Status.INFO,s1);
	}
	
	public void getApplicationUrl(String url) {
		driver.get(url);
		logExtentReport("navigation to "+url);
	}
	
	public Object[][] getExcelDD(String excelFile, String excelSheet){
		String excelLocation = ResourceHelper.getResourcePath("\\src\\main\\resources\\configFile\\")+excelFile;
		log.info(excelLocation);
		ExcelDataDriverHelper eddh = new ExcelDataDriverHelper(); 
		return eddh.getExcelDataDriven(excelLocation, excelSheet);
	}
}
