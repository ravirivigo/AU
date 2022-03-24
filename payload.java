package files;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;


public class payload extends PropertyUtils {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

public String Pu(String P) throws Exception {
    PropertyUtils pu = new PropertyUtils();
  P =  pu.gettingValueOfProperty("property");
return P;
}

    @BeforeClass
    public void setExtent()
            {
                htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport.html");
                htmlReporter.config().setDocumentTitle("Automation Report");//Title of the report
                htmlReporter.config().setReportName("Functional Report"); //Report Name
                htmlReporter.config().setTheme(Theme.DARK);
                extent = new ExtentReports();
                extent.attachReporter(htmlReporter);
                extent.setSystemInfo("OS", "Linux");
                extent.setSystemInfo("Browser", "Chrome");
                extent.setSystemInfo("Author", "Ravi" +"");
            }

            @AfterClass
            public void endReport ()
            {
                extent.flush();
            }

            @AfterMethod
            public void tearDown (ITestResult result) throws IOException {
                if (result.getStatus() == ITestResult.FAILURE) {
                    test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
                    test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
                } else if (result.getStatus() == ITestResult.SKIP) {
                    test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
                } else if (result.getStatus() == ITestResult.SUCCESS) {
                    test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
                }

            }


}



