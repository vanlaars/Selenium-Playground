package page_objects.conditions.common;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;

/**
 * Created by svanlaar on 10/08/2016.
 */
public class Condition_404 extends BasePage implements IConditions {


    private static final By error_404  = By.className("error_404");

    /**
     * Constructor that checks to see we're on Google page.
     *
     * @param driver
     *            {@link WebDriver}
     */
    public Condition_404(WebDriver driver) {
        super(driver);
        if (!driver.findElement(error_404).isDisplayed()) {
            throw new IllegalStateException("This is not a 404 Page");
        }

    }


    @Override
    public void execute_conditions(String page) {
        Assert.fail("We found a 404 on the " + page );
    }
}
