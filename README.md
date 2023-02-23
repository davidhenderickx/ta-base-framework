# BDH-TEST-AUTOMATION-FRAMEWORK

***

### Description

BDH-TEST-AUTOMATION-FRAMEWORK is a Java based test automation framework that can be used for WEB, MOBILE and API test automation.
The framework provides a user friendly layer on top of the test automation technologies, implemented with best practices in mind.

You will need to know the basic idea behind and use of:
* Appium: https://appium.io/
* Selenium: https://www.selenium.dev/
* RestAssured: https://rest-assured.io/
* TestNG: https://testng.org/doc/


### Features

* Web test automation (supported by Selenium)
* Mobile test automation (supported by Appium)
* API test automation (supported by RestAssured)
* Test case and test suite creation (supported by TestNG)
* Reporting (supported by TestNG, ExtentReports and Log4J)
* BrowserStack integration

### Project Explorer

```
project
│	ci_settings.xml			'File for GitLab CI
│	POM.XML					'The POM file containing dependencies/maven plugins/...
│	README.md				'The documentation
│	TestNG_NoTests.xml		'Test cases that are being executed when building the framework. There are no test cases in it, since no unit tests have been created
│
└──	src/main/java
│   └──	frmwrk.drivers 					'Contains all drivers
│   │	│	DriverManager.java 				'This will start and return the correct driver depending on the type of tests 
│   │	│	MobileDriverManager.java 		'This contains the connection to the mobile device through Appium/Browserstack 
│   │	│	WebDriverManager.java 			'This contains the setup and teardown of the browsers for web testing
│   │
│   └──	frmwrk.enums 					'Contains all enums
│   │	│	Environment.java 				'The environment where the test will be executed. Currently not used (Prod, UAT, ...)
│   │	│	MobileAppType.java 				'The type of mobile application that needs to be tested (Browser or App)
│   │	│	MobileBrowser.java 				'The mobile web browsers that can be tested
│   │	│	MobileOS.java 					'Possible mobile operating systems to use (IOS, Android)
│   │	│	Platform.java 					'The different platforms to test (API, WEB, ..)
│   │	│	RunLocation.java 				'Where to run the test scripts (Local machine, Remote, ...)
│   │	│	WebBrowser.java 				'The web browsers that can be tested
│   │
│   └──	frmwrk.helper 					'Contains reusable helper methods for tests and the framework
│   │	│	ADB.java 						'Contains ADB commands to directly interact with the mobile device
│   │	│	BrowserStack.java 				'Contains helper methods for BrowserStackThe type of mobile application that needs to be tested (Browser or App)
│   │	│	FileHelper.java 				'Contains helper methods for creating/moving/reading/... files
│   │	│	MobileCapabilitiesReader.java	'Contains the methods to read the capabilities.json files that contains the desired capabilities for your device/machine
│   │	│	MobileDevice.java 				' Contains helper methods for interacting with the mobile device. Example: setting airplane mode, setting geolocation, ...
│   │	│	TestDataGenerator.java 			'Contains helper methods to generate test data
│   │
│   └───frmwrk.listeners				'Contains the listeners
│   │	│	RetryAnnotationTransform.java 	'Contains ADB commands to directly interact with the mobile device
│   │	│	RetryListener.java 				'You can use this listener if you want to have retry functionality
│   │	│	TestListener.java 				'This listener helps with the creation of the ExtentReport
│   │
│   └──	frmwrk.locators					'Contains different locators in order to be very flexible when you define your element
│   │	│	AndroidLocator.java 			'Locator for Android
│   │	│	IOSLocator.java 				'Locator for IOS
│   │	│	Locator.java 					'The interface for all locators
│   │	│	MobilLocator.java				'Generic Mobile locator
│   │	│	WebLocator.java 				'Locator for WEB
│   │
│   └──	frmwrk.reporters				'Contains the setup of the different reports
│   │	│	ExtentReportManager.java		'Manages the ExtentReport
│   │	│	ExtentTestManager.java 			'Manages the data for each executed test to add into the ExtentReport
│   │	│	Log.java 						'Manages the log4J logging and adds data to the ExtentReport. This will be used within your test / page objects
│   │
│   └──	frmwrk.settings					'Contains the Run Settings
│   │	│	RunSettings.java				'Manages all the possible settings in the framework. 
│   │
│   └──	frmwrk.testbase					'Contains the main link between the framework and the test projects
│   	│	BaseElement.java				'Contains all the basic functionality that you can do with an element
│   	│	BasePage.java 					'Contains the general test actions
│   	│	BaseTest.java 					'Manages the Settings of the test 
│
└──	src/main/resources
	│	extent.properties 				'The properties of the ExtentReports
	│   log4j2.properties 				'The properties of the Log4J framework
	│   settings.properties				'The default settings of the framework, which can be overwritten

```


