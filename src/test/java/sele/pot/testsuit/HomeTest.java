package sele.pot.testsuit;

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

import sele.pot.pages.Page;
import sele.pot.pages.dialogs.HP_RegisterDialog;
import sele.pot.pages.dialogs.VideoXDialog;
import sele.pot.pages_guest.ContactPage;
import sele.pot.pages_guest.HomePage;
import sele.util.CfgLoader;
import sele.util.DriverUtil;
import sele.util.ReportUtil;
import sele.util.ScreenShotRule;
import sele.util.TestWatcheRule;

/**
 * This Test constitutes by the all test cases happens with elements on home
 * page.
 * 
 * @author XiaoXue_Chen
 * 
 */
public class HomeTest {

	private final static Logger logger = LoggerFactory
			.getLogger(HomeTest.class);
	private static WebDriver driver = DriverUtil
			.getDriver(CfgLoader.browserType);
	HomePage homePage = Page.getPage(HomePage.class);
	@Rule
	public ScreenShotRule screenshotTestRule = new ScreenShotRule(driver);
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
		driver.get(homePage.getPageUrl());
	}

	@BeforeClass
	public static void beforeAllIsSaidAndDone() {
		driver.manage().deleteAllCookies();
	}

	@Test
	public void openContactLink__6_1() {
		String actual = "none";
		boolean result = false;
		try {
			ContactPage contactPage = homePage.OpenContactPage();
			assertTrue(contactPage.isPresent());// Make sure pageIsOpen
			actual = driver.getTitle();// Get page title.
			result = "Contact Cloud Bus".equals(actual);
			assertTrue(result); // Make sure the title isRight.
			DriverUtil.savePassScreenshot("CB-28_1");// Save a screen shot
		} catch (Exception e) {
			logger.error(e.toString());
		}finally{
			ReportUtil
			.insertReportLine(
					// Insert an report line.
					"CB-28", // caseID
					"Validate ContactPage_Guest(step1)", // CaseName
					"open Contact link on home page, contact page should be opened", // CaseDescription
					"the title of the current page should be \"Contact Cloud Bus\" ", // expected
					result == true ? "pass" : "fail", // test result
					"HomeTest6-1", "none"); // comment
		}

	}

	@Test
	public void checkCopyRight__6_2() {
		String actual = "";
		boolean result = false;
		try {
			homePage.isPresent();
			actual = homePage.getCopyRightTxt();
			result = actual
					.indexOf("Copyright 2013 TIBCO Software Inc. All rights reserved.") > 0;
			assertTrue(result);
			DriverUtil.savePassScreenshot("CB-23_2");	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ReportUtil.insertReportLine("CB-23", // caseID
					"Validate HomePage_Guest(step2)", // CaseName
					"Open home page, check the copy right information", // CaseDescription
					"The copy right imformation should be there and correct", // expected
					result == true ? "pass" : "fail", // test result
					"HomeTest6-2", "none"); // comment
		}

	}

	@Test
	public void checkVideo1__6_3() {
		boolean result = false;
		try {
			VideoXDialog videox = homePage.OpenVideo1();
			result = videox.isPresent();
			assertTrue(result);
			Thread.sleep(25000); // wait for the video loaded
			DriverUtil.saveCheckScreenshot("CB-23_4");
			videox.closeVideo();		
		} catch (Exception e) {
			logger.error(e.toString());
		}finally{
			ReportUtil
			.insertReportLine(
					"CB-23", // caseID CB-23 in testlink
					"Validate HomePage_Guest(step4)", // CaseName
					"Open home page, click the first video -'key features' and then close it", // CaseDescription
					"the video player is showing up first and then closed", // expected
					result == true ? "pass" : "fail", // test result
					"HomeTest6-3",
					"Contains 20s waiting for the video loaded"); // comment
		}

	}

	@Test
	public void checkVideo2__6_4() {
		boolean result = false;
		try {
			VideoXDialog videox = homePage.OpenVideo2();
			result = videox.isPresent();
			assertTrue(result);
			Thread.sleep(25000); // wait for the video loaded
			DriverUtil.saveCheckScreenshot("CB-23_7");
			videox.closeVideo();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ReportUtil
			.insertReportLine(
					"CB-23", // caseID_step_part
					"Validate HomePage_Guest(step7)", // CaseName
					"Open home page, click the second video -'cloud bus end to end' ,and then close it", // CaseDescription
					"the video player is showing up first and then closed", // expected
					result == true ? "pass" : "fail", // test result
					"HomeTest6-4",
					"Contains 20s waiting for the video loaded"); // comment
		}

	}

	@Test
	public void checkVideo3__6_5() {
		boolean result = false;
		try {
			VideoXDialog videox = homePage.OpenVideo3();
			result = videox.isPresent();
			assertTrue(result);
			Thread.sleep(25000); // wait for the video loaded
			DriverUtil.saveCheckScreenshot("CB-23_6");
			videox.closeVideo();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ReportUtil
			.insertReportLine(
					"CB-23", // caseID
					"Validate HomePage_Guest(step6)", // CaseName
					"Open home page, click the third video -'why cloud bus', and then close it", // CaseDescription
					"the video player is showing up first and then closed", // expected
					result == true ? "pass" : "fail", // test result
					"HomeTest6-5",
					"Contains 20s waiting for the video loaded"); // comment
		}

	}

	@Test
	public void clickBanner__6_6() {
		boolean result = false;
		try {
			HP_RegisterDialog register = homePage.clickBannerImg();
			result = register.isPresent();
			assertTrue(result);
			DriverUtil.savePassScreenshot("CB-23_5");			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ReportUtil
			.insertReportLine(
					"CB-23", // caseID
					"Validate HomePage_Guest(step5)", // CaseName
					"Open home page, second point under the banner image, then click the banner image", // CaseDescription
					"The register dialog should pop-up", // expected
					result == true ? "pass" : "fail", // test result
					"HomeTest6-6", "none"); // comment
		}

	}

}