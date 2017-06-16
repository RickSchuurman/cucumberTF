/* Cucumber uses Junit framework to run. As Cucumber uses Junit we need to have a Test Runner class.
This class will use the Junit annotation @RunWith(), which tells JUnit what is the test runner class.
It more like a starting point for Junit to start executing your tests. */


import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

//@RunWith annotation tells JUnit that tests should run using Cucumber class.
@RunWith(LocalCucumber.class)

//This annotation tells Cucumber a lot of things like where to look for feature files,
@CucumberOptions(
        // Checks if all the steps have the step definition
        dryRun = false,
        // What all reports formater to use
        format = {"pretty", "html:target/cucumber"},
        // will fail execution if ther are undefined or pending steps
        strict = true,
        // path of the feature files
        features = ("src/test/java/features/"),
        // display the console output in much readable way
        monochrome = true,
        // what tags in the features files should be executed
        tags = {"@RGM"}
)

public class RunLocalCucumberTest {

}

