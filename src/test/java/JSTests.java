import framework.BaseTest;
import models.User;
import models.UserBuilder;
import org.testng.annotations.Test;
import pages.JSTestPage;

import static org.testng.Assert.assertEquals;

public class JSTests extends BaseTest {

    //    Get the coordinates of the jumping div (with a red border and the text ‘Find me !’ inside).
    //    Enter the coordinates into the input fields and press ‘Process’.
    //    Handle the alert and verify that the message ‘Whoo Hoooo! Correct!’ is displayed.
    @Test(groups = {"JS"})
    public void findCoordinatesOfJumpingDiv() {
        User user = UserBuilder.admin();
        JSTestPage jsTestPage = loginPage.loginWithValidCreds(user)
                .clickOnJSTestPageButton()
                .findJSElement();
        assertEquals(jsTestPage.alertTextMessage(), "Whoo Hoooo! Correct!", "Messages is visible");
    }
}
