import framework.BaseTest;
import models.User;
import models.UserBuilder;
import org.testng.annotations.Test;
import pages.HomePage;

import static models.UserBuilder.admin;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginLogoutTest extends BaseTest {

    //Login as a valid user with an incorrect password.
    @Test(groups = {"Login"})
    public void incorrectLoginTest() {
        User user = UserBuilder.userWithInvalidCreds();
        loginPage.loginAs(user);
        assertEquals(loginPage.getFlashMessage(), "You have entered an invalid username or password!", "User is not logged in");
    }

    @Test(groups = {"Login"})
    public void correctLoginTest() {
        User user = UserBuilder.admin();
        HomePage homePage = loginPage.loginWithValidCreds(user);
        assertEquals(homePage.getWelcomeMessage(), String.format("Welcome %s %s",
                user.getFirstName(), user.getLastName()), "Message should be equal");
    }

    @Test(groups = {"Logout"})
    public void loginAsValidUserOpenNewTabAndLogoutTest() {
        User user = admin();
        HomePage homePage = loginPage.loginAs(user);
        homePage.openNewTabAndLogout()
                .checkIsUserLoggedOutOnNewTab();
        homePage.checkIsUserLoggedOutOnFirstTab();
        assertTrue(loginPage.isLoginButtonVisibleOnLoginPage(), "Login page should be visible");
    }
}