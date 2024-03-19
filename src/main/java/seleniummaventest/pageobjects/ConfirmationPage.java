package seleniummaventest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import seleniummaventest.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;		
	}


	@FindBy(className="hero-primary")
	public WebElement confirmmsg;
	
	public String ConfirmationText()
	{
		return confirmmsg.getText();
	}
}
