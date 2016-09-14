package core.advancedPO;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.reflections.Reflections;
import page_objects.BasePage;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by svanlaar on 25/04/2016.
 */
public class AdvancedPageFactory2 extends BasePage {


//    private static WebDriver driver;
    private static Logger logger = Logger.getLogger("AdvancedPageFactory");


    public AdvancedPageFactory2(WebDriver driver) {
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
        String interfaceName = interfaceToProxy.getPackage().getName();

        // Get a set of classes that implements the interfaces.
        Reflections reflectedClass = new Reflections(interfaceName);
        Set<Class<? extends T>> subTypes = reflectedClass.getSubTypesOf(interfaceToProxy);


        for (Iterator<Class<? extends T>> iterator = subTypes.iterator(); iterator.hasNext(); ) {
            Class<? extends T> classObj = iterator.next();
            try {
                T pageInstance = PageFactory.initElements(driver, classObj);

                // Page object instantiated correctly, we have found a match to
                // return.
                logger.info("Found a matching PageObject: " + classObj.getName());
                return pageInstance;
            } catch (Exception e) {
                //These are not the droids you're looking for. Swallowing exception
            }
        }
        return null;
    }



}
