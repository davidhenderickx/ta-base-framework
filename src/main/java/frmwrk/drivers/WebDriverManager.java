package frmwrk.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import frmwrk.enums.WebBrowser;
import frmwrk.reporters.Log;
import frmwrk.settings.RunSettings;


public class WebDriverManager {
	
	private static ThreadLocal<WebDriver> ThreadDriver = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		if (ThreadDriver.get() == null) {
			setDriver();
		}  
		return ThreadDriver.get();
	}
	
	public static void setDriver() {
		if (RunSettings.getWebBrowser().equals(WebBrowser.CHROME)) {
			setChromeDriver();
		} else if (RunSettings.getWebBrowser().equals(WebBrowser.FIREFOX)){
			setFirefoxDriver();
		} 
	}

	public static void setChromeDriver() {
		Log.info("Creating Chrome Driver");
		killDriver();
		ChromeOptions co = new ChromeOptions();
		co.setBinary("C:\\Users\\dhenderickx\\Development\\Resources\\Selenium\\chrome-win64\\chrome.exe");
		ThreadDriver.set(new ChromeDriver(co));
	}

	public static void setFirefoxDriver() {
		Log.info("Creating Firefox Driver");
		killDriver();
		ThreadDriver.set(new FirefoxDriver());
	}

	public static void killDriver() {
		if (ThreadDriver.get() != null) {
			ThreadDriver.get().quit();
			ThreadDriver.set(null);
		}
	}

}
