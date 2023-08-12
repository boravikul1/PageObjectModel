package com.vikul.com.testautomation.PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vikul.com.testautomation.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		//To initialize the PageFactory WebElements
		PageFactory.initElements(driver, this);
	}
	
//	WebElement userEmail =  driver.findElement(By.id("userEmail"));
//	WebElement userPassword = driver.findElement(By.id("userPassword"));
//	WebElement loginBtn = driver.findElement(By.id("login"));
	
	//Page Factory
	@FindBy(id="userEmail") WebElement userEmail;
	@FindBy(id="userPassword") WebElement userPassword;
	@FindBy(id="login") WebElement loginBtn;
	@FindBy(css="[class*='flyInOut']") WebElement errorMessage;
	
	public void login(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();
	}
	
	public String getErrorMessage()
	{
		waitforElementToAppear(errorMessage);
		return errorMessage.getText();
	
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
}

