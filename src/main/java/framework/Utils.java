package framework;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    public static void waitForElementPresent(String id) {
        new WebDriverWait(DriverManager.getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    public static void waitForElementPresentXpath(String xpath) {
        new WebDriverWait(DriverManager.getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public static boolean isElementPresent(String xpath) {
        return isElementPresent(By.xpath(xpath));
    }

    public static boolean isElementPresent(By by) {
        return DriverManager.getDriver().findElements(by).size() > 0;
    }

    public static boolean isElementPresent(WebElement element) {
        boolean isVisible = false;
        try {
            isVisible = element.isDisplayed();
        } catch (
                NoSuchElementException e) {
        }
        return isVisible;
    }

    public static String random() {
        return RandomStringUtils.random(15, true, false);
    }
}
