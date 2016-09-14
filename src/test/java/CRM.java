import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import core.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by svanlaar on 02/09/2016.
 */
public class CRM extends BaseTest{


    @Test
    public void crm_test() {


        getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        getDriver().manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

        // ************* WE NEED TO START TESTING

        // OPEN PAGE
        getDriver().get("https://pages.mail.hotels.com/vawp/localisation/");


        int rows = getDriver().findElements(By.xpath("html/body/table/tbody/tr/td/table[2]/tbody/tr")).size();
//        System.out.println("We found  " + rows + " email templates to check" );

        for (int i = 2; i < rows; i++) {
            if ((i % 2) == 0) {
                String FriendlyName = getDriver().findElement(By.xpath(String.format("html/body/table/tbody/tr/td/table[2]/tbody/tr[%s]/td[2]", i))).getText();

                System.out.println(" Friendly Name is : " + FriendlyName);

                int img = i / 2;

                // open
                getDriver().findElement(By.xpath(String.format("//*[@id='arrow_l%s']/a/img", img))).click();

                sleep(2000);

                for (int row = 1; row < check_table_size(); row++) {
                    int check_row_size = getDriver().findElements(By.xpath(String.format("//*[@id='email_1']/tbody/tr[%s]/td", row))).size();

                    for (int column = 1; column < check_row_size; column++) {

                        // wait for table
                        By tableLocator = By.xpath(String.format("//*[@id='email_1']/tbody/tr[%s]/td[%s]/a", row, column));
                        wait_for_element(tableLocator);

                        System.out.println("Clicking on  row " + row + " column " + column);

                        String POS = getDriver().findElement(tableLocator).getText();

                        // forcing HUNGARY : 5
//                        getDriver().findElement(By.xpath("//*[@id='email_1']/tbody/tr[6]/td[5]/a")).click();

                        // Force Norway
//                        getDriver().findElement(By.xpath(".//*[@id='email_1']/tbody/tr[12]/td[5]/a")).click();

                        getDriver().findElement(tableLocator).click();
                        System.out.println("Checking POS : " + POS);

                        // Could be email specific.. Put in config if so..

                        System.out.println("Switch to tab that is open");
                        switch_to_tab(1);
                        wait_for_element(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td/div/table/tbody/tr/td[1]/a/img"));

                        // *************** CHECKING TOP LINKS
                        check_top_links(POS);
                        check_config_elements();


                        // Close tab for specific email set
                        getDriver().close();

                        // Switch to CRM email app
                        switch_to_tab(0);

                    }
                }

                // ************ CLOSE EMAIL TEMPLATE
                getDriver().findElement(By.xpath(String.format("//*[@id='arrow_d%s']/a/img", img))).click();
            }

        }
    }

    private void check_config_elements() {
        Config conf = ConfigFactory.load("t_cc_res.conf");

        System.out.println("Size of elements in config is " + conf.getObject("elements").size());


        String table_to_check = "html/body/table/tbody/tr/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr";
        check_size_table(table_to_check);


//        for (int elements=1; elements < conf.getObject("elements").size() + 1; elements ++ ){
//            String elementFormat = conf.getString(String.format("elements.element_%s.locator", elements));
//            getDriver().findElement(By.xpath(elementFormat)).click();
//            switch_or_back();
//        }


        // and for this config we need to check : Here are the hotels we discussed

    }

    private int check_table_size() {
        wait_for_element(By.xpath("html/body/table/tbody/tr/td/table[2]/tbody/tr[3]/td"));
        int table = getDriver().findElements(By.xpath(".//*[@id='email_1']/tbody/tr")).size();
        return table;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void check_top_links(String POS) {
        int top_link_count = getDriver().findElements(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td[1]/table/tbody/tr/td")).size();
        for(int top_links=1; top_links < top_link_count; top_links++) {

            String format_top_link = "html/body/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td[1]/table/tbody/tr/td[%s]";


            String linkText = getDriver().findElement(By.xpath(String.format(format_top_link, top_links))).getText();
            System.out.println("Our linkText is " + linkText);
            if (linkText.isEmpty() ) {
                System.out.println("Link has no text, can't be clicked , detected in POS " + POS);
            } else {
                System.out.println("Clicking on lixnkText " + linkText);
                getDriver().findElement(By.xpath(String.format(format_top_link, top_links))).click();
                switch_or_back();
            }

        }
        System.out.println("Done checking top_links ");
    }

    private void switch_or_back() {
        ArrayList<String> tabs2 = new ArrayList<String>(getDriver().getWindowHandles());
        if (tabs2.size() > 2) {
            switch_to_tab(2);
            check_404_not_present();
            check_page_after_click();
            getDriver().close();
            switch_to_tab(1);
        } else {
            check_404_not_present();
            check_page_after_click();
            getDriver().navigate().back();
//            wait_for_element(By.xpath(String.format(format_top_link, top_links)));
        }
    }

    private void check_page_after_click() {
        if (getDriver().getCurrentUrl().contains("service")){
            wait_for_element(By.className("logo"));
            getDriver().findElement(By.className("logo")).isDisplayed();
        } else {
            wait_for_element(By.id("header-bar"));
            getDriver().findElement(By.id("header-bar")).isDisplayed();
        }
    }

    private void wait_for_element(By element){
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void switch_to_tab(int tab){
        ArrayList<String> tabs2 = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs2.get(tab));
    }

    private void check_size_table(String xpath){
        System.out.println("Size of table is " + getDriver().findElements(By.xpath(xpath)).size());
    }

    private void check_404_not_present(){
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            getDriver().findElement(By.className("error_404"));
            Assert.fail("404 is present");
        } catch (NoSuchElementException ex) {
                /* do nothing, link is not present, assert is passed */
        }
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
}
