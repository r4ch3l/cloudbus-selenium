package demo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import pageObject.BasePage;
import pageObject.ContactPage;
import pageObject.HomePage;
import pageObject.LearnPage;
import util.CfgLoader;
import util.DriverUtil;
import util.TestWatcheRule;
import dialogObject.LoginDialog;
import dialogObject.RegisterDialog;
import dialogObject.VideoXDialog;


/**
 * Data Driven Test
 * 
 * @author XiaoXue_Chen
 * 
 */
@RunWith(value = Parameterized.class)
public class DDT {
	private final static Logger logger = LoggerFactory.getLogger(DDT.class);
	private static WebDriver driver = DriverUtil.getDriver(CfgLoader.browserType);
	HomePage homePage = BasePage.getPage(HomePage.class);
	private String copyRightTxtData;
	private String contactUsPageTitleData;
	private String helpCenterPageTitleData;
	
	
	  public DDT(String copyRightTxtData, String contactUsPageTitleData, String helpCenterPageTitleData) {
		  super();
		  this.copyRightTxtData=copyRightTxtData;
		  this.contactUsPageTitleData=contactUsPageTitleData;
		  this.helpCenterPageTitleData=helpCenterPageTitleData;	
	  }
	
	
	@Test
	/**
	 * Open sign link
	 * @throws Exception
	 */
	public void $46_1$LoginThenCheck() throws Exception {
		LoginDialog loginDialog = homePage.OpenSignInDiv();
		assertTrue(loginDialog.isPresent());
	}

	@Test
	/**
	 * Check the copyRight on home page.
	 */
	public void $38_1$checkCopyRight() {
		homePage.isPresent();
		assertTrue(homePage.getCopyRightTxt().indexOf(copyRightTxtData) > 0);
	}

	@Test
	/**
	 * Check the "key feature"  video on Home page.
	 * @throws InterruptedException
	 */
	public void $25_1$checkVideo1() throws InterruptedException {
		VideoXDialog videox = homePage.OpenVideo1();
		assertTrue(videox.isPresent());
		Thread.sleep(25000); // wait for the video loaded
		DriverUtil.saveCheckScreenshot("CB-25");
		videox.closeVideo();
	}

	@Test
	/**
	 * Click banner img ,then check the register dialog.
	 */
	public void $43_1$clickBanner() {
		RegisterDialog register = homePage.clickBannerImg();
		assertTrue(register.isPresent());
	}

	@Test
	/**
	 * Check the video "why cloud bus" on Home Page.
	 * @throws InterruptedException
	 */
	public void $40_1$checkVideo2() throws InterruptedException {
		VideoXDialog videox = homePage.OpenVideo2();
		assertTrue(videox.isPresent());
		Thread.sleep(25000); // wait for the video loaded
		DriverUtil.saveCheckScreenshot("CB-40");
		videox.closeVideo();
	}

	@Test
	/**
	 * Check video "cloud bus end to end" on Home page.
	 * @throws InterruptedException
	 */
	public void $42_1$checkVideo3() throws InterruptedException {
		VideoXDialog videox = homePage.OpenVideo3();
		assertTrue(videox.isPresent());
		Thread.sleep(25000); // wait for the video loaded
		DriverUtil.saveCheckScreenshot("CB-42");
		videox.closeVideo();

	}

	@Test
	/**
	 * Open contact link on the homepage.
	 */
	public void $47_1$openContactLink() {

		ContactPage contactPage = homePage.OpenContactPage();
		assertTrue(contactPage.isPresent());// Make sure pageIsOpen
		assertEquals(contactUsPageTitleData, driver.getTitle()); // Make sure the
																// title
																// isRight.

	}

	@Test
	/**
	 * Open learn page from "leanrn" tab.
	 */
	public void $49_1$openLearnPage() {
		try {
			@SuppressWarnings("unchecked")
			List<String> handle = (List<String>) driver.getWindowHandles();
			LearnPage learnPage = homePage.clickLearnBtn();
			driver.switchTo().window(handle.get(handle.size() - 1));
			assertTrue(learnPage.isPresent());
			assertEquals(helpCenterPageTitleData, driver.getTitle());
		} catch (ClassCastException e) {
			
		}

	}
	
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


	  @Parameters
		public  static  Collection<String[]> getTestData() throws Exception{
		 List<String[]> records= new ArrayList<String[]>();
		 InputStream doc = new FileInputStream(new File("data/DDT.yaml"));
		    Yaml yaml = new Yaml();
		    for (Object data : yaml.loadAll(doc)) {
		    	@SuppressWarnings("unchecked")
				HashMap<String,String> line=(HashMap<String,String>) data;
		    	int index=0;
		    	String[] dat= new String[line.size()];
		        dat[index++]=line.get("copyRightTxtData");
		        dat[index++]=line.get("contactUsPageTitleData");
		        dat[index++]=line.get("helpCenterPageTitleData");
		       records.add(dat); 
		    }
		 return records;
		 
	 }
}