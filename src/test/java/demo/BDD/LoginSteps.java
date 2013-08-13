package demo.BDD;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dialogObject.LoginDialog;
import pageObject.BasePage;
import pageObject.HomePage;
import tests.ValidateSignInDialog;
import util.CfgLoader;
import util.DriverUtil;

public class LoginSteps extends LoginScenarios{
	
	
	@When("User open Home page click sign in link")
	public void UserOpen(){
		driver.get(homePage.getPageUrl());
		loginDialog=homePage.OpenSignInDiv();
		
		
	}
	
	@When("User enter valid email as: '$email' password as: '$password' and submit.")
	public void EnterEmail(String email,String password) throws InterruptedException{
		 homePage = loginDialog.loginAs(email,password);
	}

	
	
	@Then("User should see home page should be reloaded and There is a username as: '$username_exp'")
	public void UserShouldPageReloadAndUserName(String username_exp){
		assertTrue(homePage.isPresent());
		assertEquals(username_exp, homePage.getUserWelcomeMsg());
		driver.quit();
	}
	
}
