package sele.pot.pages.homePageForGuest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sele.pot.pages.Page;
import sele.pot.pages.homeRelated.WhyRegisterPage;
import sele.pot.pages.register.CreateAccountPage;
import sele.util.DriverUtil;
/**
 * This is the Register dialog object shows after click the register button in the center of home page.
 * It's seen as a type of Page.
 * @author XiaoXue_Chen
 *
 */
public class HP_RegisterDialog extends Page {
	
	private final static Logger logger = LoggerFactory.getLogger(HP_RegisterDialog.class);
	@FindBy(css = "#register-dialog")		//the element identifies the page
	@CacheLookup
	private WebElement PAGE_READY_ELEMENT;
	@FindBy(css = "#register-dialog div.register-description")		//the text shows in the register dialog.
	private WebElement DESCRIPTION_DIV;
	@FindBy(css = "#register-dialog a.why-register.pull-left")		//the link lead to why register page.
	private WebElement WHY_REG_LINK;
	@FindBy(css = "#register-dialog a.btn.btn-continue.sub-action-button.pull-right")		//the continue button open the register page.
	private WebElement CONTINUE_BTN;
	@FindBy(xpath = "/html/head/title")		//the page title
	private WebElement PAGE_TITLE;

	public HP_RegisterDialog() {
		super();

	}
	protected WebElement getPageReadyElement() {
		return PAGE_READY_ELEMENT;
	}

	public WhyRegisterPage OpenWhyRegLink() {
		DriverUtil.wait(ExpectedConditions.visibilityOf(WHY_REG_LINK));
		WHY_REG_LINK.click();
		return Page.getPage(WhyRegisterPage.class);

	}

	public CreateAccountPage OpenRegisterPage() {
		DriverUtil.wait(ExpectedConditions.visibilityOf(CONTINUE_BTN));
		CONTINUE_BTN.click();
		return Page.getPage(CreateAccountPage.class);
	}

	public String getDescription() throws InterruptedException {
		DriverUtil.wait(ExpectedConditions.visibilityOf(DESCRIPTION_DIV));
		return DESCRIPTION_DIV.getText();
	}

}
