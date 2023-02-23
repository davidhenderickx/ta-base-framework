package frmwrk.settings;

import frmwrk.enums.*;
import frmwrk.reporters.Log;

import java.io.IOException;
import java.util.Properties;

/**
 * This class contains all the possible settings that can be set 
 * 
 */
public class RunSettings {

	private static ThreadLocal<Boolean> runOverwriteRunSettings = new ThreadLocal<Boolean>();
	private static ThreadLocal<RunLocation> runLocationSetting = new ThreadLocal<RunLocation>();
	private static ThreadLocal<Platform> runPlatformSetting = new ThreadLocal<Platform>();
	private static ThreadLocal<Integer> runTimeOutSetting = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> runTimeOutLongSetting = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> runTimeOutShortSetting = new ThreadLocal<Integer>();
	private static ThreadLocal<Boolean> runStepTakeScreenshot = new ThreadLocal<Boolean>();
	private static ThreadLocal<Environment> runEnvironmentSetting = new ThreadLocal<Environment>();


	private static ThreadLocal<WebBrowser> webBrowserSetting = new ThreadLocal<WebBrowser>();
	
	private static ThreadLocal<String> mobileAppiumServerURLSetting = new ThreadLocal<String>();
	private static ThreadLocal<Boolean> mobileUseMobileCapabilitiesFileSetting = new ThreadLocal<Boolean>();
	private static ThreadLocal<String> mobileDeviceNameSetting = new ThreadLocal<String>();
	private static ThreadLocal<MobileOS> mobileOSSetting = new ThreadLocal<MobileOS>();
	private static ThreadLocal<String> mobileOSVersionSetting = new ThreadLocal<String>();
	private static ThreadLocal<Integer> mobileNewCommandTimeOutSetting = new ThreadLocal<Integer>();
	private static ThreadLocal<MobileAppType> mobileAppTypeSetting = new ThreadLocal<MobileAppType>();
	private static ThreadLocal<MobileBrowser> mobileBrowserSetting = new ThreadLocal<MobileBrowser>();
	private static ThreadLocal<String> mobileAppPackageSetting = new ThreadLocal<String>();
	private static ThreadLocal<String> mobileAppActivitySetting = new ThreadLocal<String>();
	private static ThreadLocal<String> mobileAutomationNameSetting = new ThreadLocal<String>();
	private static ThreadLocal<Boolean> mobileAppAutoGrantPermissionsSetting = new ThreadLocal<Boolean>();
	private static ThreadLocal<Boolean> mobileAppNoResetSetting = new ThreadLocal<Boolean>();
	// The relative path of the .json file with the capabilities for mobile devices, starting from src/test/resources
	private static ThreadLocal<String> mobileCapabilitiesFilePathSetting = new ThreadLocal<String>(); 
	
	private static ThreadLocal<String> browserStackUsernameSetting = new ThreadLocal<String>();
	private static ThreadLocal<String> browserStackAccessKeySetting = new ThreadLocal<String>();
	private static ThreadLocal<String> browserStackUrlSetting = new ThreadLocal<String>();
	private static ThreadLocal<String> browserStackProjectNameSetting = new ThreadLocal<String>();
	private static ThreadLocal<String> browserStackBuildNameSetting = new ThreadLocal<String>();
	private static ThreadLocal<String> browserStackRunNameSetting = new ThreadLocal<String>();
	private static ThreadLocal<String> browserStackAppURLSetting = new ThreadLocal<String>();

	public static Boolean getRunOverwriteRunSettings() {
		return runOverwriteRunSettings.get();
	}

	public static void setRunOverwriteRunSettings(Boolean overwriteRunSetting) {
		if (!(overwriteRunSetting == null)) {
			Log.debug("Setting the OverwriteRunSettings setting to: " + overwriteRunSetting.toString());
			runOverwriteRunSettings.set(overwriteRunSetting);
		} else {
			Log.debug("OverwriteRunSettings has no new value");
		}
	}
	
	public static Platform getRunPlatform() {
		return runPlatformSetting.get();
	}

