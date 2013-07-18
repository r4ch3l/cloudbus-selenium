package sele.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * This is the rule for screen shooting when the assertion fail in Junit.
 * 
 * @author XiaoXue_Chen
 * 
 */
public class ScreenShotRule implements MethodRule {
	public WebDriver driver;
	public String directoryName;

	public ScreenShotRule(WebDriver driver) {
		super();
		this.driver = driver;
		directoryName = getDirectoryName();
	}

	public Statement apply(final Statement statement,
			final FrameworkMethod frameworkMethod, final Object o) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				try {
					statement.evaluate();
				} catch (Throwable t) {
					captureScreenshot(frameworkMethod.getName());
					throw t; // rethrow to allow the failure to be reported to
								// JUnit
				}
			}

			public void captureScreenshot(String fileName) {
				try {
					new File(directoryName).mkdirs(); // Insure directory is
														// there
					FileOutputStream out = new FileOutputStream(directoryName
							+ "screenshot-" + fileName + "-" + getTime()
							+ ".png");
					out.write(((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.BYTES));
					out.close();
				} catch (Exception e) {
					// No need to crash the tests if the screenshot fails
				}
			}
		};
	}

	private String getDirectoryName() {
		return "target/screenshot" + "_" + getDate() + "/" + "failed/";

	}

	private String getDate() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(new Date(System.currentTimeMillis()));
	}

	private String getTime() {
		SimpleDateFormat fmt = new SimpleDateFormat("HH-mm-ss");
		return fmt.format(new Date(System.currentTimeMillis()));
	}

}
