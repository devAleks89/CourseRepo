package pages;

import framework.DriverManager;
import framework.Log;
import org.openqa.selenium.By;

public class ListApplicationsPage extends BasicPage {

    public AppInfoPage clickOnDetailsButton(String title) {
        Log.info("Click on 'details' button");
        DriverManager.getDriver().findElement(By.xpath(String.format("//div[text()[normalize-space()='%s']]//following-sibling::a", title))).click();
        return initPage(AppInfoPage.class);
    }
}
