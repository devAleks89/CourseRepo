package pages;

import framework.DriverManager;
import framework.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static framework.Utils.isElementPresent;

public class HomePage extends BasicPage {

    @FindBy(xpath = "//a[text()='Details']")
    WebElement detailsButton;

    public String getWelcomeMessage() {
        return new WebDriverWait(DriverManager.getDriver(), 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='welcome']"))).getText();
    }

    public boolean isMyApplicationButtonVisible() {
        Log.info("Check if 'add new application' button is visible");
        return isElementPresent(myApplicationButton);
    }

    public WebElement particularApp(String title) {
        return DriverManager.getDriver().findElement(By.xpath(String
                .format("//div[@class='app' and div[@class='name' and text()[normalize-space()='%s']]]/a[text()='Details']", title)));
    }

    public AppInfoPage clickOnDetailsButtonParticularApp(String title) {
        Log.info("Click on 'details' button");
        particularApp(title).click();
        return initPage(AppInfoPage.class);
    }

    public AppInfoPage clickOnDetailsButton() {
        Log.info("Click on 'details' button");
        detailsButton.click();
        return initPage(AppInfoPage.class);
    }

    public LoginPage openNewTabAndLogout() {
        logOutButton.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));
        return initPage(LoginPage.class);
    }

    public void checkIsUserLoggedOutOnFirstTab() {
        myApplicationButton.click();
    }
}
