package frmwrk.drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import frmwrk.enums.MobileOS;
import frmwrk.enums.RunLocation;
import frmwrk.helper.BrowserStack;
import frmwrk.helper.MobileCapabilitiesReader;
import frmwrk.reporters.Log;
import frmwrk.settings.RunSettings;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class MobileDriverManager {

	private static ThreadLocal<AppiumDriver> ThreadDriver = new ThreadLocal<AppiumDriver>();

	public static AppiumDriver getDriver() {
		if (ThreadDriver.get() == null) {
			setDriver();
		}
		return ThreadDriver.get();
	}

	public static void setDriver() {
		if (RunSettings.getMobileOS().equals(MobileOS.ANDROID)) {
			if (RunSettings.getRunLocation().equals(RunLocation.LOCAL)) {
				setAndroidDriver();
			} else if (RunSettings.getRunLocation().equals(RunLocation.BROWSERSTACK)) {
				setBrowserStackAndroidDriver();
			}
		} else if (RunSettings.getMobileOS().equals(MobileOS.IOS)) {
			if (RunSettings.getRunLocation().equals(RunLocation.LOCAL)) {
				setIOSDriver();
			} else if (RunSettings.getRunLocation().equals(RunLocation.BROWSERSTACK)) {
				setBrowserStackIOSDriver();
			}
		}
	}

	public static void setAndroidDriver() {
		Log.info("Creating Android Driver");
		
		DesiredCapabilities dc = null;
		
		if (RunSettings.getMobileUseMobileCapabilitiesFile()) {
			try {
				dc = MobileCapabilitiesReader.getDesiredCapabilities(RunSettings.getMobileDeviceName(), RunSettings.getMobileCapabilitiesFilePath());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			dc = new DesiredCapabilities();
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, RunSettings.getMobileDeviceName());
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, RunSettings.getMobileOS());
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, RunSettings.getMobileOSVersion());
			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, RunSettings.getMobileAutomationName());
			dc.setCapability(MobileCapabilityType.NO_RESET, RunSettings.getMobileAppNoReset());
			dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,
					RunSettings.getMobileAppAutoGrantPermissions());
			dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, RunSettings.getMobileNewCommandTimeout());
			dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, RunSettings.getMobileAppPackage());
			dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, RunSettings.getMobileAppActivity());
			dc.setCapability("locationServicesAuthorized", true);
			dc.setCapability("locationServicesEnabled", true);
			
		}
		
		URL url = null;
		try {
			url = new URL(RunSettings.getMobileAppiumServerURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		ThreadDriver.set(new AndroidDriver(url, dc));
	}
	
	public static void setIOSDriver() {
		// TODO To implement
	}
	
	public static void setBrowserStackAndroidDriver() {
		Log.info("Creating BrowserStack Android Driver");
		
		DesiredCapabilities dc = null;
		
		if (RunSettings.getMobileUseMobileCapabilitiesFile()) {
			try {
				dc = MobileCapabilitiesReader.getDesiredCapabilities(RunSettings.getMobileDeviceName(), RunSettings.getMobileCapabilitiesFilePath());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			dc = new DesiredCapabilities();
			dc.setCapability("projectName", RunSettings.getBrowserStackProjectName());
			dc.setCapability("buildName", RunSettings.getBrowserStackBuildName());
			dc.setCapability("sessionName", RunSettings.getBrowserStackRunName());
			dc.setCapability("browserstack.debug", true);
			dc.setCapability("platformName", "android");
			dc.setCapability("platformVersion", "12.0");
			dc.setCapability("deviceName", "Google Pixel 6");
			//dc.setCapability("os_version", RunSettings.getMobileOSVersion());
			dc.setCapability("app", RunSettings.getBrowserStackAppURL());
			
		}
		
		Log.debug("Desired Capabilities: " + dc.toString());
		ThreadDriver.set(new AndroidDriver(BrowserStack.createURL(), dc));
	}
	
	public static void setBrowserStackIOSDriver() {
		// To implement
	}

	public static void killDriver() {
		Log.debug("Killing the driver");
		if (ThreadDriver.get() != null) {
			ThreadDriver.get().quit();
			ThreadDriver.set(null);
		} else {
			Log.debug("No driver to kill");
		}
	}

}
