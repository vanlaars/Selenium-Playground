package core;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import core.advancedPO.AdvancedPageFactory;
import core.advancedPO.AdvancedPageFactory2;
import core.driver.DriverFactory;
import core.utils.BrowserLogsLogger;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page_objects.pages.BookingPage;
import page_objects.pages.HomePage;
import page_objects.pages.ProductDetailsPage;
import page_objects.pages.SearchResultPage;
import page_objects.variants.searchAvancedPO.ISearchPage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;


/**
 * Created by svanlaar on 24/02/2016.
 */
public class BaseTest {

    // ============= PAGES ===================
    public ISearchPage getAdvancedSearchPage() {
        return AdvancedPageFactory2.constructPage(getDriver(),
                ISearchPage.class);
    }

    public HomePage page_Home() {
        return new HomePage(driver);
    }

    public ProductDetailsPage page_Product_Details(){
        return new ProductDetailsPage(driver);
    }

    public SearchResultPage page_Search_Result() {
        return new SearchResultPage(driver);
    }

    public BookingPage page_Booking() {
        return new BookingPage(driver);
    }


    private ISearchPage page;

    // Driver
    private WebDriver driver;

    // Utils
    private static Logger logger = Logger.getLogger("BaseTest");
    private static Config conf = ConfigFactory.load();
    private BrowserLogsLogger loggerTest = new BrowserLogsLogger();

    @BeforeMethod
    public void setup_base() {
        this.driver = DriverFactory.getInstance().getDriver();
        setTimeouts();
        this.driver.get(conf.getString("environment.url"));
        this.driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown_base(ITestResult result)  {
        capture_screenshot(result);

        Cookie mvtHistory = getDriver().manage().getCookieNamed("mvthistory");
        System.out.println("MVT history is " + mvtHistory.getValue() + " and is secure? " + mvtHistory.isSecure());

        analyzeLog(result);



        // close the driver..
       DriverFactory.getInstance().removeDriver();
    }

    // ============ TIMEOUTS ====================

    private void setTimeouts() {
        this.driver.manage().timeouts().implicitlyWait(conf.getLong("driver.timeout.element"), TimeUnit.SECONDS);
        this.driver.manage().timeouts().pageLoadTimeout(conf.getLong("driver.timeout.page"), TimeUnit.SECONDS);
        this.driver.manage().timeouts().setScriptTimeout(conf.getLong("driver.timeout.script"), TimeUnit.SECONDS);
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


    public WebDriver getDriver() {
        return DriverFactory.getInstance().getDriver();
    }

    private void analyzeLog(ITestResult result)  {
        try{
            LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
            loggerTest.log_me(result.getName(), logEntries);
        } catch (NullPointerException e){
            logger.info(Level.SEVERE + "We got an NullPointerException while grabbing the browser logs ", e);
        }
    }

    public void force_mvt(String variant, String url) {
        logger.info("Forcing MVT " + variant);
        driver.get(url + "?mvariant=" + variant);
    }


}
