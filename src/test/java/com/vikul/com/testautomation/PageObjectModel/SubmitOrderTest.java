package com.vikul.com.testautomation.PageObjectModel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;

import com.vikul.com.testautomation.TestComponents.BaseTest;
public class SubmitOrderTest extends BaseTest{  
	
//	String productName = "iphone 13 pro";
//	String username = "boratest@gmail.com";
//	String password = "P@ssword1";
	String confirmationMessage = "THANKYOU FOR THE ORDER.";
	

//	@Test(dataProvider = "getData")
//	public void submitOrder(String username,String password, String productName) throws InterruptedException, IOException {
//		// TODO Auto-generated method stub
//		//Commenting it as it will be called through @BeforeMethod annotation
//		//LandingPage landingPage = launchApplication();
//		landingPage.login(username, password);
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
//		//List<WebElement> products =  productCatalogue.getProductList();
//		productCatalogue.addProductToCart(productName);
//		Thread.sleep(3000);
//		productCatalogue.clickOnCartButton();
//		CartPage cartPage = new CartPage(driver);
//		Boolean match = cartPage.checkCart(productName);
//		Assert.assertTrue(match);
//		cartPage.clickOnCheckoutButton();
//		CheckoutPage checkoutpage = new CheckoutPage(driver);
//		checkoutpage.selectCountry();
//		checkoutpage.placeOrder();
//		String actualConfirmationMessage = checkoutpage.returnConfirmationMessage();
//		AssertJUnit.assertTrue(actualConfirmationMessage.equalsIgnoreCase(confirmationMessage));
//	}
	
	//Using HashMap
	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//Commenting it as it will be called through @BeforeMethod annotation
		//LandingPage landingPage = launchApplication();
		landingPage.login(input.get("username"), input.get("password"));
		log.info("Login successful.");
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		//List<WebElement> products =  productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		log.info("Product added to the Cart.");
		Thread.sleep(3000);
		productCatalogue.clickOnCartButton();
		log.info("Cart button clicked successfully.");
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.checkCart(input.get("productName"));
		Assert.assertTrue(match);
		log.info("Cart Validation successful.");
		cartPage.clickOnCheckoutButton();
		log.info("Clicked on Checkout Button.");
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		checkoutpage.selectCountry();
		log.info("Country selected from Dropdown");
		checkoutpage.placeOrder();
		log.info("Order placed successfully");
		String actualConfirmationMessage = checkoutpage.returnConfirmationMessage();
		Assert.assertTrue(actualConfirmationMessage.equalsIgnoreCase(confirmationMessage));
		log.info("Confirmation Message for order placement validated.");
	}
	
	
//	@Test(dataProvider = "getData", dependsOnMethods = {"submitOrder"})
//	public void checkOrderHistory(String username, String password, String productName)
//	{ 
//		landingPage.login(username, password);
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
//		productCatalogue.clickOnOrdersTab();
//		//Assert.assertTrue();
//	} 
	
	@Test(dataProvider = "getData", dependsOnMethods = {"submitOrder"})
	public void checkOrderHistory(HashMap<String,String> input)
	{ 
		landingPage.login(input.get("username"), input.get("password"));
		log.info("Login successful.");
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.clickOnOrdersTab();
		log.info("Clicked on orders Tab.");
		
		//Assert.assertTrue();
	} 
	
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("username", "boratest@gmail.com");
//		map.put("password", "P@ssword1");
//		map.put("productName", "iphone 13 pro");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("username", "boratest@gmail.com");
//		map1.put("password", "P@ssword1");
//		map1.put("productName", "ZARA COAT 3");
//		
//		
//		return new Object[][] {{map},{map1}};
//	}
	
	//DataProvider reading from Json
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data =  getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//com//vikul//com//testautomation//data//PurchaseOrder.json");
		log.info("Data fetched successfully from getData() method.");
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
		
	}

}
