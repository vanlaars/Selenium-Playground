package page_objects.pages;

import core.testdata.SearchTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page_objects.BasePage;
import page_objects.conditions.ConditionsBase;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by svanlaar on 24/02/2016.
 */
public class BookingPage extends BasePage {

    private final ConditionsBase conditions = new ConditionsBase(getDriver());
    private static final By HOTEL_NAME = By.xpath("//*[@id='headline']/h1");
    private static final By HOTEL_NAME_INFO_SECTION = By.xpath("//*[@id='hotel-info']/h3");

    private static final By BOOK_BUTTON = By.id("book-button");

    public BookingPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public BookingPage and() {
        return this;
    }

    @Step
    public BookingPage wait_for_page() {
        conditions.condition_common_check(get_page_name());
        return this;
    }

    @Step
    public BookingPage check_booking_page() {
        element_displayed(BOOK_BUTTON);
        return this;
    }

}
