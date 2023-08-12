package testautomation.CucumberFrameWork;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/testautomation/CucumberFrameWork",glue="/testautomation/stepdefinition",
monochrome=true,tags="@ErrorValidation or @SubmitOrder",plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{ 
	
}
