package com.platform.project.pageObjects;


import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.platform.project.commons.commons;

public class LoginPage {
	//WebElements REUSABILITY:
	/*Method 1:
	 * Create a HashMap<String, String>
	 * Key: name for the WebElement (you choose it)
	 * Value: Xpath of the WebElement
	 */
	//Method 2: Page Factory
	
	@FindBy(name="email_address")
	WebElement emailAddress;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(id="tdb5")
	WebElement signInButton;
	
	@FindBy(className="messageStackError")
	List<WebElement> errors;
	
	WebDriver driver;
	private Logger logger = Logger.getLogger(LoginPage.class);
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private void enterEmail(String email) {
		commons.enterText(emailAddress, email);
	}
	
	private void enterPassword(String pass) {
		commons.enterText(password, pass);
	}
	
	private void clickSignInButton() {
		commons.clickButton(signInButton, driver, 1);
	}
	
	public void loginWithCredentials(String email, String password) {
		enterEmail(email);
		enterPassword(password);
		clickSignInButton();
	}
	
	public int getErrorsSize() {
		int errorSize = errors.size();
		logger.info("Error size is: " + errorSize);
		return errorSize;
	}
	
	public String getErrorText() {
//		String errorText = errors.get(1).getText();
//		logger.info("Error text is: " + errorText);
//		return errorText;
		//return commons.getElementText(errors.get(1));
		return commons.getElementText(errors.get(1),driver, 1); //if you want to wait 1 second.
	}

}
