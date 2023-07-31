package frmwrk.testbase;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import frmwrk.drivers.DriverManager;
import frmwrk.enums.Platform;
import frmwrk.helper.TestDataGenerator;
import frmwrk.locators.Locator;
import frmwrk.reporters.Log;
import frmwrk.settings.RunSettings;

public class BaseElement {

	public int defaultTimeOut = 20000;
	
	public By by;
	
	public BaseElement() {
		
	}
	
	public BaseElement(Locator locator) {
		this.by = locator.getLocator();
	}
	
	public BaseElement(By by) {
		this.by = by;
	}
	
	/**
	 * Retrieve the locator of the element
	 * 
	 * @return the locator
	 */
	public By getLocator() {
		return by;
	}

	public WebElement getElement(By locator) {
		return DriverManager.getDriver().findElement(locator);
	}

	public WebElement getWebElement(By locator) {
		return DriverManager.getWebDriver().findElement(locator);
	}
	
	public List<WebElement> getWebElements(By locator) {
		return DriverManager.getWebDriver().findElements(locator);
	}

	public WebElement getMobileElement(By locator) {
		return DriverManager.getMobileDriver().findElement(locator);
	}

	
	/**
	 * Waiting until the element is present on the DOM of a page. This does not
	 * necessarily mean that the element is visible. The test will fail if the
	 * element is not present
	 * 
	 * @param milliseconds
	 */
	public void waitUntilElementIsPresent(int milliseconds) {
		waitUntilElementIsPresent(by, milliseconds, true);
	}
	
	/**
	 * Waiting until an element is present on the DOM of a page. This does not
	 * necessarily mean that the element is visible. The test will fail if the
	 * element is not present
	 * 
	 * @param elementLocator
	 * @param milliseconds
	 */
	public void waitUntilElementIsPresent(By elementLocator, int milliseconds) {
		waitUntilElementIsPresent(elementLocator, milliseconds, true);
	}

