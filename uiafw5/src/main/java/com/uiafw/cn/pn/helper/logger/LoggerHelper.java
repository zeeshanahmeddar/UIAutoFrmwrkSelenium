package com.uiafw.cn.pn.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uiafw.cn.pn.helper.resource.ResourceHelper;

public class LoggerHelper {
	private static boolean root = false;
	public static Logger getLogger(Class<?> cls) {
		if(root) {
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure(ResourceHelper.getResourcePath("/src/main/resources/configFile/log4j.properties"));
		root = true;
		return Logger.getLogger(cls);
	}
	public static void main(String[] args) {		
		Logger logger = LoggerHelper.getLogger(LoggerHelper.class);
		logger.info("This is my first log4j's statement");
	}
}
