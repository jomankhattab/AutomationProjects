package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.LoginPage;
import com.aventstack.extentreports.ExtentTest;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver ;
    protected LoginPage loginPage;
    protected static ExtentReports extent;
    protected static ExtentSparkReporter htmlReporter;
    protected ExtentTest test;

    @BeforeSuite
    public void setUpExtentReport() {
        extent = new ExtentReports();
        htmlReporter = new ExtentSparkReporter("src/test/java/utils/reports/UI_test_report.html");
        extent.attachReporter(htmlReporter);
    }

    @BeforeClass
    public void setup (){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        openWebsite();
    }

    public void openWebsite(){
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        handleAlertIfPresent(); // ✅ أي alert يظهر عند فتح الموقع
    }

    @BeforeMethod
    public void beforeMethod() {
        handleAlertIfPresent(); // ✅ أي alert قبل كل test
    }

    // ===============================
    // دالة للتعامل مع أي alert
    // ===============================
    public void handleAlertIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            System.out.println("Alert accepted automatically.");
        } catch (NoAlertPresentException | TimeoutException e) {
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
