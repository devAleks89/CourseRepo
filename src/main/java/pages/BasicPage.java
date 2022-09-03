package pages;

import framework.BasePage;
import framework.DriverManager;
import framework.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static framework.Utils.isElementPresent;
import static framework.Utils.waitForElementPresentXpath;

public class BasicPage extends BasePage {
    public static By logoutBy = By.xpath("//a[text()='Logout']");
    public static By flash = By.xpath("//p[@class='flash']");

    @FindBy(xpath = "//a[text()='Home']")
    WebElement homeButton;

    @FindBy(xpath = "//a[text()='My applications']")
    WebElement myApplicationButton;

    @FindBy(xpath = "//a[text()='Ajax test page']")
    WebElement ajaxButton;

    @FindBy(xpath = "//a[text()='JS test page']")
    WebElement jsTestPageButton;

    @FindBy(xpath = "//a[text()='Logout']")
    WebElement logOutButton;

    public LoginPage forceLogout() {
        Log.info("Get base Url");
        DriverManager.getDriver().get(settings.getBaseUrl());
        Log.info("Passed to page");
        if (isElementPresent(logoutBy)) {
            Log.info("Click on 'logout' button");
            DriverManager.getDriver().findElement(logoutBy).click();
        }
        return initPage(LoginPage.class);
    }

    public String getFlashMessage() {
        Log.info("Check if flash message is present");
        String message = null;
        if (isElementPresent(flash)) {
            Log.info("Check if flash message is present");
            message = DriverManager.getDriver().findElement(flash).getText();
        }
        return message;
    }

    public HomePage clickOnHomeButton() {
        Log.info("Click on 'home' button");
        homeButton.click();
        return initPage(HomePage.class);
    }

    public ListApplicationsPage clickOnMyApplicationButton() {
        Log.info("Click on 'my application' button");
        try {
            myApplicationButton.click();
        } catch (StaleElementReferenceException e) {
            myApplicationButton = DriverManager.getDriver().findElement(By.xpath("//a[text()='My applications']"));
            myApplicationButton.click();
        }
        return initPage(ListApplicationsPage.class);
    }

    public LoginPage clickOnLogoutButton() {
        Log.info("Click on 'logout' button");
        logOutButton.click();
        return initPage(LoginPage.class);
    }

    public AddNewAppPage clickOnMyApplicationsButton() {
        Log.info("Click on 'my application' button");
        waitForElementPresentXpath("//a[text()='My applications']");
        myApplicationButton.click();
        return initPage(AddNewAppPage.class);
    }

    public AjaxPage clickOAjaxTestPageButton() {
        Log.info("Click on 'ajax test page' button");
        waitForElementPresentXpath("//a[text()='Ajax test page']");
        ajaxButton.click();
        return initPage(AjaxPage.class);
    }

    public JSTestPage clickOnJSTestPageButton() {
        Log.info("Click on 'JS test page' button");
        jsTestPageButton.click();
        return initPage(JSTestPage.class);
    }

    public boolean isAppVisibleOnPage(String title) {
        Log.info("Check if application is visible");
        return isElementPresent(String.format("//div[text()[normalize-space() = '%s']]", title));
    }
}
