package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pageObject.BasePage;
import pageObject.CreateAccountPage;
import pageObject.HomePage;
import pageObject.WhyRegisterPage;
import util.CfgLoader;
import util.DriverUtil;
import util.TestWatcheRule;

/**
 * This Test constitutes by the all test cases happens with elements on register
 * dialogue of home page.
 * 
 * @author XiaoXue_Chen
 * 
 */
public class ValidateRegisterDialog {
	private final static Logger logger = LoggerFactory.getLogger(ValidateRegisterDialog.class);
	private static WebDriver driver = DriverUtil
			.getDriver(CfgLoader.browserType);
	
	HomePage homePage = BasePage.getPage(HomePage.class);

	@Rule public TestWatcheRule watcher =new TestWatcheRule(logger,driver);

	@AfterClass
	public static void afterAllIsSaidAndDone() {
		driver.quit();
	}

	@After
	public void after() {
		// driver.manage().deleteAllCookies();
	}

	@Before
	public void before() {
		driver.get(homePage.getPageUrl());
	}

	@Test
	/**
	 * Check the txt on RegDialog states right.
	 * @throws InterruptedException
	 */
	public void $63_1$CheckTxtOnRegDialog() throws InterruptedException {
		String expected = "To register for a Cloud Bus account, click Continue to go to the TIBCO Access Point (TAP) page. Your 30-day evaluation starts immediately after the registration.";
			assertEquals(expected,homePage.OpenRegisterDiv().getDescription());
		
	}

	@Test
	/**
	 * Check Why Register link.
	 * @throws Exception
	 */
	public void $53_1$clickWhyRegLink() throws Exception {
			WhyRegisterPage whypage = homePage.OpenRegisterDiv().OpenWhyRegLink(); // open why register	page from home
			assertTrue(whypage.isPresent());
			assertEquals("Why register?", driver.getTitle()); // check pagetitle
			

	}

	@Test
	/**
	 * Click continue button.
	 * @throws Exception
	 */
	public void $64_1$ClickContinue__3_3() throws Exception {
			CreateAccountPage regPage = homePage.OpenRegisterDiv().OpenRegisterPage();
			assertTrue(regPage.isPresent());
			assertEquals("Welcome Guest",regPage.GetWelcomeUserMsg());
		

	}


}