package support.cucumber;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import support.selenium.Configuration;

import java.io.IOException;


public class Hooks {


    private Scenario scenario;

    @Before()
    public void before(final Scenario scenario) throws IOException {
        this.scenario = scenario;
        Configuration.setWebdriverProvider();


    }


}
