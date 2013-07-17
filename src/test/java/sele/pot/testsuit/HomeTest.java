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
		String actual = "";
		try {
			HomePage homePage = Page.getPage(HomePage.class);
			ContactPage contactPage = homePage.OpenContactPage();
			assertTrue(contactPage.isPresent());
			actual = driver.getTitle();
			logger.info("contact page :" + actual);
			assertEquals("Contact Cloud Bus", actual);
			DriverUtil.savePassScreenshot("CB-28_1");
			boolean result = "Contact Cloud Bus".equals(actual);
			ReportUtil
					.insertReportLine(
							"CB-28_1", // caseID
							"contact", // CaseName
							"open Contact link on home page, contact page should be opened", // Case
																								// description
							"the title of the current page should be \"Contact Cloud Bus\" ", // expected
							result == true ? "pass" : "fail", // test result
							"none"); // comment assertTrue(result);

		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil
					.insertReportLine(
							"CB-28_1", // caseID
							"contact", // CaseName
							"open Contact link on home page, contact page should be opened", // Case
																								// description
							"the title of the current page should be \"Contact Cloud Bus\" ", // expected
							"error", // test result
							"none"); // comment

		}

	}

	@Test
	public void checkCopyRightOnHomePage() {
		String actual = "";
		String expected = "TIBCO Cloud Bus™ Copyright 2013 TIBCO Software Inc. All rights reserved. Proprietary and confidential | Privacy Policy | Terms of Use";

		try {
			HomePage homePage = Page.getPage(HomePage.class);
			homePage.isPresent();
			actual = homePage.getCopyRightTxt();
			assertEquals(expected, actual);
			Thread.sleep(10000);
			DriverUtil.savePassScreenshot("CB-23_2");
			boolean result = expected.equals(actual);
			ReportUtil.insertReportLine("CB-23_2", // caseID
					"copy right", // CaseName
					"Open home page, check the copy right information", // CaseDescription
					"The copy right imformation should be there and correct", // expected
					result == true ? "pass" : "fail", // test result
					"none"); // comment
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil.insertReportLine("CB-23_2", // caseID
					"copy right", // CaseName
					"Open home page, check the copy right information", // CaseDescription
					"The copy right imformation should be there and correct", // expected
					"error", // test result
					"none"); // comment

		}

	}

	@Test
	public void checkHomeVideo1() {
		boolean actual = false;
		try {
			HomePage homePage = Page.getPage(HomePage.class);
			VideoXDialog videox = homePage.OpenVideo1();
			actual = videox.isPresent();
			assertTrue(actual);
			Thread.sleep(20000); // wait for the video loaded
			DriverUtil.savePassScreenshot("CB-23_4(video1)");
			videox.closeVideo();
			ReportUtil
					.insertReportLine(
							"CB-23_4(video1)", // caseID CB-23 in testlink ,step
												// 4, part1
							"video", // CaseName
							"Open home page, click the first video -'key features' and then close it", // CaseDescription
							"the video player is showing up first and then closed", // expected
							actual == true ? "pass" : "fail", // test result
							"Contains 20s waiting for the video loaded"); // comment
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil
					.insertReportLine(
							"CB-23_4(video1)", // caseID CB-23 in testlink ,step
												// 4,
												// part1
							"video", // CaseName
							"Open home page, click the first video -'key features' and then close it", // CaseDescription
							"the video player is showing up first and then closed", // expected
							"error", // test result
							"Contains 20s waiting for the video loaded"); // comment

		}

	}

	@Test
	public void checkHomeVideo2() {
		boolean actual = false;

		try {
			HomePage homePage = Page.getPage(HomePage.class);
			VideoXDialog videox = homePage.OpenVideo2();
			actual = videox.isPresent();
			assertTrue(actual);
			Thread.sleep(20000); // wait for the video loaded
			DriverUtil.savePassScreenshot("CB-23_4(video2)");
			videox.closeVideo();
			ReportUtil
					.insertReportLine(
							"CB-23_4(video2)", // caseID_step_part
							"video", // CaseName
							"Open home page, click the second video -'cloud bus end to end' ,and then close it", // CaseDescription
							"the video player is showing up first and then closed", // expected
							actual == true ? "pass" : "fail", // test result
							"Contains 20s waiting for the video loaded"); // comment
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil
					.insertReportLine(
							"CB-23_4(video2)", // caseID_step_part
							"video", // CaseName
							"Open home page, click the second video -'cloud bus end to end' ,and then close it", // CaseDescription
							"the video player is showing up first and then closed", // expected
							"error", // test result
							"Contains 20s waiting for the video loaded"); // comment

		}

	}

	@Test
	public void checkHomeVideo3() {
		boolean actual = false;

		try {
			HomePage homePage = Page.getPage(HomePage.class);
			VideoXDialog videox = homePage.OpenVideo3();
			actual = videox.isPresent();
			assertTrue(actual);
			Thread.sleep(20000); // wait for the video loaded
			DriverUtil.savePassScreenshot("CB-23_4(video3)");
			videox.closeVideo();
			ReportUtil
					.insertReportLine(
							"CB-23_4(video3)", // caseID
							"video", // CaseName
							"Open home page, click the third video -'why cloud bus', and then close it", // CaseDescription
							"the video player is showing up first and then closed", // expected
							actual == true ? "pass" : "fail", // test result
							"Contains 20s waiting for the video loaded"); // comment
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil
					.insertReportLine(
							"CB-23_4(video3)", // caseID_step_part
							"video", // CaseName
							"Open home page, click the third video -'why cloud bus', and then close it", // CaseDescription
							"the video player is showing up first and then closed", // expected
							"error", // test result
							"Contains 20s waiting for the video loaded"); // comment

		}

	}

	//@Test
	public void clickBannerImgThenCheckRegisterDialog() {
		boolean actual = false;
		try {
			HomePage homePage = Page.getPage(HomePage.class);
			HP_RegisterDialog register = homePage.clickBannerImg();
			actual = register.isPresent();
			assertTrue(actual);
			DriverUtil
					.savePassScreenshot("CB-23_5");
			ReportUtil
					.insertReportLine(
							"CB-23_5", // caseID
							"register", // CaseName
							"Open home page, second point under the banner image, then click the banner image", // CaseDescription
							"The register dialog should pop-up", // expected
							actual == true ? "pass" : "fail", // test result
							"none"); // comment
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil
					.insertReportLine(
							"CB-23_5", // caseID_step
							"register", // CaseName
							"Open home page, second point under the banner image, then click the banner image", // CaseDescription
							"The register dialog should pop-up", // expected
							"error", // test result
							"none"); // comment
		}

	}

	 @Rule
	 public ScreenShotRule screenshotTestRule = new ScreenShotRule(driver);

}
