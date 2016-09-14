package user_journey;

import core.BaseTest;
import core.testdata.SearchTestData;
import org.apache.log4j.Logger;
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
        search_test_data = new SearchTestData("Amsterdam, Netherlands", 7, "1 room, 1 adult");
    }

    @Test
    public void uj_search_selecting_first_hotel()  {
        page_Home().wait_for_page().and().search(search_test_data);
        page_Search_Result().wait_for_page().and().select_first_hotel();
        page_Product_Details().wait_for_page().and().book_room();
        page_Booking().wait_for_page().and().check_booking_page();
    }

    @Test
    public void uj_search_random_hotel_with_retry()  {
        page_Home().wait_for_page().and().search(search_test_data);
        page_Search_Result().wait_for_page().and().select_random_hotel(search_test_data);
        page_Product_Details().wait_for_page().and().book_room();
        page_Booking().wait_for_page().and().check_booking_page();
    }


    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        logger.info("Going to destroy test data: " + search_test_data.toString());
    }

}
