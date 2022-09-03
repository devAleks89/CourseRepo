package pages;

import framework.BasePage;
import framework.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static framework.Utils.waitForElementPresent;

public class AjaxPage extends BasePage {

    @FindBy(id = "x")
    WebElement xField;

    @FindBy(id = "y")
    WebElement yField;

    @FindBy(id = "calc")
    WebElement sumButton;

    @FindBy(id = "result")
    WebElement textMessage;


    public AjaxPage inputDataInXField(String x) {
        Log.info("Input data in X text field");
        xField.sendKeys(x);
        return this;
    }

    public AjaxPage inputDataInYField(String y) {
        Log.info("Input data in Y text field");
        yField.sendKeys(y);
        return this;
    }

    public AjaxPage clickOnSumButton() {
        Log.info("Click on Sum button");
        sumButton.click();
        return this;
    }

    public AjaxPage waitForResultMessage() {
        try {
            Log.info("Wait for presence of text message");
            waitForElementPresent(textMessage.toString());
        } catch (Throwable e) {
        }
        return this;
    }

    public String getResultMessage() {
        Log.info("Get text from flashed message");
        return textMessage.getText().substring("Result is: ".length());
    }
}


