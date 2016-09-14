package page_objects.variants.searchAvancedPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;

import java.util.ArrayList;

/**
 * Created by svanlaar on 25/04/2016.
 */
public class BingSearchPage extends BasePage implements ISearchPage  {


    private static final By search_box  = By.id("sb_form_q");
    private static final By search_button  = By.id(" sb_form_go");


    public BingSearchPage(WebDriver driver) {
        super(driver);
        if (!driver.findElement(search_box).isDisplayed())
            throw new IllegalStateException("This is not the Yahoo Page");
    }

    @Override
    public void search(String query) {
        type(search_box, query);
        submit(search_box);
//        click(search_button);
        click_text(query);
    }

}
