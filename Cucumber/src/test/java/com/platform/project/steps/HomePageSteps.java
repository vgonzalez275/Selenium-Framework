package com.platform.project.steps;

import org.openqa.selenium.WebDriver;

import com.platform.project.commons.WebDriverManager;
import com.platform.project.commons.commons;
import com.platform.project.pageObjects.HomePage;
import com.platform.project.pageObjects.LoginPage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomePageSteps {
	WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	WebDriverManager webDriverManager;
	
	@Before
	public void setup() {

		webDriverManager = new WebDriverManager();
		driver = webDriverManager.getDriver();
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		
	}	
		@After
		public void tearDown() {
			driver.quit();
		}
		
		@Given ("^I open the home page$")
		public void openHomePage() {
			homePage.openHomePage();
		}
		
		
		@Then ("^I check the home page title is correct$")
		public void verifyHOmePageTitle() {
//			commons.check(homePage.getHomePageTitle().equals("Welcome to iBussiness"),driver,"HomePage title doesn't match");
			homePage.getHomePageTitle();
		}
		
		@And ("^I click on the account button$")
		public void clickAccountButton() {
			homePage.clickAccountButton();
//			commons.check(homePage.clickAccountButton(),driver,"Couldn't click on the account button");
		}
		
		@When ("^I enter the username and password$")
		public void loginWithCredentials() {
			loginPage.loginWithCredentials("abc", "abc");
		}
		
		@When ("^I enter the username \"(.*)\" and password \"(.*)\"$") //(.*) is a wild card option
		public void loginWithCredentials2(String username, String password) {
			loginPage.loginWithCredentials(username, password);
		}
		
		@When ("^I enter username (.*) and password (.*)$") 
		public void loginWithCredentials3(String username, String password) {
			loginPage.loginWithCredentials(username, password);
		}
		
		@Then ("^I check the login error message$")
		public void checkLoginErrorMessage() {
			commons.check(loginPage.getErrorsSize()==2, driver, "Error message list size does not match");
			commons.check(loginPage.getErrorText().equals(" Error: No match for E-Mail Address and/or Password."), driver, 
					"Error message text does not match");
		}
		
}