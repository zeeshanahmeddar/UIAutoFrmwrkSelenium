package com.uiafw.cn.pn.testscripts.productdetailspage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.uiafw.cn.pn.helper.browserconfiguration.config.ObjectReader;
import com.uiafw.cn.pn.helper.logger.LoggerHelper;
import com.uiafw.cn.pn.pageobject.NavigationMenu;
import com.uiafw.cn.pn.pageobject.ProductCategoryPage;
import com.uiafw.cn.pn.testbase.TestBase;

public class VerifyLowestFirstPriceSortTest extends TestBase{
	public final Logger log = LoggerHelper.getLogger(VerifyLowestFirstPriceSortTest.class);
	
	@Test
	public void verifySortByLowestPricesFirst() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		NavigationMenu navigationMenu = new NavigationMenu(driver);
		ProductCategoryPage pageCategoryPage = navigationMenu.clickOnMenu(navigationMenu.womenMenu);
		pageCategoryPage.selectSortByDropDown("Price: Lowest first");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		pageCategoryPage.lowToHighPrices();
		
	}
}
