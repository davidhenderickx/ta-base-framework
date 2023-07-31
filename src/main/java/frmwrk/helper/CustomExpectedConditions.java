package frmwrk.helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import frmwrk.reporters.Log;

public class CustomExpectedConditions {

	/**
	 * Waits until the shadow dom is loaded
	 * 
	 * @param driver
	 * @return the shadow dom element
	 */
	public static ExpectedCondition<SearchContext> shadowRootLoaded(final WebElement element) {
		return new ExpectedCondition<SearchContext>() {
			@Override
			public SearchContext apply(WebDriver driver) {
				try {
					return element.getShadowRoot();
				} catch (Exception e) {
					Log.debug("Shadowroot not available");
				}
				return null;
			}

			@Override
			public String toString() {
				return String.format("Shadowroot not available");
			}
		};
	}

	/**
	 * Wait until all the javascript of the page has been executed
	 * 
	 * @param driver
	 * @return
	 */
	public static ExpectedCondition<Boolean> javascriptLoaded(final WebDriver driver) {

		return new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				try {
					return (((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
							.equals("complete"));
				} catch (Exception e) {
					return true;
				}
			}

			@Override
			public String toString() {
				return String.format("JS loading didn't end in time");
			}
		};
	}
	
	/**
	 * Wait until all the JQuery of the page has been executed
	 * 
	 * @param driver
	 * @return
	 */
	public static ExpectedCondition<Boolean> jQueryLoaded(WebDriver driver){
		
		return new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				try {
					return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
				}
				catch (Exception e) {
					return true;
				}
			}
			
			@Override
			public String toString() {
				return String.format("JQuery loading didn't end in time");
			}
		};	
	}

}
