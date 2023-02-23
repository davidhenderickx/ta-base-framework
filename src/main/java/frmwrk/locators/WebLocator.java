package frmwrk.locators;

import org.openqa.selenium.By;

public class WebLocator implements Locator {
	
	By locator = null;
	
	public WebLocator(By locator) {
		setLocator(locator);
	}

	@Override
	public void setLocator(By locator) {
		this.locator = locator;
	}

	@Override
	public By getLocator() {
		return locator;
	}

}
