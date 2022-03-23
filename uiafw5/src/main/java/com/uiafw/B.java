package com.uiafw;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uiafw.cn.pn.testbase.TestBase;

public class B extends TestBase{
	int i = 1;
	@Test
	public void bLogin() {
		if(i==3) {
			System.out.println("test will pass"+i);
			Assert.assertTrue(true);
			++i;
		} else {
			System.out.println("test will fail"+i);
			++i;
			Assert.assertTrue(false);
		}
	}

}
