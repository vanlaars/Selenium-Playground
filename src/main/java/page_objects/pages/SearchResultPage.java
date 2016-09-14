package page_objects.pages;

import core.testdata.SearchTestData;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;
import page_objects.conditions.ConditionsBase;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by svanlaar on 24/02/2016.
 */
public class SearchResultPage extends BasePage {

    private static final String LISTINGS = "//*[@id='listings']/ol/li[%s]";
    private static final String HOTEL_LISTINGS_HOTEL_NAME = LISTINGS + "/article/div/div[1]/h3/a";
    private static final By FIRST_LISTING = By.xpath("//*[@id='listings']/ol/li[1]//h3/a");


     public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public SearchResultPage and() {
        return this;
    }

    @Step
    public SearchResultPage wait_for_page() {
        ConditionsBase conditions = new ConditionsBase(getDriver());
        conditions.condition_common_check(get_page_name());
        return this;
    }

    @Step
    public SearchResultPage select_random_hotel(SearchTestData search_test_data) {
        click_with_retries(3);
        wait_for_window();
        switch_to_tab(1);
        return this;
    }

    @Step
    public SearchResultPage select_first_hotel(){
        wait_for_element(FIRST_LISTING);
        click(FIRST_LISTING);
        return this;
    }

    private void click_with_retries(int retries) {

        RetryPolicy retryPolicy = new RetryPolicy()
                .retryOn(NoSuchElementException.class)
                .withMaxRetries(retries)
                .withDelay(1, TimeUnit.SECONDS);

        Failsafe.with(retryPolicy).run(() -> retry_click());
    }

    private void retry_click(){
        List<String> number_of_rooms = get_rooms();
        int randomNum = 1 + (int)(Math.random() * getDriver().findElements(By.cssSelector(".hotel-wrap")).size());
        By randomHotel = By.xpath(String.format(HOTEL_LISTINGS_HOTEL_NAME, randomNum));
        click(randomHotel);
    }


    private List<String> get_rooms() {
        List<String> myList = new ArrayList();
        int a=1;
        boolean breakLoop = false;

        while (!breakLoop)
        {
            try {
                By element = By.xpath(String.format(LISTINGS, a));
                getDriver().findElement(element).isDisplayed();
                myList.add(element.toString());
            } catch (NoSuchElementException e){
                breakLoop = true;
            }
            a++;
        }

        return myList;
    }
}
