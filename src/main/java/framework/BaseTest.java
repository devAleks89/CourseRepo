package framework;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.BasicPage;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static final Settings settings = new Settings();
    protected LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        WebDriver driver = settings.getDriver();
        BasePage.settings = settings;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverManager.setDriver(driver);
        loginPage = BasicPage.initPage(BasicPage.class).forceLogout();
    }

    public String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd_HH.mm").format(Calendar.getInstance().getTime());
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            String pathOfReportingDirectory = settings.getPathOfReportingDirectory();
            FileUtils.copyFile(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE),
                    new File(pathOfReportingDirectory + testResult.getName() + "_" + getTimeStamp() + ".jpg"));
        }
        DriverManager.getDriver().quit();
    }
}
