import framework.BaseTest;
import framework.DataProviderUtil;
import models.User;
import models.UserBuilder;
import models.UserRole;
import org.testng.annotations.Test;
import pages.AddNewAppPage;
import pages.HomePage;

import static org.testng.Assert.*;

public class RegistrationTest extends BaseTest {

    //Register at least 5 users with different roles using data-driven testing (DDT).
    // In other words, run one test as many times with as many users you put in the .CSV file.
    @Test(groups = {"Registration"}, dataProvider = "LoginCsvDataProvider", dataProviderClass = DataProviderUtil.class)
    public void registrationUsersFromCsvFile(User user) {
        HomePage homePage = loginPage.clickOnRegisterNewUserButton()
                .fillFormWithCreds(user, UserRole.valueOf(user.getRole().getUserRole()))
                .clickOnRegisterButton();
        assertEquals(homePage.getWelcomeMessage(), String.format("Welcome %s %s",
                user.getFirstName(), user.getLastName()), "Users are logged in");
    }

    //Register a new user, and verify that the user is logged in.
    @Test(groups = {"Registration"})
    public void newUserLoginTest() {
        User user = UserBuilder.randomUser();
        HomePage homePage = loginPage.clickOnRegisterNewUserButton()
                .fillFormWithCreds(user, UserRole.USER)
                .clickOnRegisterButton();
        assertEquals(homePage.getWelcomeMessage(), String.format("Welcome %s %s",
                user.getFirstName(), user.getLastName()), "User is logged in");
    }

    //Register as a developer, verify that the developer can open the page to upload an application.
    @Test(groups = {"Registration"})
    public void verifyNewDeveloperCanOpenPageToUploadAppTest() {
        User user = UserBuilder.randomUser();
        HomePage homePage = loginPage.clickOnRegisterNewUserButton()
                .fillFormWithCreds(user, UserRole.DEVELOPER)
                .clickOnRegisterButton();
        assertTrue(homePage.isMyApplicationButtonVisible(), "My applications button is visible for developer");

        AddNewAppPage addNewAppPage = homePage.clickOnMyApplicationsButton();
        assertTrue(addNewAppPage.isAddNewAppButtonVisible(), "New developer is logged in and new app button visible");
    }

    //Register as a regular user, verify that the regular user can see the applications but cannot upload them.
    @Test(groups = {"Registration"})
    public void regularUserCannotUploadTest() {
        User user = UserBuilder.randomUser();
        HomePage homePage = loginPage.clickOnRegisterNewUserButton()
                .fillFormWithCreds(user, UserRole.USER)
                .clickOnRegisterButton();
        assertFalse(homePage.isMyApplicationButtonVisible(), "User is logged in as a regular user");
    }

    //Register a new user, logout, and verify that the user can login.
    @Test(groups = {"Registration"})
    public void newUserLogoutAndLoginTest() {
        User user = UserBuilder.randomUser();
        HomePage homePage = loginPage.clickOnRegisterNewUserButton()
                .fillFormWithCreds(user, UserRole.USER)
                .clickOnRegisterButton()
                .clickOnLogoutButton()
                .loginWithValidCreds(user);
        assertEquals(homePage.getWelcomeMessage(), String.format("Welcome %s %s",
                user.getFirstName(), user.getLastName()), "New user is logged in");
    }
}
