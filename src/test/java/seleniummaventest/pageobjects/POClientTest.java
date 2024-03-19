package seleniummaventest.pageobjects;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import seleniummaventest.TestComponents.BaseTest;

public class POClientTest extends BaseTest {


	    @Test(dataProvider="getData", groups="Purchase")  //we used test instead of public static void main.. to run everything in testNG instead of JAVA
	    
	    public void POClienttest(HashMap<String,String> input) throws IOException
	    {			    	
		
	    	ProductCatolog productCatalog = landingpage.LoginDetails(input.get("email"), input.get("password"));  //called the productcatalog object in landing page itself, so to use that, declaring a variable here to call catalog methods
		
	    	productCatalog.getProductList();	
	    	CartPage cartPage = productCatalog.AddProducttoCart(input.get("product")); //called the child class because we used extends, even when we call with chid class, it'll work.	
	    	productCatalog.AddtoCart();  		
		
			Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
			Assert.assertTrue(match); // Assertion should be always written in test file only, because if any issue comes - It's hard to go there and check(like negative test assertions)
			CheckoutPage checkoutPage = cartPage.Checkout();
		
			checkoutPage.SelectCountryDropdown(count);
			ConfirmationPage confirmationPage = checkoutPage.PlaceOrder();

			String confirmmsg = confirmationPage.ConfirmationText();
			Assert.assertTrue(confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
				
	}
	    
	    @Test(dependsOnMethods = {"POClienttest"})
	    public void OrderHistoryTest()
	    {
	    	ProductCatolog productCatalog = landingpage.LoginDetails("sameerrizvi@gmail.com", "Helloworld123");
	    	OrderPage orderPage = productCatalog.GotoOrders();
	    	Assert.assertTrue(orderPage.VerifyOrderDisplay(productname));
	    	
	    }
	
	    
	    @DataProvider    //Using DataProvider, we are sending different data to the tests and executing all of them
	    public Object[][] getData() throws IOException
	    {   	
	    	
	    	List<HashMap<String, String>> data = getJsontoMap(System.getProperty("user.dir")+"\\src\\test\\java\\seleniummaventest\\data\\Purchaseorder.json");	    	
	    	return new Object[][] {{data.get(0)}, {data.get(1)}};
	    	
	    }
	    
	    /*
	     * ASSIGNING IN HASH MAP FORMAT
	     * HashMap<String,String> map = new HashMap<String,String>();  //Instead of sending in 2d array, sending in Hashmap so that if we have many argument, it'll not looks messy
//	    	map.put("email", "sameerrizvi@gmail.com");
//	    	map.put("password", "Helloworld123");
//	    	map.put("product", "ZARA COAT 3");
//	    	
//	    	HashMap<String,String> map1 = new HashMap<String,String>();  //Instead of sending in 2d array, sending in Hashmap so that if we have many argument, it'll not looks messy
//	    	map1.put("email", "rachinravindra@gmail.com");
//	    	map1.put("password", "Helloworld123");
//	    	map1.put("product", "ADIDAS ORIGINAL");
 * 
 *          return new Object[][] {{map}, {map1}};
	     */
	    
/* 2D Array format
 *     @DataProvider    //Using DataProvider, we are sending different data to the tests and executing all of them
//	    public Object[][] getData()
//	    { 
//	    	//return new Object[][] {{"sameerrizvi@gmail.com", "Helloworld123", "ZARA COAT 3"}, {"rachinravindra@gmail.com","Helloworld123","ADIDAS ORIGINAL"}};  - This is using 2d array
	    } */
	
}
