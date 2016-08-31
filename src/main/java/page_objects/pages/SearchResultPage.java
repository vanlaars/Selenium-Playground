package page_objects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;
import page_objects.conditions.ConditionsBase;

/**
 * Created by svanlaar on 24/02/2016.
 */
public class SearchResultPage extends BasePage {

    protected WebDriver driver;
    private final ConditionsBase conditions = new ConditionsBase(getDriver());

    private static final By SEARCH_RESUlTS = By.id("search-results");
    private static final By DEAL_OF_THE_DAY = By.xpath("//*[@id='listings']/ol/li[1]/article/div[2]/div[1]/h3/a");


    public SearchResultPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public SearchResultPage and() {
        return this;
    }

    public SearchResultPage wait_for_page() {
        wait_for_element(SEARCH_RESUlTS);
        conditions.condition_common_check(get_page_name());
        return this;
    }


    public SearchResultPage select_deal_of_the_day() {
        click(DEAL_OF_THE_DAY);
        return this;
    }
}
