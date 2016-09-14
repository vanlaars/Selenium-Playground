package page_objects.pages;

import com.google.common.base.Stopwatch;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;
import core.testdata.SearchTestData;
import page_objects.conditions.ConditionsBase;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Created by svanlaar on 24/02/2016.
 */
public class HomePage extends BasePage {

    private static Logger logger = Logger.getLogger("ConditionsBase");
    private final String PAGE = get_page_name();
//    2327.0 MVT kller Autosuggest

    private static final By DESTINATION = By.id("qf-0q-destination");
    private static final By CHECK_IN = By.id("qf-0q-localised-check-in");
    private static final By CHECK_OUT = By.id("qf-0q-localised-check-out");
    private static final By OCCUPANCY = By.id("qf-0q-compact-occupancy");
    private static final By CLOSE_AUTOSUGGEST = By.cssSelector(".cta.cta-link");
    private static final By SEARCH_FORM =  By.xpath(".//*[@id='main-content']//form");



    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step
    public HomePage wait_for_page(){
        ConditionsBase conditions = new ConditionsBase(getDriver());
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

    @Step
    public HomePage and(){
        return this;
    }

    @Step
    public HomePage search(SearchTestData data) {
        type(DESTINATION, data.getDestination());
        wait_for_element(CLOSE_AUTOSUGGEST);
        click(CLOSE_AUTOSUGGEST);
        type(CHECK_IN, data.getCheck_in());
        type(CHECK_OUT, data.getCheck_out());
        select(OCCUPANCY, data.getOccupancy());
        submit(SEARCH_FORM);
        return this;
    }

}
