package com.vikul.com.testautomation.PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vikul.com.testautomation.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	//WebElements
	@FindBy(css = ".cartSection h3") List<WebElement> cartProducts;
	@FindBy(css = ".totalRow button") WebElement checkoutBtn ;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean checkCart(String productName)
	{
		Boolean match = (cartProducts).stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public void clickOnCheckoutButton()
	{
		checkoutBtn.click();
	}

	

}
