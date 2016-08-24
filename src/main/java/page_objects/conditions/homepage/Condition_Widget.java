package page_objects.conditions.homepage;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;

/**
 * Created by svanlaar on 10/08/2016.
 */
public class Condition_Widget extends BasePage implements IConditionsHomePage {

    private static Logger logger = Logger.getLogger("Condition_Widget");


    private static final By widget  = By.cssSelector(".footer-continue-link");

    /**
     * Constructor that checks to see we're on Google page.
     *
     * @param driver
     *            {@link WebDriver}
     */
    public Condition_Widget(WebDriver driver) {
        super(driver);
        if (!driver.findElement(widget).isDisplayed()) {
            throw new IllegalStateException("This is not a Widget on the Home Page");
        }

    }

    @Override
    public void execute_conditions(String page) {
        logger.info("Clicking on widget on " + page);
        getDriver().findElement(widget).click();
    }
}
