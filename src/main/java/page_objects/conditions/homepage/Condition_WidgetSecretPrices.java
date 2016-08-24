package page_objects.conditions.homepage;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;

/**
 * Created by svanlaar on 10/08/2016.
 */
public class Condition_WidgetSecretPrices extends BasePage implements IConditionsHomePage {

    private static Logger logger = Logger.getLogger("Condition_WidgetSecretPrices");


    private static final By widget  = By.className("hmvt-3966-container");
    private static final By close_secret_price  = By.className("close-button");





    /**
     * Constructor that checks to see we're on Google page.
     *
     * @param driver
     *            {@link WebDriver}
     */
    public Condition_WidgetSecretPrices(WebDriver driver) {
        super(driver);
        if (!driver.findElement(widget).isDisplayed()) {
            throw new IllegalStateException("This is not a Secret Price Widget on the Home Page");
        }

    }

    @Override
    public void execute_conditions(String page) {
        logger.info("Clicking on secret price on " + page);
        getDriver().findElement(close_secret_price).click();
    }
}
