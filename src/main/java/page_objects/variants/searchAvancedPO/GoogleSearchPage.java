package page_objects.variants.searchAvancedPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;

/**
 * Created by svanlaar on 25/04/2016.
 */
public class GoogleSearchPage extends BasePage implements ISearchPage{


    private static final By search_box  = By.className("gsfi");

    /**
     * Constructor that checks to see we're on Google page.
     *
     * @param driver
     *            {@link WebDriver}
     */
    public GoogleSearchPage(WebDriver driver) {
        super(driver);
        if (!driver.findElement(search_box).isDisplayed())
            throw new IllegalStateException("This is not the Google Page");

    }

    public void search(String query) {
        type(search_box, query);
        submit(search_box);
        click_text(query);
    }

}
