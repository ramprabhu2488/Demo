package rahulShettyacademy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporterNG {

	// definition explained in ExtendReportProject

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "\\reports\\index.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		reporter.config().setReportName("WebAutomationResults");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extend = new ExtentReports();
		extend.attachReporter(reporter);
		extend.setSystemInfo("Tester", "Deepak");
		return extend;

	}

}
