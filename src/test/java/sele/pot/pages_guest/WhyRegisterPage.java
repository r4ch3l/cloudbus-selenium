package sele.pot.pages_guest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sele.pot.pages.Page;
import sele.pot.pages.dialogs.VideoXDialog;
import sele.util.CfgLoader;
import sele.util.DriverUtil;
/**
 * This is the page object of "Why Register" page
 * 
 * @author XiaoXue_Chen
 *
 */
public class WhyRegisterPage extends Page {
	private String pageUrl=CfgLoader.whyRegPageUrl;
	private final static Logger logger = LoggerFactory.getLogger(WhyRegisterPage.class);

	@FindBy(css = ".site.com_content.view-article.no-layout.no-task.itemid-117")
	private WebElement PAGE_READY_ELEMENT;
	@FindBy(css = ".span8.pull-left :nth-child(1).main-content-header")		//the title of the text on this page.
	private WebElement TITLE_P;
	@FindBy(css = ".why-register-video1")		//the image of the video on this page.
	private WebElement WHY_REG_IMG;

	public WhyRegisterPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPageUrl(){
		return pageUrl;
	}
	public VideoXDialog OpenWhyRegVideo() {
		DriverUtil.wait(ExpectedConditions.visibilityOf(WHY_REG_IMG));
		WHY_REG_IMG.click();
		return Page.getPage(VideoXDialog.class);
	}

	protected WebElement getPageReadyElement() {
		return this.PAGE_READY_ELEMENT;
	}


}