package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import com.aventstack.extentreports.ExtentTest;


import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

public class BaseTest {

    protected WebDriver driver ;
    protected LoginPage loginPage;
    protected static ExtentReports extent;
    protected static ExtentSparkReporter htmlReporter;
    protected ExtentTest test;


    @BeforeSuite
    public void setUpExtentReport() {
        extent=new ExtentReports();
        htmlReporter = new ExtentSparkReporter("src/test/java/utils/reports/UI_test_report.html");
        extent.attachReporter(htmlReporter);
    }


    @BeforeClass
    public void setup (){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        openWebsite();
    }

    public void  openWebsite(){
        driver.get ("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }



    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }


    }





}
