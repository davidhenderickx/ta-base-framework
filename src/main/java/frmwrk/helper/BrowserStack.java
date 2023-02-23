package frmwrk.helper;

import java.net.MalformedURLException;
import java.net.URL;

import frmwrk.reporters.Log;
import frmwrk.settings.RunSettings;

public class BrowserStack {

	public static URL createURL() {
		Log.debug("Creating BrowserStack URL");
		
		String settingsURL = RunSettings.getBrowserStackUrl();
		if (settingsURL.contains("username") && settingsURL.contains("accesskey")){
			settingsURL = settingsURL.replace("username", RunSettings.getBrowserStackUsername());
			settingsURL = settingsURL.replace("accesskey", RunSettings.getBrowserStackAccessKey());	
		} 
		Log.debug("BrowserStack URL: " + settingsURL);
		
		try {
			URL url = new URL(settingsURL);
			return url;
		} catch (MalformedURLException e) {
			Log.error("Not possible to create URL.");
			return null;
		}
	
	}
}
