package variant;

import core.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by svanlaar on 31/08/2016.
 */
public class conditions_404 extends BaseTest {
    private static final String URL = "https://uk.int-milan-hotels.com";


    @Test
    public void conditions_test_404()  {
        final String force_404 = "/404";


        getDriver().get(URL + force_404);
        page_Home().wait_for_page();
    }

}
