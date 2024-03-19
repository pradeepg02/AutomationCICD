package seleniummaventest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import seleniummaventest.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	public List<WebElement> cartName;
	
	@FindBy(css=".subtotal button")
	WebElement Checkout;
	
	
//	List<WebElement> cartName = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
//	Boolean match = cartName.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productname));
//	Assert.assertTrue(match);
//	driver.findElement(By.cssSelector(".subtotal button")).click();
	
	public Boolean VerifyProductDisplay(String productname)
	{
		Boolean match = cartName.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	public CheckoutPage Checkout()
	{
		Checkout.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}

}
