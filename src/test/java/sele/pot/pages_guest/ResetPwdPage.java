package sele.pot.pages_guest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sele.pot.pages.Page;
/**
 *  This is the reset-password page object represent The page let you reset your password 
 *  
 * @author XiaoXue_Chen
 *
 */

public class ResetPwdPage extends Page {

	private final static Logger logger = LoggerFactory
			.getLogger(ResetPwdPage.class);

	@FindBy(css = ".forgetPasswordDiv")		//the element identifies the page
	private WebElement PAGE_READY_ELEMENT;

	@FindBy(xpath = "/html/head/title")		//page title
	private WebElement PAGE_TITLE;

	public ResetPwdPage() {
		super();

	}

	protected WebElement getPageReadyElement() {
		return this.PAGE_READY_ELEMENT;
	}

}