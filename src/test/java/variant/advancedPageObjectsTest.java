package variant;

import org.junit.Assert;
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
    private static final String MAKE_URL_FAIL = "FAIL";



    @Test(description = "Advanced PageObject Test with jUnit assertion navigation to Google", dataProvider = "URLS")
    public void test_advanced_page_factory(String url) {
        getDriver().get(url);

        getAdvancedSearchPage().search(SEARCH_QUERY);

        // jUnit assertion
        Assert.assertEquals("The url should be correct", URL_EXPECTED + MAKE_URL_FAIL, getDriver().getCurrentUrl());
    }

    @DataProvider(name = "URLS")
    public static Object[][] urls_advanced_page_factory() {
        return new Object[][] {{"http://www.google.com"}, {"http://www.yahoo.com"}};
    }

}
