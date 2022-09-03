package pages;

import framework.BasePage;
import framework.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JsonPage extends BasePage {

    @FindBy(xpath = "//body")
    WebElement textOnJsonPage;

    public String getTextFromJson() {
        Log.info("Get text from Json file");
        return textOnJsonPage.getText();
    }
}
