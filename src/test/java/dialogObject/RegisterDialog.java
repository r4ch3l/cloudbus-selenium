package dialogObject;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pageObject.BasePage;
import pageObject.CreateAccountPage;
import pageObject.WhyRegisterPage;
import util.DriverUtil;
/**
 * This is the Register dialog object shows after click the register button in the center of home page.
 * It's seen as a type of Page.
 * @author XiaoXue_Chen
 *
 */
public class RegisterDialog extends BasePage {
	

	@FindBy(css = "#register-dialog")		//the element identifies the page
	@CacheLookup
	private WebElement PAGE_READY_ELEMENT;
	@FindBy(css = "#register-dialog div.register-description")		//the text shows in the register dialog.
	private WebElement DESCRIPTION_DIV;
	@FindBy(css = "#register-dialog a.why-register.pull-left")		//the link lead to why register page.
	private WebElement WHY_REG_LINK;
	@FindBy(css = "#register-dialog a.btn.btn-continue.sub-action-button.pull-right")		//the continue button open the register page.
	private WebElement CONTINUE_BTN;

	public RegisterDialog() {
		super();

	}
	protected WebElement getPageReadyElement() {
		return PAGE_READY_ELEMENT;
	}

	public WhyRegisterPage OpenWhyRegLink() {
		DriverUtil.wait(ExpectedConditions.visibilityOf(WHY_REG_LINK));
		WHY_REG_LINK.click();
		return BasePage.getPage(WhyRegisterPage.class);

	}

	public CreateAccountPage OpenRegisterPage() {
		DriverUtil.wait(ExpectedConditions.visibilityOf(CONTINUE_BTN));
		CONTINUE_BTN.click();
		return BasePage.getPage(CreateAccountPage.class);
	}

	public String getDescription() throws InterruptedException {
		DriverUtil.wait(ExpectedConditions.visibilityOf(DESCRIPTION_DIV));
		return DESCRIPTION_DIV.getText();
	}

}
