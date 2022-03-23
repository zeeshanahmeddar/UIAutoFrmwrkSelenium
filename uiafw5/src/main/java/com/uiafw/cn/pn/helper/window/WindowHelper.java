package com.uiafw.cn.pn.helper.window;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.uiafw.cn.pn.helper.logger.LoggerHelper;

public class WindowHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WindowHelper.class);
	public WindowHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public void switchToParentWindow() {
		driver.switchTo().defaultContent();
		log.info("switched to parent window");
	}
	
	public void switchToWindow(int index) {
		Set<String> windows = driver.getWindowHandles();
		int i = 0;
		for(String window : windows) {
			if (i==index) {
				driver.switchTo().window(window);
				log.info("switched to "+index+" window");
			} else {
				i++;
			}
		}
	}
	
	public void closeAllTabsAndSwitchToMainWindow() {
		String mainWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		
		for(String window : windows) {
			if (!mainWindow.equalsIgnoreCase(window)) {
				driver.close();
			}
			log.info("switched to main window");
			driver.switchTo().window(mainWindow);
		}
	}
	
	public void navigateBack() {
		log.info("navigating back");
		driver.navigate().back();
	}
	
	public void navigateForward() {
		log.info("navigating forward");
		driver.navigate().forward();
	}

}
