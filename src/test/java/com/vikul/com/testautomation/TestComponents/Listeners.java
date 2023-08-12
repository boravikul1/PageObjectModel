package com.vikul.com.testautomation.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.vikul.com.testautomation.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	public static ExtentTest test;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		extentTest.get().log(Status.PASS, "Test Passed");
		
	}

	@SuppressWarnings("null")
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//ExtentTest test = null;
		ITestListener.super.onTestFailure(result);
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		extentTest.get().fail(result.getThrowable());
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshot(result.getMethod().getMethodName(),driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Screenshot , attach it to the report
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extent.flush();
	}

}
