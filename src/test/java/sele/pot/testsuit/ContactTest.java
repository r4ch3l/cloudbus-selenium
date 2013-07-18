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
import sele.pot.pages.homeRelated.ContactPage;
import sele.util.CfgLoader;
import sele.util.DriverUtil;
import sele.util.ReportUtil;
import sele.util.ScreenShotRule;

public class ContactTest {
	private final static Logger logger = LoggerFactory
			.getLogger(HomeTest.class);
	private static WebDriver driver = DriverUtil
			.getDriver(CfgLoader.browserType);
	private static String hostAndPortAndContext = "https://cloudbus.tibco.com/index.php/contact";

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
		// driver.manage().deleteAllCookies();
	}

	@Test
	public void SubmitContactMsgWithInvalidEmail() throws IOException {
		logger.info("ContactTest 3-1: start");
		String actual = "";
		try {

			ContactPage contactPage = Page.getPage(ContactPage.class);
			assertTrue(contactPage.isPresent());
			contactPage.inputEmailAndMsg("test.com", "test test test test");
			contactPage.Submit();
			actual = contactPage.getEmailErrorNotic();
			assertEquals("What is your email address?", actual);
			DriverUtil.savePassScreenshot("CB-28_2");
			boolean result = "What is your email address?".equals(actual);
			ReportUtil
					.insertReportLine(
							"CB-28", // caseID
							"Validate ContactPage_Guest(step2)", // CaseName
							"open Contact link on home page, contact page should be opened", // CaseDescription
							"There is a red message showing up", // expected
							result == true ? "pass" : "fail", // test result
							"ContactTest3-1", "none"); // comment
														// assertTrue(result);
			logger.info("ContactTest 3-1: done");

		} catch (Exception e) {
			ReportUtil.insertReportLine("CB-28", // caseID
					"Validate ContactPage_Guest(step2)", // CaseName
					"Input invalid email and message then click submit", // CaseDescription
					"There is a red message showing up ", // expected
					"error", // test result
					"ContactTest3-1", e.toString()); // comment

		}

	}

	@Test
	public void SubmitContactMsgThenCheck() throws IOException {
		logger.info("ContactTest 3-2: start");
		String actual = "";
		try {

			ContactPage contactPage = Page.getPage(ContactPage.class);
			assertTrue(contactPage.isPresent());
			contactPage
					.inputEmailAndMsg("test@test.com", "test test test test");
			contactPage.Submit();
			actual = contactPage.getThankUAftersubmit();
			assertEquals("Your form has been submitted.", actual);
			DriverUtil.savePassScreenshot("CB-28_3");
			boolean result = "Your form has been submitted.".equals(actual);

			ReportUtil
					.insertReportLine(
							"CB-28", // caseID_step
							"Validate ContactPage_Guest(step3)", // CaseName
							"Open Contact pape, input valid msg and email ,then submit.", // CaseDescription
							"There will be a \"Thank you \" msg shows up after the message send out. ", // expected
							result == true ? "pass" : "fail", // test result
							"ContactTest3-2", "none"); // comment
														// assertTrue(result);
			logger.info("ContactTest 3-2: done");
		} catch (Exception e) {
			ReportUtil
					.insertReportLine(
							"CB-28", // caseID_step
							"Validate ContactPage_Guest(step3)", // CaseName
							"Open Contact pape, input valid msg and email ,then submit.", // CaseDescription
							"There will be a \"Thank you \" msg shows up after the message send out. ", // expected
							"error", // test result
							"ContactTest3-2", e.toString()); // comment

		}

	}

	@Test
	public void ClickResetThenCheck() throws IOException {
		logger.info("ContactTest 3-3: start");
		boolean actual = false;
		try {

			ContactPage contactPage = Page.getPage(ContactPage.class);
			assertTrue(contactPage.isPresent());
			contactPage
					.inputEmailAndMsg("test@test.com", "test test test test");
			contactPage.clickReset();
			actual = contactPage.isInputEmpty();
			assertTrue(actual);
			DriverUtil.savePassScreenshot("CB-28_4");

			ReportUtil
					.insertReportLine(
							"CB-28", // caseID_step
							"Validate ContactPage_Guest(step4)", // CaseName
							" OpenContact page ,input valid msg and email adress,then click \"reset\".", // CaseDescription
							" Email address input and Message input should be empty now.", // expected
							actual == true ? "pass" : "fail", // test result
							"ContactTest3-3", "none"); // comment
														// assertTrue(result);
			logger.info("ContactTest 3-3: done");
		} catch (Exception e) {
			ReportUtil
					.insertReportLine(
							"CB-28", // caseID_step
							"Validate ContactPage_Guest(step4)", // CaseName
							" OpenContact page ,input valid msg and email adress,then click \"reset\".", // CaseDescription
							" Email address input and Message input should be empty now.", // expected
							"error", // test result
							"ContactTest3-3", e.toString()); // comment

		}

	}

	@Rule
	public ScreenShotRule screenshotTestRule = new ScreenShotRule(driver);
}
