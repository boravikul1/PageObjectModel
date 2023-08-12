package testautomation.stepdefinition;

import java.io.IOException;

import org.testng.Assert;

import com.vikul.com.testautomation.PageObjectModel.CartPage;
import com.vikul.com.testautomation.PageObjectModel.CheckoutPage;
import com.vikul.com.testautomation.PageObjectModel.LandingPage;
import com.vikul.com.testautomation.PageObjectModel.ProductCatalogue;
import com.vikul.com.testautomation.TestComponents.BaseTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage checkoutpage;
	
	@Given("I landed on eCommerce Page")
	public void I_landed_on_eCommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}

	
	@Given("^logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password)
	{
		landingPage.login(username, password);
		
	}
	
	@When("^I add product (.+) to Cart$")
	public void add_product_to_cart(String productName)
	{
		productCatalogue = new ProductCatalogue(driver);
		//List<WebElement> products =  productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

    @And("^checkout product (.+) and submit order$")
    public void checkout_product_and_submit_order(String productName)
    {
		productCatalogue.clickOnCartButton();
		cartPage = new CartPage(driver);
		Boolean match = cartPage.checkCart(productName);
		Assert.assertTrue(match);
		cartPage.clickOnCheckoutButton();
		checkoutpage = new CheckoutPage(driver);
		checkoutpage.selectCountry();
		checkoutpage.placeOrder();

    }
    //Then "THANKYOU FOR THE ORDER." message is displayed
    
@Then("{string} message is displayed")
public void message_is_displayed(String string)
{
		String actualConfirmationMessage = checkoutpage.returnConfirmationMessage();
		Assert.assertTrue(actualConfirmationMessage.equalsIgnoreCase(string));
		driver.close();
}

@Then("^\"([^\"]*)\" error message is displayed$")
public void error_message_is_displayed(String strArg1) throws Throwable {
	Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	driver.close();
}
}
