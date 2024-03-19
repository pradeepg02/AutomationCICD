package seleniummaventest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import seleniummaventest.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
    WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	@FindBy(css="[placeholder*='Select Country']")
	WebElement countryDropdown;
	
	@FindBy(css=".ta-results button")
	List<WebElement> countries;
	
	@FindBy(xpath="//div[@class='details__user']/div/div[@class='actions']/a")
	WebElement placeOrderCTA;
	
	public void SelectCountryDropdown(String count)
	{
		Actions a = new Actions(driver);
		a.sendKeys(countryDropdown, count).build().perform();
		for(WebElement country : countries)
			{
				if(country.getText().equalsIgnoreCase(count))
				{
					country.click();
					break;
				}			
			}		
	}
	
	public ConfirmationPage PlaceOrder()
	{
		Actions a = new Actions(driver);
		a.moveToElement(placeOrderCTA).click().build().perform();	
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
}
