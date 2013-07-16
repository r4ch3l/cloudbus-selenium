package sele.pot.pages.homePageForSignedInUser;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sele.pot.pages.homePageForGuest.HP_LoginDialog;
import sele.pot.pages.homePageForGuest.HomePage;

/**
 * This is the home page object It contains the web elements need to be use in
 * the later tests and the methods based on those elements.
 * 
 * @author XiaoXue_Chen
 * 
 */

public class HomePage_SignedIn extends HomePage {
	public static String url = "https://cloudbus.tibco.com/";
	 private final static Logger logger =
	 LoggerFactory.getLogger(HomePage.class); //logger

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
	@FindBy(css = ".btn.register-button")
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
	@FindBy(css=".slidesjs-slide img[src=\"https://cloudbus.tibco.com/images/banners/banner2.png\"]")// banner image 
	private WebElement Banner_IMG2;
	public HomePage_SignedIn() {
		super();

	}
	
	protected WebElement getPageReadyElement() {
		return this.PAGE_READY_ELEMENT;
	}
	
	public HomePage getPageSignedIn(String emailAdress, String password) throws InterruptedException{
		HP_LoginDialog login= new HP_LoginDialog();
		HomePage page=login.loginAs(emailAdress, password);
		return page;
		
	}
	
}
