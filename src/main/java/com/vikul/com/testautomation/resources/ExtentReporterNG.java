package com.vikul.com.testautomation.resources;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	

	public static ExtentReports getReportObject()
	{
		//ExtentReports, ExtentSparkReporter
				String path = System.getProperty("user.dir")+"\\Reports\\index.html";
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				reporter.config().setReportName("Web Automation Results");
				reporter.config().setDocumentTitle("Test Results");
				
				ExtentReports extent = new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Vikul Bora", "Sr. Technical Lead");
				return extent;
	}
}
