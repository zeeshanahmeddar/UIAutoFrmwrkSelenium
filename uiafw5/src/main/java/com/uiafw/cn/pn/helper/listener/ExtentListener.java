package com.uiafw.cn.pn.helper.listener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.uiafw.cn.pn.helper.logger.LoggerHelper;
import com.uiafw.cn.pn.utils.ExtentManager;

public class ExtentListener implements ITestListener{

	private Logger log = LoggerHelper.getLogger(ExtentListener.class);
	public static ExtentReports extent;
//	public static ExtentTest test;
	@Override
	public void onTestStart(ITestResult result) {
//		test.log(Status.INFO, result.getName()+" started");
		Reporter.log(result.getMethod().getMethodName()+" test started");
		log.info(result.getMethod().getMethodName()+" test started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
//		test.log(Status.INFO, result.getName()+" succeeded");
		Reporter.log(result.getMethod().getMethodName()+" test passed");
		log.info(result.getMethod().getMethodName()+" test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
//		test.log(Status.FAIL, result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+" test falied "+result.getThrowable());
		log.error(result.getMethod().getMethodName()+" test falied "+result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
//		test.log(Status.SKIP, result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+" test skipped "+result.getThrowable());
		log.warn(result.getMethod().getMethodName()+" test skipped "+result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		extent = ExtentManager.getInstance();
//		test = extent.createTest(context.getName());
		Reporter.log(context.getName()+" started");
		log.info(context.getName()+" started");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		Reporter.log(context.getName()+" finished");
		log.info(context.getName()+" finished");
	}

}
