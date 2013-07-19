package sele.pot.testsuit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

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
import sele.pot.pages_guest.ContactPage;
import sele.util.CfgLoader;
import sele.util.DriverUtil;
import sele.util.ReportUtil;
import sele.util.ScreenShotRule;
import sele.util.TestWatcheRule;




public class ContactTest {
	private final static Logger logger = LoggerFactory
			.getLogger(HomeTest.class);
	private static WebDriver driver = DriverUtil
			.getDriver(CfgLoader.browserType);
	ContactPage contactPage = Page.getPage(ContactPage.class);

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
		driver.get(contactPage.getPageUrl());
	}

	@BeforeClass
	public static void beforeAllIsSaidAndDone() {
		driver.manage().deleteAllCookies();
	}

	@Test
	public void SubmitWithInvalidEmail__3_1() throws IOException {
		String actual = "";
		boolean result=false;
		try {
			assertTrue(contactPage.isPresent());// Make sure PageIsOpen
			contactPage.inputEmailAndMsg("test.com", "test test test test");// InvalidEmailAndValidMSG
			contactPage.Submit();
			actual = contactPage.getEmailErrorNotic();// getErrorNotice
			result = "What is your email address?".equals(actual);//check error
			assertTrue(result);//assert result
			DriverUtil.savePassScreenshot("CB-28_2");//screen shot
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ReportUtil
			.insertReportLine(
					"CB-28", // caseID
					"Validate ContactPage_Guest(step2)", // CaseName
					"open Contact link on home page, contact page should be opened", // CaseDescription
					"There is a red message showing up", // expected
					result == true ? "pass" : "fail", // test result
					"ContactTest3-1", "none"); // comment									
		}
	}

	@Test
	public void SubmitThenCheckTxt__3_2() throws IOException {
		String actual = "";
		boolean result=false;
		try {
			assertTrue(contactPage.isPresent());
			contactPage
					.inputEmailAndMsg("test@test.com", "test test test test");
			contactPage.Submit();
			actual = contactPage.getThankUAftersubmit();
			result = "Your form has been submitted.".equals(actual);
			assertEquals("Your form has been submitted.", actual);
			DriverUtil.savePassScreenshot("CB-28_3");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ReportUtil
			.insertReportLine(
					"CB-28", // caseID_step
					"Validate ContactPage_Guest(step3)", // CaseName
					"Open Contact pape, input valid msg and email ,then submit.", // CaseDescription
					"There will be a \"Thank you \" msg shows up after the message send out. ", // expected
					result == true ? "pass" : "fail", // test result
					"ContactTest3-2", "none"); // commentAssertTrue(result);
		}
	}

	@Test
	public void ResetThenCheck__3_3() throws IOException {

		boolean result = false;
		try {
			assertTrue(contactPage.isPresent());
			contactPage
					.inputEmailAndMsg("test@test.com", "Test reset whether reset button works.");
			contactPage.clickReset();
			result = contactPage.isInputEmpty();
			assertTrue(result);
			DriverUtil.savePassScreenshot("CB-28_4");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ReportUtil
			.insertReportLine(
					"CB-28", // caseID_step
					"Validate ContactPage_Guest(step4)", // CaseName
					" OpenContact page ,input valid msg and email adress,then click \"reset\".", // CaseDescription
					" Email address input and Message input should be empty now.", // expected
					result == true ? "pass" : "fail", // test result
					"ContactTest3-3", "none"); // commentAssertTrue(result);
		}
	}

}
