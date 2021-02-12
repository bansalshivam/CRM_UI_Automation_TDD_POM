package com.crm.test.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.crm.test.base.Base;
import com.crm.test.config.Environment;

public class RetryRunner extends Base implements IRetryAnalyzer {

	private int currentRetryCount = 0;
	private int maxRetryCount = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		if(currentRetryCount < maxRetryCount) {
			currentRetryCount++;
			Environment.IS_RETRY = 1;
			log.warningMSG("Retrying test : " + result.getName()
				+ " : with status " + (result.getStatus() == 2 ? "FAILURE" : "SKIP") 
				+ " for the " + (currentRetryCount + 1) + " time(s).");
			return true;
		}
		return false;
	}
}
