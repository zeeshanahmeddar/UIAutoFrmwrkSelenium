package com.uiafw.cn.pn.helper.browserconfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.uiafw.cn.pn.helper.resource.ResourceHelper;

public class FirefoxBrowser {
	public FirefoxOptions getFirefoxOptions() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);
		DesiredCapabilities firefox = new DesiredCapabilities();
		firefox.setCapability(FirefoxDriver.Capability.PROFILE, profile);
		firefox.setCapability(FirefoxDriver.Capability.MARIONETTE, true);
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.merge(firefox);
		if(System.getProperty("os.name").contains("Linux")) {
			firefoxOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return firefoxOptions;
	}
	
	public WebDriver getFirefoxDriver(FirefoxOptions firefoxOptionCapabilities) {
		if(System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.gecko.driver", ResourceHelper.getResourcePath("\\src\\main\\resources\\drivers\\geckodriver"));
			return new FirefoxDriver(firefoxOptionCapabilities);
		}
		else if(System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.gecko.dirver",  ResourceHelper.getResourcePath("\\src\\main\\resources\\drivers\\geckodriver"));
			return new FirefoxDriver(firefoxOptionCapabilities);
		}
		else if(System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.gecko.dirver",  ResourceHelper.getResourcePath("\\src\\main\\resources\\drivers\\geckodriver.exe"));
			return new FirefoxDriver(firefoxOptionCapabilities);
		} else {
			return null;
		}
	}
	

}
