package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dialogObject.VideoXDialog;
import pageObject.BasePage;
import pageObject.WhyRegisterPage;
import util.CfgLoader;
import util.DriverUtil;
import util.TestWatcheRule;

public class ValidateWhyRegisterPage {
	private final static Logger logger = LoggerFactory.getLogger(ValidateHomePageAsGuest.class);
	private static WebDriver driver = DriverUtil.getDriver(CfgLoader.browserType);
	WhyRegisterPage whyPage= BasePage.getPage(WhyRegisterPage.class);
	private String title;
	
	/**
	 * CB-126
	 * @throws IOException
	 */
	@Test public void $126_1$openPage() throws Exception {
		assertTrue(whyPage.isPresent());
		assertEquals("Why register?", driver.getTitle()); // check pagetitle
	}
	
	/**
	 * CB-127
	 * @throws IOException
	 */
	@Test public void $127_1$checkVideo() throws Exception {	
		assertTrue(whyPage.isPresent());
		VideoXDialog videoDialog = whyPage.OpenWhyRegVideo();// open video
		assertTrue(videoDialog.isPresent());// check video player
		Thread.sleep(25000); // wait for the video loaded
		DriverUtil.saveCheckScreenshot("CB-127");
		videoDialog.closeVideo();
	}
	
	@Rule
	public TestWatcheRule watcher = new TestWatcheRule(logger, driver);

	@AfterClass
	public static void afterAllIsSaidAndDone() {
		driver.quit();
	}

	@After
	public void tearDown() {
	}

	@Before
	public void setUp() {
		driver.get(WhyRegisterPage.getPageUrl());
	}

	@BeforeClass
	public static void beforeAllIsSaidAndDone() {
		driver.manage().deleteAllCookies();
	}
	

	


}