	public static void setRunPlatform(Platform platform) {
		if (platform != null) {
			if (runOverwriteRunSettings.get()) {
				Log.debug("Setting the Platform setting to: " + platform.toString());
				runPlatformSetting.set(platform);
			} else {
				Log.debug("Overwriting the setting 'Platform' has been disabled.");
			}
		}
	}

	public static RunLocation getRunLocation() {
		return runLocationSetting.get();
	}

	public static void setRunLocation(RunLocation runLocation) {
		if (runLocation != null) {
			if (runOverwriteRunSettings.get()) {
				Log.debug("Setting the RunLocation setting to: " + runLocation.toString());
				runLocationSetting.set(runLocation);
			} else {
				Log.debug("Overwriting the setting 'RunLocation' has been disabled.");
			}
		}
	}
	
	public static Integer getRunTimeOut() {
		return runTimeOutSetting.get();
	}

	public static void setRunTimeOut(Integer milliseconds) {
		if (runTimeOutSetting != null) {
			if (runOverwriteRunSettings.get()) {
				Log.debug("Setting the TimeOut setting to: " + milliseconds);
				runTimeOutSetting.set(milliseconds);
			} else {
				Log.debug("Overwriting the setting 'TimeOut' has been disabled.");
			}
		}
	}
	
	public static Integer getRunTimeOutLong() {
		return runTimeOutLongSetting.get();
	}

	public static void setRunTimeOutLong(Integer milliseconds) {
		if (runTimeOutLongSetting != null) {
			if (runOverwriteRunSettings.get()) {
				Log.debug("Setting the TimeOutLong setting to: " + milliseconds);
				runTimeOutLongSetting.set(milliseconds);
			} else {
				Log.debug("Overwriting the setting 'TimeOutLong' has been disabled.");
			}
		}
	}
	
	public static Integer getRunTimeOutShort() {
		return runTimeOutShortSetting.get();
	}

	public static void setRunTimeOutShort(Integer milliseconds) {
		if (runTimeOutShortSetting != null) {
			if (runOverwriteRunSettings.get()) {
				Log.debug("Setting the TimeOutShort setting to: " + milliseconds);
				runTimeOutShortSetting.set(milliseconds);
			} else {
				Log.debug("Overwriting the setting 'TimeOutShort' has been disabled.");
			}
		}
	}
	
	public static Boolean getRunStepTakeScreenshot() {
		return runStepTakeScreenshot.get();
	}

	public static void setRunStepTakeScreenshot(Boolean takeScreenshotsOnStep) {
		if (runStepTakeScreenshot != null) {
			if (runOverwriteRunSettings.get()) {
				Log.debug("Setting the StepTakeScreenshot setting to: " + takeScreenshotsOnStep);
				runStepTakeScreenshot.set(takeScreenshotsOnStep);
			} else {
				Log.debug("Overwriting the setting 'StepTakeScreenshot' has been disabled.");
			}
		}
	}
	
	
	

	public static WebBrowser getWebBrowser() {
		return webBrowserSetting.get();
	}

	public static void setWebBrowser(WebBrowser webBrowser) {
		if (webBrowser != null) {
			if (runOverwriteRunSettings.get()) {
				Log.debug("Setting the WebBrowser setting to: " + webBrowser.toString());
				webBrowserSetting.set(webBrowser);
			} else {
				Log.debug("Overwriting the setting 'WebBrowser' has been disabled.");
			}
		}
	}

	

	public static String getMobileAppiumServerURL() {
		return mobileAppiumServerURLSetting.get();
	}

