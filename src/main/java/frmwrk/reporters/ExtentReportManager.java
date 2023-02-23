package frmwrk.reporters;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	private static ExtentReports extentReport;
	
	public static ExtentReports getExtentReport() {
		if (extentReport == null) {
			createInstance();
		}
		return extentReport;
	}
	
	public static ExtentReports createInstance() {
		String fileName = getReportName();
		String directory = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "extentreports";
		new File(directory).mkdirs();
		String path = directory + File.separator + fileName; 
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setEncoding("utf-8");
        reporter.config().setDocumentTitle("Test Automation Reports");
        reporter.config().setReportName("Test Results");
        reporter.config().setTheme(Theme.STANDARD);
        
        extentReport = new ExtentReports();
        extentReport.setSystemInfo("Author", "CTG");
        extentReport.attachReporter(reporter);
        return extentReport;
        
    }
	
	private static String getReportName() {
		Date d = new Date();
		String fileName = "AutomationReport_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
		return fileName;
	}
}
