package core.report;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import core.BaseTest;
import core.driver.DriverFactory;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by svanlaar on 02/03/2016.
 */
public class ExtentReportNG extends BaseTest implements IReporter {
    private ExtentReports extent;
    static final String report_location =  "./target/test-output/" + File.separator;


    public static ExtentReports Instance()
    {
        ExtentReports extent = new ExtentReports(report_location + "TestReport.html", true);

        return extent;
    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        final String report_location =  "./target/test-output/" + File.separator;
        extent = Instance();

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(context.getPassedTests(), LogStatus.PASS, report_location);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL, report_location);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP, report_location);
            }
        }

        extent.flush();
        extent.close();
    }

    private void buildTestNodes(IResultMap tests, LogStatus status, String outputDirectory) {
        ExtentTest test;
        //target/test-output/
        String log_file;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.startTest(result.getMethod().getMethodName());

                test.getTest().setStartedTime(getTime(result.getStartMillis()));
                test.getTest().setEndedTime(getTime(result.getEndMillis()));
                log_file = "logs/" + result.getName() + ".log";

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                String message = "Test " + status.toString().toLowerCase() + "ed";

                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();

                test.log(status, message);

               if (result.getStatus() == ITestResult.FAILURE){
                   test.log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture("./screenshots/" + result.getName() + ".png"));
                   print_log(test, log_file);
               }

                if (result.getStatus() == ITestResult.SUCCESS){
                    print_log(test, log_file);
                }

                if (result.getStatus() == ITestResult.SKIP){
                    print_log(test, log_file);
                }


                extent.endTest(test);
            }
        }
    }

    private void print_log(ExtentTest test, String log_file) {
        test.log(LogStatus.INFO, "<span class='label warn'>BROWSER LOGS</span> " + "<a href=" + log_file + ">Download</a>");
    }


    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}