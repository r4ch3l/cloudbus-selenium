package temp;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sele.pot.pages.Page;
import sele.pot.pages.dialogs.HP_LoginDialog;
import sele.pot.pages.dialogs.VideoXDialog;
import sele.pot.pages_guest.HomePage;
import sele.util.CfgLoader;
import sele.util.DriverUtil;
import sele.util.FileDownloader;
import sele.util.ReportUtil;
import sele.util.ScreenShotRule;
import sele.util.TestWatcheRule;


/**
 * 
 * 
 * @author XiaoXue_Chen
 * 
 */

public class tempTest {
	
	
	private final static Logger logger = LoggerFactory
			.getLogger(tempTest.class);
	private static WebDriver driver = DriverUtil
			.getDriver(CfgLoader.browserType);
	String name="";
	@Rule public ScreenShotRule screenshotTestRule = new ScreenShotRule(driver);
	@Rule public TestWatcheRule watcher =new TestWatcheRule(logger,driver);
	

	@Test
	public void child(){
		
	}
	@Test
	public void child2(){
		fail();
	}
	//@Test
	public void checkHomeVideo3() {
		driver.get("http://cloudbus.tibco.com");
		logger.info("HomeTest6-5: start");
		boolean actual = false;

		try {
			HomePage homePage = Page.getPage(HomePage.class);
			VideoXDialog videox = homePage.OpenVideo3();
			actual = videox.isPresent();
			assertTrue(actual);
			Thread.sleep(25000); // wait for the video loaded
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
	

	
	
	
	@AfterClass
	public static void afterAllIsSaidAndDone() {
		// driver.quit();
	}

	@After
	public void after() {
		// driver.manage().deleteAllCookies();
	}

	@Before
	public void before() {

	}

//	 @Test
	public void downloadAFile() throws Exception {
		driver.get("https://cloudbus.tibco.com");
		HP_LoginDialog loginDialog = Page.getPage(HP_LoginDialog.class);
		loginDialog.OpenSignInDiv();
		loginDialog.failLoginAs("chen_xiaoxue-bj@pactera.com", "19870625");
		Thread.sleep(5000);
		driver.get("https://cloudbus.tibco.com/index.php/integrate/10-integrations/38-sap-application-22");
		
		FileDownloader downloadTestFile = new FileDownloader(driver);

		WebElement downloadLink = driver.findElement(By.cssSelector(".download-button"));
		logger.info(downloadLink.getAttribute("href"));
		
		
		// href="/Installers/tap/CLOUDBUS/tcbip/tibbr/1.0.1/windows/TIB_tcbip-tibbr_1.0.1_win_x86_64.zip">TIBCO® Integration Pack for tibbr®</a>
//		String downloadedFileAbsoluteLocation = downloadTestFile.downloadFile(downloadLink);
//		assertTrue(new File(downloadedFileAbsoluteLocation).exists());
//		assertEquals(downloadTestFile.getHTTPStatusOfLastDownloadAttempt(), 200);

	}

	//@Test
	public void downloadAFileB() throws Exception {
		HP_LoginDialog loginDialog = Page.getPage(HP_LoginDialog.class);
		loginDialog.OpenSignInDiv();
		loginDialog.failLoginAs("chen_xiaoxue-bj@pactera.com", "19870625");
		FileDownloader downloadTestFile = new FileDownloader(driver);
		driver.get("http://www.localhost.com/downloadTest.html");
		WebElement downloadLink = driver.findElement(By.id("fileToDownload"));
		String downloadedFileAbsoluteLocation = downloadTestFile
				.downloadFile(downloadLink);
		assertTrue(new File(downloadedFileAbsoluteLocation).exists());
		assertEquals(downloadTestFile.getHTTPStatusOfLastDownloadAttempt(), 200);
		// assertThat(new File(downloadedFileAbsoluteLocation).exists(),
		// is(equals(true)));
		// assertThat(downloadTestFile.getHTTPStatusOfLastDownloadAttempt(),
		// is(equals(200)));
	}

	// @Test
	public void testdownload() {
		boolean result = false;
		ReportUtil
				.insertReportLine(
						"CB-26_4", // caseID
						"fake", // CaseName
						"login with a pair of wrong username and password", // CaseDescription
						"there will be a notification like'The email or password you entered is invalid!' ", // expected
						result == true ? "pass" : "fail", // test result
								"test",
						"none"); // comment
	}

	// @Test
	public void testtemp2() {
		boolean result = true;
		ReportUtil
				.insertReportLine(
						"CB-26_4", // caseID
						"fake", // CaseName
						"login with a pair of wrong username and password", // CaseDescription
						"there will be a notification like'The email or password you entered is invalid!' ", // expected
						result == true ? "pass" : "fail", // test result
								"test",
						"none"); // comment
	}

//	 @Test
	public void testtemp3() {
		boolean result = true;
		try {
			throw new Exception("test");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
