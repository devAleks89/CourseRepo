package pages;

import framework.BasePage;
import framework.DriverManager;
import framework.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JSTestPage extends BasePage {
    @FindBy(id = "top")
    WebElement topTextField;

    @FindBy(id = "left")
    WebElement lefTextField;

    @FindBy(id = "process")
    WebElement processButton;

    public JSTestPage findJSElement() {
        fillTopTextField();
        fillLeftTextField();
        clickOnProcessButton();
        return this;
    }

    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) DriverManager.getDriver();

    public int getCoordinateOfTopJumpingDiv() {
        Log.info("Get top coordinate of jumping div");
        return Math.round(Float.parseFloat(String.valueOf(javascriptExecutor.executeScript("return $('.flash').css('top').replace('px', '')"))));
    }

    public void fillTopTextField() {
        Log.info("Fill top text field with value");
        topTextField.sendKeys(String.valueOf(getCoordinateOfTopJumpingDiv()));
    }

    public int getCoordinateOfLeftJumpingDiv() {
        Log.info("Get left coordinate of jumping div");
        return Math.round(Float.parseFloat(String.valueOf(javascriptExecutor.executeScript("return $('.flash').css('left').replace('px', '')"))));
    }

    public void fillLeftTextField() {
        Log.info("Fill left text field with value");
        lefTextField.sendKeys(String.valueOf(getCoordinateOfLeftJumpingDiv()));
    }

    public void clickOnProcessButton() {
        Log.info("Click on 'process' button");
        processButton.click();
    }

    public String alertTextMessage() {
        Log.info("Switch to alert and get text");
        return DriverManager.getDriver().switchTo().alert().getText();
    }
}
