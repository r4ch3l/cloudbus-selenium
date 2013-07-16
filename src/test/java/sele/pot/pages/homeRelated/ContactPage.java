package sele.pot.pages.homeRelated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sele.pot.pages.Page;
import sele.pot.pages.homePageForGuest.HomePage;
import sele.pot.pages.homePageForGuest.VideoXDialog;
import sele.util.DriverUtil;
/**
 * This is the page object of contact page https://cloudbus.tibco.com/index.php/contact
 * It contains some elements to be tested or use on that page and some methods operated with those elements. 
 * 
 * @author XiaoXue_Chen
 *
 */
public class ContactPage extends HomePage {
	private final static Logger logger = LoggerFactory.getLogger(ContactPage.class);
	public static String url="https://cloudbus.tibco.com/index.php/contact";
	@FindBy(css = ".site.com_content.view-article.no-layout.no-task.itemid-126")		//the web element identifies the page
	private WebElement PAGE_READY_ELEMENT;
	@FindBy(css = "#_input_a101")		// the email adress input on this page
	private WebElement EMAIL_ADRESS_INPUT;
	@FindBy(css = "#_input_a102")		//the message input
	private WebElement MSG_INPUT;
	@FindBy(xpath = "//*[@class='button button_submit default']/descendant::span[@class='center']")		//the submit button
	private WebElement SUBMIT_BTN;
	@FindBy(xpath = "//*[@class='button button_reset']/descendant::span[@class='center']")		//the reset button
	private WebElement RESET_BTN;
	@FindBy(xpath = "/html/head/title")		//the page title
	private WebElement PAGE_TITLE;
	@FindBy(xpath = "id('submiterrors')/descendant::a")		//the page title
	private WebElement EMAIL_ERROR;
	@FindBy(css="#pf-thankyou h2")
	private WebElement THANKYOU_MSG; //text shows after you submit msg
	@FindBy(id="contact-iframe")
	private WebElement CONTACT_IFRAME; //iframe of contact form
	
	private String CURRENT_WINDOW=DriverUtil.getDriver().getWindowHandle();
	
	public ContactPage() {
		super();
	}
	
	protected WebElement getPageReadyElement() {
		return this.PAGE_READY_ELEMENT;
	}


	public void inputEmailAndMsg(String email,String msg){

		DriverUtil.getDriver().switchTo().frame(CONTACT_IFRAME);// get in iframe
		DriverUtil.wait(ExpectedConditions.visibilityOf(EMAIL_ADRESS_INPUT));
		EMAIL_ADRESS_INPUT.clear();
		EMAIL_ADRESS_INPUT.sendKeys(email);
		MSG_INPUT.clear();
		MSG_INPUT.sendKeys(msg);
		DriverUtil.getDriver().switchTo().window(CURRENT_WINDOW);//get out iframe
		
	}
	
	public void Submit(){
		DriverUtil.getDriver().switchTo().frame(CONTACT_IFRAME);//get in iframe
		SUBMIT_BTN.click();
		DriverUtil.getDriver().switchTo().window(CURRENT_WINDOW);//get out iframe
	}
	public String getThankUAftersubmit(){
		String thankyou="";
		DriverUtil.getDriver().switchTo().frame(CONTACT_IFRAME);//get in iframe
		DriverUtil.wait(ExpectedConditions.visibilityOf(THANKYOU_MSG));
		thankyou=THANKYOU_MSG.getText();
		DriverUtil.getDriver().switchTo().window(CURRENT_WINDOW);//get out iframe
		return thankyou; //should be "Your form has been submitted."
	}
	
	
	public String getEmailErrorNotic(){
		String error="";
		DriverUtil.getDriver().switchTo().frame(CONTACT_IFRAME);//get in iframe
		DriverUtil.wait(ExpectedConditions.visibilityOf(EMAIL_ERROR));
		error=EMAIL_ERROR.getText();
		DriverUtil.getDriver().switchTo().window(CURRENT_WINDOW);// get out iframe
		return error; //should be "What is your email address?"
	}
	
	
	public void clickReset(){
		DriverUtil.getDriver().switchTo().frame(CONTACT_IFRAME);//get in iframe
		RESET_BTN.click();
		DriverUtil.getDriver().switchTo().window(CURRENT_WINDOW);// get out iframe
	}
	
	public boolean isInputEmpty(){
		DriverUtil.getDriver().switchTo().frame(CONTACT_IFRAME);//get in iframe
		boolean isEmpty=MSG_INPUT.getText().isEmpty()&&EMAIL_ADRESS_INPUT.getText().isEmpty();
		DriverUtil.getDriver().switchTo().window(CURRENT_WINDOW);// get out iframe
		return isEmpty;
		
	}
}
