package pages;

import framework.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static framework.Utils.isElementPresent;

public class AddNewAppPage extends BasicPage {

    @FindBy(xpath = "//a[@href='/new']")
    WebElement addNewAppButton;

    public boolean isAddNewAppButtonVisible() {
        Log.info("Check if 'add new application' button is visible");
        return isElementPresent(addNewAppButton);
    }

    public CreateNewAppPage clickOnAddNewAppButton() {
        Log.info("Click on add new application button");
        addNewAppButton.click();
        return initPage(CreateNewAppPage.class);
    }
}
