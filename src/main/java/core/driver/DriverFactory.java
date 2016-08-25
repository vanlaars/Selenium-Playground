package core.driver;

import core.config.Configuration;
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
    private static Configuration config;

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
            System.out.println("************ We got this shit , FUCKAH" + config.getDriver());
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
            return getDriverSelect(capabilities);
        }
    };

    private WebDriver getDriverSelect(DesiredCapabilities capabilities) {
        WebDriver driver_selected = null;
        System.out.println("************ We got this shit , FUCKAH" + config.getDriver());
        switch (config.getDriver()) {
            case "chrome":
                driver_selected = new ChromeDriver(capabilities); // can be replaced with other browser drivers
                break;
            case "remote":
                try {
                    driver_selected = new RemoteWebDriver(new URL(config.getRemoteUrl()), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            default:
                throw new IllegalArgumentException("The config or override value was invalid, we received as a browser  " + config.getDriver());
        }
        return driver_selected;
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
