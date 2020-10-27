package com.platform.project.commons;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import static java.lang.Thread.currentThread;


public class commons {
	
	private static Logger logger = Logger.getLogger(commons.class);
	
	public static void clickButton(WebElement el, WebDriver driver, int seconds) {
		if(isElementVisible(driver, el, seconds)) {
			logger.info("Clicking on Sign In button" + el.getText());
			el.click();
		}else {
			logger.info("Couldn't click on button.");
			check(false, driver, "Couldn't click on button"); //Could be a problem if you do not want a click failure.
		}
		
	}
	
	public static String getElementText(WebElement el) {
		String text = el.getText();
		logger.info("Element text is: "+ text);
		return text;
	}
	
	//you could force it to use an explicit wait by using polymorphism
	public static String getElementText(WebElement el, WebDriver driver, int seconds) {
		if(waitForElement(driver,el,seconds)) {
			return getElementText(el); //
		}else {
			logger.info("Couldn't get the element text");
		}
		return "";
	}
	
	public static void enterText(WebElement el, String text) {
		logger.info("Entering text "+text+" in element "+el.getText());
		el.sendKeys(text);
	}
	
	public static String createEnvVariable(String envVariable, String defaultValue) {
		String value = System.getProperty(envVariable);
		logger.info("Environment value for "+envVariable+" is "+ value);
		return value!=null ? value : defaultValue;
	}
	
	public static void screenShot(WebDriver driver,String className, String methodName) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss_").format(new Date());
		
		String fileName = timeStamp + className + "_" + methodName + ".png";
		
		File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(ss, new File ("./screenshot/" + fileName));
			logger.info("Screenshot "+ fileName +" taken");
		}catch(IOException ioe) {
			ioe.printStackTrace();
			logger.info("Unable to take a screenshot "+ fileName);
		}
	}
	
	public static void check(boolean condition, WebDriver driver, String failMessage) {
		if(condition) {
			Assert.assertTrue(true);
			logger.info("Check condition is true.");
		}else {
			logger.info(failMessage);
			screenShot(driver,currentThread().getStackTrace()[2].getClassName(),currentThread().getStackTrace()[2].getMethodName());
			org.junit.Assert.fail();  //same statement as AssertTrue(false);
		}
	}
	
	public static boolean waitForElement(WebDriver driver, WebElement el, int seconds) { //usually what they use in industry but it is not good.
		//if you write it this way, if you find it and wait until I find the element. 
		WebDriverWait wait = new WebDriverWait(driver,seconds);
//		wait.until(ExpectedConditions.visibilityOf(el)); //After 1 second I continue when I find it.
		//OUtcome #2: If i do not find the element, I wait for 5 seconds and continue;
		//Whether you find it or not you have to wait more time, hence it is not good.
		//We change the return type from void to boolean to make a decision on how to proceed.
		try {
			wait.until(ExpectedConditions.visibilityOf(el));
			logger.info("Element "+el.getText()+" is visible");
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("Element is not visible");
			return false;
		}
		
	}
	
	//Another complex way of creating an explicit wait
	public static boolean isElementVisible(WebDriver driver, WebElement el, int seconds) { 
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			.withTimeout(seconds,TimeUnit.SECONDS)   //If reach max of seconds, can wait another 10 ms.
			.pollingEvery(10,TimeUnit.MILLISECONDS); //If wait until returns false it waits another 10 milliseconds
		
			//This function gets rid of the explicit wait time
			Function<WebDriver, Boolean> function = arg0 ->{
				try {
					driver.manage().timeouts().implicitlyWait(1,TimeUnit.MICROSECONDS);
					el.getSize();
					return true;
				}catch(NoSuchElementException nsee) {
					return false;
				}
			};
		try {
			
			wait.until(function);
			logger.info("Element "+el.getText()+" is visible");
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("Element is NOT visible");
			return false;
		}
		
	}
	
}
