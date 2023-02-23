package frmwrk.reporters;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import frmwrk.enums.Platform;
import frmwrk.helper.TestDataGenerator;
import frmwrk.settings.RunSettings;
import frmwrk.testbase.BaseElement;

public class Log {
	// Initialize Log4j instance
	private static final Logger Log = LogManager.getLogger(Log.class);

	// Info Level Logs
	public static void info(String message) {
		Log.info(Thread.currentThread().getId() + " - " + message);
	}

	// Warn Level Logs
	public static void warn(String message) {
		Log.warn(Thread.currentThread().getId() + " - " + message);
	}

	// Error Level Logs
	public static void error(String message) {
		Log.error(Thread.currentThread().getId() + " - " + message);
	}

	// Fatal Level Logs
	public static void fatal(String message) {
		Log.fatal(Thread.currentThread().getId() + " - " + message);
	}

	// Debug Level Logs
	public static void debug(String message) {
		Log.debug(Thread.currentThread().getId() + " - " + message);
	}

	/**
	 * Log a step in the extent report. Based on the setting, the step will contain 
	 * a screenshot or not.
	 * 
	 * @param message
	 */
	public static void step(String message) {
		info(message);
		if (RunSettings.getRunStepTakeScreenshot()) {
			// TODO get name of test
			takeScreenshot(ExtentTestManager.getTest() + "_" + TestDataGenerator.getDateTime(), message);
		} else {
			if (ExtentTestManager.getTest() != null) {
				ExtentTestManager.getTest().log(Status.INFO, message);
			}
		}
	}

	/**
	 * Takes a screenshot and adds it to the report
	 * 
	 * @param screenshotName
	 * @param message
	 */
	public static void takeScreenshot(String screenshotName, String message) {
		if (!RunSettings.getRunPlatform().equals(Platform.API)) {
			BaseElement baseObject = new BaseElement();
			String screenshotPath = baseObject.takeScreenshot(screenshotName, true);
			screenshot(screenshotPath, message);
		}
	}

	/**
	 * Adds an existing screenshot to the report
	 */
	public static void screenshot(String screenshotPath, String message) {
		BaseElement baseObject = new BaseElement();
		String screenshotFileName = baseObject.getScreenshotName(screenshotPath);
		try {
			ExtentTestManager.getTest().log(Status.INFO, message,
					MediaEntityBuilder
							.createScreenCaptureFromPath(
									".." + File.separator + "screenshots" + File.separator + screenshotFileName)
							.build());
		} catch (Exception e) {
			if (ExtentTestManager.getTest() != null) {
				ExtentTestManager.getTest().log(Status.INFO, "Cannot add screenshot");
			}
		}
	}
}
