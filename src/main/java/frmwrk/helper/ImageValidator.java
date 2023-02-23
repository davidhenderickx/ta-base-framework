package frmwrk.helper;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import frmwrk.drivers.DriverManager;


public class ImageValidator {

	public static void isPresent() {

		WebDriver driver = DriverManager.getDriver();

		WebElement logoElement = driver.findElement(By.xpath("//h1[@id='logo']//a//img"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		Boolean logoPresent = (Boolean) (js.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				logoElement));
		Assert.assertTrue(logoPresent, "Logo Present");
		


	}

	public static void isDisplayed(WebElement element) {

		WebDriver driver = DriverManager.getDriver();

		WebElement logo = driver.findElement(By.xpath("//h1[@id='logo']//a//img"));
		Boolean logoPresent = logo.isDisplayed();
		Assert.assertTrue(logoPresent, "Logo Present");

	}

	public static void arePixelSame() {

		// Declaration of files from local drive
		String imgFile1 = "C:\\Users\\Training\\Actual logo.jpg";
		String imgFile2 = "C:\\Users\\Training\\Expected logo.jpg";

		// Encoding image file
		Image img1 = Toolkit.getDefaultToolkit().getImage(imgFile1);
		Image img2 = Toolkit.getDefaultToolkit().getImage(imgFile2);

		try {
			// Getting pixels
			PixelGrabber pixGrab1 = new PixelGrabber(img1, 0, 0, -1, -1, false);
			PixelGrabber pixGrab2 = new PixelGrabber(img2, 0, 0, -1, -1, false);

			// Integer array to store the pixels
			int[] dataArry1 = null;
			int[] dataArry2 = null;

			// Getting Height & Width of the pixels
			if (pixGrab1.grabPixels()) {
				int height = pixGrab1.getHeight();
				int width = pixGrab1.getWidth();
				dataArry1 = new int[width * height];
				dataArry1 = (int[]) pixGrab1.getPixels();
			}

			if (pixGrab2.grabPixels()) {
				int height2 = pixGrab2.getHeight();
				int width2 = pixGrab2.getWidth();
				dataArry2 = new int[width2 * height2];
				dataArry2 = (int[]) pixGrab2.getPixels();
			}

			System.out.println("Pixel Comparison: " + Arrays.equals(dataArry1, dataArry2));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void sameLocation() {

		WebDriver driver = DriverManager.getDriver();

		WebElement logo = driver.findElement(By.xpath("//h1[@id='logo']//a//img"));
		
		int ExpectedLocationX = 100;
		int ExpectedLocationY = 500;
		
		int x = logo.getLocation().getX();
		int y = logo.getLocation().getY();
		
		if (x == ExpectedLocationX && y == ExpectedLocationY) {
			System.out.println("logo is visible");
		} else {
			System.out.println("logo is NOT visible");
		}

	}
	
	public static void isDisplayed2() {

		WebDriver driver = DriverManager.getDriver();

		WebElement logo = driver.findElement(By.xpath("//h1[@id='logo']//a//img"));
		
		int w = logo.getRect().getWidth();
		int h = logo.getRect().getHeight();
		if (w > 0 && h > 0) {
			System.out.println("logo is visible");
		} else {
			System.out.println("logo is NOT visible");
		}

	}

}
