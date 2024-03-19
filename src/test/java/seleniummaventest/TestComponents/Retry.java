package seleniummaventest.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	
	int count = 0;
	int maxTry = 2;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxTry)  //just a normal method, if count was less than maxtry, it will re run. We have to give this in the text we want to rerun
		{
			count++;  
			return true;
		}
		return false;
		
		//  retryAnalyzer= Retry.class - we have to give this in that @test we have to run two times -- Retry is the class of this file
	}

}