## Build Framework

***

### Prerequisites

The following needs to be installed:
* Java Developtment Kit (JDK): Java JDK 11 (https://jdk.java.net/archive/)
* Maven: https://maven.apache.org/download.cgi


### Build

1. Clone the project from git: 
	```
	> git clone https://xxxx@gitlab.com/biogenhs/bdh-tests-automation/bdh-tests-automation-framework
	```
  
2. Open a terminal

3. Go to the root folder of the project, where the POM.XML file is located
	```
    > cd xxx/xxx/bdh-tests-automation-framework
    ```

3. Run the command:
    ```
    > mvn clean install
    ```

4. Maven will build jar files and place them in different folders:
	* 'Maven dependency' jars in user's personal Maven folder: ```userdir\.m2\repository\com\biogen\bdh-tests-automation-framework```
	* 'referenceable' jars in the project folder: ```bdh-tests-automation-framework\target\generated-jars```


## Distribute Framework

***

When a user wants to use the framework, he needs to 'link' it to his java project.
Depending on the type of Java project, the user needs different files.

### By Reference

When a user wants to reference the framework through external jars, he needs the generated jars found in the  ```bdh-tests-automation-framework\target\generated-jars``` folder

### By POM.XML

When a user creates a Maven project and wants to reference the framework in the POM.XML, he needs to have all the files (including the parent folder structure) generated in your local Maven repository location (```.m2```)

```
<dependencies>
	<dependency>
		<groupId>com.biogen</groupId>
		<artifactId>bdh-tests-automation-framework</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>
</dependencies>
```

Remark: These files are not available in the Maven cloud repository for automatic retrieval. Manual effort is needed to put them in your local maven dependency folder (.m2)

## Implement Framework

***

### Add framework to test project

##### By Reference:

You can directly reference the framework by using the generated jars in the ```bdh-tests-automation-framework\target\generated-jars``` folder

##### By POM.XML:

When creating a Maven project for the automated tests, you can reference the framework in the POM.XML: 

```
<dependencies>
	<dependency>
		<groupId>com.biogen</groupId>
		<artifactId>bdh-tests-automation-framework</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>
</dependencies>
```

This dependency is not available in the Maven cloud repository for automatic retrieval. Manual effort is needed to put the correct files in the local maven dependency folder (```.m2```)

### Extend framework

To be able to use all the functionality of the framework, the test project needs to extend the base classes of the framework:

* ```frmwrk.testbase.BaseElement.java``` : The parent class for all element classes. It contains the test element (input fields, buttons, ...) interactions
* ```frmwrk.testbase.BasePage.java``` : The parent class for all page object classes. It contains generic test steps and test object interaction
* ```frmwrk.testbase.BaseTest.java``` : The parent class for all test classes. It contains the initialisation of the RunSettings and manages the start and end of a test.

## Creating tests
***

Tests are created in a separate project that is dependant on the framework project.
Structure your test project with packages, so that it is easy to find everything.  
An example of a Maven test project:

```
project
│	ci_settings.xml			'File for GitLab CI
│	POM.XML					'The POM file containing dependencies/maven plugins/...
│	README.md				'The documentation
│	TestNG_MobileTests.xml	'A list of mobile test cases that The Mobile test casesest cases that are being executed when building the framework. There are no test cases in it, since no unit tests have been created
│	TestNG_APITests.xml		'Test cases that are being executed when building the framework. There are no test cases in it, since no unit tests have been created
│
└──	src/test/java
│   └──	mobile.elements					'Contains types of (complex) elements of the application
│   │	│	MobileBaseElement.java 			'This class extends the BaseElement.java class of the framework. All test project elements will extend from this class. 
│   │	│	Button.java 					'This class contains the actions that you can do with a button
│   │	│	Field.java 						'This class contains the actions that you can do with a field
│   │
│   └──	mobile.pages 					'Contains the page object classes of the application
│   │	│	MobileBasePage.java 			'This class extends the BasePage.java class of the framework. All test project pages will extend from this class.
│   │	│	LoginPage.java 					'The page object class of the login screen
│   │	│	WelcomePage.java 				'The page object class of the welcome screen
│   │
│   └──	mobile.tests 					'Contains the test cases of the application
│   │	│	MobileBaseTest.java 			'This class extends the BaseTest.java class of the framework. All test project tests will extend from this class. 
│   │	│	LoginTests.java 				'The tests regarding the login functionality
│   │	│	DisclaimerTests.java			'The tests regarding the disclaimer functionality
│   │
│   └──	mobile.helpers 					'Helper classes for testing application
│   │	│	GenerateTestData.java 			'Generating test data that is needed for the tests 
│
└──	src/test/resources
	│	deviceCapabilities.json			'JSON file containing the devices that need to be used

```


### Base

To be able to use all the functionality of the framework, the test project needs to extend the base classes of the framework:

* ```frmwrk.testbase.BaseElement``` : The parent class for all element classes. It contains the test element (input fields, buttons, ...) interactions
* ```frmwrk.testbase.BasePage``` : The parent class for all page object classes. It contains generic test steps and test object interaction
* ```frmwrk.testbase.BaseTest``` : The parent class for all test classes. It contains the initialisation of the RunSettings and manages the start and end of a test.


### Elements 

For more complex elements (datepickers / checkboxes dynamic tables / custom dropdowns / ...)  in your application, it is adviced to create custom classes. These custom classess contain, next to the constructor(s), also the action(s) that you can do with these complex elements.

Often complex elements require a multitude of small actions, in order to do something. Instead of copying these small actions every time within your tests or page objects, you can store them in a reusable method in the class for this elements.
Custom class for elements, should extend from the ```BaseElement.java``` class of the framework. This lets you reuse all the framework functionality.

Example: Checkbox

```java
public class CheckBox extends BaseElement {
	
	public CheckBox(Locator locator) {
		this(locator.getLocator());
	}
	
	public CheckBox(By by) {
		super.elementLocator = by;
	}

	public void check() {
		Log.debug("Checking the checkbox: " + elementLocator.toString());
		waitUntilElementIsPresent(defaultTimeOut);
		waitUntilElementIsVisible(defaultTimeOut);
		WebElement element = getElement(elementLocator);
		if (element.getAttribute("checked").equals("true")){
			Log.debug("The checkbox is already checked");
		} else {
			Log.debug("The checkbox is not yet checked, checking it");
			element.click();
		};	
	}
	
	public void uncheck() {
		Log.debug("Unchecking the checkbox: " + elementLocator.toString());
		waitUntilElementIsPresent(defaultTimeOut);
		waitUntilElementIsVisible(defaultTimeOut);
		WebElement element = getElement(elementLocator);
		if (element.getAttribute("checked").equals("false")){
			Log.debug("The checkbox is already unchecked");
		} else {
			Log.debug("The checkbox is not yet unchecked, unchecking it");
			element.click();
		};	
		
	}
}
```

Using the checkbox in your page object:

```java
public class ActivityDailyWalkPage extends BasePage {
	
	private CheckBox chkBeforeStartingMyPhoneHasEnoughBattery = new CheckBox(AppiumBy.accessibilityId("SMW_BEFORE_STARTING_PAGE_BATTERY_CHECKBOX_LABEL"));
	private CheckBox chkBeforeStartingOutdoorAndCanWalkSafely = new CheckBox(AppiumBy.accessibilityId("SMW_BEFORE_STARTING_PAGE_OUTDOOR_CHECKBOX_LABEL"));
	private Button btnBeforeStartingIAmReady = new Button(AppiumBy.accessibilityId("SMW_ACTIVITY_BEFORE_STARTING_BUTTON"));
	
	
	/**
	 * Checking all before starting points and clicking on the I'm ready button
	 */
	public void checkAllBeforeStartPoints() {
		Log.step("Checking all start points");
		chkBeforeStartingMyPhoneHasEnoughBattery.check();
		chkBeforeStartingOutdoorAndCanWalkSafely.check();
		Log.step("Clicking 'I'm ready' button");
		btnBeforeStartingIAmReady.click();
	}
}
```


### Pages - Page Object Pattern


Page object pattern is a way to minimise the percentage of repetitive code and to make the code more maintainable by separating the implementation of test scripts into the following sections:

* Locators of elements
* Page functionality 
* Test scripts 

A page object class abstracts a complete or part of a page/view of your application into a class.
The page object class contains:

* Private elements with their locator 
* Public functionality that those elements provide in methods

The page object class methods do not contain assertions. They can return nothing, an integer, text, boolean, another page object class, ... . 
This return value can then be asserted in the test script.

The test script:

* Calls the functionalities that those page object classes provide
* The test script does not call directly the elements
* The test script does not search for elements!
* The test script asserts return values from the page object class methods

Advantages of Page objects:

* Increased Reusability: The page object methods can be reused across different test cases/test suites. Hence, the overall code size will reduce by a good margin due to the increase in the page method reusability factor.
* Improved Maintainability: As the test scenarios and locators are stored separately, it makes the code cleaner, and less effort is spent on maintaining the test code.
* Minimal impact due to UI changes: Even if there are frequent changes in the UI, changes might only be required in the object repository part of the page object class. There is minimal to no impact on the implementation of the test scenarios.

Example:

```java
public class LoginPage extends MobileBasePage {
	
	/** The elements **/
	private Field fldUsername = new Field(AppiumBy.accessibilityId("USERNAME"));
	private Field fldPassword = new Field(AppiumBy.accessibilityId("PASSWORD"));
	private Button btnLogin = new Button(AppiumBy.accessibilityId("LOGIN_BUTTON"));
	
	private Text txtModalLoginErrorTitle = new Text(AppiumBy.accessibilityId("ERROR_MODAL_TITLE"));
	private Text txtModalLoginErrorDescription = new Text(AppiumBy.accessibilityId("ERROR_MODAL_DESCRIPTION"));
		
	
	/** The actions **/
	
	/**
	 * Log in into the application
	 * @param username
	 * @param password
	 */
	public void loginWith(String username, String password) {
		Log.step("Filling in the username: " + username);
		fldUsername.setText(username);
		Log.step("Filling in the password: " + password);
		fldPassword.setText(password);
		Log.step("Clicking on the login button");
		btnLogin.click();
	}
	
	/**
	 * Verifies if the "Error" modal is shown
	 * @return true if the modal is shown, false if not
	 */
	public Boolean isErrorModalShown() {
		Log.step("Checking if the error modal is shown");
		return txtModalLoginErrorTitle.isElementVisible();
	}
	
	/**
	 * Get the message of the "Error" modal
	 * @return The message displayed in the modal
	 */
	public String getErrorModalMessage() {
		Log.step("Getting the text of the modal");
		return txtModalLoginErrorDescription.getText();
	}
}
```

The LoginPage class is an extensions of the MobileBasePage. 

It could be that for some reason it is not possible to have the locator of an element as a constant in the page objects. 
Often this has to do with text inside the locator. In those rare occasions you can manage the locators inside the action methods of the page object class.

It is advisable to have logging available within the methods of the page object classes to know which functionality is called and enhance the reporting.
Please look at the topic regarding logging.

By default, elements should be listed as private. For some projects it could be more useful to have the elements as public so that you can more dynamically 
use them inside your tests, without creating a lot of page object actions.


##### Element Locator and interactions

You can define elements:
* based on there type of element: ```private Field fldUsername_type = new Field(AppiumBy.accessibilityId("USERNAME"));```
* based on there locator: ```private By fldUsername_locator = AppiumBy.accessibilityId("USERNAME");``` 

The default element interactions are stored in the ```frmwrk.testbase.BaseElement.java class```
Each type of interaction should be available for both type of element definitions:
* ```fldUsername_type.setText("user1");```
* ```setText(fldUsername_locator, "user1");```

It is advisable to use the predefined element interactions from the BaseElement.java class and not directly use the Appium or Selenium interactions.
The element interactions found in the BaseElement.java class contain more advanced features like logging, waiting mechanisms and verifications.

If your application can be build for multiple platforms (IOS, Android), it could be that different locators are needed for each platform.
In that case, you should use the MobileLocator interface and its implemented classes found in ```frmwrk.locators```. 
These let you define a different locator for each platform.
The framework will know on which platform the test is running and select the correct locator during the test.

Example of a different locator for IOS and Android:
```private Locator fldUsername = new MobileLocator(new AndroidLocator(By.id("USERNAME_ANDROID"), new IOSLocator(By.id("USERNAME_IOS")));```

Other possibilities:
* Shorter: ```private Locator fldUsername = new MobileLocator(By.id("USERNAME_ANDROID"), By.id("USERNAME_IOS"));```
* Element is only available on IOS: ```private Locator fldUsername = new MobileLocator(new IOSLocator(By.id("USERNAME_IOS")));```
* Element is the same on Android and IOS: ```private Locator fldUsername = new MobileLocator(By.id("USERNAME_IOS"));```

The element type classes can also make use of these MobileLocators: 
 ```private Field fldUsername_type = new Field(new MobileLocator(By.id("USERNAME_ANDROID"), By.id("USERNAME_IOS")));```
 

### Tests

Using the above page object class, we can create test cases around them:


```java
public class LoginTests extends MobileBaseTest {
	
	@Test(priority = 1, description = "Login with incorrect credentials")
	public void unsuccessfullLogin(Method method) {
		loginPage.loginWith("QVGPMNLQ", "123456");
		Assert.assertTrue(loginPage.isErrorModalShown(), "The modal is not shown");
		Assert.assertEquals(loginPage.getErrorModalMessage().equals("Please check your details and try again"), "The modal message is incorrect");	
	}
	
	@Test(priority = 1, description = "Login with a patient that is already in use")
	public void successfullLoginWithPatientDetailsInUse(Method method) {
		loginPage.loginWith("QVGPMNLQ", "112418");
		Assert.assertTrue(welcomePage.isShown(), "The welcome page is not shown");
	}
}
```

The page object classes need to be initialised to be able to use them. It is a more convenient to initialize them in a single method instead of initializing them in each test method. 
This is typically something that you can add to the Base of all test classes: 

```java
public class MobileBaseTest extends BaseTest {
	
	public LoginPage loginPage;
	public WelcomePage welcomePage;

	@BeforeMethod
	public void before() {
		loginPage = new LoginPage();
		welcomePage = new WelcomePage();
	}

	@AfterMethod
	public void teardown() {
		DriverManager.KillDriver();
	}
	
} 
```

## Settings

***

### Introduction

The framework knows what, how and where to test based on settings.
The settings are stored for each Java thread, meaning you can run tests in parallel with different settings. 
These settings can be set by:
* Within a TestNG .XML file
* In the Maven command line
* In the Maven POM.XML file
* An environment variable

If a setting is not set through one of the above methods, the framework will take default value from the  ```settings.properties``` file, located in ```src\main\resources\settings.properties```.

The settings are stored in the ```RunSettings``` class, located in ```src\main\java\frmwrk.settings\RunSettings.java```.
This is a static class, meaning you don't have to initiate it and you can directly call a getter or setter from anywhere within the framework or testproject. 
```
Platform theCurrentPlatform = RunSettings.getRunPlatform();
```

There is no need to give a value for all of the possible settings. You only need to set the settings that are needed for your tests.

Order of setting value:
1. The framework will search for the environment variable for a specific setting and update the setting value if the setting ```ta.run.overwrite.settings``` is ```TRUE``` . 
2. If the Maven POM.XML file contains a new value for this setting it will use the Maven POM.XML setting value if the setting ```ta.run.overwrite.settings``` is ```TRUE``` . 
3. If you run the tests through a Maven command line and this command line contains a new value for a setting AND the setting ```ta.run.overwrite.settings``` is ```TRUE``` it will use the command line setting value 
4. If the TestNG .XML file contains a new value for a setting AND the setting ```ta.run.overwrite.settings``` is ```TRUE``` 
5. If no value has been set for the setting in one of the above possibilities, it will take the default setting value from the  ```settings.properties``` file.


### Settings list

##### General Settings:

* ```ta.run.overwrite.settings```: If it is allowed to overwrite the default settings. Possible values: TRUE, FALSE
* ```ta.run.platform```: Which type of tests need to run. Possible values: MOBILE, WEB, API
* ```ta.run.location```: Where do you want to run your test. Possible values: LOCAL, REMOTE, BROWSERSTACK
* ```ta.run.timeout```: The default timeout in milliseconds
* ```ta.run.timeout.long```: The default "long" timout in milliseconds
* ```ta.run.timeout.short```: The default "short" timeout in milliseconds
* ```ta.run.step.takescreenshot```: If each steps needs to create a screenshot. Possible values: TRUE, FALSE

##### Web Automation Settings:

These settings are used when executing tests on a web browser:
* ```ta.web.browser```: On which browser does your test need to run. Possible values: CHROME, FIREFOX

##### Mobile Automation Settings:

These settings are used when executing tests on a mobile device:

* ```ta.mobile.appium.server.url```: The url to the Appium server you want to connect. Example: http://0.0.0.0:4723/wd/hub
* ```ta.mobile.use.capabilitiesfile```: If you want to use a capabilitiesfile, which contains the desired capabilities for a device
* ```ta.mobile.capabilitiesfile```: If you want to use a capabilitiesfile, you need to specify the location within the project. Example: src/test/resources/mobile/capabilities.json
* ```ta.mobile.devicename```: The name of the device where your tests need to be executed on. Example: emulator-5554
* ```ta.mobile.os```: The operating system on the mobile device. Possible values: ANDROID, IOS
* ```ta.mobile.os.version```: The version of the operating system on the mobile device. Example: 11
* ```ta.mobile.automationname```: The automation technology Appium needs to use. Example: UiAutomator2
* ```ta.mobile.apptype```: The type of application that needs to be tested on the mobile device: Possible values: APP, BROWSER
* ```ta.mobile.browser```: Which browser needs to be used to run the automated tests on the mobile device. Possible values: CHROME
* ```ta.mobile.newcommandtimeout```: How long (in seconds) Appium will wait for a new command from the client before assuming the client quit and ending the session. Example: 4500
* ```ta.mobile.app.package```: The application package name of the application that needs to be tested. Example: com.biogen.konectom.staging
* ```ta.mobile.app.activitiy```: The activity with which the the application needs to start. Example: com.biogen.konectom.staging
* ```ta.mobile.app.autograntpermissions```: Have Appium automatically determine which permissions your app requires and grant them to the app on install. Defaults to false. If noReset is true, this capability doesn't work.  Possible values: TRUE, FALSE
* ```ta.mobile.app.noreset```: If don't want to reset the app state before this session. Possible values: TRUE, FALSE

##### Browserstack Settings:

These settings are used when executing tests on Browserstack:

* ```ta.browserstack.username```: The username for BrowserStack. Example: davidhenderickx_ABCD
* ```ta.browserstack.accesskey```: The access key for BrowserStack. Example: dfsfsMuUdiySxwPM89sW
* ```ta.browserstack.url```: The browserstack URL.Example: https://username:accesskey@hub-cloud.browserstack.com/wd/hub.  When giving the username and accesskey settings, the framework will automatically generate the correct url
* ```ta.browserstack.projectname```: The name of the project you want to create in BrowserStack for this run. Example: My first project
* ```ta.browserstack.buildname```: The name of the build you want to create in BrowserStack for this run. Example: My first build
* ```ta.browserstack.runname```: The name of the run you want to create in BrowserStack for this run. Example: Sample run
* ```ta.browserstack.app.url```: The url of the application within BrowserStack. Example: bs://j3c874f21852ba57957a3fdc33f47514288c4ba4

(Do not forget to specify ```ta.run.location=BROWSERSTACK``` if you want to run your tests on BrowserStack)

#### Default Settings

These are the current default settings:

```
ta.run.overwrite.settings=TRUE
ta.run.platform=MOBILE 
ta.run.location=LOCAL
ta.run.timeout=20000
ta.run.timeout.long=40000
ta.run.timeout.short=10000
ta.run.step.takescreenshot=TRUE


ta.web.browser=CHROME

ta.mobile.appium.server.url=http://0.0.0.0:4723/wd/hub
ta.mobile.use.capabilitiesfile=FALSE
ta.mobile.capabilitiesfile=src/test/resources/mobile/capabilities.json
ta.mobile.devicename=emulator-5556
ta.mobile.os=ANDROID
ta.mobile.os.version=12
ta.mobile.automationname=UiAutomator2
ta.mobile.apptype=APP
ta.mobile.browser=CHROME
ta.mobile.newcommandtimeout=4500
ta.mobile.app.package=com.biogen.konectom.staging
ta.mobile.app.activity=com.konectom.MainActivity
ta.mobile.app.autograntpermissions=TRUE
ta.mobile.app.noreset=TRUE

ta.browserstack.username=xxx
ta.browserstack.accesskey=xxx
ta.browserstack.url=https://username:accesskey@hub-cloud.browserstack.com/wd/hub
ta.browserstack.projectname=My first project
ta.browserstack.buildname=My first build
ta.browserstack.runname=Sample run
ta.browserstack.app.url=bs://d7b1ef14bacb92e4b68e387049a6057636de937e
```

### Set settings through environment variables

You can specify the value of one or more settings through environment variables.
Creating these environment variables is different for each operating system:

* **Windows**: 
On the Windows taskbar, right-click the Windows icon and select System. In the Settings window, under Related Settings, click Advanced system settings. On the Advanced tab, click Environment Variables. Click New to create a new environment variable. The variable name is equal to the one of the above settings  (```ta.run.platform```), the variable value is the value you want the setting to have.

### Set settings in Maven POM.XML

You can specify the value of one or more settings in your Maven POM.XML.
This is done by adding a ```systemPropertyVariable``` in the configuration of the maven-surefire-plugin, which is responsible for running the tests.

```
...
<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>${maven-surefire-plugin-version}</version>
				<configuration>
					<systemPropertyVariables>
						<ta.run.overwrite.settings>TRUE</ta.run.overwrite.settings>
						<ta.run.platform>MOBILE</ta.run.platform>
						<ta.web.browser>CHROME</ta.web.browser>
					</systemPropertyVariables>
					<suiteXmlFiles>
						<suiteXmlFile>${test-suite}</suiteXmlFile>
					</suiteXmlFiles>
				</configuration>
			</plugin>
		...
...
```

### Set settings in Maven command line

You can specify the value of one or more settings in your Maven command line.
This is done by adding ```-D``` before the name and value of the setting:

```
$ mvn clean test -Dta.run.platform=MOBILE
```

### Set settings in TestNG .XML file

You can specify the value of one or more settings in your TestNG .XML files.
This is done by adding a ```parameter``` node where you specify the name and value of the setting:
```
<parameter name="ta.run.platform" value="MOBILE"/>
```

Example in TestNG .XML file:
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Mobile Test Suite Local">
    <listeners>
        <listener class-name="frmwrk.listeners.TestListener"/>
    </listeners>
    <parameter name="ta.run.platform" value="MOBILE"/>
    <parameter name="ta.mobile.use.capabilitiesfile" value="TRUE"/>
    <parameter name="ta.mobile.capabilitiesfile" value="src/test/resources/deviceCapabilities.json"/>
    <parameter name="ta.mobile.devicename" value="emulator-5554"/>
    
    <test name="InitialTests" >
        <classes>
            <class name="mobile.tests.InitialTests"/>
        </classes>
    </test>
</suite>
```

### Set settings in code

You can specify the value of one or more settings in code by calling the RunSettings class.

```
RunSettings.setMobileAppActivity("com.konectom.UserRegistration");
```

## Logging

In order to track the actions that are being executed, you should add some logging to your tests, actions, helper methods.
The framework is using Log4J to support logging. The implementation of this can be found in ```frmwrk.reports.Log.java```
```Log.java``` is a static class, meaning you don't need to initialize it and it can be used everywhere.



There are different levels of logging possible:
* ```Log.debug()```: Should be used for information that may be needed for diagnosing issues and troubleshooting or when running application in the test environment for the purpose of making sure everything is running correctly
* ```Log.info()```: The standard log level indicating that something happened
* ```Log.warn()```: Should be used when  something unexpected happened in the application, but the code can continue the work.
* ```Log.error()```: Should be used when the application hits an issue preventing one or more functionalities from properly functioning. 
* ```Log.fatal()```: Should be used when the application encountered an event or entered a state in which one of the crucial business functionality is no longer working. 
* ```Log.step()```: Should be used when executing a step or functionality on the test application
* ```Log.takeScreenshot()```: Needs to be used when you want to create a screenshot

The following logging will be added to the ExtentReport: 
* ```Log.step()``` 
* ```Log.takeScreenshot()```

## Reporting

***

When running the tests, there will be log4J logfile and a TestNG report creaeted.
If you want to have a nice HTML report, created by the extentreports framework, you need to add the listener ```frmwrk.listeners.TestListener``` as a listener to the TestNG .XML file:

```
<suite name="Mobile Test Suite BrowserStack">
    <listeners>
        <listener class-name="frmwrk.listeners.TestListener"/>
    </listeners>
    <test name="InitialTests" >
        <classes>
            <class name="mobile.tests.InitialTests"/>
        </classes>
    </test>
</suite>
```

The reports can be found in the ```\test-output``` folder of the project.

### Retry

***

It is possible to have an automatic retry functionality when the test fails. 
You need to add the ```RetryListener``` found in ```frmwrk.listeners.TestListener``` as a listener to the TestNG .XML file:

```
<suite name="Mobile Test Suite BrowserStack">
    <listeners>
        <listener class-name="frmwrk.listeners.RetryListener"/>
    </listeners>
    <test name="InitialTests" >
        <classes>
            <class name="mobile.tests.InitialTests"/>
        </classes>
    </test>
</suite>
```

The test will be retried 4 times until it passes. There is currently no setting that lets you change the amount of retries.
This could be added later through the RunSettings

## Running tests

***

### Maven

Maven-surefire-plugin is managing the execution of test cases.
You can define which test cases need to run by adding a ```suiteXmlFile``` node for each TestNG .XML file you want to call in your POM.XML:
```
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-surefire-plugin</artifactId>
	<version>${maven-surefire-plugin-version}</version>
	<configuration>
		<suiteXmlFiles>
			<suiteXmlFile>MobileTests.xml</suiteXmlFile>
		</suiteXmlFiles>
	</configuration>
</plugin>
```

If you use a Maven property variable instead of the hardcoded TestNG .XML file, you can update this Maven poperty through  command line:
```
<suiteXmlFiles>
	<suiteXmlFile>${test-suite}</suiteXmlFile>
</suiteXmlFiles>
```

For a valid POM.XML file, you also need to define the property variable in your POM.XML. This is done as a direct child of the root node.

```
<properties>
		<test-suite>>MobileTests.xml</test-suite>
</properties>
```

If you want to use the standard defined test-suite in your POM.XML, run the command: ```mvn clean test```

If you want to run tests in a specific TestNG .xml file, run the command: ```mvn clean test -Dtest-suite=WebTests.xml```
or updating the node value: ```mvn clean test -DsuiteXmlFile=WebTests.xml```

### TestNG

You should be able to run your TestNG xml files directly from your IDE. Example for Eclipe:
1. Right click on the TestNG xml file
2. Go To "Run As"
3. Select "TestNG Suite"

You can run the TestNG tests withouth Maven by command line. It requires the following steps:
1. Set the classpath for the testng.jar in the environment variables (either manually or via command line).
2. Create a new folder "MyTest"
3. Compile all your classes(testNG+java) and put them (.class files ) in a new folder "Mytest\bin".
4. Put your 'testng.xml' and '.properties' files to "Mytest\bin" again.
5. Put all the .jar files (testng,selenium... etc) in a new folder "Mytest\lib".
6. Browse to the folder "bin" via command line and run the command java -cp "..\lib\*;" org.testng.TestNG testng.xml


## Other framework features

***

### Drivermanager

When running test scripts on a browser through Selenium or on mobile devices through Appium, the test script needs to connect to a browser or mobile device.
The connection and translation of actions is done through a driver. Something that "drives" the run platform into doing actions. 
Each test environment has it's own drivers: ChromeDriver, IOSDriver, AndroidDriver, FirefoxDriver, ...

The correct driver is created and set by reading the RunSettings for that specific test run. 

The drivers are managed in the ```frmwrk.drivers``` package. 

The static method ```DriverManager.getDriver()``` will return you the current active driver and lets you use it wherever you need it. 


### JSON Desired Capabilities

When connecting to a mobile device/emulator, the test script needs to tell the Appium server which device is needed.  you need to te
This is done by setting desired capabilities values.
Most of the desired capabilities are available through the RunSettings:

```
ta.mobile.os=ANDROID
ta.mobile.os.version=12
ta.mobile.automationname=UiAutomator2 
...
```

It can be more convenient to have a list of devices with their capabilities and point to 1 of those devices instead of changing all the different values of the RunSettings 
for each device. Execute the following

1. A JSON file is needed which holds the deviceName and the capabilities in the following structure: 

	```json
	[
		{
			"deviceName": "pixel6",
			"caps": {
				"appium:platformName": "Android",
				"appium:app": "app-staging-release.apk",
				"appium:appPackage": "com.biogen.konectom.staging",
				"appium:appActivity": "com.mjd.viper.activity.SplashScreenActivity",
				"appium:automationName": "UiAutomator2",
				"appium:autoGrantPermissions": true,
				"appium:newCommandTimeout": 4500
			}
		},
		{
			"deviceName": "5203168047c72419",
			"caps": {
				"appium:deviceName": "5203168047c72419",
				"appium:platformName": "Android",
				"appium:platformVersion": "7.1.1",
				"appium:app": "app-staging-release.apk",
				"appium:appPackage": "com.biogen.konectom.staging",
				"appium:appActivity": "com.mjd.viper.activity.SplashScreenActivity",
				"appium:automationName": "UiAutomator2",
				"appium:autoGrantPermissions": true,
				"appium:noReset": true,
				"appium:newCommandTimeout": 4500
			}
		}
	]
	```

2. Tell the framework that the test needs to use the capabilities file: ```ta.mobile.use.capabilitiesfile=TRUE```
3. Tell the framework where the capabilities file is located: ```ta.mobile.capabilitiesfile=src/test/resources/mobile/capabilities.json```
4. Tell the framework which device to search in the capabilities file: ```ta.mobile.devicename=pixel6```




