package user_journey;

import core.BaseTest;
import core.testdata.SearchTestData;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Created by svanlaar on 27/02/2016.
 */
public class uj_simpleCart extends BaseTest {

    private SearchTestData search_test_data;
    private static Logger logger = Logger.getLogger("uj_simpleCart");

    @BeforeMethod
    public void setUp(){
        search_test_data = new SearchTestData("Amsterdam, the Netherlands", 7, "1 room, 1 adult");
    }

    @Test
    public void uj_search_happy()  {
        final String expected = "com/XXX_hotel/details.html";
        final String actual_url;
        page_Home().wait_for_page().and().search(search_test_data);
        page_Search_Result().wait_for_page().and().select_deal_of_the_day().and().switch_to_tab(1);

        actual_url = getDriver().getCurrentUrl();
        Assert.assertTrue(actual_url.contains(expected), "URL should contain " + expected + " But was " + actual_url);

    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        logger.info("Going to destroy test data: " + search_test_data.toString());
    }

}
