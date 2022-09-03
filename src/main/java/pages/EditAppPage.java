package pages;

import framework.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditAppPage extends BasicPage {

    @FindBy(xpath = "//input[@type='submit']")
    WebElement updateButton;

    @FindBy(xpath = "//textarea[@name='description']")
    WebElement description;

    public BasicPage clickOnUpdateButton() {
        Log.info("Click on 'update' button");
        updateButton.click();
        return initPage(BasicPage.class);
    }

    public EditAppPage clearDescription() {
        Log.info("Click on 'description' button");
        description.clear();
        return this;
    }

    public EditAppPage getEditedDescription(String newDescription) {
        Log.info("Edit description of application");
        description.sendKeys(newDescription);
        return this;
    }
}
