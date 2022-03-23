package com.uiafw.cn.pn.helper.browserconfiguration.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.uiafw.cn.pn.helper.browserconfiguration.BrowserType;
import com.uiafw.cn.pn.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader{
	
	private static FileInputStream file;
	protected static final Properties properties = new Properties();
	public PropertyReader() {
		String filePath = ResourceHelper.getResourcePath("\\src\\main\\resources\\configFile\\config.properties");
		try {
			file = new FileInputStream(new File(filePath));
			properties.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int getImplicitWait() {
		return Integer.parseInt(properties.getProperty("implicitwait"));
	}

	@Override
	public int getExplicitWait() {
		return Integer.parseInt(properties.getProperty("explicitwait"));
	}

	@Override
	public int getPageLoadTime() {
		return Integer.parseInt(properties.getProperty("payloadtime"));
	}

	@Override
	public BrowserType getBrowserType() {
		return BrowserType.valueOf(properties.getProperty("browserType"));
	}

	@Override
	public String getUrl() {
		return properties.getProperty("applicationURL");
	}

	@Override
	public String getUsername() {
		return properties.getProperty("username");
	}

	@Override
	public String getPassword() {
		return properties.getProperty("password");
	}

}
