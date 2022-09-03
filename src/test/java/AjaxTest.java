import framework.BaseTest;
import models.User;
import models.UserBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AjaxPage;
import pages.HomePage;

import static org.testng.Assert.assertEquals;

public class AjaxTest extends BaseTest {

    private User user;
    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void userinfo() {
        user = UserBuilder.admin();
        homePage = loginPage.loginWithValidCreds(user);
    }

    //To open the test page for the AJAX test click on ‘Ajax test page’ on the home page. On this page there is a sample form for obtaining the sum of two numbers.
    //The result won't be displayed immediately: it appears with a few seconds delay.
    // The goal of it is to learn how to wait for result properly.
    //Enter two valid numbers, click ‘Sum’, wait for the result and check if the result is correct.
    @Test(groups = "Ajax")
    public void sumOfTwoNumbers() {
        String x = "1";
        String y = "2";
        String expectedResultTextMessage = String.valueOf(Double.parseDouble(x) + Double.parseDouble(y));

        AjaxPage ajaxPage = homePage.clickOAjaxTestPageButton()
                .inputDataInXField(x)
                .inputDataInYField(y)
                .clickOnSumButton()
                .waitForResultMessage();
        assertEquals(ajaxPage.getResultMessage(), expectedResultTextMessage, "Result is correct");
    }

    //    Enter one valid number and one string (not a number), click ‘Sum’, wait for the result, and verify that the message ‘Incorrect data’ appears.
    @Test(groups = "Ajax")
    public void sumOfNumberAndStringToVerifyAlert() {
        String x = "1";
        String y = "some string";

        AjaxPage ajaxPage = homePage.clickOAjaxTestPageButton()
                .inputDataInXField(x)
                .inputDataInYField(y)
                .clickOnSumButton()
                .waitForResultMessage();
        assertEquals(ajaxPage.getResultMessage(), "Incorrect data", "Alert message is correctly displayed");
    }
}