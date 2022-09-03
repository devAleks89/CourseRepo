package pages;

import framework.DriverManager;
import framework.Log;
import models.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreateNewAppPage extends BasicPage {

    @FindBy(xpath = "//input[@type='text']")
    WebElement title;

    @FindBy(xpath = "//textarea[@name='description']")
    WebElement description;

    @FindBy(xpath = "//select[@name='category']")
    WebElement category;

    @FindBy(xpath = "//input[@name='image']")
    WebElement image;

    @FindBy(xpath = "//input[@name='icon']")
    WebElement icon;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement createButton;

    public CreateNewAppPage fillAppFormWithoutImages(Application application) {
        Log.info("Fill title of application");
        title.sendKeys(application.getTitle());

        Log.info("Fill description of application");
        description.sendKeys(application.getDescription());

        Log.info("Select category of application");
        (new Select(category)).selectByVisibleText(application.getCategory());
        return this;
    }

    public CreateNewAppPage fillAppFormWithImages(Application application) {
        fillAppFormWithoutImages(application);

        Log.info("Input icon of application");
        icon.sendKeys(application.getIconPath());

        Log.info("Input image of application");
        image.sendKeys(application.getImagePath());
        return this;
    }

    public ListApplicationsPage clickOnCreateButton() {
        Log.info("Click on 'create' button");
        try {
            createButton.click();
        } catch (StaleElementReferenceException e) {
            createButton = DriverManager.getDriver().findElement(By.xpath("//input[@type='submit']"));
            createButton.click();
        }
        return initPage(ListApplicationsPage.class);
    }
}

