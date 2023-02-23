package frmwrk.testbase;

import frmwrk.helper.TestDataGenerator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import frmwrk.enums.MobileAppType;
import frmwrk.enums.MobileBrowser;
import frmwrk.enums.MobileOS;
import frmwrk.enums.Platform;
import frmwrk.enums.RunLocation;
import frmwrk.enums.WebBrowser;
import frmwrk.drivers.DriverManager;
import frmwrk.enums.Environment;
import frmwrk.reporters.Log;
import frmwrk.settings.RunSettings;
import java.lang.reflect.Method;

public class BaseTest {

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "ta.run.overwrite.settings", "ta.run.location", "ta.run.platform",
			"ta.run.timeout","ta.run.timeout.long","ta.run.timeout.short", "ta.run.step.takescreenshot",
			"ta.web.browser", "ta.mobileappium.server.url",
			"ta.mobile.use.capabilitiesfile", "ta.mobile.capabilitiesfile", "ta.mobile.devicename", "ta.mobile.os",
			"ta.mobile.os.version", "ta.mobile.automationname", "ta.mobile.apptype", "ta.mobile.browser",
			"ta.mobile.newcommandtimeout", "ta.mobile.app.package", "ta.mobile.app.activity", "ta.mobile.app.autograntpermissions",
			"ta.mobile.app.noreset", "ta.browserstack.username", "ta.browserstack.accesskey", "ta.browserstack.url",
			"ta.browserstack.projectname", "ta.browserstack.buildname", "ta.browserstack.app.url",
			"ta.run.environment"})
	public void setRunSettings(@Optional("") String runOverwriteSettings, @Optional("") String runLocaton,
			@Optional("") String runPlatform, @Optional("") String runTimeOut, @Optional("") String runTimeOutLong, 
			@Optional("") String runTimeOutShort, @Optional("") String runStepTakeScreenshot,
			@Optional("") String webBrowser, @Optional("") String mobileAppiumServerURL,
			@Optional("") String mobileUseMobileCapabilitiesfile, @Optional("") String mobileCapabilitiesfile,
			@Optional("") String mobileDeviceName, @Optional("") String mobileOS, @Optional("") String mobileOSVersion,
			@Optional("") String mobileAutomationName, @Optional("") String mobileAppType,
			@Optional("") String mobileBrowser, @Optional("") String mobileNewCommandTimeout,
			@Optional("") String mobileAppPackage, @Optional("") String mobileAppActivity,
			@Optional("") String mobileAppAutoGrantPermissions, @Optional("") String mobileAppNoReset,
			@Optional("") String browserStackUsername, @Optional("") String browserStackAccesskey,
			@Optional("") String browserStackURL, @Optional("") String browserStackProjectName,
			@Optional("") String browserStackBuildName, @Optional("") String browserStackAppURL,
			@Optional("") String runEnvironment, Method method ) {

		Log.info("Initializing Settings");

		RunSettings.initialize();

		if (!runOverwriteSettings.equals("")) {
			RunSettings.setRunOverwriteRunSettings(Boolean.valueOf(runOverwriteSettings));
		}
		if (!runLocaton.equals("")) {
			RunSettings.setRunLocation(RunLocation.valueOf(runLocaton));
		}
		if (!runPlatform.equals("")) {
			RunSettings.setRunPlatform(Platform.valueOf(runPlatform));
		}
		if (!runTimeOut.equals("")) {
			RunSettings.setRunTimeOut(Integer.valueOf(runTimeOut));
		}
		if (!runTimeOutLong.equals("")) {
			RunSettings.setRunTimeOutLong(Integer.valueOf(runTimeOutLong));
		}
		if (!runTimeOutShort.equals("")) {
			RunSettings.setRunTimeOutShort(Integer.valueOf(runTimeOutShort));
		}
		if (!runStepTakeScreenshot.equals("")) {
			RunSettings.setRunStepTakeScreenshot(Boolean.valueOf(runStepTakeScreenshot));
		}
		if (!runEnvironment.equals("")) {
			RunSettings.setRunEnvironment(Environment.valueOf(runEnvironment));
		}
		if (!webBrowser.equals("")) {
			RunSettings.setWebBrowser(WebBrowser.valueOf(webBrowser));
		}
		if (!mobileAppiumServerURL.equals("")) {
			RunSettings.setMobileAppiumServerURL(mobileAppiumServerURL);
		}
		if (!mobileUseMobileCapabilitiesfile.equals("")) {
			RunSettings.setMobileUseMobileCapabilitiesFile(Boolean.valueOf(mobileUseMobileCapabilitiesfile));
		}
		if (!mobileCapabilitiesfile.equals("")) {
			RunSettings.setMobileCapabilitiesFilePath(mobileCapabilitiesfile);
		}
		if (!mobileDeviceName.equals("")) {
			RunSettings.setMobileDeviceName(mobileDeviceName);
		}
		if (!mobileOS.equals("")) {
			RunSettings.setMobileOS(MobileOS.valueOf(mobileOS));
		}
		if (!mobileOSVersion.equals("")) {
			RunSettings.setMobileOSVersion(mobileOSVersion);
		}
		if (!mobileAutomationName.equals("")) {
			RunSettings.setMobileAutomationName(mobileAutomationName);
		}
		if (!mobileAppType.equals("")) {
			RunSettings.setMobileAppType(MobileAppType.valueOf(mobileAppType));
		}
		if (!mobileBrowser.equals("")) {
			RunSettings.setMobileBrowser(MobileBrowser.valueOf(mobileBrowser));
		}
		if (!mobileNewCommandTimeout.equals("")) {
			RunSettings.setMobileNewCommandTimeout(Integer.valueOf(mobileNewCommandTimeout));
		}
		if (!mobileAppPackage.equals("")) {
			RunSettings.setMobileAppPackage(mobileAppPackage);
		}
		if (!mobileAppActivity.equals("")) {
			RunSettings.setMobileAppActivity(mobileAppActivity);
		}
		if (!mobileAppAutoGrantPermissions.equals("")) {
			RunSettings.setMobileAppAutoGrantPermissions(Boolean.valueOf(mobileAppAutoGrantPermissions));
		}
		if (!mobileAppNoReset.equals("")) {
			RunSettings.setMobileAppNoReset(Boolean.valueOf(mobileAppNoReset));
		}
		if (!browserStackUsername.equals("")) {
			RunSettings.setBrowserStackUsername(browserStackUsername);
		}
		if (!browserStackAccesskey.equals("")) {
			RunSettings.setBrowserStackAccessKey(browserStackAccesskey);
		}
		if (!browserStackURL.equals("")) {
			RunSettings.setBrowserStackUrl(browserStackURL);
		}
		if (!browserStackProjectName.equals("")) {
			RunSettings.setBrowserStackProjectName(browserStackProjectName);
		}
		if (!browserStackBuildName.equals("")) {
			//RunSettings.setBrowserStackBuildName(browserStackBuildName);
			// adding details to the BS build name
			RunSettings.setBrowserStackBuildName(browserStackBuildName +'_'+ runEnvironment	+'_'+ mobileOS +'_'
			+ ((System.getenv("CI_PIPELINE_ID") == null) ? TestDataGenerator.getDate() : System.getenv("CI_PIPELINE_ID")));
		}
		// use for dynamic browserStackRunName
		if (!method.getName().equals("")) {
			RunSettings.setBrowserStackRunName(method.getName());
		}
		// use for static browserStackRunName
		//if (!browserStackRunName.equals("")) {
		//	RunSettings.setBrowserStackRunName(browserStackRunName);
		//}
		if (!browserStackAppURL.equals("")) {
			RunSettings.setBrowserStackAppURL(browserStackAppURL);
		}

		Log.info("Test is starting!");
	}

	@AfterMethod(alwaysRun = true)
	public void baseTestAfter() {
		Log.info("Test is ending!");
	}

}
