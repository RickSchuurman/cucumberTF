package support.selenium;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class InternetExplorerWebDriverProvider implements WebDriverProvider {
    private final static Logger log = Logger.getLogger(InternetExplorerWebDriverProvider.class.getName());

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        log.info("Configuration.browser.name=" + Configuration.browser);

        WebDriver driver;

        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

        if (System.getProperty("webdriver.ie.driver") == null) {
            System.setProperty("webdriver.ie.driver", Configuration.internetExplorerDriverLocation);
        }

        driver = new InternetExplorerDriver(capabilities);

        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

        return driver;
    }
}
