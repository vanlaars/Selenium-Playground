package page_objects.conditions.common;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;

/**
 * Created by svanlaar on 10/08/2016.
 */
public class Condition_500 extends BasePage implements IConditions {


    private static final By error_500  = By.className("error_500");

    /**
     * Constructor that checks to see we're on Google page.
     *
     * @param driver
     *            {@link WebDriver}
     */
    public Condition_500(WebDriver driver) {
        super(driver);
        if (!driver.findElement(error_500).isDisplayed()) {
            throw new IllegalStateException("This is not a 500 Page");
        }

    }

    @Override
    public void execute_conditions(String page) {
        Assert.fail("We found a 500 on page " + page);
    }
}
