package core.advancedPO;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.reflections.Reflections;
import page_objects.BasePage;

import java.util.Set;

/**
 * Created by svanlaar on 25/04/2016.
 */
public class AdvancedPageFactory3 extends BasePage {


//    private static WebDriver driver;
    private static Logger logger = Logger.getLogger("AdvancedPageFactory");


    public AdvancedPageFactory3(WebDriver driver) {
        super(driver);
    }

    /**
     * Instantiate a PageObject of matching Interface.
     *
     * @param driver
     * @param pageClassToProxy
     * @return
     */
    public static <T> T constructPage(WebDriver driver,
                                      Class<T> interfaceToProxy) {

        // Get interface namespace.
        String namespace = interfaceToProxy.getPackage().getName();

        // Get a set of classes that implements the interfaces.
        Reflections reflections = new Reflections(namespace);
        Set<Class<? extends T>> subTypes = reflections.getSubTypesOf(interfaceToProxy);


        for (Class<? extends T> classObj : subTypes)
            try {
                T pageInstance = PageFactory.initElements(driver, classObj);

                // Page object instantiated correctly, we have found a match to
                // return.
                logger.info("Found a matching PageObject: " + classObj.getName());
                return pageInstance;
            } catch (Exception e) {
                //These are not the droids you're looking for. Swallowing exception
            }
        return null;
    }



}
