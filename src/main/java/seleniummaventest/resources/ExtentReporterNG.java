package seleniummaventest.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getobject()
	{
		String path = System.getProperty("user.dir")+"\\reports\\index.html"; 
		ExtentSparkReporter report = new ExtentSparkReporter(path);  
		report.config().setReportName("Selenium Automation Reports");
		report.config().setDocumentTitle("Test Reports");
		
		ExtentReports extent = new ExtentReports(); //main class to create reports
		extent.attachReporter(report);  //to attach the helper class object name
		extent.setSystemInfo("Tester", "Pradeep");
		return extent;  //returning this to catch this in listeners file for execution before every test
		
	}

}
