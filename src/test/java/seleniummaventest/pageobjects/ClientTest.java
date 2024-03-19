package seleniummaventest.pageobjects;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ClientTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		String buy = "ZARA COAT 3";
		
		driver.findElement(By.id("userEmail")).sendKeys("sameerrizvi@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Helloworld123");
		driver.findElement(By.id("login")).click();
		
		//The below code is using CSS selector, another one using x path
//		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//		WebElement item = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
//		item.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']"));
		WebElement item = products.stream().filter(product->product.findElement(By.xpath("//h5/b")).getText().equals(buy)).findFirst().orElse(null);
		item.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		String count = "India";
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartName = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match = cartName.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(buy));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".subtotal button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder*='Select Country']")), "India").build().perform();
		
		int i;
		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button"));
		for(WebElement country : countries)
		{
			if(country.getText().equalsIgnoreCase(count))
			{
				country.click();
				break;
			}			
		}
		
		a.moveToElement(driver.findElement(By.xpath("//div[@class='details__user']/div/div[@class='actions']/a"))).click().build().perform();
		
		String confirmmsg = driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();		
		
	}

}
