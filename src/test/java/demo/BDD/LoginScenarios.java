package demo.BDD;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pageObject.BasePage;
import pageObject.HomePage;
import util.CfgLoader;
import util.DriverUtil;
import dialogObject.LoginDialog;

public abstract class LoginScenarios extends JUnitStory {
	protected static WebDriver driver = DriverUtil
			.getDriver(CfgLoader.browserType);
	protected LoginDialog loginDialog = BasePage.getPage(LoginDialog.class);
	protected HomePage homePage = BasePage.getPage(HomePage.class);

	@Override
	public Configuration configuration() {
		URL storyURL = null;
		try {
			// This requires you to start Maven from the project directory
			storyURL = new URL("file://" + System.getProperty("user.dir")
					+ "/src/test/java/resources/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return new MostUsefulConfiguration().useStoryLoader(
				new LoadFromClasspath(this.getClass().getClassLoader()))
				.useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(Format.HTML,Format.CONSOLE).withRelativeDirectory("jbehave-report"));

	}
	
	@Override
	public List candidateSteps(){
		return new InstanceStepsFactory(configuration(),this).createCandidateSteps();
	}
	
	
	@Override
	@Test
	 public void run() throws Throwable{
		super.run();
	}
}
