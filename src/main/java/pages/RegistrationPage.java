package pages;

import framework.Log;
import models.User;
import models.UserRole;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasicPage {
    @FindBy(name = "name")
    WebElement nameTextField;

    @FindBy(name = "fname")
    WebElement firstNameTextField;

    @FindBy(name = "lname")
    WebElement lastNameTextField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(name = "passwordConfirm")
    WebElement passwordConfirmField;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement registerButton;

    @FindBy(xpath = "//select[@name='role']")
    WebElement role;

    public RegistrationPage fillFormWithCreds(User newUser, UserRole userRole) {
        fillNameField(newUser);
        fillFirstNameField(newUser);
        fillLastNameField(newUser);
        fillPasswordField(newUser);
        confirmInputtedPassword(newUser);
        role.sendKeys(userRole.name());
        return this;
    }

    public HomePage clickOnRegisterButton() {
        Log.info("Click on 'register' button");
        registerButton.click();
        return initPage(HomePage.class);
    }

    public RegistrationPage fillNameField(User newUser) {
        Log.info("Clear name text field");
        nameTextField.clear();

        Log.info("Fill name text field");
        nameTextField.sendKeys(newUser.getUsername());
        return this;
    }

    public RegistrationPage fillFirstNameField(User newUser) {
        Log.info("Clear first name text field");
        firstNameTextField.clear();

        Log.info("Fill first name text field");
        firstNameTextField.sendKeys(newUser.getFirstName());
        return this;
    }

    public RegistrationPage fillLastNameField(User newUser) {
        Log.info("Clear last name text field");
        lastNameTextField.clear();

        Log.info("Fill last name text field");
        lastNameTextField.sendKeys(newUser.getLastName());
        return this;
    }

    public RegistrationPage fillPasswordField(User newUser) {
        Log.info("Clear first password field");
        passwordField.clear();

        Log.info("Fill password field");
        passwordField.sendKeys(newUser.getPassword());
        return this;
    }

    public RegistrationPage confirmInputtedPassword(User newUser) {
        Log.info("Clear first confirm password field");
        passwordConfirmField.clear();

        Log.info("Fill confirm password field");
        passwordConfirmField.sendKeys(newUser.getPassword());
        return this;
    }
}
