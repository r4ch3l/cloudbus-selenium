package sele.pot.testsuit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sele.pot.pages.Page;
import sele.pot.pages.homePageForGuest.HP_LoginDialog;
import sele.pot.pages.homePageForGuest.HomePage;
import sele.util.CfgLoader;
import sele.util.DriverUtil;
import sele.util.ScreenShotRule;

/**
 * This class includes all test cases happens on home page after user login.
 * 
 * @author XiaoXue_Chen
 * 
 */
public class HomeAfterLoginTest {
	private final static Logger logger = LoggerFactory
			.getLogger(HomeTest.class);
	private static WebDriver driver = DriverUtil
			.getDriver(CfgLoader.browserType);
	private static String hostAndPortAndContext = "http://cloudbus.tibco.com";
	HomePage homePage;

	HomeAfterLoginTest() {
		homePage = getUserHomePage();
	}

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

	}

	private HomePage getUserHomePage() {
		try {
			HP_LoginDialog loginDialog = Page.getPage(HP_LoginDialog.class);
			HomePage homePage = loginDialog.loginAs("zxiong@tibco-support.com",
					"findbear");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return homePage;
	}

	@Rule
	public ScreenShotRule screenshotTestRule = new ScreenShotRule(driver);

}
