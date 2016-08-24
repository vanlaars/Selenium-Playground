package user_journey;

import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import core.BaseTest;
import core.test_manager.domain.TestData;
import core.test_manager.domain.TestDataBuilder;
import page_objects.pages.HomePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by svanlaar on 27/02/2016.
 */
public class uj_simpleCart extends BaseTest {

    private TestData test_data;
    private static Logger logger = Logger.getLogger("uj_simpleCart");

    @BeforeMethod
    public void setUp(){
        test_data = new TestDataBuilder().setSearch_criteria("Omega Aqua Terra").setUser_name(getRandom_String(5) + "@test.com").setPassword(getRandom_String(10)).setSubsection("Today's Deals").createTestData();
    }

    @Test
    public void conditions_test() throws InterruptedException {
        final String force_404 = "/404";
        final String url = "https://uk.int-milan-hotels.com";
        final String MVT_ALERT_HOME_PAGE = "2176.1";

        logger.info("We are forcing MVT ");
        force_mvt(MVT_ALERT_HOME_PAGE, url);
        page_Home().wait_for_page();

        logger.info("***************************");
        logger.info("We are forcing a 404 error");
//        getDriver().get(url);
        getDriver().get(url + force_404);
        page_Home().wait_for_page();
    }

    @Test
    public void uj_search_happy() throws InterruptedException {
        page_Home().wait_for_page().and().search(test_data);
        sr_page.check_result_found(test_data).and().click_text(test_data.getSubsection());
        header.navigate_to_sign_in();
        signIn_page.sign_in(test_data).and().check_not_signedIn();

        // Hamcrest assertion
        assertThat(getDriver().getCurrentUrl(), is(equalTo("http://amazon.co.uk")));
    }

    @AfterMethod
    public void tearDown(){
        logger.info("Going to destroy : " + test_data.toString());
    }

}
