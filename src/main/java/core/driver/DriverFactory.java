package core.driver;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

/**
 * Created by svanlaar on 02/03/2016.
 */
public class DriverFactory {

    private static DriverFactory instance = new DriverFactory();
    WebDriver returnedDriver;


    private DriverFactory()
    {
        //Do-nothing..Do not allow to initialize this class from outside
    }

    public static DriverFactory getInstance()
    {
        return instance;
    }

    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() // thread local driver object for webdriver
    {
        @Override
        protected WebDriver initialValue()
        {
            Config conf = ConfigFactory.load();
            returnedDriver = selectDriver(conf.getString("driver.browser"));
            return returnedDriver;
        }
    };

    private WebDriver selectDriver(String browserSettings) {
        WebDriver returningDriver = null;
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        System.out.println("Our config is " + browserSettings);

        if (browserSettings.equals("chrome")){
            returningDriver = new ChromeDriver(capabilities);
        }

        if (browserSettings.equals("remote")){
            returningDriver = new RemoteWebDriver(getRemoteUrl(), capabilities);
        }

        return returningDriver;
    }

    private URL getRemoteUrl(){
        URL url = null;
        try {
            url = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    public WebDriver getDriver() // call this method to get the driver object and launch the browser
    {
        return driver.get();
    }

    public void removeDriver() // Quits the driver and closes the browser
    {
        driver.get().quit();
        driver.remove();
    }
}
