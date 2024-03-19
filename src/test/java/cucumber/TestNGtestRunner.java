package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue="seleniummaventest.stepDefenitions", monochrome=true,
tags = "@Regression", plugin= {"html:target/cucumber.html"})
public class TestNGtestRunner extends AbstractTestNGCucumberTests {  //have to extend this AbstractTestNGCucumberTests to run testNG assertions and other things

}