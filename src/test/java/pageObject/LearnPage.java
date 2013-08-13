package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.CfgLoader;
import util.DriverUtil;

public class LearnPage extends BasePage{
	private String pageUrl=CfgLoader.learnPageUrl;

	@FindBy(css = "frameset[cols='300,*']")		//the web element identifies the page
	private WebElement PAGE_READY_ELEMENT;
	private String CURRENT_WINDOW=DriverUtil.getDriver().getWindowHandle();
	@Override
	protected WebElement getPageReadyElement() {
		return PAGE_READY_ELEMENT;
	}
	
}
