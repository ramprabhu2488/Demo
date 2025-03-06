package rahulShetty.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulShettyacademy.Resources.ExtendReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test; // it holds the details about the reports

	ExtentReports extend =	 ExtendReporterNG.getReportObject();


	/**
	 * To handle Concurrency or overriding the test value, we have to use ThreadSafe
	 */

	ThreadLocal<ExtentTest> extendTest = new ThreadLocal<ExtentTest>(); // ThreadSafe =

	// Class extendReportNg has the actual method of implementation

	// Listener class here implements the ItestListener class methods.

	//As per the name stand of the method, the action is reflects.
	@Override
	public void onTestStart(ITestResult result) {
		test = extend.createTest(result.getMethod().getMethodName()); // this will gets the test method name before its starts the execution
		extendTest.set(test); // => This helps to create unique id for all all testmethods, then it will give the correct test failure method name. if we dont use thread safe, while running the test parellely, we may get different method name for different mthod failure
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extendTest.get().log(Status.PASS, "Test Passed"); // when method pass, we set the text as Test Passed

	}

	@Override
	public void onTestFailure(ITestResult result) {
		//test.log(Status.FAIL, "Test failed");
		// We recorrecting this when we use Thread.
		//test.fail(result.getThrowable()); // this will throw the error.

		// we are catching the unique ID here, from the Thread.
		extendTest.get().fail(result.getThrowable());  // This will throw the errors in the reports. 

		// TakeScreenshot and Attach to Report

		/**
		 * if failure, it will take the screenshot, if no failure, we have to handle the exception, that why we use try catch
		 */

		String filePath = null;
		try {

			// we are receiving the driver access by using this below code. 
			try {
				driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 




			filePath = takeScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // it gives the file path of the screenshot, where it placed exactly.


		extendTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}




	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		extend.flush();

	}

}
