package framework;

import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public static Settings settings;

    public static <T extends BasePage> T initPage(Class<T> pageClass) {
        return PageFactory.initElements(DriverManager.getDriver(), pageClass);
    }
}
