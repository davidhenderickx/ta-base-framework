package frmwrk.locators;

import org.openqa.selenium.By;

public class AndroidLocator implements Locator {

	By locator = null;

	public AndroidLocator(By locator) {
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
