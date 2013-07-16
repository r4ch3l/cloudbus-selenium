package sele.pot.pages.homePageForGuest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class VideoXDialog extends HomePage{
	private final static Logger logger = LoggerFactory.getLogger(VideoXDialog.class);

	@FindBy(id = "player")		//Sign-in dialog window shows after click the sign-in link.
	@CacheLookup
	private WebElement PAGE_READY_ELEMENT;
	@FindBy(css = "div[aria-labelledby='ui-id-1'] .ui-icon.ui-icon-closethick")		//Close button of the left video
	private WebElement CLOSE_BTN;
	@FindBy(css="#player")	//the video player
	private WebElement PLAYER;
	
	
	public void closeVideo() throws InterruptedException{
		while (!isElementVisible(CLOSE_BTN)) {
			Thread.sleep(1000);
			System.out.println("wait 1......");
		}
		CLOSE_BTN.click();
	}
		


	@Override
	protected WebElement getPageReadyElement() {
		
		return PAGE_READY_ELEMENT;
	}


}
