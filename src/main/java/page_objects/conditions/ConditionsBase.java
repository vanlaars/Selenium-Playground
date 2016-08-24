package page_objects.conditions;

import core.advancedPO.AdvancedPageFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;
import page_objects.conditions.common.IConditions;
import page_objects.conditions.homepage.IConditionsHomePage;

import java.util.concurrent.TimeUnit;

/**
 * Created by svanlaar on 11/08/2016.
 */
public class ConditionsBase extends BasePage {

    private static Logger logger = Logger.getLogger("ConditionsBase");

    private Class<IConditions> commonConditions = IConditions.class;
    private Class<IConditionsHomePage> conditionsHomePage = IConditionsHomePage.class;


    public ConditionsBase(WebDriver driver) {
        super(driver);
    }

    public IConditions getCondtions() {
        return AdvancedPageFactory.constructPage(getDriver(),
                commonConditions);
    }

    public IConditionsHomePage getCondtionsHomePage() {
        return AdvancedPageFactory.constructPage(getDriver(),
                conditionsHomePage);
    }

    public void condition_common_check(String page) {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.NANOSECONDS);
        try {
            getCondtions().execute_conditions(page);
        } catch (NullPointerException nullP) {
            // But these are droids you are not looking for.. Swallowing
            logger.info("No Common Conditions found on " + page);
        }
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void condition_homepage_check(String page) {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
        try {
            getCondtionsHomePage().execute_conditions(page);
        } catch (NullPointerException nullP) {
            // But these are droids you are not looking for.. Swallowing
            logger.info("No specific conditions found on " + page);
        }
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}
