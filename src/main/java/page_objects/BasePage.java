package page_objects;

import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.conditions.common.IConditions;
import page_objects.conditions.homepage.IConditionsHomePage;
import core.advancedPO.AdvancedPageFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by svanlaar on 24/02/2016.
 */
public class BasePage {

    private static WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void click_text(String linkText) {
        getDriver().findElement(By.linkText(linkText)).click();
    }

    public void click(By element){
        getDriver().findElement(element).click();
    }

    public void submit(By element){
        getDriver().findElement(element).submit();
    }

    public void type(By element, String txt){
        this.getDriver().findElement(element).sendKeys(txt);
    }

    public boolean is_displayed(By element){
        return this.getDriver().findElement(element).isDisplayed();
    }

    public String get_text_from_element(By element){
        return this.getDriver().findElement(element).getText();
    }

    public void wait_for_element(By element){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public String get_page_name(){
        return getClass().getName().toString().replace("page_objects.pages.", "");
    }

}
