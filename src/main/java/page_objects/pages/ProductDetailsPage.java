package page_objects.pages;

import core.testdata.SearchTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page_objects.BasePage;
import page_objects.conditions.ConditionsBase;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by svanlaar on 24/02/2016.
 */
public class ProductDetailsPage extends BasePage {

//    private static final By HOTEL_NAME = By.xpath(".//*[@id='property-header']/div/div/h1");
//    private static final By BOOK_BUTTON = By.id("book-now-button");


    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public ProductDetailsPage and() {
        return this;
    }

    @Step
    public ProductDetailsPage wait_for_page() {
        ConditionsBase conditions = new ConditionsBase(getDriver());
        conditions.condition_common_check(get_page_name());
        return this;
    }


    @Step
    public void book_room() {
//        wait_for_element(BOOK_BUTTON);
//        click(BOOK_BUTTON);
        click(get_random_room());
    }

    private By get_random_room() {
        List<By> myList = new ArrayList();

        myList = get_hotel_rooms();
        By random_hotel = get_random_from_list(myList);
        return random_hotel;
    }

    private List<By> get_hotel_rooms() {
        List<By> myList = new ArrayList();
        int a=1;
        boolean breakLoop = false;

        while (!breakLoop)
        {
            try {
                By element = By.id(String.format("room-%s-rateplan-1", a));
                getDriver().findElement(element).isDisplayed();
                System.out.println("Element " + element.toString());
                myList.add(element);
            } catch (NoSuchElementException e){
                breakLoop = true;
            }
            a++;
        }

        return myList;
    }

}
