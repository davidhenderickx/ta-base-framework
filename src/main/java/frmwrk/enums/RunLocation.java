package frmwrk.enums;

/**
 * The location to run your automated tests
 */
public enum RunLocation {
	/**
	 * Own machine
	 */
	LOCAL,
	/**
	 * Remote server
	 */
	REMOTE,
	/**
	 * Cloud provider browserstack: https://www.browserstack.com/
	 */
	BROWSERSTACK
}
