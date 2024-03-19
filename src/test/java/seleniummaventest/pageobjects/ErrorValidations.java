package seleniummaventest.pageobjects;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import seleniummaventest.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest {


	    @Test //we used test instead of public static void main.. to run everything in testNG instead of JAVA
	    
	    public void POClientErrorValidation() throws IOException
	    {		
		
			landingpage.LoginDetails("sameerrizvi@gmail.com", "Helloworld");
			Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		}
	    
		@Test(groups= {"Error Test"}) // we used test instead of public static void main.. to run everything in testNG instead of JAVA
	    
	    public void POClienttest() throws IOException
	    {		
	    	String productname = "ZARA COAT 3";
			String count = "India";	
		
	    	ProductCatolog productCatalog = landingpage.LoginDetails("rachinravindra@gmail.com", "Helloworld123");  //called the productcatalog object in landing page itself, so to use that, declaring a variable here to call catalog methods
		
	    	productCatalog.getProductList();	
	    	CartPage cartPage = productCatalog.AddProducttoCart(productname); //called the child class because we used extends, even when we call with chid class, it'll work.	
	    	productCatalog.AddtoCart();  		
		
			Boolean match = cartPage.VerifyProductDisplay("Zara Coat 33");
			Assert.assertFalse(match); // Assertion should be always written in test file only, because if any issue comes - It's hard to go there and check(like negative test assertions)
				
	}

	}
