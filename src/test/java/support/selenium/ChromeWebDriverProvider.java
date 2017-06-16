package support.selenium;

import com.codeborne.selenide.WebDriverProvider;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ChromeWebDriverProvider implements WebDriverProvider {

    private final static Logger log = Logger.getLogger(ChromeWebDriverProvider.class.getName());

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        log.info("Configuration.browser.name=" + Configuration.browser);
        log.info("Configuration.browser.size=" + Configuration.browserSize);
        log.info("Configuration.browser.startMaximized=" + Configuration.browserStartMaximized);
        log.info("Configuration.browser.leaveOpen=" + Configuration.browserLeaveOpen);

        if (System.getProperty("webdriver.chrome.driver") == null) {
            System.setProperty("webdriver.chrome.driver", Configuration.chromeDriverLocation);
        }

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = createChromeOptions();
        options.addArguments("--ignore-certificate-errors");

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        WebDriver driver = new ChromeDriver(capabilities);

        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

        return driver;
    }

    private ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (!StringUtils.isEmpty(Configuration.chromeAppLocation)) {
            options.setBinary(Configuration.chromeAppLocation);
        }

        return options;
    }
}
