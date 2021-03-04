package com.crm.test.utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.test.base.Base;
import com.crm.test.constants.CommonLogConstants;

public class DriverWaitHelper extends Base {
	
	public DriverWaitHelper(WebDriver driver, LoggerUtility log) {
		super(driver, log);
	}
	
	final WebDriverWait wait = new WebDriverWait(driver.get(), 60);
	
	final Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver.get())
									.pollingEvery(Duration.ofMillis(3000))
									.ignoring(WebDriverException.class)
									.withTimeout(Duration.ofSeconds(300));
	final WebElement element = null;
	
	public WebElement waitAndReturnElementAvailable(WebElement elementToWait) {
		pageWait();
		return wait.until(ExpectedConditions.visibilityOf(elementToWait));
	}
	
	public WebElement waitAndReturnElementClickable(WebElement elementToWait) {
		pageWait();
		return wait.until(ExpectedConditions.elementToBeClickable(elementToWait));
	}
	
	public WebElement waitAndReturnElementClickableUsingFluent(WebElement elementToWait) {
		pageWait();
		return fWait.until(ExpectedConditions.elementToBeClickable(elementToWait));
	}
	
	public List<WebElement> waitAndReturnAllElementsVisible(List<WebElement> elements) {
		pageWait();
		return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public boolean areAllElementsPresentByLocator(final String locatorStrategy, final String locator) {
		pageWait();
		int sizeOfElement;
		switch(locatorStrategy) {

			case CommonLogConstants.CSS_LOCATOR:
				sizeOfElement = fWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
						By.cssSelector(locator))).size();
				break;
			
			case CommonLogConstants.XPATH_LOCATOR:
				sizeOfElement = fWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
						By.xpath(locator))).size();
				break;
			
			default:
				sizeOfElement = 0;
		}
		return sizeOfElement > 0 ? true : false;
	}
	
	public Alert waitForAlertVisibility() {
		pageWait();
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void waitAndSwitchToFrame(int frameNumber) {
		pageWait();
		fWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNumber));
	}
	
	public void pageWait() {
		log.infoMSG("Page Wait Start.");
		if(waitForAjaxToLoad()) {
			log.infoMSG("Page Loaded Successfully.");
		} else {
			throw new TimeoutException();
		}
		
	}
	
	public boolean waitForAjaxToLoad() {

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long)((JavascriptExecutor)driver).executeScript("return JQuery.active") == 0);
				} catch(Exception exp) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jSLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (((JavascriptExecutor)driver).executeScript(
						"return document.readyState").toString().equals("complete"));
			}
		};
		return wait.until(jQueryLoad) && wait.until(jSLoad);
	}
	
	public boolean waitforLoaderDisappear(WebElement element) {
		pageWait();
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}
}
