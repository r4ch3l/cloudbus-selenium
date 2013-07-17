package sele.pot.testsuit;

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

import sele.pot.pages.Page;
import sele.pot.pages.homePageForGuest.HP_RegisterDialog;
import sele.pot.pages.homePageForGuest.HomePage;
import sele.pot.pages.homePageForGuest.VideoXDialog;
import sele.pot.pages.homeRelated.WhyRegisterPage;
import sele.pot.pages.register.CreateAccountPage;
import sele.util.CfgLoader;
import sele.util.DriverUtil;
import sele.util.ReportUtil;
import sele.util.ScreenShotRule;

/**
 * This Test constitutes by the all test cases happens with elements on register
 * dialogue of home page.
 * 
 * @author XiaoXue_Chen
 * 
 */
public class RegisterTest {
	private final static Logger logger = LoggerFactory
			.getLogger(RegisterTest.class);
	private static WebDriver driver = DriverUtil
			.getDriver(CfgLoader.browserType);
	private static String hostAndPortAndContext = "http://cloudbus.tibco.com";

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
		driver.get(hostAndPortAndContext);
	}

	@Test
	public void shouldOpenWhyRegPageThenCheckText() throws InterruptedException {
		String actual = "";
		String expected = "To register for a Cloud Bus account, click Continue to go to the TIBCO Access Point (TAP) page. Your 30-day evaluation starts immediately after the registration.";
		try {
			HomePage home=Page.getPage(HomePage.class);
			HP_RegisterDialog regDialog=home.OpenRegisterDiv();
			
			actual = regDialog.getDescription();
			assertEquals(actual, expected);
			boolean result = actual.equals(expected);
			DriverUtil.savePassScreenshot("CB-27_2_3");
			ReportUtil.insertReportLine("CB-27_1",// caseID
					"register",// CaseName
					"Click the register button on the home page. ",// CaseDescription
					"There will be a register dialog pop-up",// expected
					result == true ? "pass" : "fail",// test result
					"none");// comment
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil.insertReportLine("CB-27_1",// caseID
					"register",// CaseName
					"Click the register button on the home page. ",// CaseDescription
					"There will be a register dialog pop-up",// expected
					"error",// test result
					"none");// comment

		}
		
	}

	@Test
	public void shouldOpenWhyRegPageThenCheckVideo() throws Exception {
		boolean actual = false;
		try {
			HomePage home=Page.getPage(HomePage.class);
			HP_RegisterDialog regDialog=home.OpenRegisterDiv();
			WhyRegisterPage whypage = regDialog.OpenWhyRegLink(); //open why register link
			assertTrue(whypage.isPresent());
			assertEquals("Why register?", driver.getTitle()); //check page title
			VideoXDialog videoDialog=whypage.OpenWhyRegVideo();//open video
			actual = videoDialog.isPresent();
			assertTrue(actual);//check video player
			Thread.sleep(20000); // wait for the video loaded
			DriverUtil.savePassScreenshot("CB-27_2_3");
			videoDialog.closeVideo();
			actual = !videoDialog.isPresent();
			assertTrue(actual);// check player closed
			boolean result = "Why register?".equals(actual);
			ReportUtil
					.insertReportLine(
							"CB-27_2 CB-27_3",// caseID
							"register",// CaseName
							"Click the register button on the home page, then click 'Why register' link",// CaseDescription
							"A page called 'Why Register' will be open",// expected
							result == true ? "pass" : "fail",// test result
							"none");// comment
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil
					.insertReportLine(
							"CB-27_2 CB-27_3",// caseID
							"register",// CaseName
							"Click the register button on the home page, then click 'Why register' link",// CaseDescription
							"A page called 'Why Register' will be open",// expected
							"error",// test result
							"none");// comment

		}
	
	}
	
	
	
	@Test
	public void shouldOpenRegPageThenCheck() throws Exception {
		String actual = "";
		try {
			HomePage home=Page.getPage(HomePage.class);
			HP_RegisterDialog regDialog=home.OpenRegisterDiv();
			CreateAccountPage regPage = regDialog.OpenRegisterPage();
			assertTrue(regPage.isPresent());
			regPage.GetWelcomeUserMsg();
			actual = regPage.GetWelcomeUserMsg();
			assertEquals("Welcome Guest", actual);
			DriverUtil
					.savePassScreenshot("CB-27_4");
			boolean result = "Welcome Guest".equals(actual);
			ReportUtil
					.insertReportLine(
							"CB-27_4",// caseID
							"register",// CaseName
							"Click the register button on the home page, then click 'continue",// CaseDescription
							"The registeration page should be open up",// expected
							result == true ? "pass" : "fail",// test result
							"none");// comment
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil
					.insertReportLine(
							"CB-27_4",// caseID
							"register",// CaseName
							"Click the register button on the home page, then click 'continue",// CaseDescription
							"The registeration page should be open up",// expected
							"error",// test result
							"none");// comment
		}
		
	}
	
	@Rule
	public ScreenShotRule screenshotTestRule = new ScreenShotRule(driver);

}