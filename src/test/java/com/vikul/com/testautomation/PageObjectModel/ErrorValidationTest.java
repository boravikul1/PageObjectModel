package com.vikul.com.testautomation.PageObjectModel;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.vikul.com.testautomation.TestComponents.BaseTest;
import com.vikul.com.testautomation.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {
	
	String username = "boratest@gmail.com";
	String password = "Password1";
	
	@Test(retryAnalyzer = Retry.class)
	public void incorrectLoginValidation()
	{
		landingPage.login(username, password);
		log.info("Login successful.");
		//Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		log.info("Error Message validated.");
		
	}

}
