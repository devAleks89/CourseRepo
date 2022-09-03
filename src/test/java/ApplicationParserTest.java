import framework.BaseTest;
import io.restassured.path.json.JsonPath;
import models.AppInfo;
import models.User;
import models.UserBuilder;
import org.testng.annotations.Test;
import pages.AppInfoPage;
import pages.JsonPage;

import static org.testng.Assert.assertEquals;

public class ApplicationParserTest extends BaseTest {

    // Open a page of any application. Note down the information about this application given on the page.
    // Then click the download button. Firstly make sure that the information about this application is shown on the opened page in text format (this is a JSON response).
    // Then you must compare the parameters displayed on the application page and the parameters displayed on the JSON page.
    // They must be identical. For example, if the number of downloads on the application page is 4,
    // the "numberOfDownloads" parameter must display 4 (please note that after clicking the download button the value will be +1).
    // You don’t have to verify stuff that is not shown on the application page, e.g. “id” or "imageData".
    @Test(groups = {"Application"})
    public void applicationParserTest() {
        User user = UserBuilder.admin();
        AppInfoPage appInfoPage = loginPage.loginWithValidCreds(user).clickOnDetailsButton();
        AppInfo infoBuilder = appInfoPage.getInfoFromAppPage();
        JsonPage jsonPage = appInfoPage.clickOnDownloadButton();
        JsonPath jsonPath = JsonPath.from(jsonPage.getTextFromJson());
        assertEquals(jsonPath.getString("title"), infoBuilder.getTitle(), "Title should be equal");
        assertEquals(jsonPath.getString("description"), infoBuilder.getDescription(), "Description should be equal");
        assertEquals(jsonPath.getString("author.name"), infoBuilder.getAuthor(), "author name should be equal");
        assertEquals(jsonPath.getString("category.title"), infoBuilder.getCategory(), "Category should be equal");
        assertEquals(jsonPath.getInt("numberOfDownloads"), infoBuilder.getNumberOfDownloads() + 1, "Number of downloads should be correct");
    }
}
