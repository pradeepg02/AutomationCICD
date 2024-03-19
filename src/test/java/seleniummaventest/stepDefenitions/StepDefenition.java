package seleniummaventest.stepDefenitions;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniummaventest.TestComponents.BaseTest;
import seleniummaventest.pageobjects.CartPage;
import seleniummaventest.pageobjects.CheckoutPage;
import seleniummaventest.pageobjects.ConfirmationPage;
import seleniummaventest.pageobjects.LandingPage;
import seleniummaventest.pageobjects.ProductCatolog;

public class StepDefenition extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatolog productCatalog;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException
	{
		landingpage = launchApplication();		
	}
	
	//^ and $ should be used in beginning and ending if we input data inside that was coming from feature file
	@Given("^Logged in with username (.+) and password (.+)$")  //.+ is used in the place where name and password we are passing through cucumber table - we have to use to show this is regular exp and that will catch that.
	public void Login_with_username_and_password(String username, String password)
	{
		productCatalog = landingpage.LoginDetails(username,password);  //remover testng way and implemented normal way of cucumber
	}
	
	@When("^I add product (.+) to Cart$")
	public void Adding_product_to_Cart(String productName) throws InterruptedException
	{
		productCatalog.getProductList();	
    	cartPage = productCatalog.AddProducttoCart(productName);
	}
	
	@And("^Checkout (.+) and submit the Order$")
	public void Checkout_and_submit_the_Order(String productName) throws InterruptedException
	{	
		productCatalog.AddtoCart();  	
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match); // Assertion should be always written in test file only, because if any issue comes - It's hard to go there and check(like negative test assertions)
		checkoutPage = cartPage.Checkout();
		checkoutPage.SelectCountryDropdown(count);
		confirmationPage = checkoutPage.PlaceOrder();
	}
	
//	Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page
	@Then("{string} message is displayed on the confirmation page")  //If data is coming from same steps in cucumber - we are cathing it via {string} - it will come directly. If it comes from examples like last few steps - we should add ^$ and(.+) to get it
	public void message_displayed_in_confirmation_page(String string)  //getting that string and pu
	{
		String confirmmsg = confirmationPage.ConfirmationText();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase(string));	
		driver.close();
	}
	

}
