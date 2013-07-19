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
import sele.pot.pages.dialogs.HP_RegisterDialog;
import sele.pot.pages.dialogs.VideoXDialog;
import sele.pot.pages_guest.CreateAccountPage;
import sele.pot.pages_guest.HomePage;
import sele.pot.pages_guest.WhyRegisterPage;
import sele.util.CfgLoader;
import sele.util.DriverUtil;
import sele.util.ReportUtil;
import sele.util.ScreenShotRule;
import sele.util.TestWatcheRule;

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
	
	HomePage homePage = Page.getPage(HomePage.class);
	@Rule public ScreenShotRule screenshotTestRule = new ScreenShotRule(driver);
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
	public void CheckTxtOnRegDialog__3_1() throws InterruptedException {
		String actual = "";
		String expected = "To register for a Cloud Bus account, click Continue to go to the TIBCO Access Point (TAP) page. Your 30-day evaluation starts immediately after the registration.";
		boolean result=false;
		try {
			actual = homePage.OpenRegisterDiv().getDescription();
			result = actual.equals(expected);
			assertTrue(result);
			DriverUtil.savePassScreenshot("CB-27_1");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ReportUtil.insertReportLine("CB-27",// caseID
					"Validate HomePage_Guest-RegisterDialog(step1)",// CaseName
					"Click the register button on the home page. ",// CaseDescription
					"There will be a register dialog pop-up",// expected
					result == true ? "pass" : "fail",// test result
					"RegisterTest3-1", "none");// comment
		}
	}

	@Test
	public void clickWhyRegLink__3_2() throws Exception {
		boolean actual = false;
		try {			
			WhyRegisterPage whypage = homePage.OpenRegisterDiv().OpenWhyRegLink(); // open why register	page from home
			assertTrue(whypage.isPresent());
			assertEquals("Why register?", driver.getTitle()); // check pagetitle
			VideoXDialog videoDialog = whypage.OpenWhyRegVideo();// open video
			actual = videoDialog.isPresent();
			assertTrue(actual);// check video player
			Thread.sleep(25000); // wait for the video loaded
			DriverUtil.saveCheckScreenshot("CB-39");
			videoDialog.closeVideo();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ReportUtil
			.insertReportLine(
					"CB-39",// caseID
					"Validate WhyRegisterPage",// CaseName
					"Click the register button on the home page, then click 'Why register' link",// CaseDescription
					"A page called 'Why Register' will be open",// expected
					actual == true ? "pass" : "fail",// test result
					"RegisterTest3-2", "none");// comment
		}

	}

	@Test
	public void ClickContinue__3_3() throws Exception {
		String actual = "";
		boolean result=false;
		try {
			CreateAccountPage regPage = homePage.OpenRegisterDiv().OpenRegisterPage();
			assertTrue(regPage.isPresent());
			regPage.GetWelcomeUserMsg();
			actual = regPage.GetWelcomeUserMsg();
			result = "Welcome Guest".equals(actual);
			assertTrue(result);
			DriverUtil.savePassScreenshot("CB-27_3");

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ReportUtil.insertReportLine(
					"CB-27",// caseID
					"Validate HomePage_Guest-RegisterDialog(step3)",// CaseName
					"Click the register button on the home page, then click 'continue",// CaseDescription
					"The registeration page should be open up",// expected
					result == true ? "pass" : "fail",// test result
					"RegisterTest3-3", "none");// comment
		}
	}


}