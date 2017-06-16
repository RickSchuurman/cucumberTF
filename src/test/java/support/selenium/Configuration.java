package support.selenium;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Platform;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Configuration {

    private static final String FRONTEND_SELENIUM_PROPERTIES = "cucumber" + System.getProperty("cucumber-env") + ".properties";
    private static final String FRONTEND_SELENIUM_OVERRIDE_PROPERTIES = "cucumber" + System.getProperty("cucumber-env") + "-override.properties";
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String INTERNET_EXPLORER = "ie";
    public static final String EDGE = "edge";
    private static Properties properties;

    // Load a properties file *.properties from project classpath, and get the keys and values.
    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(FRONTEND_SELENIUM_PROPERTIES));
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid properties file " + FRONTEND_SELENIUM_PROPERTIES);
        }

        properties = new Properties(properties);

        if (classLoader.getResourceAsStream(FRONTEND_SELENIUM_OVERRIDE_PROPERTIES) != null) {
            try {
                properties.load(classLoader.getResourceAsStream(FRONTEND_SELENIUM_OVERRIDE_PROPERTIES));
            } catch (IOException e) {
                throw new IllegalArgumentException("Invalid properties file " + FRONTEND_SELENIUM_OVERRIDE_PROPERTIES);
            }
        }
    }

    public static String browser = getSystemOrProvidedProperty("browser", "firefox");

    public static String browserSize = getSystemOrProvidedProperty("browser.size");

    public static Platform browserPlatform = getSystemOrProvidedProperty("browser.platform") != null ? Platform.fromString(System.getProperty("browser.platform")) : Platform.ANY;

    public static boolean browserStartMaximized = Boolean.parseBoolean(getSystemOrProvidedProperty("browser.startMaximized", "true"));

    public static boolean browserLeaveOpen = Boolean.parseBoolean(getSystemOrProvidedProperty("browser.leaveOpen", "false"));

    public static boolean marionette = Boolean.parseBoolean(getSystemOrProvidedProperty("browser.firefox.marionette", "false"));

    public static boolean grid = Boolean.parseBoolean(getSystemOrProvidedProperty("grid", "false"));

    public static URL gridUrl = getURL(getSystemOrProvidedProperty("grid.url"));

    public static boolean video = Boolean.parseBoolean(getSystemOrProvidedProperty("grid.video"));

    public static String proxyUrl = getSystemOrProvidedProperty("proxy.url");

    public static String internetExplorerDriverLocation = getSystemOrProvidedProperty("internetexplorer.driver.location");

    public static String firefoxAppLocation = getSystemOrProvidedProperty("firefox.app.location");

    public static String firefoxVersion = getSystemOrProvidedProperty("firefox.version");

    public static String geckoDriverLocation = getSystemOrProvidedProperty("gecko.driver.location");

    public static String chromeAppLocation = getSystemOrProvidedProperty("chrome.app.location");

    public static String chromeVersion = getSystemOrProvidedProperty("chrome.version");

    public static String chromeDriverLocation = getSystemOrProvidedProperty("chrome.driver.location");

    // gets the system property or property from the properties file
    private static String getSystemOrProvidedProperty(String propertyName, String defaultValue) {
        String systemProperty = System.getProperty(propertyName);
        return systemProperty != null ? systemProperty : properties.getProperty(propertyName) != null ? properties.getProperty(propertyName) : defaultValue;
    }

    private static String getSystemOrProvidedProperty(String propertyName) {
        return getSystemOrProvidedProperty(propertyName, null);
    }

    private static URL getURL(String url) {
        if (!StringUtils.isEmpty(url)) {
            try {
                return new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Invalid url: " + url);
            }
        } else
            return null;
    }

    public static void setWebdriverProvider() {
        if (grid)
            com.codeborne.selenide.Configuration.browser = "support.selenium.RemoteWebDriverProvider";
        else if (CHROME.equalsIgnoreCase(browser))
            com.codeborne.selenide.Configuration.browser = "support.selenium.ChromeWebDriverProvider";
        else if (FIREFOX.equalsIgnoreCase(browser))
            com.codeborne.selenide.Configuration.browser = "support.selenium.FirefoxWebDriverProvider";
        else if (INTERNET_EXPLORER.equalsIgnoreCase(browser))
            com.codeborne.selenide.Configuration.browser = "support.selenium.InternetExplorerWebDriverProvider";
        else if (EDGE.equalsIgnoreCase(browser))
            com.codeborne.selenide.Configuration.browser = "support.selenium.EdgeWebDriverProvider";
        else
            throw new IllegalArgumentException("Invalid browsername: " + browser);

        if (!grid)
            com.codeborne.selenide.Configuration.holdBrowserOpen = browserLeaveOpen;

        com.codeborne.selenide.Configuration.browserSize = browserSize;
        com.codeborne.selenide.Configuration.startMaximized = browserStartMaximized;
    }
}
