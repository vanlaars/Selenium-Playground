package page_objects.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;

/**
 * Created by svanlaar on 24/02/2016.
 */
public class HeaderModule extends BasePage {


    private By my_account = By.id("hdr-signin");
    private By sign_in = By.id("hdr-account");

    public HeaderModule(WebDriver driver) {
        super(driver);
    }

    public HeaderModule navigate_to_sign_in(){
        wait_for_element(sign_in);
        click(sign_in);
        click(my_account);
        return this;
    }

    public HeaderModule and(){
        return this;
    }

}
