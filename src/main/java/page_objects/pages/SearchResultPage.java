package page_objects.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page_objects.BasePage;
import core.test_manager.domain.TestData;
import page_objects.conditions.ConditionsBase;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by svanlaar on 24/02/2016.
 */
public class SearchResultPage extends BasePage {

    protected WebDriver driver;
    private final ConditionsBase conditions = new ConditionsBase(getDriver());

    private static final By SEARCH_RESUTLS = By.id("search-results");


    public SearchResultPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public SearchResultPage and() {
        return this;
    }

    public SearchResultPage wait_for_page() {
        wait_for_element(SEARCH_RESUTLS);
        conditions.condition_common_check(get_page_name());
        return this;
    }

    public SearchResultPage select_random_hotel(){
        System.out.println("We need to implement this");

        boolean stop_parsing;
        do {


        } while (stop_parsing = true);
        return this;
    }

    public SearchResultPage select_hotel_by_name(String hotelName){
        click_text(hotelName);
        return this;
    }
}
