package sele.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * This is the utility for get a certain Web driver.
 * 
 * @author XiaoXue_Chen
 * 
 */
public class DriverUtil {
	/**
	 * The max waiting time for waiting the condition : seconds
	 */
	final static int MAX_WAIT = 10;

	/**
	 * Web driver
	 */
	static WebDriver driver;
	
	
	/**
	 * Get the driver for initializing the page object.
	 * 
	 * @return
	 */
	static public WebDriver getDriver() {
		return driver;
	}

	/**
	 * For getting a Web driver by described type.
	 * 
	 * @param driverType
	 *            : Firefox, Chrome, or IE
	 * @return the driver described by the driverType
	 */
	static public WebDriver getDriver(String driverType) {
		if ("Firefox".equals(driverType)) {
			// FirefoxProfile profile = new FirefoxProfile(new
			// File("data"+File.separatorChar+"7zoduvrc.Tester"));
			driver = new FirefoxDriver();

		} else if ("Chrome".equals(driverType)) {
			System.setProperty("webdriver.chrome.driver",CfgLoader.chromeDriverPath);
			driver = new ChromeDriver();
		} else if ("IE".equals(driverType)) {
			System.setProperty("webdriver.ie.driver",CfgLoader.ieDriverPath);

			WebDriver driver = new InternetExplorerDriver();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			return driver;
		} else {
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * Wait for the expected condition with the stated driver and stated max
	 * waiting time.
	 * 
	 * @param cond
	 *            the Expected condition
	 */
	@SuppressWarnings("unchecked")
	static public void wait(ExpectedCondition cond) {
		(new WebDriverWait(driver, MAX_WAIT)).until(cond);
	}

	/**
	 * Save a screenshot by current driver for manifesting the testing
	 * processes.
	 * 
	 * @param screenshotFileName
	 *            use testing method name as the file name here
	 * @throws IOException
	 */
	static public void savePassScreenshot(String screenshotFileName) {
		try {
			File screenshot = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("target/screenshot_"
					+ getDate() + File.separatorChar + "passed"
					+ File.separatorChar + screenshotFileName + "_" + getTime()
					+ ".png"));
		} catch (Exception e) {
			// No need to crash the tests if the screenshot fails
		}

	}

	static private String getDate() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(new Date(System.currentTimeMillis()));
	}

	static private String getTime() {
		SimpleDateFormat fmt = new SimpleDateFormat("HH-mm-ss");
		return fmt.format(new Date(System.currentTimeMillis()));
	}

}