package support.selenium;

import com.codeborne.selenide.WebDriverProvider;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class FirefoxWebDriverProvider implements WebDriverProvider {
    private final static Logger log = Logger.getLogger(FirefoxWebDriverProvider.class.getName());

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        log.info("Configuration.browser.name=" + Configuration.browser);

        WebDriver driver;

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        FirefoxProfile profile = new FirefoxProfile();
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);

        if (Configuration.marionette) {
            if (System.getProperty("webdriver.gecko.driver") == null) {
                System.setProperty("webdriver.gecko.driver", Configuration.geckoDriverLocation);
            }
            capabilities.setCapability("marionette", true);
        }
        else {
            capabilities.setCapability("marionette", false);
        }

        if (!StringUtils.isEmpty(Configuration.firefoxAppLocation)) {
            FirefoxBinary firefoxBinary = new FirefoxBinary(new File(Configuration.firefoxAppLocation));
            driver = new FirefoxDriver(firefoxBinary, null, capabilities);
        } else {
            driver = new FirefoxDriver(capabilities);
        }

        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        return driver;
    }
}
