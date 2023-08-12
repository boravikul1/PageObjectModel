package com.vikul.com.testautomation.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vikul.com.testautomation.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		//To initialize the PageFactory WebElements
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	@FindBy(css=".mb-3") List<WebElement> products;
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	@FindBy(css=".mb-3") WebElement loader;
	@FindBy(css="[routerlink*='/dashboard/cart']") WebElement cartButton;
	@FindBy(css="[routerlink*='/dashboard/myorders']") WebElement ordersTab;
	
	
	
	public List<WebElement> getProductList()
	{
		waitforElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitforElementToAppear(toastMessage);
		waitforElementToDisappear(toastMessage);
		//waitforElementToDisappear(loader);
	}
	
	public void clickOnCartButton()
	{
		waitforElementToAppear(cartButton);
		waitforElementToBeClickable(cartButton);
		cartButton.click();
	}
	
	public void clickOnOrdersTab()
	{
		waitforElementToAppear(ordersTab);
		waitforElementToBeClickable(ordersTab);
		ordersTab.click();
	}
	
	
	

}

