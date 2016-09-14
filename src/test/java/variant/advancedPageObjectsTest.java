package variant;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import core.BaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by svanlaar on 25/04/2016.
 */
public class advancedPageObjectsTest extends BaseTest {

    private static final String URL_EXPECTED = "https://en.wikipedia.org/wiki/Cheese";
    private static final String SEARCH_QUERY = "Cheese - Wikipedia, the free encyclopedia";


    @Test(description = "Advanced PageObject Test with jUnit assertion navigation to Google")
    public void test_advanced_page_factory() {
//        String url = "http://www.google.com";
        String url = "http://www.bing.com";

        getDriver().get(url);

        getAdvancedSearchPage().search(SEARCH_QUERY);

        // jUnit assertion
        String current_url = getDriver().getCurrentUrl();
        Assert.assertEquals("The url is " + current_url + " and should be " + URL_EXPECTED, URL_EXPECTED, current_url);
    }

}
