package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import util.DriverUtil;


/**
 * This is a abstract page contains most methods a page should have.
 * 
 * @author XiaoXue_Chen
 * 
 */
abstract public class BasePage {
	

	public BasePage() {
		super();
	}

	/**
	 * Get the page object by using PageFactory
	 * 
	 * @param pageClass
	 *            the page object
	 * @return
	 */
	public static <T> T getPage(java.lang.Class<T> pageClass) {
		return PageFactory.initElements(DriverUtil.getDriver(), pageClass);
	}

	/**
	 * Get the web element could show this page already loaded.
	 * 
	 * @return a web element shows in the page.
	 */
	abstract protected WebElement getPageReadyElement();

	/**
	 * To see if the page is ready by the visibility of the web element.
	 * 
	 * @return the expected condition or false when the condition gets
	 *         exception.
	 */
	@SuppressWarnings("rawtypes")
	public ExpectedCondition ifPageReadyCondition() {
		if (this.getPageReadyElement() != null) {
			return ExpectedConditions.visibilityOf(getPageReadyElement());
		} else {
			return new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return false;
				}
			};
		}
	}

	/**
	 * Get the result of whether a web page is visible by waiting for the
	 * expected condition.
	 * 
	 * @return true : page is present. or false:page is not present.
	 * 
	 */
	public boolean isPresent() {
		try {
			DriverUtil.wait(this.ifPageReadyCondition());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Get the result of whether a web element is visible
	 * 
	 * @param element
	 *            WebElement
	 * @return the WebElement if it's visible or false when it's not visible.
	 */
	public boolean isElementVisible(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
	}
	

	
}