package core.utils;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by svanlaar on 29/02/2016.
 */
public class StringUtils {

    public String get_random_string(int length){
        return RandomStringUtils.randomAlphanumeric(length).toUpperCase();
    }

}
