package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import util.DriverUtil;
/**
 * This is the page object of "Creat Account" page
 * 
 * @author XiaoXue_Chen
 *
 */
public class CreateAccountPage extends BasePage {
	@FindBy(css = ".regText")
	private WebElement PAGE_READY_ELEMENT;
	@FindBy(css = "#login p")		// the welcome message at the upper-right of this page.
	private WebElement USER_WELCOME_MSG;

	public CreateAccountPage() {
		super();
		// Util.wait(ExpectedConditions.titleIs("My TAP Registration"));
	}

	@Override
	protected WebElement getPageReadyElement() {

		return PAGE_READY_ELEMENT;
	}

	public String GetWelcomeUserMsg() {
		DriverUtil.wait(ExpectedConditions.visibilityOf(USER_WELCOME_MSG));
		return USER_WELCOME_MSG.getText();
	}

	public HomePage creat(){
		
		return BasePage.getPage(HomePage.class); //https://cloudbus.tibco.com/?status=1
	}
	

}
