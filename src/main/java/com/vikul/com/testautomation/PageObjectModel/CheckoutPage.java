package com.vikul.com.testautomation.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vikul.com.testautomation.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Checkout Page Objects
	@FindBy(css="[placeholder='Select Country']") WebElement selectCountry;
	By countryOptions = By.cssSelector(".ta-results");
	@FindBy(xpath ="(//button[contains(@class,'ta-item')])[2]") WebElement clickCountry;
	@FindBy(css=".action__submit") WebElement submitButton;
	@FindBy(css=".hero-primary") WebElement confirmationMsg;
	
	
	//To Select Country from dropdown option
	public void selectCountry()
	{
	Actions a = new Actions(driver);
	a.sendKeys(selectCountry,"India").build().perform();
	waitforElementToAppear(countryOptions);
	clickCountry.click();
	}
	
	//Click on Place Order button
	public void placeOrder()
	{

	submitButton.click();
	}
	
	public String returnConfirmationMessage()
	{
		//Verify Order Confirmation
		String actualConfirmationMessage = confirmationMsg.getText();
		return actualConfirmationMessage;
	}
	

	

}
