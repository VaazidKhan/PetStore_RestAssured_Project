package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    // This method will be invoked before the start of any test context
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify location of the report
        sparkReporter.config().setDocumentTitle("PetStore_RestAssured_AutomationProject"); // title of report
        sparkReporter.config().setReportName("Pet Store Users API"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
    }

    // This method will be invoked after the completion of a test context
    public void onFinish(ITestContext testContext) {
        extent.flush(); // Writes the test report once the execution is completed
    }

    // This method will be invoked whenever a test case passes
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName()); // Create new entry in the report for the test case
        test.pass("Test Passed");
    }

    // This method will be invoked whenever a test case fails
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName()); // Create new entry in the report for the test case
        test.fail("Test Failed: " + result.getThrowable()); // Add the error/exception in the report
    }

    // This method will be invoked whenever a test case is skipped
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName()); // Create new entry in the report for the test case
        test.skip("Test Skipped: " + result.getThrowable());
    }

    // This method will be invoked whenever a test case starts
    public void onTestStart(ITestResult result) {
        // Not required to implement for ExtentReports
    }

    // This method will be invoked when a test case fails but is within success percentage
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not required to implement for ExtentReports
    }

    // This method will be invoked when a test case fails due to an internal configuration failure
    public void onConfigurationFailure(ITestResult result) {
        // Optional implementation if needed
    }
}
