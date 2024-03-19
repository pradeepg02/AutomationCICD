package seleniummaventest.TestComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import seleniummaventest.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getobject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();  //Thread safe - It'll run each test in seperate thread so that one method will not get affected with another one when we run tests in parallel
	
	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName()); //result.get... is used to get that testcase name
		extentTest.set(test); //unique thread id - to run all thread with unqiue ids
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.get().log(Status.PASS, "Test is passed"); // Some issue  //To get the log's status and printing it in Report
	}

	@Override
	public void onTestFailure(ITestResult result){
		
		extentTest.get().fail(result.getThrowable()); //To get the error so we used throwable
		//extenttest.get() is used to get that method we want to get, We are placing whereever we need output to resolve concurrency issues
		/*
		 * From the below try catch method - we are getting the driver that was used inside Screenshot
		 * result is argument
		 * .gettestclass - to get information about the testclass in the .xml file
		 * .getrealclass - to get the info about the real class used inside the test
		 * .getfield is used to get the driver field inside the class
		 * To print any error if we get, we are using catch(exception e1) - It'll print the error
		 */
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);  //to get the screenshot - getScreenshot method is extended from basetest - It was throwing try/catch exception, if we click on that - it'll automatically change like this
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());  //to add the screenshot to the report
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
		
	}

}


