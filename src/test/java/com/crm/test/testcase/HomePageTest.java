package com.crm.test.testcase;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crm.test.base.BaseTestCases;
import com.crm.test.pages.HomePage;

public class HomePageTest extends BaseTestCases {

	@BeforeClass(alwaysRun = true)
	public void commonSetup() {
		try {
			initialize_driver();
		} catch(WebDriverException driverExp) {
			driverExp.printStackTrace();
			throw driverExp;
		}
	}
	
	@Test
	public void testHomePageTC1() {
		try {
			log.initiateTestHTML("testHomePageTC1");
			HomePage homePage;
			homePage = launchCRM();
			homePage.firstFunc();
			log.reportScreenshotInHtml(captureScreenShotInBase64String(), "testHomePageTC1.jpg");
		} catch(Exception exp) {
			throw exp;
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void testTearDown() {
		log.endTestHTML();
	}
	
	@AfterClass(alwaysRun = true)
	public void commontearDown() {
		closeBrowser();
	}
}
