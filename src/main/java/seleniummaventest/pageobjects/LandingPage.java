package seleniummaventest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniummaventest.AbstractComponents.AbstractComponent;


public class LandingPage extends AbstractComponent {
	
WebDriver driver;
	
	public LandingPage(WebDriver driver) {  //Constructor to get that driver from the parent page - There we created an object and gave an argument
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);  //Optional it seems - works without this code too(not sure might be due to Child-parent)
	}
	
	/*
	 * WORK OF CONSTRUCTOR IN CALLING THE DRIVER
	 * 1. Using Constructor, we are passing that webdriver in the testpage to here, that's y WebDriver driver in arguments
	 * 2. this.driver=driver; is used to give that value to the driver mentioned outside the constructor
	 * 3. Using WeDriver driver; - that was mentioned outside - it will use that to provide for entire page
	 * 4. In the test page, object creating - we'll give the driver as argument - from there it comes here
	 */
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//Page Factory
	@FindBy(id="userEmail")   //This is page factory method - we can find that element just by @FindBy and we have give that locator and path
	WebElement userEmail;  //initialing the name of that element
	
	@FindBy(id="userPassword")  //different types of locator name to be given here can be found when we click on go into FindBy 
	WebElement userPassword; 
	
	@FindBy(id="login")  
	WebElement submit; 
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement ErrorMessage;
	
	public ProductCatolog LoginDetails(String email, String password) {
		
		userEmail.sendKeys(email); //using that declared name in page factory, we can call that here and use click or gettext etc..
		userPassword.sendKeys(password);
		submit.click();
		
		ProductCatolog productCatalog = new ProductCatolog(driver);  //instead of creating object in test file, we are creating in Page Object file only, so it'll minimize the code and returning them
		return productCatalog;
		
	}
	
	public String getErrorMessage()
	{
		waitforWebElementtoAppear(ErrorMessage);
		return ErrorMessage.getText();
	}
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}

}
