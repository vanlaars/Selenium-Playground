package page_objects.pages;

import com.google.common.base.Stopwatch;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;
import core.test_manager.domain.TestData;
import page_objects.conditions.ConditionsBase;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Created by svanlaar on 24/02/2016.
 */
public class HomePage extends BasePage {

    private static Logger logger = Logger.getLogger("ConditionsBase");
    private final String PAGE = get_page_name();


    private final By search_box = By.id("twotabsearchtextbox");
    private final ConditionsBase conditions = new ConditionsBase(getDriver());

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage wait_for_page(){
        // for printing the time in MilliSeconds
        final Stopwatch stopwatch = new Stopwatch();
        final Stopwatch stopwatch_home = new Stopwatch();
        stopwatch.start();
        conditions.condition_common_check(PAGE);
        stopwatch.stop();
        logger.info("Total time common conditions in milliseconds : " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        stopwatch_home.start();
        conditions.condition_homepage_check(PAGE);
        stopwatch_home.stop();
        logger.info("Total time homePage condition in milliseconds : " + stopwatch_home.elapsed(TimeUnit.MILLISECONDS));
        return this;
    }


    public HomePage and(){
        return this;
    }

    public HomePage search(TestData data) {
        type(search_box, data.getSearch_criteria() );
        submit(search_box);
        return this;
    }

}
