package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    public static final String PATH_OF_REPORTING_DIRECTORY = "selenium.pathOfReportingDirectory";
    private static final String SELENIUM_BASEURL = "selenium.baseUrl";
    private static final String SELENIUM_BROWSER = "selenium.browser";
    private static final String HTTP_AUTH = "selenium.basicAuthentication";
    private static final String SELENIUM_PROPERTIES = "selenium.properties";
    private static final String SELENIUM_URL_WITH_AUTH = "selenium.urlWithBasicAuth";
    private static final String USER_LOGIN = "application.adminLogin";
    private static final String USER_PASSWORD = "application.adminPassword";

    private String baseUrl;
    private String auth;
    private BrowserType browser;
    private Properties properties = new Properties();
    private String pathToReportingDirectory;

    private String userLogin;
    private String userPassword;

    public Settings() {
        loadSettings();
    }

    private void loadSettings() {
        properties = loadPropertiesFile();
        auth = getPropertyOrThrowException(HTTP_AUTH);
        if (auth.equals("on")) {
            baseUrl = getPropertyOrThrowException(SELENIUM_URL_WITH_AUTH);
        } else {
            baseUrl = getPropertyOrThrowException(SELENIUM_BASEURL);
        }
        browser = BrowserType.Browser(getPropertyOrThrowException(SELENIUM_BROWSER));
        userLogin = getPropertyOrThrowException(USER_LOGIN);
        userPassword = getPropertyOrThrowException(USER_PASSWORD);
        pathToReportingDirectory = getPropertyOrThrowException(PATH_OF_REPORTING_DIRECTORY);
    }

    private Properties loadPropertiesFile() {
        try {
            String filename;
            // get specified property file
            if (getPropertyOrNull(SELENIUM_PROPERTIES) != null) {
                filename = getPropertyOrNull(SELENIUM_PROPERTIES);
            } else {
                filename = SELENIUM_PROPERTIES;
            }
            InputStream stream;
            // try to load from classpath
            if (getClass().getClassLoader().getResourceAsStream(filename) != null) {
                stream = getClass().getClassLoader().getResourceAsStream(filename);
            } else {
                // no file in classpath, look on disk
                stream = new FileInputStream(new File(filename));
            }
            Properties result = new Properties();
            result.load(stream);
            return result;
        } catch (IOException e) {
            throw new UnknownPropertyException("Property file is not found");
        }
    }

    public String getPropertyOrNull(String name) {
        return getProperty(name, false);
    }

    public String getPropertyOrThrowException(String name) {
        return getProperty(name, true);
    }

    private String getProperty(String name, boolean forceExceptionIfNotDefined) {
        String result;
        if ((result = System.getProperty(name, null)) != null && result.length() > 0) {
            return result;
        } else if ((result = getPropertyFromPropertiesFile(name)) != null && result.length() > 0) {
            return result;
        } else if (forceExceptionIfNotDefined) {
            throw new UnknownPropertyException("Unknown property: [" + name + "]");
        }
        return result;
    }

    private String getPropertyFromPropertiesFile(String name) {
        Object result = properties.get(name);
        if (result == null) {
            return null;
        } else {
            return result.toString();
        }
    }

    public WebDriver getDriver() {
        return getDriver(browser);
    }

    private WebDriver getDriver(BrowserType browserType) {
        switch (browserType) {
            case GC:
                System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
                return new ChromeDriver();
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
                return new FirefoxDriver();
            case IE:
                return new InternetExplorerDriver();
            default:
                throw new UnknownBrowserException("Cannot create driver for unknown browser type");
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getPathOfReportingDirectory() {
        return pathToReportingDirectory;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }
}