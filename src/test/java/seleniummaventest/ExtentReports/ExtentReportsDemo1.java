package seleniummaventest.ExtentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsDemo1 {
	
	ExtentReports extent; //declared outside to use in other test files also

	@BeforeTest
	public void initialConfig()
	{
		String path = System.getProperty("user.dir")+"\\reports\\index.html";  //we have to give the path where we have to save the report
		ExtentSparkReporter report = new ExtentSparkReporter(path);  //This will find a path to store the report and responsible for creating the report for the test. It's a helper class to create the config
		report.config().setReportName("Selenium Automation Reports");
		report.config().setDocumentTitle("Test Reports");
		
		extent = new ExtentReports(); //main class to create reports
		extent.attachReporter(report);  //to attach the helper class object name
		extent.setSystemInfo("Tester", "SeleniumReports");  //To set the name and Tester who ran the test
		
	}

	
	@Test
	public void initialDemo()
	{
		ExtentTest test = extent.createTest("Initial Demo");  //This is the test that was created to run
		WebDriver driver = new ChromeDriver();
		driver.get("https://experiencealula.com/");
		System.out.println(driver.getTitle());
		test.fail("Do not Match");  //To report fail the test by ourself to extent report
		
		extent.flush();
		
	}
}
