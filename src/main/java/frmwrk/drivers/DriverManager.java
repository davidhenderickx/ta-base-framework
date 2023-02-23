package frmwrk.drivers;

import org.openqa.selenium.WebDriver;

import frmwrk.enums.Platform;
import frmwrk.reporters.Log;
import frmwrk.settings.RunSettings;
import io.appium.java_client.AppiumDriver;

/**
 * This class manages the driver.
 *
 */
public class DriverManager {

	static Log log;
	
	/**
	 * Get the current active web browser or mobile application.
	 * Depending on the
	 * @return the active web browser or mobile application
	 */
	public static WebDriver getDriver() {
		if (RunSettings.getRunPlatform().equals(Platform.WEB)) {
			return getWebDriver();
		} else if (RunSettings.getRunPlatform().equals(Platform.MOBILE)){
			return getMobileDriver();
		} else {
			return null;
		}	
	}
	
	public static void KillDriver() {
		if (RunSettings.getRunPlatform().equals(Platform.WEB)) {
			killWebDriver();
		} else if (RunSettings.getRunPlatform().equals(Platform.MOBILE)){
			killMobileDriver();
		} else {
			//Do nothing
		}	
	}

	/**
	 * Get the current active web browser. If no web browser is active, a web browser will be created based on the current run settings.
	 * @return the active web browser
	 */
	public static WebDriver getWebDriver() {
		return WebDriverManager.getDriver();
	}

	/**
	 * Closes the current active web browser and removes the web browser from the memory
	 */
	public static void killWebDriver() {
		WebDriverManager.killDriver();
	}
	
	/**
	 * Get the current active mobile application. If no mobile application is active, an application will be started based on the current run settings.
	 * @return the active web browser
	 */
	public static AppiumDriver getMobileDriver() {
		return MobileDriverManager.getDriver();
	}
	
	/**
	 * Closes the current active mobile application browser and removes the application from the memory
	 */
	public static void killMobileDriver() {
		MobileDriverManager.killDriver();
	}
	


}