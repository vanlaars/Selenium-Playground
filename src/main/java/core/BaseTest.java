package core;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import core.advancedPO.AdvancedPageFactory;
import core.driver.DriverFactory;
import core.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page_objects.BasePage;
import page_objects.modules.HeaderModule;
import page_objects.pages.HomePage;
import page_objects.pages.SearchResultPage;
import page_objects.pages.SignIn_Page;
import page_objects.variants.searchAvancedPO.ISearchPage;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


/**
 * Created by svanlaar on 24/02/2016.
 */
public class BaseTest {

    // Pages
    public HomePage home_page;
    public BasePage base_page;
    public SearchResultPage sr_page;
    public HeaderModule header;
    public SignIn_Page signIn_page;


    public ISearchPage getAdvancedSearchPage() {
        return AdvancedPageFactory.constructPage(getDriver(),
                ISearchPage.class);
    }


    public HomePage page_Home(){
        return new HomePage(driver);
    }

    public SearchResultPage page_Search_Result(){
        return new SearchResultPage(driver);
    }

    private ISearchPage page;

    // Driver
    private WebDriver driver;

    // Utils
    public StringUtils stringUtils = new StringUtils();
    private static Logger logger = Logger.getLogger("BaseTest");
    private static Config conf = ConfigFactory.load();

    @BeforeMethod
    public void setup_base() {
        set_driver();
        init_pages();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown_base(ITestResult result) throws IOException {
        capture_screenshot(result);

        // analyze logs
        analyzeLog(result);

        // close the driver..
        DriverFactory.getInstance().removeDriver();
    }

    @AfterClass
    public void secure_tear_down(){
        DriverFactory.getInstance().removeDriver();
    }

    // ========= SET DRIVER ==============
    private void set_driver() {
        this.driver =  DriverFactory.getInstance().getDriver();
        this.driver.manage().window().maximize();
        setTimeouts();
        this.driver.get(conf.getString("environment.url"));
    }


    // ========= INITIALIZE PAGES ==============
    private void init_pages() {
        home_page = new HomePage(this.driver);
        base_page = new BasePage(this.driver);
        sr_page = new SearchResultPage(this.driver);
        header = new HeaderModule(this.driver);
        signIn_page = new SignIn_Page(this.driver);
        page = AdvancedPageFactory.constructPage(getDriver(),
                ISearchPage.class);
    }

    // ============ TIMEOUTS ====================

    private void setTimeouts() {
        this.driver.manage().timeouts().implicitlyWait(conf.getLong("driver.timeout.element"), TimeUnit.SECONDS);
        this.driver.manage().timeouts().pageLoadTimeout(conf.getLong("driver.timeout.page"), TimeUnit.SECONDS);
        this.driver.manage().timeouts().setScriptTimeout(conf.getLong("driver.timeout.script"), TimeUnit.SECONDS);
    }

    // ========= UTILS & CONFIG ==============

    public String getRandom_String(int length) {
        return stringUtils.get_random_string(length);
    }

    private void capture_screenshot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                // Create reference of TakesScreenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                // Call method to capture screenshot
                File source = ts.getScreenshotAs(OutputType.FILE);
                // Copy files to specific location here it will save all screenshot in our project target home directory and
                // result.getName() will return name of test case so that screenshot name will be same
                FileUtils.copyFile(source, new File("./target/test-output/screenshots/" + result.getName() + ".png"));
                logger.error("Screenshot taken");
            } catch (Exception e) {
                logger.error("Exception while taking screenshot " + e.getMessage());
            }
        }
    }


    public WebDriver getDriver(){
        return DriverFactory.getInstance().getDriver();
    }

    public void analyzeLog(ITestResult result) {
        ExtentReports extent = new ExtentReports("./target/test-output/TestReport.html" , false);
        ExtentTest test = extent.startTest(result.getName(), "We need to check the browser logs");
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            logger.info(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            test.log(LogStatus.INFO, new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());

            //do something useful with the data
            if (entry.getLevel() == Level.SEVERE && entry.getMessage().contains("404")){
                logger.info("404 detected in the browser logs " + entry.getMessage() + " during test " + result.getName());
                test.log(LogStatus.WARNING, "404 detected in the browser logs " + entry.getMessage() + " during test ");
            }
        }
        extent.endTest(test);
        extent.flush();
    }

    public void force_mvt(String variant, String url){
        driver.get(url + "?mvariant=" + variant);
    }


}
