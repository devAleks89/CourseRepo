package framework;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> newDriver = new ThreadLocal<WebDriver>();

    public static void setDriver(WebDriver driver) {
        newDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return newDriver.get();
    }
}