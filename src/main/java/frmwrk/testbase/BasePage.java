package frmwrk.testbase;

public class BasePage extends BaseElement {
	
	public BaseElement app = new BaseElement();	
	
	/**
	 * Takes a screenshot
	 * 
	 * @param screenshotName
	 * @param addUniquePart  if you want to add a unique part to the screenshotname
	 * @return 
	 */
	public String takeScreenshot(String screenshotName, boolean addUniquePart) {
		app.takeScreenshot(screenshotName, addUniquePart);
		return "";
	}
	
}