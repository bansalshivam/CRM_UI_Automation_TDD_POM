package com.crm.test.utility;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.crm.test.constants.CommonLogConstants;

public class LoggerUtility {

	private static Logger log = Logger.getLogger(LoggerUtility.class);
	private ExtentReports extent = ExtentReportManager.getInstance();
	private static ExtentTest test;
	
	public void startTestCase(final String testCaseName) {
		log.info("******************************************************************************");
		log.info("----------------- Starting Test Case  :  " + testCaseName);
		log.info("******************************************************************************");
	}
	
	public void endTestCase(final String testCaseName) {
		log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		log.info("----------------- Ending Test Case    :  " + testCaseName);
		log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}
	
	public void infoMSG(final String message) {
		log.info(message);
	}
	
	public void warningMSG(final String message) {
		log.warn(message);
	}
	
	public void errorMSG(final String message) {
		log.error(message);
	}
	
	public void fatalMSG(final String message) {
		log.fatal(message);
	}
	
	public void debugMSG(final String message) {
		log.debug(message);
	}
	
	public void initiateTestHTML(final String testCaseName) {
		startTestCase(testCaseName);
		test = extent.createTest(testCaseName).assignCategory(CommonLogConstants.TAGE_NAME);
	}
	
	public void endTestHTML() {
		endTestCase(test.getModel().getName());
		if(extent != null) {
			extent.flush();
		}
	}
	
	public void reportInfoMsgInHtml(final String message) {
		infoMSG(message);
		test.log(Status.INFO, message);
	}
	
	public void reportWarningMsgInHtml(final String message) {
		warningMSG(message);
		test.log(Status.WARNING, message);
	}
	
	public void reportFailureMsgInHtml(final String message) {
		errorMSG(message);
		test.log(Status.FAIL, message);
	}
	
	public void reportSkippedMsgInHtml(final String message) {
		warningMSG(message);
		test.log(Status.SKIP, message);
	}
	
	public void reportPassInHtml(final String message) {
		infoMSG(message);
		test.log(Status.PASS, message);
	}
	
	public void reportFailureInHtml(final String message) {
		errorMSG("****************** Test Item Failed ******************");
		test.log(Status.FAIL, message);
		Assert.fail(message);
	}

	public void reportScreenshotInHtml(final String base64ImageString, final String imgName) {
		test.addScreenCaptureFromBase64String(base64ImageString, imgName);
	}
	
	public void removeTestFromHtml(final String testCaseName) {
		extent.removeTest(testCaseName);
	}

}
