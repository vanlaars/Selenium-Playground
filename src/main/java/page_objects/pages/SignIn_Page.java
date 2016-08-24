package page_objects.pages;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_objects.BasePage;
import core.test_manager.domain.TestData;

/**
 * Created by svanlaar on 02/03/2016.
 */
public class SignIn_Page extends BasePage {

    private By login = By.id("ap_email");
    private By password = By.id("ap_password");
    private By error_message = By.id("auth-error-message-box");
    private By submit_signIn_form = By.id("signInSubmit");


    public SignIn_Page(WebDriver driver) {
        super(driver);
    }

    public SignIn_Page and() {
        return this;
    }

    public SignIn_Page sign_in(TestData data){
        type(login, data.getUser_name());
        type(password, data.getPassword());
        submit(submit_signIn_form);
        return this;
    }
    public SignIn_Page check_not_signedIn() {
        Assert.assertTrue("Error message is not displayed", is_displayed(error_message));
        return this;
    }


}