	public static void setMobileAppiumServerURL(String appiumServerURL) {
		if (runOverwriteRunSettings.get()) {
			if (!appiumServerURL.trim().equals("")) {
				Log.debug("Setting the AppiumServerURL setting to: " + appiumServerURL.toString());
				mobileAppiumServerURLSetting.set(appiumServerURL);
			} else {
				Log.debug("AppiumServerURL has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'AppiumServerURL' has been disabled.");
		}
	}

	public static String getMobileDeviceName() {
		return mobileDeviceNameSetting.get();
	}

	public static void setMobileDeviceName(String deviceName) {
		if (runOverwriteRunSettings.get()) {
			if (!deviceName.trim().equals("")) {
				Log.debug("Setting the MobileDeviceName setting to: " + deviceName.toString());
				mobileDeviceNameSetting.set(deviceName);
			} else {
				Log.debug("MobileDeviceName has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'MobileDeviceName' has been disabled.");
		}
	}

	public static MobileOS getMobileOS() {
		return mobileOSSetting.get();
	}

	public static void setMobileOS(MobileOS mobileOS) {
		if (mobileOS != null) {
			if (runOverwriteRunSettings.get()) {
				Log.debug("Setting the MobileOS setting to: " + mobileOS.toString());
				mobileOSSetting.set(mobileOS);
			} else {
				Log.debug("Overwriting the setting 'MobileOS' has been disabled.");
			}
		}
	}

	public static String getMobileOSVersion() {
		return mobileOSVersionSetting.get();
	}

	public static void setMobileOSVersion(String oSVersion) {
		if (runOverwriteRunSettings.get()) {
			if (!oSVersion.trim().equals("")) {
				Log.debug("Setting the MobileOSVersion setting to: " + oSVersion);
				mobileOSVersionSetting.set(oSVersion);
			} else {
				Log.debug("MobileOSVersion has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'MobileOSVersion' has been disabled.");
		}
	}

	public static MobileAppType getMobileAppType() {
		return mobileAppTypeSetting.get();
	}

	public static void setMobileAppType(MobileAppType appType) {
		if (appType != null) {
			if (runOverwriteRunSettings.get()) {
				Log.debug("Setting the MobileAppType setting to: " + appType.toString());
				mobileAppTypeSetting.set(appType);
			} else {
				Log.debug("Overwriting the setting 'MobileAppType' has been disabled.");
			}
		}
	}

	public static MobileBrowser getMobileBrowser() {
		return mobileBrowserSetting.get();
	}

	public static void setMobileBrowser(MobileBrowser browser) {
		if (browser != null) {
			if (runOverwriteRunSettings.get()) {
				Log.debug("Setting the MobileBrowser setting to: " + browser.toString());
				mobileBrowserSetting.set(browser);
			} else {
				Log.debug("Overwriting the setting 'MobileBrowser' has been disabled.");
			}
		}
	}

	public static String getMobileAppPackage() {
		return mobileAppPackageSetting.get();
	}

	public static void setMobileAppPackage(String appPackage) {
		if (runOverwriteRunSettings.get()) {
			if (!appPackage.trim().equals("")) {
				Log.debug("Setting the MobileAppPackage setting to: " + appPackage);
				mobileAppPackageSetting.set(appPackage);
			} else {
				Log.debug("MobileAppPackage has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'MobileApp' has been disabled.");
		}
	}

	public static String getMobileAppActivity() {
		return mobileAppActivitySetting.get();
	}

	public static void setMobileAppActivity(String appActivity) {
		if (runOverwriteRunSettings.get()) {
			if (!appActivity.trim().equals("")) {
				Log.debug("Setting the MobileAppActivity setting to: " + appActivity.toString());
				mobileAppActivitySetting.set(appActivity);
			} else {
				Log.debug("MobileAppActivity has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'MobileAppActivity' has been disabled.");
		}
	}

	public static String getMobileCapabilitiesFilePath() {
		return mobileCapabilitiesFilePathSetting.get();
	}

	public static void setMobileCapabilitiesFilePath(String mobileCapabilitiesFilePath) {
		if (runOverwriteRunSettings.get()) {
			if (!mobileCapabilitiesFilePath.trim().equals("")) {
				Log.debug("Setting the MobileCapabilitiesFilePath setting to: " + mobileCapabilitiesFilePath);
				mobileCapabilitiesFilePathSetting.set(mobileCapabilitiesFilePath);
			} else {
				Log.debug("MobileCapabilitiesFilePath has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'MobileCapabilitiesFilePath' has been disabled.");
		}
	}

	public static Boolean getMobileAppAutoGrantPermissions() {
		return mobileAppAutoGrantPermissionsSetting.get();
	}

	public static void setMobileAppAutoGrantPermissions(Boolean mobileAutoGrantPermissions) {
		if (runOverwriteRunSettings.get()) {
			if (!(mobileAutoGrantPermissions == null)) {
				Log.debug(
						"Setting the MobileAutoGrantPermissions setting to: " + mobileAutoGrantPermissions.toString());
				mobileAppAutoGrantPermissionsSetting.set(mobileAutoGrantPermissions);
			} else {
				Log.debug("MobileAutoGrantPermissions has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'MobileAutoGrantPermissions' has been disabled");
		}
	}

	public static Boolean getMobileAppNoReset() {
		return mobileAppNoResetSetting.get();
	}

	public static void setMobileAppNoReset(Boolean mobileNoReset) {
		if (runOverwriteRunSettings.get()) {
			if (!(mobileNoReset == null)) {
				Log.debug("Setting the MobileNoReset setting to: " + mobileNoReset.toString());
				mobileAppNoResetSetting.set(mobileNoReset);
			} else {
				Log.debug("MobileNoReset has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'MobileNoReset' has been disabled.");
		}
	}

	public static Integer getMobileNewCommandTimeout() {
		return mobileNewCommandTimeOutSetting.get();
	}

	public static void setMobileNewCommandTimeout(Integer mobileNewCommandTimeout) {
		if (runOverwriteRunSettings.get()) {
			if (!(mobileNewCommandTimeout == null)) {
				Log.debug("Setting the MobileNewCommandTimeout setting to: " + mobileNewCommandTimeout.toString());
				mobileNewCommandTimeOutSetting.set(mobileNewCommandTimeout);
			} else {
				Log.debug("MobileNewCommandTimeout has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'MobileNewCommandTimeout' has been disabled.");
		}
	}

	public static String getMobileAutomationName() {
		return mobileAutomationNameSetting.get();
	}

	public static void setMobileAutomationName(String mobileAutomationName) {
		if (runOverwriteRunSettings.get()) {
			if (!mobileAutomationName.trim().equals("")) {
				Log.debug("Setting the MobileAutomationName setting to: " + mobileAutomationName);
				mobileAutomationNameSetting.set(mobileAutomationName);
			} else {
				Log.debug("MobileAutomationName has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'MobileAutomationName' has been disabled.");
		}
	}

	public static Boolean getMobileUseMobileCapabilitiesFile() {
		return mobileUseMobileCapabilitiesFileSetting.get();
	}

	public static void setMobileUseMobileCapabilitiesFile(Boolean useMobileCapabilitiesFile) {
		if (runOverwriteRunSettings.get()) {
			if (!(useMobileCapabilitiesFile == null)) {
				Log.debug("Setting the UseMobileCapabilitiesFile setting to: " + useMobileCapabilitiesFile.toString());
				mobileUseMobileCapabilitiesFileSetting.set(useMobileCapabilitiesFile);
			} else {
				Log.debug("UseMobileCapabilitiesFile has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'UseMobileCapabilitiesFile' has been disabled.");
		}
	}

	public static String getBrowserStackUsername() {
		return browserStackUsernameSetting.get();
	}

	public static void setBrowserStackUsername(String browserStackUsername) {
		if (runOverwriteRunSettings.get()) {
			if (!browserStackUsername.trim().equals("")) {
				Log.debug("Setting the BrowserStackUsername setting to: " + browserStackUsername);
				browserStackUsernameSetting.set(browserStackUsername);
			} else {
				Log.debug("BrowserStackUsername has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'BrowserStackUsername' has been disabled.");
		}
	}

	public static String getBrowserStackAccessKey() {
		return browserStackAccessKeySetting.get();
	}

	public static void setBrowserStackAccessKey(String browserStackAccessKey) {
		if (runOverwriteRunSettings.get()) {
			if (!browserStackAccessKey.trim().equals("")) {
				Log.debug("Setting the BrowserStackAccessKey setting to: " + browserStackAccessKey);
				browserStackAccessKeySetting.set(browserStackAccessKey);
			} else {
				Log.debug("BrowserStackAccessKey has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'BrowserStackAccessKey' has been disabled.");
		}
	}

	public static String getBrowserStackUrl() {
		return browserStackUrlSetting.get();
	}

	public static void setBrowserStackUrl(String browserStackUrl) {
		if (runOverwriteRunSettings.get()) {
			if (!browserStackUrl.trim().equals("")) {
				Log.debug("Setting the BrowserStackUrl setting to: " + browserStackUrl);
				browserStackUrlSetting.set(browserStackUrl);
			} else {
				Log.debug("BrowserStackUrl has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'BrowserStackUrl' has been disabled.");
		}
	}

	public static String getBrowserStackProjectName() {
		return browserStackProjectNameSetting.get();
	}

	public static void setBrowserStackProjectName(String browserStackProjectName) {
		if (runOverwriteRunSettings.get()) {
			if (!browserStackProjectName.trim().equals("")) {
				Log.debug("Setting the BrowserStackProjectName setting to: " + browserStackProjectName);
				browserStackProjectNameSetting.set(browserStackProjectName);
			} else {
				Log.debug("BrowserStackProjectName has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'BrowserStackProjectName' has been disabled.");
		}
	}

	public static String getBrowserStackBuildName() {
		return browserStackBuildNameSetting.get();
	}

	public static void setBrowserStackBuildName(String browserStackBuildName) {
		if (runOverwriteRunSettings.get()) {
			if (!browserStackBuildName.trim().equals("")) {
				Log.debug("Setting the BrowserStackBuildName setting to: " + browserStackBuildName);
				browserStackBuildNameSetting.set(browserStackBuildName);
			} else {
				Log.debug("BrowserStackBuildName has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'BrowserStackBuildName' has been disabled.");
		}
	}

	public static String getBrowserStackRunName() {
		return browserStackRunNameSetting.get();
	}

	public static void setBrowserStackRunName(String browserStackRunName) {
		if (runOverwriteRunSettings.get()) {
			if (!browserStackRunName.trim().equals("")) {
				Log.debug("Setting the BrowserStackRunName setting to: " + browserStackRunName);
				browserStackRunNameSetting.set(browserStackRunName);
			} else {
				Log.debug("BrowserStackRunName has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'BrowserStackRunName' has been disabled.");
		}
	}

	public static String getBrowserStackAppURL() {
		return browserStackAppURLSetting.get();
	}

	public static void setBrowserStackAppURL(String browserStackAppURL) {
		if (runOverwriteRunSettings.get()) {
			if (!browserStackAppURL.trim().equals("")) {
				Log.debug("Setting the BrowserStackAppURL setting to: " + browserStackAppURL);
				browserStackAppURLSetting.set(browserStackAppURL);
			} else {
				Log.debug("BrowserStackAppURL has no new value");
			}
		} else {
			Log.debug("Overwriting the setting 'BrowserStackAppURL' has been disabled.");
		}
	}

	public static Environment getRunEnvironment() {
		return runEnvironmentSetting.get();
	}

	public static void setRunEnvironment(Environment environment) {
		if (runEnvironmentSetting != null) {
			if (runOverwriteRunSettings.get()) {
				Log.debug("Setting the Environment setting to: " + environment.toString());
				runEnvironmentSetting.set(environment);
			} else {
				Log.debug("Overwriting the setting 'Environment' has been disabled.");
			}
		}
	}

	public static void initialize() {

		Log.debug("Initializing the run settings");

		setRunOverwriteRunSettings(Boolean.valueOf(getPropertyValue("ta.run.overwrite.settings")));
		if (getPropertyValue("ta.run.location") != null) {
			setRunLocation(RunLocation.valueOf(getPropertyValue("ta.run.location")));
		}
		if (getPropertyValue("ta.run.platform") != null) {
			setRunPlatform(Platform.valueOf(getPropertyValue("ta.run.platform")));
		}
		setRunTimeOut(Integer.valueOf(getPropertyValue("ta.run.timeout")));
		setRunTimeOutLong(Integer.valueOf(getPropertyValue("ta.run.timeout.long")));
		setRunTimeOutShort(Integer.valueOf(getPropertyValue("ta.run.timeout.short")));
		setRunStepTakeScreenshot(Boolean.valueOf(getPropertyValue("ta.run.step.takescreenshot")));
		if (getPropertyValue("ta.web.browser") != null) {
			setWebBrowser(WebBrowser.valueOf(getPropertyValue("ta.web.browser")));
		}
		setMobileAppiumServerURL(getPropertyValue("ta.mobile.appium.server.url"));
		setMobileUseMobileCapabilitiesFile(Boolean.valueOf(getPropertyValue("ta.mobile.use.capabilitiesfile")));
		setMobileCapabilitiesFilePath(getPropertyValue("ta.mobile.capabilitiesfile"));
		setMobileDeviceName(getPropertyValue("ta.mobile.devicename"));
		if (getPropertyValue("ta.mobile.os") != null) {
			setMobileOS(MobileOS.valueOf(getPropertyValue("ta.mobile.os")));
		}
		setMobileOSVersion(getPropertyValue("ta.mobile.os.version"));
		setMobileAutomationName(getPropertyValue("ta.mobile.automationname"));
		setMobileNewCommandTimeout(Integer.valueOf(getPropertyValue("ta.mobile.newcommandtimeout")));
		if (getPropertyValue("ta.mobile.apptype") != null) {
			setMobileAppType(MobileAppType.valueOf(getPropertyValue("ta.mobile.apptype")));
		}
		if (getPropertyValue("ta.mobile.browser") != null) {
			setMobileBrowser(MobileBrowser.valueOf(getPropertyValue("ta.mobile.browser")));
		}
		setMobileAppPackage(getPropertyValue("ta.mobile.app.package"));
		setMobileAppActivity(getPropertyValue("ta.mobile.app.activity"));
		setMobileAppAutoGrantPermissions(Boolean.valueOf(getPropertyValue("ta.mobile.app.autograntpermissions")));
		setMobileAppNoReset(Boolean.valueOf(getPropertyValue("ta.mobile.app.noreset")));
		setBrowserStackUsername(getPropertyValue("ta.browserstack.username"));
		setBrowserStackAccessKey(getPropertyValue("ta.browserstack.accesskey"));
		setBrowserStackUrl(getPropertyValue("ta.browserstack.url"));
		setBrowserStackProjectName(getPropertyValue("ta.browserstack.projectname"));
		setBrowserStackBuildName(getPropertyValue("ta.browserstack.buildname"));
		setBrowserStackRunName(getPropertyValue("ta.browserstack.runname"));
		setBrowserStackAppURL(getPropertyValue("ta.browserstack.app.url"));
		if (getPropertyValue("ta.run.environment") != null) {
			setRunEnvironment(Environment.valueOf(getPropertyValue("ta.run.environment")));
		}

	}


	private static String getPropertyValue(String property) {
		Log.debug("Getting the property value for: " + property);
		String propValue = getEnvironmentPropertyValue(property);
		if (propValue == null) {
			propValue = getDefaultPropertyValue(property);
		}
		return propValue;
	}

	private static String getEnvironmentPropertyValue(String property) {
		Log.debug("Getting the environment property value for: " + property);
		String propValue = System.getProperty(property);
		if ((propValue == null) || (propValue.trim().equals(""))) {
			Log.debug("The environment property '" + property + "' has not been set.");
			return null;
		} else {
			Log.debug("The environment property '" + property + "' has been set: " + propValue);
			return propValue.trim();
		}
	}

	private static String getDefaultPropertyValue(String property) {
		Log.debug("Getting the default property value for: " + property);
		Properties defaultProperties = new Properties();
		try {
			defaultProperties.load(RunSettings.class.getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String propValue = defaultProperties.getProperty(property);
		if (propValue == null) {
			Log.debug("The default property '" + property
					+ "' has not been set in the settings.properties file. Returning null");
			return null;
		} else {
			Log.debug("The default property '" + property + "' has been set in the settings.properties file: "
					+ propValue);
			return propValue.trim();
		}
	}

}
