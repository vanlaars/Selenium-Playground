package user_journey;

import com.relevantcodes.extentreports.ExtentTest;
import core.testdata.SearchTestData;
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

    private SearchTestData search_test_data;
    private static Logger logger = Logger.getLogger("uj_simpleCart");

    @BeforeMethod
    public void setUp(){
        search_test_data = new SearchTestData("Amsterdam, the Netherlands", "7", "1 room, 1 adult");
    }

    @Test
    public void conditions_test()  {
        final String force_404 = "/404";
        final String url = "https://uk.int-milan-hotels.com";
        final String MVT_ALERT_HOME_PAGE = "2176.1";

        logger.info("We are forcing MVT " + MVT_ALERT_HOME_PAGE);
        force_mvt(MVT_ALERT_HOME_PAGE, url);
        page_Home().wait_for_page();

        logger.info("***************************");
        logger.info("We are forcing a 404 error");
        getDriver().get(url + force_404);
        page_Home().wait_for_page();
    }

    @Test
    public void uj_search_happy()  {
        page_Home().wait_for_page().and().search(search_test_data);
        page_Search_Result().wait_for_page().and().select_hotel_by_name("De L'Europe Amsterdam").and().switch_to_tab(1);

        assertThat("Should be details page", getDriver().getCurrentUrl().contains("com/XXX_hotel/details.html"));

        header.navigate_to_sign_in();
//        signIn_page.sign_in(test_data).and().check_not_signedIn();


        // *** Hamcrest assertions ***
        assertThat(getDriver().getCurrentUrl(), is(equalTo("http://uk.hotels.com")));
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        logger.info("Going to destroy test data: " + search_test_data.toString());
    }

}
