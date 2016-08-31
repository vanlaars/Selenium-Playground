package page_objects.pages;

import com.google.common.base.Stopwatch;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;
import core.testdata.SearchTestData;
import page_objects.conditions.ConditionsBase;

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
    private static final By SEARCH_BUTTON = By.xpath(".//*[@id='main-content']/main/div/div/div[1]/div/div[1]/div[1]/div/div/form/fieldset[5]/button");



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

    public HomePage search(SearchTestData data) {
        type(DESTINATION, data.getDestination() );
        click(By.xpath(".//*[@id='citysqm-asi0-s0']/td"));
        type(CHECK_IN, data.getCheck_in());
        type(CHECK_OUT, data.getCheck_out());
        select(OCCUPANCY, data.getOccupancy());
        click(SEARCH_BUTTON);
        return this;
    }

}
