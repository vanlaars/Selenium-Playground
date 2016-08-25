package core.config;

import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;

/**
 * Created by svanlaar on 01/05/2016.
 */
@Resource.Classpath("default.properties")
public interface Configuration {

        @Property("test.environment")
        String getHost();

        @Property("element.timeout")
        int getElementTimout();

        @Property("page.load.timeout")
        int getPageLoadTimeout();

        @Property("script.timeout")
        int getScriptTimeout();

        @Property("driver.type")
        String getDriver();

        @Property("remote.url")
        String getRemoteUrl();

}
