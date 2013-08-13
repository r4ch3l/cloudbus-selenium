package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 *  This is the reset-password page object represent The page let you reset your password 
 *  
 * @author XiaoXue_Chen
 *
 */

public class ResetPwdPage extends BasePage {


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