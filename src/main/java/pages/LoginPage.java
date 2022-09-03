package pages;

import framework.DriverManager;
import framework.Log;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

import static framework.Utils.isElementPresent;

public class LoginPage extends BasicPage {

    @FindBy(id = "j_username")
    WebElement userNameTextField;

    @FindBy(id = "j_password")
    WebElement passwordTextField;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginButton;

    @FindBy(xpath = "//a[@href='../register']")
    WebElement newUserRegisterButton;

    ArrayList<String> tabs = new ArrayList<>(DriverManager.getDriver().getWindowHandles());

    public HomePage loginWithValidCreds(User user) {
        Log.info("User login with valid credentials");
        loginAs(user);
        return initPage(HomePage.class);
    }

    public HomePage loginAs(User user) {
        fillUserNameField(user);
        fillPasswordField(user);
        clickOnLoginButton();
        return initPage(HomePage.class);
    }

    public HomePage clickOnLoginButton() {
        Log.info("Click on 'login' button");
        try {
            loginButton.click();
        } catch (StaleElementReferenceException e) {
            loginButton = DriverManager.getDriver().findElement(By.xpath("//input[@value='Login']"));
            loginButton.click();
        }
        return initPage(HomePage.class);
    }

    public LoginPage fillUserNameField(User user) {
        Log.info("clear username text field");
        userNameTextField.clear();

        Log.info("Fill username field with credentials");
        userNameTextField.sendKeys(user.getUsername());
        return this;
    }

    public LoginPage fillPasswordField(User user) {
        Log.info("clear password text field");
        passwordTextField.clear();
        try {
            passwordTextField.sendKeys(user.getPassword());
        } catch (StaleElementReferenceException e) {
            passwordTextField = DriverManager.getDriver().findElement(By.id("j_password"));
            passwordTextField.sendKeys(user.getPassword());
        }
        return this;
    }

    public RegistrationPage clickOnRegisterNewUserButton() {
        Log.info("Click on 'register as a new user' button");
        newUserRegisterButton.click();
        return initPage(RegistrationPage.class);
    }

    public boolean isLoginButtonVisibleOnLoginPage() {
        Log.info("Check if 'Login' button is visible");
        return isElementPresent(loginButton);
    }

    public LoginPage checkIsUserLoggedOutOnNewTab() {
        DriverManager.getDriver().switchTo().window(tabs.get(1));
        DriverManager.getDriver().switchTo().window(tabs.get(0));
        return initPage(LoginPage.class);
    }
}

