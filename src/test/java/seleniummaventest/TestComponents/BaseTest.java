package seleniummaventest.TestComponents;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import seleniummaventest.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;  //declared here to access teh driver outside if condition also
	public LandingPage landingpage;  //declared a global variable here to run that menthod as testng BeforeMethod
	
	public String productname = "ZARA COAT 3";
	public String count = "India";	
	
	public WebDriver initializeDriver() throws IOException
	{
		/*
		 * The below three lines of code is used to extract code from a global property file - eg: which may used to fetch drivers from properties file if we use different drivers to run the test
		 * I have created a Globaldata(file - not a class) to give that. This should be a file and should have extension .properties, so that we can call that here using properties method
		 * FileInputStream is used because we have to change that file to a input, so we created a class and give that path as an argument
		 */
		Properties prop = new Properties();  
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\seleniummaventest\\resources\\GlobalData.properties"); //System.getproperty(argu) is used to get the system path, so that even if we run all this code in any other system, it'll fetch that path and run
		prop.load(fis);  //To load that properties page
		String browserName = prop.getProperty("browser");  // to get that browser property in properties page
		
		
		if(browserName.equalsIgnoreCase("Chrome"))  //If we want to run in chrome, we can use this
		{
			driver = new ChromeDriver();
			
		}
		else if(browserName.equalsIgnoreCase("Firefox"))  //If we want to run in firefox, we can use this
		{
			//Firefox code
		}
		else if(browserName.equalsIgnoreCase("Edge"))  //If we want to run in edge, we can use this
		{
			//Edge browser code
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		return driver;		
	}
	
	
	/*
	 * The JSON to string and String to Map convertor code is added in Base test here so that we can just call this method in Main test as it extends Base test
	 */
	public List<HashMap<String, String>> getJsontoMap(String filePath) throws IOException
	{    
		//reading Json and coverting JSON to String using Fileutils
		String JsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);  //The File readability format standartcharsets.. is used to restrict deprication.
		
		//Changing the string to hashmap using Jackson databind(This is exteranl as there's no change internal change method)

		  ObjectMapper mapper = new ObjectMapper();   //This is object to change the JSOn to Map
		  List<HashMap<String, String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>() {  //to read value from String and giving that JSON and how we have to convert it  - coverting to list of hashmaps
	      });
		  return data; //returning that data we converted
	}
	
    
    //To get the Screenshot and save it in a file
    public String getScreenshot(String testcaseName, WebDriver driver) throws IOException  //Webdriver driver is used to get the life to the driver inside the body, we are giving that via listeners
    {
    	TakesScreenshot ts = (TakesScreenshot)driver;
    	File source = ts.getScreenshotAs(OutputType.FILE);
    	File file = new File(System.getProperty("user.dir") + "\\reports" + testcaseName + ".png");
    	FileUtils.copyFile(source, file);
    	return System.getProperty("user.dir") + "\\reports" + testcaseName + ".png";
    }
    
	@BeforeMethod  // (alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingpage = new LandingPage(driver); //Creating the child page object here to give this driver to constructor  -- Instead of putting this in test file, I did it here bcoz anyway we gonna launch application here
		landingpage.goTo();
		return landingpage;  //returning this here because we can use that in the real test
	}
	
	@AfterMethod  //(alwaysRun=true)  //alwaysrun=true is used to always run this test even we run the test in goroups, if don't give this - Testng will not consider this to run
	public void CloseDriver()
	{
		driver.close();
	}
}
