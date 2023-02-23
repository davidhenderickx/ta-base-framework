package frmwrk.helper;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.mobile.NetworkConnection.ConnectionType;

import frmwrk.drivers.DriverManager;
import frmwrk.enums.MobileOS;
import frmwrk.reporters.Log;
import frmwrk.settings.RunSettings;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.SupportsRotation;

public class MobileDevice {

	/**
	 * Move by a given coordinatestring. 
	 * @param coordinateString colon seperated string of coordinates. Example: "4.24953,51.15546,0;4.24961,51.15537,0;4.25022,51.15475,0"
	 * @param moveTimeInMilliseconds
	 */
	public static void moveByCoordinates(String coordinateString, int moveTimeInMilliseconds) {
		Log.debug("Moving for " + moveTimeInMilliseconds + "ms by coordinates: " + coordinateString);
		List<String> coordinatesList = Arrays.asList(coordinateString.split(";"));
		
		int millisecondsForEachCoordinate = moveTimeInMilliseconds / coordinatesList.size(); 

		for (int i = 0; i < coordinatesList.size(); i++) {
			List<String> coordinate = Arrays.asList(coordinatesList.get(i).split(","));
			double latitude = Double.parseDouble(coordinate.get(1));
			double longitude = Double.parseDouble(coordinate.get(0));
			double altitude = Double.parseDouble(coordinate.get(2));
			setLocation(latitude, longitude, altitude);
			try {
				Thread.sleep(millisecondsForEachCoordinate);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getLocation();
		}

	}

	/**
	 * Sets the location
	 * 
	 * Running
	 * 'C:\Users\dhenderickx\AppData\Local\Android\Sdk\platform-tools\adb.exe -P
	 * 5037 -s emulator-5554 emu geo fix 51.15546 4.24953 0' [debug] [ADB] Running
	 * 'adb -P 5037 -s emulator-5554 emu geo fix 51,15546 4,24953 0' [debug] [ADB]
	 * Running 'adb -P 5037 -s emulator-5554 shell am broadcast -n
	 * io.appium.settings/.receivers.LocationInfoReceiver -a
	 * io.appium.settings.location'
	 */
	public static void setLocation(double latitude, double longitude, double altitude) {
		Log.debug("Setting the location of the device. Latitude: " + latitude + ", Longitude: " + longitude + ", Altitude: " + altitude);
		Location location = new Location(latitude, longitude, altitude);
		((LocationContext) DriverManager.getDriver()).setLocation(location);
	}
	
	/**
	 * Return the location of the device
	 * @return
	 */
	public static Location getLocation() {
		Location location = ((LocationContext) DriverManager.getDriver()).location();
		Log.debug("Getting the location of the device. Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude() + ", Altitude: " + location.getAltitude());
		return location;
	}
	
	/**
	 * Move by a given coordinatestring. 
	 * @param coordinateString colon seperated string of coordinates. Example: "0,20,90;0,21,90;0,22,90"
	 * @param moveTimeInMilliseconds
	 */
	public static void rotateByCoordinates(String coordinateString, int moveTimeInMilliseconds) {
		Log.debug("Rotating for " + moveTimeInMilliseconds + "ms by coordinates: " + coordinateString);
		List<String> coordinatesList = Arrays.asList(coordinateString.split(";"));
		
		int millisecondsForEachCoordinate = moveTimeInMilliseconds / coordinatesList.size(); 

		for (int i = 0; i < coordinatesList.size(); i++) {
			List<String> coordinate = Arrays.asList(coordinatesList.get(i).split(","));
			int z = Integer.parseInt(coordinate.get(1));
			int x = Integer.parseInt(coordinate.get(0));
			int y = Integer.parseInt(coordinate.get(2));
			rotate(z, x, y);
			try {
				Thread.sleep(millisecondsForEachCoordinate);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getLocation();
		}

	}
	
	/**
	 * Rotating the device
	 * @param z rotate over the clockwise over the center of the device
	 * @param x rotate over the X asis ( - )
	 * @param y rotate over the Y axis ( | )
	 */
	public static void rotate(int z, int x, int y) {
		Log.debug("Rotating the device. Z: " + z + "X: " + x + ", Y: " + y);
		((SupportsRotation) DriverManager.getDriver()).rotate(new DeviceRotation(z, x, y));
		
	}
	
	
	

	public void setAirplaneMode() {
		NetworkConnection mobileDriver = (NetworkConnection) DriverManager.getDriver();
		if (mobileDriver.getNetworkConnection() != ConnectionType.AIRPLANE_MODE) {
			// enabling Airplane mode
			mobileDriver.setNetworkConnection(ConnectionType.AIRPLANE_MODE);
		}
		// Android 6.0 andlower
		((AndroidDriver) DriverManager.getDriver()).toggleAirplaneMode();
		// Android 7.0 and higer
		// ((AndroidDriver)DriverManager.getDriver()).setConnection(ConnectionState.AIRPLANE_MODE_MASK);
	}

	public void enableNetwork() {

	}

	// turn on all (data and wi-fi)
	public void turnOnAllData() {

	}

	// turn off all (data and wi-fi)
	public void turnOffAllData() {
		// driver.setConnection(Connection.NONE);

	}

	// turn on airplane
	public void turnOnAirplaneMode() {
		// driver.setConnection(Connection.AIRPLANE);
	}

	// turn on data
	public void turnOnMobileData() {
		// driver.setConnection(Connection.DATA);
	}

	/** 
	 * Since Android Q, a method to change the WiFi service state has been restricted. #12327 Please toggle the state via UI instead of this method. 
	 * The UI flow depends on devices. Please make sure to encode the correct UI flow on your target device under test.
	 */
	public void turnOnWiFi() {
		// driver.setConnection(Connection.WIFI);

	}
	
	/**
	 * Killing the test app
	 */
	public void killApp() {
		if (RunSettings.getMobileOS().equals(MobileOS.ANDROID)) {
			((AndroidDriver)DriverManager.getDriver()).terminateApp(RunSettings.getMobileAppPackage());
		} else if (RunSettings.getMobileOS().equals(MobileOS.ANDROID)) {
			((IOSDriver)DriverManager.getDriver()).terminateApp(RunSettings.getMobileAppPackage());
		}
	}
	
	/**
	 * Activate the test app
	 */
	public void activateApp() {
		if (RunSettings.getMobileOS().equals(MobileOS.ANDROID)) {
			((AndroidDriver)DriverManager.getDriver()).activateApp(RunSettings.getMobileAppPackage());
		} else if (RunSettings.getMobileOS().equals(MobileOS.ANDROID)) {
			((IOSDriver)DriverManager.getDriver()).activateApp(RunSettings.getMobileAppPackage());
		}
	}
	
	/**
	 * Put the current app in the app
	 */
	public void putAppInBackground(int milliseconds) {
		if (RunSettings.getMobileOS().equals(MobileOS.ANDROID)) {
			((AndroidDriver)DriverManager.getDriver()).runAppInBackground(Duration.ofMillis(milliseconds));
		} else if (RunSettings.getMobileOS().equals(MobileOS.ANDROID)) {
			((IOSDriver)DriverManager.getDriver()).runAppInBackground(Duration.ofMillis(milliseconds));
		}
	}

}
