package com.vikul.com.testautomation.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class Retry extends Listeners implements IRetryAnalyzer{
	
	int count=0;
	int maxTry=1;

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.FAIL, "Retry");
		if(count<maxTry)
		{
			count++;
			return true;
			
		}
		
		return false;
	}

	
}
