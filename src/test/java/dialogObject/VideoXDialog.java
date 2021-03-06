package dialogObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pageObject.BasePage;
import util.DriverUtil;



public class VideoXDialog extends BasePage{

	@FindBy(id = "player")		//Sign-in dialog window shows after click the sign-in link.
	@CacheLookup
	private WebElement PAGE_READY_ELEMENT;
	@FindBy(css = "div[aria-labelledby='ui-id-1'] .ui-icon.ui-icon-closethick")		//Close button of the left video
	private WebElement CLOSE_BTN;
	@FindBy(css="#player")	//the video player
	private WebElement PLAYER;
	
	
	public void closeVideo() throws InterruptedException{
		DriverUtil.wait(ExpectedConditions.visibilityOf(CLOSE_BTN));
		CLOSE_BTN.click();
	}
		



	protected WebElement getPageReadyElement() {
		
		return PAGE_READY_ELEMENT;
	}


}
