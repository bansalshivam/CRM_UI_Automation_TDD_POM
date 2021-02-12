package com.crm.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.crm.test.base.Base;
import com.crm.test.utility.LoggerUtility;

public class HomePage extends Base {

	
	public HomePage(WebDriver driver, LoggerUtility log) {
		super(driver, log);
		PageFactory.initElements(driver, this);
	}
	
	public void firstFunc() {
		
	}
}
