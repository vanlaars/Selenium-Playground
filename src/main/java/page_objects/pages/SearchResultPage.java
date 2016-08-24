package page_objects.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;
import core.test_manager.domain.TestData;

import java.util.concurrent.TimeUnit;

/**
 * Created by svanlaar on 24/02/2016.
 */
public class SearchResultPage extends BasePage {

    protected WebDriver driver;
    private static final By no_result = By.cssSelector("#noResultsTitle");
    private static final By search_results_for_criteria = By.id("s-result-count");

    public SearchResultPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void check_nothing_found(){
        Assert.assertTrue(get_text_from_element(no_result).contains("did not match any products."));
    }

    public SearchResultPage check_result_found(TestData test_data) {
        wait_for_element(search_results_for_criteria);
        Assert.assertTrue(get_text_from_element(search_results_for_criteria).contains(test_data.getSearch_criteria()));
        return this;
    }

    public SearchResultPage and() {
        return this;
    }

    public void wait_for_page() {
        System.out.println("We are going to wait for page");
    }
}
