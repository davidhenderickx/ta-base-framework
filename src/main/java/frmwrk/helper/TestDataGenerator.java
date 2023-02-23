package frmwrk.helper;

import java.util.Date;
import java.time.LocalDate;

public class TestDataGenerator {
	
	
	public static String generateRandomText() {
		// TODO implement random generator
		return "";
	}
	
	/**
	 * Generates a unique date-time string based on the current date and time
	 * @return
	 */
	public static String getDateTime() {
		Date d = new Date();
		return d.toString().replace(":", "_").replace(" ", "_") ;
	}

	/**
	 * Generates a date string based on the current date
	 * @return
	 */
	public static String getDate() {
		return LocalDate.now().toString().replace("-", "") ;
	}
}
