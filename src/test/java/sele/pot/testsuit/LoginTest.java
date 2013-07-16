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
import sele.pot.pages.homePageForGuest.HP_LoginDialog;
import sele.pot.pages.homePageForGuest.HomePage;
import sele.pot.pages.homeRelated.ResetPwdPage;
import sele.util.CfgLoader;
import sele.util.DriverUtil;
import sele.util.ReportUtil;
import sele.util.ScreenShotRule;
/**
 * This Test constitutes by the all test cases happens with elements on Login dialogue of home page.
 * @author XiaoXue_Chen
 *
 */
public class LoginTest {
	private final static Logger logger = LoggerFactory
			.getLogger(LoginTest.class);
	private static WebDriver driver = DriverUtil.getDriver(CfgLoader.browserType);
	private static String hostAndPortAndContext = "http://cloudbus.tibco.com";
	private static String logOutURI = "";
	private static String logInURI = "";
	private HP_LoginDialog loginDialog = Page.getPage(HP_LoginDialog.class);
	private HomePage homePage = Page.getPage(HomePage.class);

	@AfterClass
	public static void afterAllIsSaidAndDone() {
		driver.quit();
	}

	@After
	public void tearDown() {
		// driver.get(hostAndPortAndContext+logOutURI);
		driver.manage().deleteAllCookies();
	}

	@Before
	public void setUp() {
		driver.get(hostAndPortAndContext);
	}

	@Test
	public void shouldLoginThenCheckUserName() throws Exception {
		String actual = "";
		try {
			HP_LoginDialog loginDialog = Page.getPage(HP_LoginDialog.class);
			loginDialog.OpenSignInDiv();
			HomePage homePage = loginDialog.loginAs("zxiong@tibco-support.com",
					"findbear");
			assertTrue(homePage.isPresent());
			actual = homePage.getUserWelcomeMsg();
			assertEquals("Tom Zhao", actual);
			DriverUtil.savePassScreenshot("shouldLoginThenCheckUserName");
			boolean result = "Tom Zhao".equals(actual);
			ReportUtil
					.insertReportLine(
							"CB-26_1 CB-26_2",// caseID_step
							"login",// CaseName
							"login with a pair of right username and password",// CaseDescription
							" after user logined in, homepage reloaded and there is a welcome msg on the right-upper of the page ",// expected
							result == true ? "pass" : "fail",// test result
							"none");// comment
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil
					.insertReportLine(
							"CB-26_1 CB-26_2",// caseID_step
							"login",// CaseName
							"login with a pair of right username and password",// CaseDescription
							" after user logined in, homepage reloaded and there is a welcome msg on the right-upper of the page ",// expected
							"error",// test result
							"none");// comment assertTrue(result);

		}
		
	}

	@Test
	public void shouldFailLoginThenCheckErrorMsg() throws Exception {
		String actual = "";
		try {
			HP_LoginDialog loginDialog = Page.getPage(HP_LoginDialog.class);
			loginDialog.OpenSignInDiv();
			loginDialog.failLoginAs("tapuser", "tibco123");
			actual = loginDialog.getErrorMessage();
			assertEquals("The email or password you entered is invalid!",
					actual);
			DriverUtil.savePassScreenshot("shouldFailLoginThenCheckErrorMsg");
			boolean result = "The email or password you entered is invalid!"
					.equals(actual);
			ReportUtil
					.insertReportLine(
							"CB-26_4",	// caseID
							"login",	// CaseName
							"login with a pair of wrong username and password",	// CaseDescription
							"there will be a notification like'The email or password you entered is invalid!' ",	// expected
							result == true ? "pass" : "fail",	// test result
							"none");	// comment
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil
					.insertReportLine(
							"CB-26_4",// caseID
							"login",// CaseName
							"login with a pair of wrong username and password",	// CaseDescription
							"there will be a notification like'The email or password you entered is invalid!' ",// expected
							"error",// test result
							"none");// comment

		}
	

	}

	@Test
	public void shouldOpenResetPWDPage() throws Exception {
		boolean actual = false;
		try {
			HP_LoginDialog loginDialog = Page.getPage(HP_LoginDialog.class);
			loginDialog.OpenSignInDiv();
			driver.get(loginDialog.GetUrlOfResetPwdPage());
			ResetPwdPage resetPwdPage = Page.getPage(ResetPwdPage.class);
			actual = resetPwdPage.isPresent();
			assertTrue(actual);
			DriverUtil.savePassScreenshot("shouldOpenResetPWDPage");
			boolean result = actual;
			ReportUtil.insertReportLine("CB-26_3",// caseID
					"login",// CaseName
					"Click the link for reset password on the login dialoug",// CaseDescription
					"The page for users to reset password should be open ",// expected
					result == true ? "pass" : "fail",// test result
					"none");// comment
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil.insertReportLine("CB-26_3",	// caseID
					"login",	// CaseName
					"Click the link for reset password on the login dialoug",	// CaseDescription
					"The page for users to reset password should be open ",	// expected
					"error",	// test result
					"none");	// comment

		}
		
	}

	@Rule
	public ScreenShotRule screenshotTestRule = new ScreenShotRule(driver);

}