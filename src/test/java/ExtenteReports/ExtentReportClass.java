package ExtenteReports;

import java.io.File;

import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import sun.rmi.runtime.Log;

public class ExtentReportClass implements ITestListener {

    protected static ExtentReports reports;
    protected static ExtentTest test;
    public void onTestStart(ITestResult result) {
        System.out.println("on test start");
        test = reports.startTest(result.getMethod().getMethodName());
        test.log(LogStatus.INFO, result.getClass().getName() + "test is started");
    }
    public void onTestSuccess(ITestResult result) {
        System.out.println("on test success");
        test.log(LogStatus.PASS, result.getMethod().getMethodName() + " - Test Passed");
    }
    public void onTestFailure(ITestResult result) {
        System.out.println("on test failure");
        test.log(LogStatus.FAIL, result.getMethod().getMethodName() + " - Test Failed");

    }
    public void onTestSkipped(ITestResult result) {
        System.out.println("on test skipped");
        test.log(LogStatus.SKIP, result.getMethod().getMethodName() + " - Test Skipped");
    }
    public void onStart(ITestContext context) {
        System.out.println("on start");
        reports = new ExtentReports("./build/reports/tests/test/extentreport.html");
        //reports = new ExtentReports(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + "reports.html");
        reports.loadConfig(new File("./src/test/java/ExtenteReports/extent-config.xml"));
    }
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("on test success within percentage");
    }
    public void onFinish(ITestContext context) {
        System.out.println("on finish");
        reports.endTest(test);
        reports.flush();
    }

}
