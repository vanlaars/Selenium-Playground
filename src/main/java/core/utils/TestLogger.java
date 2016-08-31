package core.utils;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by svanlaar on 30/08/2016.
 */
public class TestLogger {

    public void log_me(String test, LogEntries logEntries){
        String filePath = new File("").getAbsolutePath();

        Logger logger = Logger.getLogger("TestLogger");
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler(filePath + "/target/test-output/logs/" + test + ".log");
            logger.addHandler(fh);
            //logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
//            logger.info("My first log @ ") ;

            for (LogEntry entry : logEntries) {
                logger.info(LogStatus.INFO + " : " + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            }

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}

