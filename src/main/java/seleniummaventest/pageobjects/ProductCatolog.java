package seleniummaventest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniummaventest.AbstractComponents.AbstractComponent;

public class ProductCatolog extends AbstractComponent {
	
WebDriver driver;
	
	public ProductCatolog(WebDriver driver) {  //Constructor to get that driver from the parent page - There we created an object and gave an argument
		
		super(driver); //used to send the driver to parent(AbstractComponent), we have code this all child classes
		this.driver=driver; //
		PageFactory.initElements(driver, this);  //Optional it seems - works without this code too(not sure might be due to Child-parent)
	}
	
	
	//Page Factory
	@FindBy(css=".mb-3")   //This is page factory method - we can find that element just by @FindBy and we have give that locator and path
	List<WebElement> products;  //initialing the name of that element
	
	@FindBy(css="#toast-container")   //This is page factory method - we can find that element just by @FindBy and we have give that locator and path
	WebElement disappeartoast;  	
	
	
	By ProductBy = By.cssSelector(".mb-3");
	
	public List<WebElement> getProductList()
	{
		waitforElementtoAppear(ProductBy);
		return products;
		
	}
	
	public WebElement getProductbyName(String productname)
	{
		WebElement item = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		return item;
	}
	
	public CartPage AddProducttoCart(String productname)
	{
		WebElement item = getProductbyName(productname);
		item.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitforElementtoDisappear(disappeartoast);
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
}
