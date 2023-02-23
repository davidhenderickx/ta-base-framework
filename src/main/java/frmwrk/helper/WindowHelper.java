package frmwrk.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import frmwrk.drivers.DriverManager;
import frmwrk.reporters.Log;

public class WindowHelper {

	private static ThreadLocal<ArrayList<String>> windowHandles = ThreadLocal.withInitial(ArrayList::new);
	private static ThreadLocal<ArrayList<String>> windowNames = ThreadLocal.withInitial(ArrayList::new);

	/**
	 * Add Window ID to the internal windowHandles list
	 * 
	 * @param ID
	 */
	private static void addWindowHandleToList(String windowHandle) {
		Log.debug("Adding the window handle to the list: " + windowHandle);
		if (windowHandles.get() == null) {

			windowHandles.get().add(windowHandle);
		} else if (windowHandles.get().contains(windowHandle)) {
			Log.debug("The window handle is already present in the list");
		} else {
			windowHandles.get().add(windowHandle);
		}
	}

	/**
	 * Add Window Name to the internal WindowNames list
	 * 
	 * @param name
	 */
	private static void addWindowNameToList(String name) {
		Log.debug("Adding the window name to the list: " + name);
		if (windowNames.get().contains(name)) {
			Log.debug("The window name is already present in the list");
		} else {
			windowNames.get().add(name);
		}
	}

	
	
	/**
	 * Getting the window handle by the given name
	 * 
	 * @param name
	 * @return
	 */
	public static String getWindowHandleByName(String name) {
		Log.debug("Getting the window handle by name: " + name);
		if (windowNames.get().contains(name)) {
			int index = windowNames.get().indexOf(name);
			String wh = windowHandles.get().get(index);
			Log.debug("The window handle: " + name);
			return wh;
		} else {
			Log.debug("No window found in the list by the name");
			return "";
		}
	}

	/**
	 * Get the index from the internal list where a Window Handle is found
	 * 
	 * @param Window Handler
	 * @return
	 */
	public static int getWindowHandleListIndex(String windowHandler) {
		Log.debug("Getting the index of the window in the list with window handle: " + windowHandler);
		int index = windowHandles.get().indexOf(windowHandler);
		if (index != -1) {
			Log.debug("The index of the window handle in the list: " + index);
		} else {
			Log.debug("The window is not in the list");
		}
		return index;
	}
	
	/**
	 * Get the index from the internal list where a specific name is found
	 * 
	 * @param name
	 * @return
	 */
	public static int getWindowNameListIndex(String name) {
		Log.debug("Getting the index of the window in the list with name: " + name);
		int index = windowNames.get().indexOf(name);
		if (index != -1) {
			Log.debug("The index of the window in the list: " + index);
		} else {
			Log.debug("The window is not in the list");
		}
		return index;
	}

	/**
	 * Remove the Window from the list, both on the name and the window handle list
	 * 
	 * @param nameOrWH
	 */
	public static void removeWindowFromList(String nameOrWH) {
		Log.debug("Removing window from the list: " + nameOrWH);
		// When the Window Handle is given
		int index = getWindowHandleListIndex(nameOrWH);
		if (index != -1) {
			Log.debug("Window ID givven. Removing it from the internal list based on window handle");
			windowHandles.get().remove(index);
			windowNames.get().remove(index);
			return;
		}
		// When the Window Name is given
		index = getWindowNameListIndex(nameOrWH);
		if (index != -1) {
			Log.debug("Window ID givven. Removing it from the internal list based on Index");
			windowHandles.get().remove(index);
			windowNames.get().remove(index);
			return;
		}
		// When window not in the lists
		Log.debug("Cannot remove the window from the list since it is not available in the list");
	}

	/**
	 * Adding an entry in the internal window list
	 * 
	 * @param name the name you want to identify the window with
	 * @param WH   the window handler
	 */
	public static void addWindowToList(String name, String WH) {
		Log.debug("Adding window to the list: name= " + name + "; WH= " + WH);
		addWindowHandleToList(WH);
		addWindowNameToList(name);
	}

	
	/**
	 * Get the window handle of the current focussed window
	 * @return
	 */
	public static String getFocussedWindowHandle() {
		Log.debug("Getting the window handle of the focussed window");
		String wh = DriverManager.getDriver().getWindowHandle();
		Log.debug("The window handle: " + wh);
		return wh;
	}

	/**
	 * Get the given name of the current focusessed window
	 * @return the given focussed window name or empty if it doesn't exist in the list
	 */
	public static String getFocussedWindowName() {
		Log.debug("Getting the name of the focussed window");
		String wh = getFocussedWindowHandle();
		if (windowHandles.get().contains(wh)) {
			int index = windowHandles.get().indexOf(wh);
			String windowName = windowNames.get().get(index);
			Log.debug("The window name: " + windowName);
			return windowName;
		} else {
			Log.debug("The focussed window doesn't have a stored name");
			return "";
		}
	}

