package com.uiafw.cn.pn.helper.browserconfiguration.config;

import com.uiafw.cn.pn.helper.browserconfiguration.BrowserType;

public interface ConfigReader {
	public int getImplicitWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();
	public String getUrl();
	public String getUsername();
	public String getPassword();
}
