import org.junit.runner.RunWith;


//    @RunWith(Cucumber.class)
@RunWith(ExternalEnvironment.class)
@cucumber.api.CucumberOptions(
        format = {"pretty", "html:target/cucumber"},
        strict = true,
        features = ("classpath:scenarios/"),
        monochrome = true,
        tags = {"@RGM"}
)

public class RunExternalCucumberTest {

}