	/**
	 * Waiting until an element is present on the DOM of a page. This does not
	 * necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param milliseconds
	 * @param failTest     if the test fails
	 * @return true if the element is visible, false if not
	 */
	public boolean waitUntilElementIsPresent(By locator, int milliseconds, boolean failTest) {
		Log.debug("Waiting until element is present within " + String.valueOf(milliseconds) + "ms: "
				+ locator.toString() + "");
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofMillis(milliseconds));
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			Log.debug("Element is present");
			return true;
		} catch (Exception e) {
			if (failTest) {
				Log.error("Element is not present: " + e.getMessage());
				throw new AssertionError("Element not present: " + e.getMessage());
			} else {
				Log.debug("Element is not present: " + e.getMessage());
				return false;
			}
		}
	}

	/**
	 * Waiting until an element is present on the DOM of a page. This does not
	 * necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param milliseconds
	 * @param failTest     if the test fails
	 * @return true if the element is visible, false if not
	 */
	public boolean waitUntilElementIsPresent(WebElement element, int milliseconds, boolean failTest) {
		Log.debug("Waiting until element is present within " + String.valueOf(milliseconds) + "ms: "
				+ element.toString() + "");
		long start = System.currentTimeMillis();
		long end = start + milliseconds;
		while (System.currentTimeMillis() < end) {
			try {
				element.findElement(By.xpath("."));
				Log.debug("Element is present");
				return true;
			} catch (Exception e) {
				Log.debug("Element not present: " + e.getMessage());
			}
			waitForMilliseconds(500);
		}
		if (failTest) {
			Log.error("Element is not present");
			throw new AssertionError("Element not present");
		} else {
			Log.debug("Element is not present");
			return false;
		}
	}
	
	
	/**
	 * Waiting until an element is visible. The test will fail if the element is not
	 * present
	 * 
	 * @param milliseconds
	 */
	public void waitUntilElementIsVisible(int milliseconds) {
		waitUntilElementIsVisible(by, milliseconds, true);
	}

	/**
	 * Waiting until an element is visible. The test will fail if the element is not
	 * present
	 * 
	 * @param locator
	 * @param milliseconds
	 */
	public void waitUntilElementIsVisible(By locator, int milliseconds) {
		waitUntilElementIsVisible(locator, milliseconds, true);
	}


	/***
	 * Waiting until an element is visible on the page.
	 * 
	 * @param locator
	 * @param milliseconds
	 * @param failTest     if the test should fail
	 * @return true if the element is visible, false if not
	 */
	public boolean waitUntilElementIsVisible(By locator, int milliseconds, boolean failTest) {
		Log.debug("Waiting until element is visible within " + String.valueOf(milliseconds) + "ms: "
				+ locator.toString() + "");
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofMillis(milliseconds));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			Log.debug("Element is visible");
			return true;
		} catch (Exception e) {
			if (failTest) {
				Log.error("Element is not visible: " + e.getMessage());
				throw new AssertionError("Element not visible: " + e.getMessage());
			} else {
				Log.debug("Element is not visible: " + e.getMessage());
				return false;
			}
		}
	}

	/**
	 * Waiting until an element is visible on the page.
	 * 
	 * @param locator
	 * @param timeout
	 * @param failTest     if the test should fail
	 * @return true if the element is visible, false if not
	 */
	public boolean waitUntilElementIsVisible(WebElement element, int milliseconds, boolean failTest) {
		Log.debug("Waiting until element is visible within " + String.valueOf(milliseconds) + "ms: "
				+ element.toString() + "");
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofMillis(milliseconds));
			wait.until(ExpectedConditions.visibilityOf(element));
			Log.debug("Element is visible");
			return true;
		} catch (Exception e) {
			if (failTest) {
				Log.error("Element is not visible: " + e.getMessage());
				throw new AssertionError("Element not visible: " + e.getMessage());
			} else {
				Log.debug("Element is not visible: " + e.getMessage());
				return false;
			}
		}
	}
	
	/**
	 * Wait until the element is not visible
	 * 
	 * @param milliseconds
	 */
	public boolean waitUntilElementIsNotVisible(int milliseconds){
		return waitUntilElementIsNotVisible(by, milliseconds, true);
	}
	
	/**
	 * Wait until the element is not visible
	 * 
	 * @param milliseconds
	 */
	public boolean waitUntilElementIsNotVisible(By elementLocator, int milliseconds){
		return waitUntilElementIsNotVisible(elementLocator, milliseconds, true);
	}
	
	
	/**
	 * Wait until element is not visible
	 * 
	 * @param elementLocator
	 * @param milliseconds
	 * @param failTest
	 * @return true if the element is not visible, false if yes
	 */
	public boolean waitUntilElementIsNotVisible(By elementLocator, int milliseconds, boolean failTest){
		Log.debug("Waiting until element is not visible within " + String.valueOf(milliseconds) + "ms: "
				+ elementLocator.toString() + "");
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofMillis(milliseconds));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
			Log.debug("Element is not visible");
			return true;
		} catch (Exception e) {
			if (failTest) {
				Log.error("Element is visible: " + e.getMessage());
				throw new AssertionError("Element visible: " + e.getMessage());
			} else {
				Log.debug("Element is visible: " + e.getMessage());
				return false;
			}
		}
	}
	
	
	

	/**
	 * Waiting until an element is clickable on the page.
	 * 
	 * @param locator
	 * @param timeout
	 */
	public void waitUntilElementIsClickable(By locator, int milliseconds) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofMillis(milliseconds));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * Waiting until an element is clickable on the page.
	 * 
	 * @param locator
	 * @param timeout
	 */
	public void waitUntilElementIsClickable(WebElement element, int milliseconds) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofMillis(milliseconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * Waiting until the element contains a specific text 
	 * @param text
	 * @param milliseconds
	 */
	public void waitUntilElementContainsText(String text, int milliseconds) {
		waitUntilElementContainsText(by, text, milliseconds, true);
	}
	
	
	/**
	 * Waiting until an element contains a specific text 
	 * @param elementLocator
	 * @param text
	 * @param milliseconds
	 */
	public boolean waitUntilElementContainsText(By elementLocator, String text, int milliseconds, boolean failTest) {
		Log.debug("Waiting until element (" + elementLocator + ") contains the text '" + text + "' within " + String.valueOf(milliseconds) + "ms:");
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofMillis(milliseconds));
			wait.until(ExpectedConditions.textToBePresentInElementLocated(elementLocator, text));
			Log.debug("Element contains the text");
			return true;
		} catch (Exception e) {
			if (failTest) {
				Log.error("Element does not contain the text: " + e.getMessage());
				throw new AssertionError("Element does not contain the text: " + e.getMessage());
			} else {
				Log.debug("Element does not contain the text: " + e.getMessage());
				return false;
			}
		}
		
	}
	
	/**
	 * Checking if the element is present on the DOM of a page. This does not
	 * necessarily mean that the element is visible. 
	 * It takes the default timeout.
	 * 
	 */
	public boolean isElementPresent() {
		return isElementPresent(by, defaultTimeOut);
	}
	
	/**
	 * Checking if the element is present on the DOM of a page. This does not
	 * necessarily mean that the element is visible.
	 * 
	 * @param milliseconds
	 */
	public boolean isElementPresent(int milliseconds) {
		return isElementPresent(by, milliseconds);
	}
	
	/**
	 * Checking if an element is present on the DOM of a page. This does not
	 * necessarily mean that the element is visible.
	 * 
	 * @param elementLocator
	 * @param milliseconds
	 */
	public boolean isElementPresent(By elementLocator, int milliseconds) {
		Log.debug("Checking if element is present within " + String.valueOf(milliseconds) + "ms: " + elementLocator.toString()
				+ "");
		return waitUntilElementIsPresent(elementLocator, milliseconds, false);
	}
	
	/**
	 * Checking if the element is visible on the screen page. 
	 * It takes the default timeout.
	 * 
	 */
	public boolean isElementVisible() {
		return isElementVisible(by, defaultTimeOut);
	}
	
	/**
	 * Checking if the element is visible on the screen. 
	 * 
	 * @param milliseconds
	 */
	public boolean isElementVisible(int milliseconds) {
		return isElementVisible(by, milliseconds);
	}
	
	
	/**
	 * Checking if the element is visible on the screen. 
	 * 
	 * @param locator
	 * @param milliseconds
	 */
	public boolean isElementVisible(Locator locator, int milliseconds) {
		return isElementVisible(locator.getLocator(), milliseconds);
	}
	
	/**
	 * Checking if an element is visible on the screen. 
	 * 
	 * @param locator
	 * @param milliseconds
	 */
	public boolean isElementVisible(By elementLocator, int milliseconds) {
		Log.debug("Checking if element is visible within " + String.valueOf(milliseconds) + "ms: " + elementLocator.toString()
				+ "");
		if (!isElementPresent(elementLocator, milliseconds)) {
			Log.debug("Element is not present.");
			return false;
		}
		if (waitUntilElementIsVisible(elementLocator, milliseconds, false)) {
			Log.debug("Element is visible.");
			return true;
		} else {
			Log.debug("Element is not visible.");
			return false;
		}
	}
	
	/**
	 * Click on the element
	 * 
	 */
	public void click() {
		click(by);
	}

	/**
	 * Click on an element
	 * 
	 * @param locator
	 */
	public void click(Locator locator) {
		click(locator.getLocator());
	}

	/**
	 * Click on an element
	 * 
	 * @param locator
	 */
	public void click(By locator) {
		Log.debug("Trying to click on " + locator.toString());
		waitUntilElementIsPresent(locator, defaultTimeOut);
		waitUntilElementIsVisible(locator, defaultTimeOut);
		waitUntilElementIsClickable(locator, defaultTimeOut);
		getElement(locator).click();
		Log.debug("Clicked on " + locator.toString());
	}

	/**
	 * Click on an element
	 * 
	 * @param element
	 */
	public void click(WebElement element) {
		Log.debug("Trying to click on " + element.toString());
		waitUntilElementIsPresent(element, defaultTimeOut, true);
		waitUntilElementIsVisible(element, defaultTimeOut, true);
		waitUntilElementIsClickable(element, defaultTimeOut);
		element.click();
		Log.debug("Clicked on " + element.toString());
	}

	/**
	 * Move to the element and click on it on an element
	 * 
	 * @param locator
	 */
	public void moveToAndClick(By locator) {
		waitUntilElementIsPresent(locator, defaultTimeOut);
		waitUntilElementIsVisible(locator, defaultTimeOut);
		waitUntilElementIsClickable(locator, defaultTimeOut);
		WebElement element = getElement(locator);
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		new Actions(DriverManager.getDriver()).moveToElement(element).click().perform();
	}
	
	/**
	 * Removes the existing text and adds the new text in the element
	 * 
	 * @param text
	 */
	public void setText(String text) {
		setText(by, text);
	}

	/**
	 * Removes the existing text and adds the new text
	 * 
	 * @param locator
	 * @param text
	 */
	public void setText(Locator elementlocator, String text) {
		setText(elementlocator.getLocator(), text);
	}

	/**
	 * Removes the existing text and adds the new text
	 * 
	 * @param by
	 * @param text
	 */
	public void setText(By elementlocator, String text) {
		Log.debug("Setting the text of " + elementlocator.toString() + ": " + text);
		waitUntilElementIsPresent(elementlocator, defaultTimeOut);
		waitUntilElementIsVisible(elementlocator, defaultTimeOut);
		WebElement element = getElement(elementlocator);
		element.click();
		element.clear();
		element.sendKeys(text);
		Log.debug("Text set of " + elementlocator.toString() + ": " + text);
		// element.sendKeys(Keys.TAB); // onchange event will not fire until a different
		// element is selected

	}
	
	/**
	 * Get the text of the element
	 */
	public String getText() {
		return getText(by);
	}

	/**
	 * Get the text of an element
	 * 
	 * @param locator
	 */
	public String getText(Locator locator) {
		return getText(locator.getLocator());
	}

	/**
	 * Get the text of an element
	 * 
	 * @param locator
	 */
	public String getText(By locator) {
		Log.debug("Getting the text of " + locator.toString());
		waitUntilElementIsPresent(locator, defaultTimeOut);
		waitUntilElementIsVisible(locator, defaultTimeOut);
		return getElement(locator).getText();
	}

	

	/**
	 * Returns the RGB value of the background of an element
	 * 
	 * @param locator
	 * @return
	 */
	public String getBackGroundColor(By locator) {
		WebElement element = getElement(locator);

		if (RunSettings.getRunPlatform().equals(Platform.WEB)) {
			return element.getCssValue("background-color");
		} else if (RunSettings.getRunPlatform().equals(Platform.MOBILE)) {
			org.openqa.selenium.Point point = getCenter(locator);
			int centerx = point.getX();
			int centerY = point.getY();

			File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

			BufferedImage image = null;
			try {
				image = ImageIO.read(scrFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Getting pixel color by position x and y
			int clr = image.getRGB(centerx, centerY);
			int red = (clr & 0x00ff0000) >> 16;
			int green = (clr & 0x0000ff00) >> 8;
			int blue = clr & 0x000000ff;
			System.out.println("Red Color value = " + red);
			System.out.println("Green Color value = " + green);
			System.out.println("Blue Color value = " + blue);
			return String.valueOf(red) + String.valueOf(green) + String.valueOf(blue);
		}
		return "not found";
	}

	/**
	 * Getting the center of an element
	 * 
	 * @param locator
	 * @return
	 */
	public Point getCenter(By locator) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Wait for an amount of milliseconds
	 * 
	 * @param milliseconds
	 */
	public void waitForMilliseconds(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Takes a screenshot
	 * 
	 * @param screenshotName
	 * @param addUniquePart  if you want to add a unique part to the screenshotname
	 * @return
	 */
	public String takeScreenshot(String screenshotName, boolean addUniquePart) {
		if (!RunSettings.getRunPlatform().equals(Platform.API)) {
			if (addUniquePart) {
				screenshotName = screenshotName + TestDataGenerator.getDateTime();
			}
			String directory = System.getProperty("user.dir") + File.separator + "test-output" + File.separator
					+ "screenshots";
			new File(directory).mkdirs();
			String path = directory + File.separator + screenshotName + ".png";
			try {
				File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File(path));
				Log.info("Captured screenshot: " + path);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Log.screenshot(path, "Screenshot:");
			return path;
		} else {
			Log.info("Cannot capture screenshot for API");
			return null;
		}
	}

	public String getScreenshotName(String absolutePath) {
		return absolutePath.substring(absolutePath.lastIndexOf(File.separator) + 1).trim();
	}

	
	/***
	 * Swipe an element vertically into the viewport of the device. 
	 * Sometimes the element is outside of the screen of a mobile device.
	 * When that occurs, it could be that the element is also not found in the DOM.
	 */
	public void swipeVerticalIntoView() {
		swipeVerticalIntoView(by);
	}
	
	/***
	 * Swipe an element into the viewport of the device. 
	 * Sometimes the element is outside of the screen of a mobile device.
	 * When that occurs, it could be that the element is also not found in the DOM.
	 * 
	 * @param elementLocator
	 */
	public void swipeVerticalIntoView(Locator elementLocator) {
		swipeVerticalIntoView(elementLocator.getLocator());
	}

	/***
	 * Swipe an element into the viewport of the device. 
	 * Sometimes the element is outside of the screen of a mobile device.
	 * When that occurs, it could be that the element is also not found in the DOM.
	 * 
	 * @param elementLocator
	 */
	public void swipeVerticalIntoView(By elementLocator) {
		Log.debug("Swiping the element into view: " + elementLocator.toString());

		int maxSwipes = 10;
		int viewIsTheSame = 0; 
		String orgView = "";
		String newView = "";

		Log.debug("First Swiping down into view");
		viewIsTheSame = 0; 
		orgView = "";
		newView = "";
		for (int i = 1; i <= maxSwipes; i++) {
			Log.debug("Swiping down into view, attempt: " + String.valueOf(i));
			if (isElementVisible(elementLocator, 2000)) {
				Log.debug("Element swiped into view");
				return;
			}

			newView = DriverManager.getDriver().getPageSource();
			if (newView.equals(orgView)) {
				if (viewIsTheSame == 3) { //Extra check because sometimes the DOM is not refreshed fast enough
					Log.error("Reached the end of the view");
					throw new AssertionError("Reached the end of the view");
				} else {
					viewIsTheSame++;
					Log.debug("View was not updated, checking again.");
				}
				
			} else {
				Log.debug("View is updated");
				viewIsTheSame = 0;
				orgView = newView;
			}
			// Get the size of screen
			Dimension screenSize = DriverManager.getDriver().manage().window().getSize();
			// Find swipe start and end point from screen’s with and height.
			// Starting y location set to 80% of the height (near bottom)
			int fromY = (int) (screenSize.height * 0.80);
			// Ending y location set to 20% of the height (near top)
			int toY = (int) (screenSize.height * 0.20);
			// x position set to mid-screen horizontally
			int x = screenSize.width / 2;
			// Swipe
			swipeByCoordinates(x, fromY, x, toY);
			waitForMilliseconds(500);
			
		}
		
		Log.debug("Element not found by swiping down");
		
		Log.debug("Now swiping up into view");
		viewIsTheSame = 0; 
		orgView = "";
		newView = "";
		for (int i = 1; i <= maxSwipes; i++) {
			Log.debug("Swiping up into view, attempt: " + String.valueOf(i));
			if (isElementVisible(elementLocator, 2000)) {
				Log.debug("Element swiped into view");
				return;
			}

			newView = DriverManager.getDriver().getPageSource();
			if (newView.equals(orgView)) {
				if (viewIsTheSame == 3) { //Extra check because sometimes the DOM is not refreshed fast enough
					Log.error("Reached the end of the view");
					throw new AssertionError("Reached the end of the view");
				} else {
					viewIsTheSame++;
					Log.debug("View was not updated, checking again.");
				}
				
			} else {
				Log.debug("View is updated");
				viewIsTheSame = 0;
				orgView = newView;
			}
			// Get the size of screen
			Dimension screenSize = DriverManager.getDriver().manage().window().getSize();
			// Find swipe start and end point from screen’s with and height.
			// Starting y location set to 20% of the height (near bottom)
			int fromY = (int) (screenSize.height * 0.20);
			// Ending y location set to 80% of the height (near top)
			int toY = (int) (screenSize.height * 0.80);
			// x position set to mid-screen horizontally
			int x = screenSize.width / 2;
			// Swipe
			swipeByCoordinates(x, fromY, x, toY);
			waitForMilliseconds(500);
			
		}

		Log.debug("Swipe into view failed!");

	}
	
	/***
	 * Swipe an element until another element is found
	 * 
	 * @param elementToFind the element you need to find
	 * 
	 */
	public void swipeHorizontalIntoView(By elementToFind) {
		swipeHorizontalIntoView(by, elementToFind);
	}
	
	/***
	 * Swipe an element until another element is found
	 * 
	 * @param elementToSwipe the element you are going to swipe
	 * @param elementToFind the element you need to find
	 * 
	 */
	public void swipeHorizontalIntoView(By elementToSwipe, By elementToFind) {
		Log.debug("Swiping the element " + elementToSwipe.toString() + ", until element " + elementToFind.toString() + " is displayed");

		int maxSwipes = 20;
		int viewIsTheSame = 0; 
		String orgView = "";
		String newView = "";

		viewIsTheSame = 0; 
		orgView = "";
		newView = "";
		for (int i = 1; i <= maxSwipes; i++) {
			Log.debug("Swiping to the right, attempt: " + String.valueOf(i));
			if (isElementVisible(elementToFind, 2000)) {
				Log.debug("Element that needs to be found is displayed");
				return;
			}

			newView = DriverManager.getDriver().getPageSource();
			if (newView.equals(orgView)) {
				if (viewIsTheSame == 3) { //Extra check because sometimes the DOM is not refreshed fast enough
					Log.error("No updates in the view");
					throw new AssertionError("No updates in the view");
				} else {
					viewIsTheSame++;
					Log.debug("View was not updated, checking again.");
				}
				
			} else {
				Log.debug("View is updated");
				viewIsTheSame = 0;
				orgView = newView;
			}
			WebElement swipeElement = getElement(elementToSwipe);
			Log.debug("Swiping element location: " + swipeElement.getLocation());
			Log.debug("Swiping element size: " + swipeElement.getSize());
			int middleOfElement = (int) (swipeElement.getLocation().getY() + (swipeElement.getSize().height/2));
			int almostLeftSideOfElement = (int) (swipeElement.getLocation().getX() + (swipeElement.getSize().width * 0.20));
			int almostRightSideOfElement = (int) (swipeElement.getLocation().getX() + (swipeElement.getSize().width * 0.80)); 
			swipeByCoordinates(almostRightSideOfElement, middleOfElement, almostLeftSideOfElement, middleOfElement);
			waitForMilliseconds(500);
			
		}

		Log.debug("Swipe into view failed!");

	}
	
	

	/***
	 * Swipe action on a mobile device through coordinates
	 * 
	 * @param fromX
	 * @param toX
	 * @param fromY
	 * @param toY
	 */
	public void swipeByCoordinates(int fromX, int fromY, int toX, int toY) {
		Log.debug("Swiping from (" + String.valueOf(fromX) + "," + String.valueOf(fromY) + ") to ("
				+ String.valueOf(toX) + "," + String.valueOf(toY) + ")");

		Point source = new Point(fromX, fromY);
		Point target = new Point(toX, toY);
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		Sequence swipe = new Sequence(finger, 0);
		swipe.addAction(
				finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), source.x, source.y));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(
				finger.createPointerMove(Duration.ofMillis(450), PointerInput.Origin.viewport(), target.x, target.y));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		((RemoteWebDriver) DriverManager.getDriver()).perform(Arrays.asList(swipe));
		waitForMilliseconds(500);

		Log.debug("Swipe executed");

	}
	
	
	public void getTableData() {
		/**
		 * TODO
		 */
	}
	
}
