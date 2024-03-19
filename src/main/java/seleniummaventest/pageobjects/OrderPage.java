package seleniummaventest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import seleniummaventest.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	@FindBy(css="tr td:nth-child(3)")
	public List<WebElement> ProductList;
	
	
//	List<WebElement> cartName = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
//	Boolean match = cartName.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productname));
//	Assert.assertTrue(match);
//	driver.findElement(By.cssSelector(".subtotal button")).click();
	
	public Boolean VerifyOrderDisplay(String productname)
	{
		Boolean match = ProductList.stream().anyMatch(order->order.getText().equalsIgnoreCase(productname));
		return match;
	}
	

}
