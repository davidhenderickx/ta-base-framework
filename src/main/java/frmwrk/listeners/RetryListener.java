package frmwrk.listeners;



import java.io.File;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import frmwrk.enums.Platform;
import frmwrk.reporters.ExtentTestManager;
import frmwrk.settings.RunSettings;
import frmwrk.testbase.BaseElement;

public class RetryListener implements IRetryAnalyzer {
	
	int counter = 0;
	int retryLimit = 4; //Run the failed test 4 times
	
	/*
	 * (non-Javadoc)
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 * 
	 * This method decides how many times a test needs to be rerun.
	 * TestNg will call this method every time a test fails. So we 
	 * can put some code in here to decide when to rerun the test.
	 * 
	 * Note: This method will return true if a tests needs to be retried
	 * and false it not.
	 *
	 */

    @Override
    public boolean retry(ITestResult iTestResult) {
    	if (!iTestResult.isSuccess()) {                     //Check if test not succeed
            if (counter < retryLimit) {                     //Check if maxTry count is reached
            	counter++;                                  //Increase the maxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE); //Mark test as failed and take base64Screenshot
                extendReportsFailOperations(iTestResult);   //ExtentReports fail operations
                return true;                                //Tells TestNG to re-run the test
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);     //If test passes, TestNG marks it as passed
        }
        return false;
    }

    public void extendReportsFailOperations(ITestResult iTestResult) {
    	
    	if (!RunSettings.getRunPlatform().equals(Platform.API)) {
			BaseElement baseObject = new BaseElement();
			String screenshotPath = baseObject.takeScreenshot(iTestResult.getMethod().getMethodName(), true);
			String screenshotFileName = baseObject.getScreenshotName(screenshotPath);
			try {
				ExtentTestManager.getTest().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
						MediaEntityBuilder.createScreenCaptureFromPath(".." + File.separator + "screenshots" + File.separator + screenshotFileName).build());
			} catch (Exception e) {
				ExtentTestManager.getTest().fail("Test failed, cannot attach screenshot");
			}
		}     	
		String logText = "<b>Test Method" + iTestResult.getMethod().getMethodName() + " Failed</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		ExtentTestManager.getTest().log(Status.FAIL, m); 	

    }
    
    
    
    
}
