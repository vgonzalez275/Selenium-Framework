package com.platform.project.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.commons;

public class HomePage {
	@FindBy(xpath="//div[@id='bodyContent']//h1")
	WebElement pageTitle;
	
	@FindBy(id="tdb3")
	WebElement accountButton;
	
	WebDriver driver;
	private Logger logger = Logger.getLogger(HomePage.class);
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void openHomePage() {
		String url = ReadPropertyFile.getConfigPropertyVal("homePageURL");
		logger.info("Opening the home page URL: "+ url);
		driver.get(url);
		
	}
	
	public String getHomePageTitle() {
//		String title =  pageTitle.getText();
//		logger.info("Home Page Title is: " + title);
//		return title;
		//return commons.getElementText(pageTitle);
		//if you want to wait 5 seconds to fetch the home title
		return commons.getElementText(pageTitle, driver, 5);
	}
	
	public void clickAccountButton() {
		commons.clickButton(accountButton,driver, 1);
	}

}
