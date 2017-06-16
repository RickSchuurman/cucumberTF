package support.selenium;

import com.codeborne.selenide.WebDriverProvider;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.openqa.selenium.Proxy.ProxyType.MANUAL;
import static org.openqa.selenium.remote.CapabilityType.PROXY;

public class RemoteWebDriverProvider implements WebDriverProvider {

    private final static Logger log = Logger.getLogger(RemoteWebDriverProvider.class.getName());

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        RemoteWebDriver driver;

        if (Configuration.CHROME.equalsIgnoreCase(Configuration.browser))
            driver = createChromeDriver();
        else if (Configuration.FIREFOX.equalsIgnoreCase(Configuration.browser))
            driver = createFirefoxDriver();
        else if (Configuration.INTERNET_EXPLORER.equalsIgnoreCase(Configuration.browser))
            driver = createInternetExplorerDriver();
        else if (Configuration.EDGE.equalsIgnoreCase(Configuration.browser))
            driver = createEdgeDriver();
        else throw new IllegalArgumentException("Invalid browser name: " + Configuration.browser);

        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.setFileDetector(new LocalFileDetector());

        return driver;
    }

    private RemoteWebDriver createFirefoxDriver() {
        DesiredCapabilities capabilities = createCommonCapabilities();
        capabilities.setBrowserName(Configuration.browser);

        if (!StringUtils.isEmpty(Configuration.firefoxVersion)) {
            capabilities.setVersion(Configuration.firefoxVersion);
            log.info("Configuration.browser.version=" + Configuration.firefoxVersion);
        }

        if (Configuration.marionette) {
            capabilities.setCapability("marionette", true);
        }
        else {
            capabilities.setCapability("marionette", false);
        }
        return new RemoteWebDriver(Configuration.gridUrl, capabilities);
    }

    private RemoteWebDriver createChromeDriver() {
        DesiredCapabilities capabilities = createCommonCapabilities();
        ChromeOptions options = new ChromeOptions();

        if (!StringUtils.isEmpty(Configuration.chromeVersion)) {
            capabilities.setVersion(Configuration.chromeVersion);
            log.info("Configuration.browser.version=" + Configuration.chromeVersion);
        }

        options.addArguments("--ignore-certificate-errors");
        if (Configuration.browserStartMaximized) {
            options.addArguments("--start-maximized");
        }

        capabilities.setBrowserName(Configuration.browser);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return new RemoteWebDriver(Configuration.gridUrl, capabilities);
    }

    private RemoteWebDriver createInternetExplorerDriver() {
        DesiredCapabilities capabilities = createCommonCapabilities();
        capabilities.setBrowserName(Configuration.browser);
        capabilities.setBrowserName("internet explorer");
        capabilities.setCapability("ie.forceCreateProcessApi", true);
        capabilities.setCapability("ie.browserCommandLineSwitches", "-private");
        capabilities.setCapability("ie.ensureCleanSession", true);

        return new RemoteWebDriver(Configuration.gridUrl, capabilities);
    }

    private RemoteWebDriver createEdgeDriver() {
        DesiredCapabilities capabilities = createCommonCapabilities();
        capabilities.setBrowserName("MicrosoftEdge");

        return new RemoteWebDriver(Configuration.gridUrl, capabilities);
    }

    private DesiredCapabilities createCommonCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (!StringUtils.isEmpty(Configuration.proxyUrl)){
            capabilities.setCapability(PROXY, createProxy());
            log.info("Configuration.proxyUrl=" + Configuration.proxyUrl);
        }
        if (Configuration.browserPlatform != null) {
            capabilities.setPlatform(Configuration.browserPlatform);
            log.info("Configuration.browser.platform=" + Configuration.browserPlatform);
        }
        if (Configuration.video && !Configuration.INTERNET_EXPLORER.equalsIgnoreCase(Configuration.browser)){
            capabilities.setCapability("nodeType", "video");
        }

        return capabilities;
    }

    private Proxy createProxy() {
        return new Proxy()
                .setProxyType(MANUAL)
                .setHttpProxy(Configuration.proxyUrl);
    }
}
