package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import dialogObject.LoginDialog;
import dialogObject.RegisterDialog;
import dialogObject.VideoXDialog;
import util.CfgLoader;
import util.DriverUtil;

/**
 * This is the home page object It contains the web elements need to be use in
 * the later tests and the methods based on those elements.
 * 
 * @author XiaoXue_Chen
 * 
 */

public class HomePage extends BasePage {

	private String pageUrl=CfgLoader.homePageUrl;
	@FindBy(css = ".site.com_content.view-article.no-layout.no-task.itemid-101.home-welcome")
	// Web element in the page
	@CacheLookup
	private WebElement PAGE_READY_ELEMENT;
	@FindBy(css = ".txt-bold.username")
	// The user welcome message shows after user logined.
	private WebElement USER_WELCOME_MSG;
	@FindBy(xpath = "/html/head/title")
	// page title
	private WebElement PAGE_TITLE;
	@FindBy(css = "#top .btn.login-btn")
	// Sign in link at the upper right of the page
	private WebElement SIGN_IN_LINK;
	@FindBy(css = ".btn.register-button") //btn register-button
	// register button in the senter of the page
	private WebElement REGISTER_BTN;
	@FindBy(css = ".btn.contact-link.txt-bold")
	// Contact link at the upper right of the page
	private WebElement CONTACT_BTN;
	@FindBy(css = "#footer .pull-left")
	// CopyRight information at the bottom of the page
	private WebElement COPYRIGHT_TXT;
	@FindBy(css = ".video1 .btn-play")
	// key feature video img at the left middle of the page
	private WebElement VIDEO1_BTN;
	@FindBy(css = ".video2 .btn-play")
	// end to end video img at the center of the page
	private WebElement VIDEO2_BTN;
	@FindBy(css = ".video3 .btn-play")
	// why cloud bus video img at the right middle of the page
	private WebElement VIDEO3_BTN;
	
	@FindBy(css =".slidesjs-pagination-item a[data-slidesjs-item=\"1\"]")// the point button under the banner image
	private WebElement POINT_BTN1;
	@FindBy(css=".slidesjs-slide img")// banner image 
	private WebElement BANNER_IMG2;	
	
	@FindBy(css=".item-128 a")// banner image 
	private WebElement LEARN_BTN;
	
	public HomePage() {
		super();

	}
	public String getPageUrl(){
		return pageUrl;
	}
	protected WebElement getPageReadyElement() {
		return this.PAGE_READY_ELEMENT;
	}
	/**
	 * get the user welcome message at the upper-right corner of home page.
	 * @return string msg
	 */
	public String getUserWelcomeMsg() {
		DriverUtil.wait(ExpectedConditions.visibilityOf(USER_WELCOME_MSG));
		return USER_WELCOME_MSG.getText();
	}
	
	/**
	 * click the sign in link ,open sign in dialog
	 */
	public LoginDialog OpenSignInDiv() {
		DriverUtil.wait(ExpectedConditions.visibilityOf(SIGN_IN_LINK));
		SIGN_IN_LINK.click();
		return BasePage.getPage(LoginDialog.class);

	}
	
	/**
	 * click the register button, open register dialog
	 */
	public RegisterDialog OpenRegisterDiv() {
		DriverUtil.wait(ExpectedConditions.visibilityOf(REGISTER_BTN));
		REGISTER_BTN.click();
		return BasePage.getPage(RegisterDialog.class);
	}
	
	/**
	 * get the copy right text at the bottom of home page.
	 * @return copyright string 
	 */
	public String getCopyRightTxt() {
		DriverUtil.wait(ExpectedConditions.visibilityOf(COPYRIGHT_TXT));
		return COPYRIGHT_TXT.getText();
	}
	
	
	/**
	 * click the watch video button of the first video, count from left
	 * @return HP_VideoXDialog
	 * @throws InterruptedException
	 */
	public VideoXDialog OpenVideo1() throws InterruptedException {
		DriverUtil.wait(ExpectedConditions.visibilityOf(VIDEO1_BTN));
		VIDEO1_BTN.click();
		return BasePage.getPage(VideoXDialog.class);
	}
	/**
	 * click the watch video button of the second video, count from left
	 * @return HP_VideoXDialog
	 * @throws InterruptedException
	 */
	public VideoXDialog OpenVideo2() throws InterruptedException {
		DriverUtil.wait(ExpectedConditions.visibilityOf(VIDEO2_BTN));
		VIDEO2_BTN.click();
		return BasePage.getPage(VideoXDialog.class);
	}
	/**
	 * click the watch video button of the third video, count from left
	 * @return HP_VideoXDialog
	 * @throws InterruptedException
	 */
	public VideoXDialog OpenVideo3() throws InterruptedException {
		DriverUtil.wait(ExpectedConditions.visibilityOf(VIDEO3_BTN));
		VIDEO3_BTN.click();
		return BasePage.getPage(VideoXDialog.class);
	}
	/**
	 * click the contact link ,open contact page.
	 * @return ContactPage
	 */
	public ContactPage OpenContactPage() {
		DriverUtil.wait(ExpectedConditions.visibilityOf(CONTACT_BTN));
		CONTACT_BTN.click();
		return BasePage.getPage(ContactPage.class);
	}
	/**
	 * click the banner image, open RigisterDialog
	 * @return HP_RegisterDialog
	 */
	public RegisterDialog clickBannerImg(){
		DriverUtil.wait(ExpectedConditions.visibilityOf(BANNER_IMG2));
		BANNER_IMG2.click();
		return BasePage.getPage(RegisterDialog.class);
		
	}
	
	public LearnPage clickLearnBtn(){
		DriverUtil.wait(ExpectedConditions.visibilityOf(LEARN_BTN));
		LEARN_BTN.click();
		return BasePage.getPage(LearnPage.class);
		
	}

}
