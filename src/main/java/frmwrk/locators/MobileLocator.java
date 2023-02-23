package frmwrk.locators;

import org.openqa.selenium.By;

import frmwrk.enums.MobileOS;
import frmwrk.reporters.Log;
import frmwrk.settings.RunSettings;
import io.appium.java_client.AppiumBy;

public class MobileLocator implements Locator {
	
	By androidLocator = null;
	By iosLocator = null;

	public MobileLocator(By locator) {
		this.androidLocator = locator;
		this.iosLocator = locator;
	}
	
	public MobileLocator(By androidLocator, By iosLocator) {
		this.androidLocator = androidLocator;
		this.iosLocator = iosLocator;
	}
	
	public MobileLocator(AndroidLocator locator) {
		this.androidLocator = locator.getLocator();
	}
	
	public MobileLocator(IOSLocator locator) {
		this.iosLocator = locator.getLocator();
	}
	
	public MobileLocator(AndroidLocator androidLocator, IOSLocator iosLocator) {
		this.iosLocator = iosLocator.getLocator();
		this.androidLocator = androidLocator.getLocator();
	}
	
	public MobileLocator(AppiumBy locator) {
		this.androidLocator = locator;
		this.iosLocator = locator;
	}
	
	public MobileLocator(AppiumBy androidLocator, AppiumBy iosLocator) {
		this.androidLocator = androidLocator;
		this.iosLocator = iosLocator;
	}
	

	@Override
	public void setLocator(By locator) {
		if (RunSettings.getMobileOS().equals(MobileOS.ANDROID)) {
			this.androidLocator = locator;
		} else if (RunSettings.getMobileOS().equals(MobileOS.IOS)) {
			this.iosLocator =  locator;
		} else {
			Log.error("The locator ("+ locator.toString() + ") cannot be stored, since the run platform is unknown.");
		}
	}

	@Override
	public By getLocator() {
		if (RunSettings.getMobileOS().equals(MobileOS.ANDROID)) {
			return androidLocator;
		} else if (RunSettings.getMobileOS().equals(MobileOS.IOS)) {
			return iosLocator;
		} else {
			Log.error("The locator cannot be returned, since the run platform is unknown.");
			return null;
		}
		
	}

}
