package pages;

import framework.BasePage;
import framework.DriverManager;
import framework.Log;
import models.AppInfo;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static framework.Utils.isElementPresent;

public class AppInfoPage extends BasePage {

    @FindBy(xpath = "//a[text()='Download']")
    WebElement downloadButton;

    @FindBy(xpath = "//div[@class='name']")
    WebElement title;

    @FindBy(xpath = "//div[@class='description' and contains(text(),'Description')]")
    WebElement description;

    @FindBy(xpath = "//div[@class='description' and contains(text(),'Category')]")
    WebElement category;

    @FindBy(xpath = "//div[@class='description' and contains(text(),'Author')]")
    WebElement authorInfo;

    @FindBy(xpath = "//div[@class='downloads']")
    WebElement numberOfDownloads;

    @FindBy(xpath = "//a[text()='Edit']")
    WebElement editButton;

    @FindBy(xpath = "//a[text()='Delete']")
    WebElement deleteButton;

    public AppInfo getInfoFromAppPage() {
        AppInfo appInfo = new AppInfo();
        appInfo.setTitle(title.getText());
        appInfo.setDescription(description.getText());
        appInfo.setCategory(category.getText());
        appInfo.setAuthor(authorInfo.getText());
        appInfo.setNumberOfDownloads(numberOfDownloads.getText());
        return appInfo;
    }

    public EditAppPage clickOnEditButton() {
        Log.info("Click on edit button");
        editButton.click();
        return initPage(EditAppPage.class);
    }

    public boolean isDownloadButtonVisible() {
        try {
            Log.info("Check visibility of 'download button'");
            downloadButton.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAppPopular(String title) {
        Log.info("Check if element is in popular section");
        return isElementPresent(String.format("//div[@class='popular-app']//div[text() = '%s']", title));
    }

    public boolean makeAppPopular(String title) {
        while (!isAppPopular(title)) {
            Log.info("Click on download button");
            downloadButton.click();
            Log.info("Navigate back to AppInfoPage");
            DriverManager.getDriver().navigate().back();
            Log.info("Refresh AppInfoPage");
            DriverManager.getDriver().navigate().refresh();
        }
        return true;
    }

    public AppInfoPage clickOnDeleteButton() {
        Log.info("Click on delete button");
        deleteButton.click();
        return this;
    }

    public BasicPage acceptDeleteAppAlert() {
        Log.info("Switch to alert message and accept it");
        DriverManager.getDriver().switchTo().alert().accept();
        return initPage(BasicPage.class);
    }

    public JsonPage clickOnDownloadButton() {
        Log.info("Click on download button");
        downloadButton.click();
        return initPage(JsonPage.class);
    }
}

