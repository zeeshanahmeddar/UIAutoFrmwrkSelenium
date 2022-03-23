package com.uiafw.cn.pn.helper.browserconfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.ElementScrollBehavior;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.uiafw.cn.pn.helper.resource.ResourceHelper;

public class InternetExplorerBrowser {
	
	public InternetExplorerOptions getIECapabilites() {
		DesiredCapabilities ieDesiredCapabilities = new DesiredCapabilities();
		ieDesiredCapabilities.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
		ieDesiredCapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		ieDesiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		ieDesiredCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		ieDesiredCapabilities.setJavascriptEnabled(true);
		return new InternetExplorerOptions(ieDesiredCapabilities);
	}
	
	public WebDriver getEdgeDriver(InternetExplorerOptions ieOptionCapabilities) {
		
		if(System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.ie.driver", ResourceHelper.getResourcePath("\\src\\main\\resources\\drivers\\IEDriverServer"));
			return new InternetExplorerDriver(ieOptionCapabilities);
		} else if(System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.ie.driver", ResourceHelper.getResourcePath("\\src\\main\\resources\\drivers\\IEDriverServer.exe"));
			return new InternetExplorerDriver(ieOptionCapabilities);
		} else {
			return null;
		}
	}
	
}
