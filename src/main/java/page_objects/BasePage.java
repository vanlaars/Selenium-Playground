package page_objects;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by svanlaar on 24/02/2016.
 */
public class BasePage {

    private static WebDriver driver;
    private List<By> myList;

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
        getDriver().findElement(element).sendKeys(txt);
    }

    public boolean is_displayed(By element){
        return this.getDriver().findElement(element).isDisplayed();
    }

    public String get_text_from_element(By element){
        return this.getDriver().findElement(element).getText();
    }

    public void select(By element, String value){
        Select dropdown = new Select(getDriver().findElement(element));
        dropdown.selectByVisibleText(value);
    }

    public void clear_field(By element){
        getDriver().findElement(element).clear();
    }

    public void select_by_index(By element, int value){
        Select dropdown = new Select(driver.findElement(element));
        dropdown.selectByIndex(value);
    }

    public void wait_for_element(By element){
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void wait_for_window(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 60);
        wait.until(WebDriver::getWindowHandles);
    }

    public void switch_to_tab(int tab){
        ArrayList<String> tabs2 = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs2.get(tab));
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public String get_page_name(){
        return getClass().getName().toString().replace("page_objects.pages.", "");
    }

    public String get_attribute(By element, String attribute){
        return getDriver().findElement(element).getAttribute(attribute);
    }

    public void element_displayed(By element){
        getDriver().findElement(element).isDisplayed();
    }

    public By get_random_from_list(List<By> myList) {
        Assert.assertTrue(myList.size()  > 0 );
        Random randomizer = new Random();
        By list_hotels = myList.get(randomizer.nextInt(myList.size()));
        return list_hotels;
    }


}
