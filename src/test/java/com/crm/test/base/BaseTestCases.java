package com.crm.test.base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.test.config.Environment;
import com.crm.test.constants.CommonLogConstants;
import com.crm.test.listeners.DriverEventListener;
import com.crm.test.pages.HomePage;
import com.crm.test.utility.LoggerUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestCases extends Base {

	public LoggerUtility log = new LoggerUtility();
	public ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();;
	
	public void initialize_driver() {

		switch(Environment.fetchBrowser()) {
		
			case CommonLogConstants.CHROME:
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOpt = new ChromeOptions();
				chromeOpt.addArguments("--ignore-certificate-errors");
				log.infoMSG("Opening Browser : " + CommonLogConstants.CHROME);
				driver.set(new ChromeDriver(chromeOpt));
				break;
			
			case CommonLogConstants.FIREFOX:
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOpt = new FirefoxOptions();
				firefoxOpt.addArguments("--ignore-certificate-errors");
				log.infoMSG("Opening Browser : " + CommonLogConstants.FIREFOX);
				driver.set(new FirefoxDriver(firefoxOpt));
				break;

			default:
				log.infoMSG("Browser Launch Successful");
		}

		driver.set((new EventFiringWebDriver(driver.get())).register(new DriverEventListener()));
	}
	
	public HomePage launchCRM() {
		driver.get().get(Environment.fetchURL());
		return new HomePage(driver.get(), log);
	}
	
	public void closeBrowser() {
		if(driver != null) {
			driver.get().quit();
			driver = null;
		}
	}
	
	public String captureScreenShotInBase64String() {
		return "data:image/jpeg;base64," + ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.BASE64);
	}

}
