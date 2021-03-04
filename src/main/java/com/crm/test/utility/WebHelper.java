package com.crm.test.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.crm.test.base.Base;

public class WebHelper extends Base {

	public WebHelper(WebDriver driver, LoggerUtility log) {
		super(driver, log);
	}
	
	DriverWaitHelper waitHelper = new DriverWaitHelper(driver.get(), log);
	
	public void waitMoveAndClickElement(WebElement element, boolean isMoveToElement) {
		element = waitHelper.waitAndReturnElementClickable(element);
		if(isMoveToElement) {
			moveToElement(element);
		}
		element.click();
	}
	
	public void waitAndSendText(WebElement element, String txtToEnter, boolean isMoveToElement) {
		element = waitHelper.waitAndReturnElementAvailable(element);
		if(isMoveToElement) {
			moveToElement(element);
		}
		element.sendKeys(txtToEnter);
	}
	
	public void moveToElement(WebElement element) {
		Actions action = new Actions(driver.get());
		action.moveToElement(element).perform();
	}
	
	public String getValueAttribute(WebElement element, String attributeName) {
		return waitHelper.waitAndReturnElementAvailable(element).getAttribute(attributeName);
	}
	
	public void alertHandler(String action) {
		
		switch(action) {

			case "Accept":
				waitHelper.waitForAlertVisibility().accept();
				break;
				
			case "Dismiss":
				waitHelper.waitForAlertVisibility().dismiss();
				break;
				
			default:
				log.errorMSG("No Valid Alert Action passed, found action is : " + action);
				log.errorMSG("Only Accept / Dismiss is permissible.");
				break;
		}
	}
	
	public void frameHandler(String action, int frameNumber) {
		
		switch(action) {

			case "Switch":
				waitHelper.waitAndSwitchToFrame(frameNumber);
				break;
				
			case "DefaultSwitch":
				driver.get().switchTo().defaultContent();
				break;
				
			default:
				log.errorMSG("No Valid Frame Action passed, found action is : " + action);
				log.errorMSG("Only Switch / DefaultSwitch is permissible.");
				break;
		}
	}
	
}
