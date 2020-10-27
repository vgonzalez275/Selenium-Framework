package com.platform.project.commons;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverManager {
	
	private WebDriver driver;
	private Logger logger = Logger.getLogger(WebDriverManager.class);
	
	private String osName = System.getProperty("os.name");
	
	private WebDriver createDriver(String browser) {
		if(osName.toLowerCase().contains("windows")) {
			logger.info("Windows OS detected");
			
			if(browser.equalsIgnoreCase("chrome")) {
				logger.info("Chrome browser detected");
				System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				logger.info("Firefox browser detected");
				System.setProperty("webdriver.gecko.driver", "src//test//resources//drivers//gecko.exe");
				driver = new FirefoxDriver();
			}else if(browser.equalsIgnoreCase("ie")) {
				logger.info("Internet Explorer detected");
				System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//ie.exe");
				driver = new InternetExplorerDriver();
			}else {
				logger.info("Default browser detected");
				System.setProperty("webdriver.gecko.driver", "src//test//resources//drivers//chromedriver.exe");
				driver = new ChromeDriver();
			}
		}else if(osName.toLowerCase().contains("mac")) {
			logger.info("MAC OS detected");
			
			if(browser.equalsIgnoreCase("chrome")) {
				logger.info("Chrome browser detected");
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				logger.info("Firefox browser detected");
				System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/gecko");
				driver = new FirefoxDriver();
			}else if(browser.equalsIgnoreCase("safari")) {
				logger.info("Internet Explorer detected");
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/safari");
				driver = new InternetExplorerDriver();
			}else {
				logger.info("Default browser detected");
				System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/chromedriver");
				driver = new ChromeDriver();
			}
		}else if(osName.toLowerCase().contains("linux")) {
				logger.info("Linux OS detected");
				
				if(browser.equalsIgnoreCase("chrome")) {
					logger.info("Chrome browser detected");
					System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
					driver = new ChromeDriver();
				}
				else if(browser.equalsIgnoreCase("firefox")) {
					logger.info("Firefox browser detected");
					System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/gecko");
					driver = new FirefoxDriver();
				}else if(browser.equalsIgnoreCase("safari")) {
					logger.info("Internet Explorer detected");
					System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/safari");
					driver = new InternetExplorerDriver();
				}else {
					logger.info("Default browser detected");
					System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/chromedriver");
					driver = new ChromeDriver();
				}
		}
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); //waits until a maximum of 2 seconds if does not find element
		return driver;
	}


	public WebDriver getDriver(String browser) {
		if(driver==null) {
			try {
				driver = createDriver(browser);
				logger.info("browser initialization successful.");
			}catch(Exception ex){
				ex.printStackTrace();
				logger.info("couldnt initialize the driver");
			}
		}else {
			logger.info("Driver already initialized");
		}
		return driver;
	}
	
	public WebDriver getDriver() {
		//return getDriver(ReadPropertyFile.getConfigPropertyVal("browser"));
		
//		String value = commons.createEnvVariable("browser");
//		if(value==null) {
//			return getDriver(commons.createEnvVariable("browser"));
//		}else {
//			return getDriver(value);
//		}
		
		return getDriver(commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
	}
	

}