	/**
	 * Switch the focus to a specific window
	 * @param nameOrWH the given name or window handle
	 */
	public static void switchToWindow(String nameOrWH) {
		// If window handle is given and found in the list
		if (windowHandles.get().contains(nameOrWH)) {
			DriverManager.getDriver().switchTo().window(nameOrWH);
		} 
		// If window name is given and found in the list
		else if (windowNames.get().contains(nameOrWH)) {
			int index = windowNames.get().indexOf(nameOrWH);
			DriverManager.getDriver().switchTo().window(windowHandles.get().get(index));
		} 
		// If not found in the list, try to switch to it based on window handle
		else {
			DriverManager.getDriver().switchTo().window(nameOrWH);
		}

	}

	/**
	 * Switch the focus to a window with a given name
	 * @param name
	 */
	public static void switchToWindowName(String name) {
		Log.debug("Switching to the window name: " + name);
		String wh = getWindowHandleByName(name);
		if (!wh.equals("")) {
			DriverManager.getDriver().switchTo().window(wh);
		} else {
			Log.debug("Cannot switch to the window");
		}
	}

	/**
	 * Switch the focus to a window with a given window handle
	 * @param windowHandle
	 */
	public static void switchToWindowHandle(String windowHandle) {
		Log.debug("Switching to the window handle: " + windowHandle);
		DriverManager.getDriver().switchTo().window(windowHandle);
	}
	
	/**
	 * Opens a new window, switches to this window and saves the name to the list
	 * @param name
	 */
	public static void openNewWindow(String name) {
		Log.debug("Switching to a new window and giving it the name: " + name);
		DriverManager.getDriver().switchTo().newWindow(WindowType.WINDOW);
		addWindowToList(name, getFocussedWindowHandle());
	}

	/**
	 * Updates the window name in the private list 
	 * @param nameOrWH
	 * @param newName
	 */
	public static void updateWindowName(String nameOrWH, String newName) {
		Log.debug("Updating the window name to '" + newName + "' in the list for: " + nameOrWH );
		// If window handle is given and found in the list
		int index = getWindowHandleListIndex(nameOrWH);
		if (index != -1) {
			Log.debug("Updating the window name by found window handle");
			windowNames.get().set(index, newName);
			return;
		}
		// If window name is given and found in the list 
		index = getWindowNameListIndex(nameOrWH);
		if (index != -1) {
			Log.debug("Updating the window name by found name");
			windowNames.get().set(index, newName);
			return;
		} else {
			Log.error("Cannot update the window name since it is not found in the private lists");
		}
	}

	/**
	 * Close window based on given Window Name or Window Handle
	 * 
	 * @param nameOrWH
	 */
	public static void closeWindow(String nameOrWH) {
		Log.debug("Closing the window: " + nameOrWH);
		int idIndex = getWindowHandleListIndex(nameOrWH);
		int nameIndex = getWindowNameListIndex(nameOrWH);

		if (idIndex != -1) {
			Log.debug("Closing the window by window handle");
			String currentWindowHandle = getFocussedWindowHandle();
			DriverManager.getDriver().switchTo().window(nameOrWH);
			DriverManager.getDriver().close();
			removeWindowFromList(nameOrWH);
			if (!currentWindowHandle.equals(nameOrWH)) {
				DriverManager.getDriver().switchTo().window(currentWindowHandle);
			}
		} else if (nameIndex != -1) {
			Log.debug("Closing the window by name");
			String closingWindowHandle = getWindowHandleByName(nameOrWH);
			String currentWindowHandle = getFocussedWindowHandle();
			DriverManager.getDriver().switchTo().window(closingWindowHandle);
			DriverManager.getDriver().close();
			removeWindowFromList(nameOrWH);
			if (!currentWindowHandle.equals(closingWindowHandle)) {
				DriverManager.getDriver().switchTo().window(currentWindowHandle);
			}
		} else {
			Log.debug("Window not found in the list. Trying to close the window by window handle");
			String currentWindowHandle = getFocussedWindowHandle();
			DriverManager.getDriver().switchTo().window(nameOrWH);
			DriverManager.getDriver().close();
			removeWindowFromList(nameOrWH);
			if (!currentWindowHandle.equals(nameOrWH)) {
				DriverManager.getDriver().switchTo().window(currentWindowHandle);
			}
		}

	}

	/**
	 * This method will look for the current active windows of the driver and remove
	 * the non existing ones from the internal lists
	 */
	public static void autoCleanupList() {
		Set<String> whs = DriverManager.getDriver().getWindowHandles();
		for (String wh : whs) {
			int index = getWindowHandleListIndex(wh);
			if (index != -1) {
				windowHandles.get().remove(index);
				windowNames.get().remove(index);
			}
		}
	}

}
