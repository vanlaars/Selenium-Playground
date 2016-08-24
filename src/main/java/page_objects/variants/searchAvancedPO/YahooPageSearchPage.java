package page_objects.variants.searchAvancedPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;

import java.util.ArrayList;

/**
 * Created by svanlaar on 25/04/2016.
 */
public class YahooPageSearchPage extends BasePage implements ISearchPage  {

    public YahooPageSearchPage(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().contains("yahoo.com")) {
            throw new IllegalStateException("This is not the Yahoo Page");
        }
    }

    private By searchBox = By.id("UHSearchBox");

    @Override
    public void search(String query) {
        final int second_tab = 1;

        getDriver().findElement(searchBox).sendKeys(query);
        getDriver().findElement(searchBox).submit();
        getDriver().findElement(By.linkText(query)).click();

        ArrayList<String> tabs2 = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs2.get(second_tab));
    }

}
