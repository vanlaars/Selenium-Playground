package variant;

import core.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by svanlaar on 31/08/2016.
 */
public class conditions_test_mvt extends BaseTest {
    private static final String URL = "https://uk.int-milan-hotels.com";
    

    @Test
    public void conditions_test_mvt()  {
        final String MVT_ALERT_HOME_PAGE = "2176.1";

        force_mvt(MVT_ALERT_HOME_PAGE, URL);
        page_Home().wait_for_page();
    }


}
