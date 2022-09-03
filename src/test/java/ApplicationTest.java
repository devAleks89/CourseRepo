import framework.BaseTest;
import models.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AppInfoPage;
import pages.CreateNewAppPage;
import pages.HomePage;
import pages.ListApplicationsPage;

import static framework.Utils.random;
import static org.testng.Assert.*;

public class ApplicationTest extends BaseTest {

    CreateNewAppPage createNewAppPage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void userinfo() {
        user = UserBuilder.admin();
        createNewAppPage = loginPage.loginAs(user)
                .clickOnMyApplicationsButton()
                .clickOnAddNewAppButton();
    }

    //Create a new application without images. Verify that it is displayed correctly and can be downloaded.
    @Test(groups = {"Application"})
    public void createApplicationWithoutImageTest() {
        Application application = AppBuilder.appWithoutImages("Games");
        ListApplicationsPage listApplicationsPage = createNewAppPage
                .fillAppFormWithoutImages(application)
                .clickOnCreateButton();
        assertTrue(listApplicationsPage.isAppVisibleOnPage(application.getTitle()), "App is displayed correctly");

        AppInfoPage appInfoPage = listApplicationsPage.clickOnDetailsButton(application.getTitle());
        assertTrue(appInfoPage.isDownloadButtonVisible(), "App can be downloaded");
    }

    //Edit an application without images, and verify that the changes were applied.
    @Test(groups = {"Application"})
    public void editApplicationAndVerifyChangesAppliedTest() {
        Application application = AppBuilder.appWithoutImages("Games");

        ListApplicationsPage listApplicationsPage = createNewAppPage
                .fillAppFormWithoutImages(application)
                .clickOnCreateButton();
        assertTrue(listApplicationsPage.isAppVisibleOnPage(application.getTitle()),
                "New app is visible on listOfApplicationsPage");

        String getNewDescription = "New description" + random();
        AppInfoPage appInfoPage = listApplicationsPage
                .clickOnDetailsButton(application.getTitle())
                .clickOnEditButton()
                .clearDescription()
                .getEditedDescription(getNewDescription)
                .clickOnUpdateButton()
                .clickOnMyApplicationButton()
                .clickOnDetailsButton(application.getTitle());
        AppInfo infoBuilder = appInfoPage.getInfoFromAppPage();
        assertEquals(infoBuilder.getDescription(), getNewDescription, "Description is edited");
    }

    //Create a new application with an image and icon.
    @Test(groups = {"Application"})
    public void createAppWithImage() {
        Application applicationWithImage = AppBuilder.appWithImageAndIcon("News");

        ListApplicationsPage listApplicationsPage = createNewAppPage
                .fillAppFormWithImages(applicationWithImage)
                .clickOnCreateButton();
        assertTrue(listApplicationsPage.isAppVisibleOnPage(applicationWithImage.getTitle()),
                "App with image is visible on listApplicationsPage");
    }

    //Create an application, and download it many times (5 or 10, for example).
    // Verify that it has appeared in the most popular apps section, and if you click it, you will be taken to the details' page of this application.
    @Test(groups = {"Application"})
    public void makeAppPopular() {
        Application popularApp = AppBuilder.appWithoutImages("Games");

        HomePage homePage = createNewAppPage
                .fillAppFormWithoutImages(popularApp)
                .clickOnCreateButton()
                .clickOnHomeButton();
        assertTrue(homePage.isAppVisibleOnPage(popularApp.getTitle()), "App is visible on home page");

        AppInfoPage appInfoPage = homePage.clickOnDetailsButtonParticularApp(popularApp.getTitle());
        appInfoPage.makeAppPopular(popularApp.getTitle());
        assertTrue(appInfoPage.isAppPopular(popularApp.getTitle()), "App is visible in popular category");
    }

    //Delete an application and verify that it has been removed.
    @Test(groups = {"Application"})
    public void verifyAppIsRemoved() {
        Application application = AppBuilder.appWithoutImages("Games");

        ListApplicationsPage listApplicationsPage = createNewAppPage.fillAppFormWithoutImages(application)
                .clickOnCreateButton();
        assertTrue(listApplicationsPage.isAppVisibleOnPage(application.getTitle()), "New app is visible");

        listApplicationsPage.clickOnDetailsButton(application.getTitle())
                .clickOnDeleteButton()
                .acceptDeleteAppAlert()
                .clickOnMyApplicationButton();
        assertFalse(listApplicationsPage.isAppVisibleOnPage(application.getTitle()), "App is removed");
    }
}
