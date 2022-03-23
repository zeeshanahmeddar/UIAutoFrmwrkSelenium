package com.uiafw.cn.pn.helper.browserconfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.uiafw.cn.pn.helper.resource.ResourceHelper;

public class ChromeBrowser {
	public ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--incognito");
		DesiredCapabilities chromeDCapabilities = new DesiredCapabilities();
		chromeDCapabilities.setJavascriptEnabled(true);
		chromeDCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(chromeDCapabilities);
		if(System.getProperty("os.name").contains("Linux")) {
			options.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return options;
	}
	
	public WebDriver getChromeDriver(ChromeOptions chromeOptionCapabilities) {
		if(System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("\\src\\main\\resources\\drivers\\chromedriver"));
			return new ChromeDriver(chromeOptionCapabilities);
		} else if(System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.chrome.driver",  ResourceHelper.getResourcePath("\\src\\main\\resources\\drivers\\chromedriver"));
			return new ChromeDriver(chromeOptionCapabilities);
		} else if(System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.chrome.driver",  ResourceHelper.getResourcePath("\\src\\main\\resources\\drivers\\chromedriver.exe"));
			return new ChromeDriver(chromeOptionCapabilities);
		} else {
			return null;
		}
	}
}
