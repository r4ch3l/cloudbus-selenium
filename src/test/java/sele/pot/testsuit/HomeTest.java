package sele.pot.testsuit;

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

import sele.pot.pages.Page;
import sele.pot.pages.homePageForGuest.HP_RegisterDialog;
import sele.pot.pages.homePageForGuest.HomePage;
import sele.pot.pages.homePageForGuest.VideoXDialog;
import sele.pot.pages.homeRelated.ContactPage;
import sele.util.CfgLoader;
import sele.util.DriverUtil;
import sele.util.ReportUtil;
import sele.util.ScreenShotRule;

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
	private static String hostAndPortAndContext = "http://cloudbus.tibco.com";

	@AfterClass
	public static void afterAllIsSaidAndDone() {
		driver.quit();

	}

	@After
	public void tearDown() {
	}

	@Before
	public void setUp() {
		driver.get(hostAndPortAndContext);
	}

	@BeforeClass
	public static void beforeAllIsSaidAndDone() {
		driver.manage().deleteAllCookies();
	}

	@Test
	public void shouldOpenContactPageThenCheck() {
		logger.info("HomeTest6-1: start");
		String actual = "";
		try {
			HomePage homePage = Page.getPage(HomePage.class);
			ContactPage contactPage = homePage.OpenContactPage();
			assertTrue(contactPage.isPresent());
			actual = driver.getTitle();

			assertEquals("Contact Cloud Bus", actual);
			DriverUtil.savePassScreenshot("CB-28_1");
			boolean result = "Contact Cloud Bus".equals(actual);
			ReportUtil
					.insertReportLine(
							"CB-28", // caseID
							"Validate ContactPage_Guest(step1)", // CaseName
							"open Contact link on home page, contact page should be opened", // Case
																								// description
							"the title of the current page should be \"Contact Cloud Bus\" ", // expected
							result == true ? "pass" : "fail", // test result
							"HomeTest6-1", "none"); // comment
													// assertTrue(result);
			logger.info("HomeTest6-1: done");
		} catch (Exception e) {
			ReportUtil
					.insertReportLine(
							"CB-28", // caseID
							"Validate ContactPage_Guest(step1)", // CaseName
							"open Contact link on home page, contact page should be opened", // Case
																								// description
							"the title of the current page should be \"Contact Cloud Bus\" ", // expected
							"error", // test result
							"HomeTest6-1", e.toString()); // comment

		}

	}

	@Test
	public void checkCopyRightOnHomePage() {
		logger.info("HomeTest6-2: start");
		String actual = "";
		String expected = "TIBCO Cloud Bus™ Copyright 2013 TIBCO Software Inc. All rights reserved. Proprietary and confidential | Privacy Policy | Terms of Use";

		try {
			HomePage homePage = Page.getPage(HomePage.class);
			homePage.isPresent();
			actual = homePage.getCopyRightTxt();
			assertTrue(actual.indexOf("Copyright 2013 TIBCO Software Inc. All rights reserved.")>0);
			DriverUtil.savePassScreenshot("CB-23_2");
			boolean result =actual.indexOf("Copyright 2013 TIBCO Software Inc. All rights reserved.")>0;
			ReportUtil.insertReportLine("CB-23", // caseID
					"Validate HomePage_Guest(step2)", // CaseName
					"Open home page, check the copy right information", // CaseDescription
					"The copy right imformation should be there and correct", // expected
					result == true ? "pass" : "fail", // test result
					"HomeTest6-2", "none"); // comment
			logger.info("HomeTest6-2: done");
		} catch (Exception e) {
			ReportUtil.insertReportLine("CB-23", // caseID
					"Validate HomePage_Guest(step2)", // CaseName
					"Open home page, check the copy right information", // CaseDescription
					"The copy right imformation should be there and correct", // expected
					"error", // test result
					"HomeTest6-2", e.toString()); // comment

		}

	}

	@Test
	public void checkHomeVideo1() {
		logger.info("HomeTest6-3: start");
		boolean actual = false;
		try {
			HomePage homePage = Page.getPage(HomePage.class);
			VideoXDialog videox = homePage.OpenVideo1();
			actual = videox.isPresent();
			assertTrue(actual);
			Thread.sleep(20000); // wait for the video loaded
			DriverUtil.savePassScreenshot("CB-23_4");
			videox.closeVideo();
			ReportUtil
					.insertReportLine(
							"CB-23", // caseID CB-23 in testlink
							"Validate HomePage_Guest(step4)", // CaseName
							"Open home page, click the first video -'key features' and then close it", // CaseDescription
							"the video player is showing up first and then closed", // expected
							actual == true ? "pass" : "fail", // test result
							"HomeTest6-3",
							"Contains 20s waiting for the video loaded"); // comment
			logger.info("HomeTest6-3: done");
		} catch (Exception e) {
			ReportUtil
					.insertReportLine(
							"CB-23", // caseID CB-23 in testlink
							"Validate HomePage_Guest(step4)", // CaseName
							"Open home page, click the first video -'key features' and then close it", // CaseDescription
							"the video player is showing up first and then closed", // expected
							"error", // test result
							"HomeTest6-3", e.toString()); // comment

		}

	}

	@Test
	public void checkHomeVideo2() {
		logger.info("HomeTest6-4: start");
		boolean actual = false;

		try {
			HomePage homePage = Page.getPage(HomePage.class);
			VideoXDialog videox = homePage.OpenVideo2();
			actual = videox.isPresent();
			assertTrue(actual);
			Thread.sleep(20000); // wait for the video loaded
			DriverUtil.savePassScreenshot("CB-23_7");
			videox.closeVideo();
			ReportUtil
					.insertReportLine(
							"CB-23", // caseID_step_part
							"Validate HomePage_Guest(step7)", // CaseName
							"Open home page, click the second video -'cloud bus end to end' ,and then close it", // CaseDescription
							"the video player is showing up first and then closed", // expected
							actual == true ? "pass" : "fail", // test result
							"HomeTest6-4",
							"Contains 20s waiting for the video loaded"); // comment
			logger.info("HomeTest6-4: done");
		} catch (Exception e) {
			ReportUtil
					.insertReportLine(
							"CB-23", // caseID_step_part
							"Validate HomePage_Guest(step7)", // CaseName
							"Open home page, click the second video -'cloud bus end to end' ,and then close it", // CaseDescription
							"the video player is showing up first and then closed", // expected
							"error", // test result
							"HomeTest6-4", e.toString()); // comment

		}

	}

	@Test
	public void checkHomeVideo3() {
		logger.info("HomeTest6-5: start");
		boolean actual = false;

		try {
			HomePage homePage = Page.getPage(HomePage.class);
			VideoXDialog videox = homePage.OpenVideo3();
			actual = videox.isPresent();
			assertTrue(actual);
			Thread.sleep(20000); // wait for the video loaded
			DriverUtil.savePassScreenshot("CB-23_6");
			videox.closeVideo();
			ReportUtil
					.insertReportLine(
							"CB-23", // caseID
							"Validate HomePage_Guest(step6)", // CaseName
							"Open home page, click the third video -'why cloud bus', and then close it", // CaseDescription
							"the video player is showing up first and then closed", // expected
							actual == true ? "pass" : "fail", // test result
							"HomeTest6-5",
							"Contains 20s waiting for the video loaded"); // comment
			logger.info("HomeTest6-5: done");
		} catch (Exception e) {
			ReportUtil
					.insertReportLine(
							"CB-23", // caseID_step_part
							"Validate HomePage_Guest(step6)", // CaseName
							"Open home page, click the third video -'why cloud bus', and then close it", // CaseDescription
							"the video player is showing up first and then closed", // expected
							"error", // test result
							"HomeTest6-5", e.toString()); // comment

		}

	}

	@Test
	public void clickBannerImgThenCheckRegisterDialog() {
		logger.info("HomeTest6-6: start");
		boolean actual = false;
		try {
			HomePage homePage = Page.getPage(HomePage.class);
			HP_RegisterDialog register = homePage.clickBannerImg();
			actual = register.isPresent();
			assertTrue(actual);
			DriverUtil.savePassScreenshot("CB-23_5");
			ReportUtil
					.insertReportLine(
							"CB-23", // caseID
							"Validate HomePage_Guest(step5)", // CaseName
							"Open home page, second point under the banner image, then click the banner image", // CaseDescription
							"The register dialog should pop-up", // expected
							actual == true ? "pass" : "fail", // test result
							"HomeTest6-6", "none"); // comment
			logger.info("HomeTest6-6: done");
		} catch (Exception e) {
			ReportUtil
					.insertReportLine(
							"CB-23", // caseID_step
							"Validate HomePage_Guest(step5)", // CaseName
							"Open home page, second point under the banner image, then click the banner image", // CaseDescription
							"The register dialog should pop-up", // expected
							"error", // test result
							"HomeTest6-6", e.toString()); // comment
		}

	}

	@Rule
	public ScreenShotRule screenshotTestRule = new ScreenShotRule(driver);

}
