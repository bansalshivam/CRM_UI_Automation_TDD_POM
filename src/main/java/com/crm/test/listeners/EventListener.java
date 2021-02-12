package com.crm.test.listeners;

import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.crm.test.utility.LoggerUtility;


public class EventListener implements ITestListener {

	LoggerUtility log = new LoggerUtility();
	
	@Override  
	public void onTestStart(ITestResult result) {  
	
	}  
	  
	@Override  
	public void onTestSuccess(ITestResult result) {  
	// TODO Auto-generated method stub  
	log.infoMSG("Success of test cases and its details are : "+result.getName());  
	}  
	  
	@Override  
	public void onTestFailure(ITestResult result) {  
	// TODO Auto-generated method stub  
	log.infoMSG("Failure of test cases and its details are : "+result.getName());  
	}  
	  
	@Override  
	public void onTestSkipped(ITestResult result) {  
	// TODO Auto-generated method stub  
	log.infoMSG("Skip of test cases and its details are : "+result.getName());  
	}  
	  
	@Override  
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
	// TODO Auto-generated method stub  
	log.infoMSG("Failure of test cases and its details are : "+result.getName());  
	}  
	  
	@Override  
	public void onStart(ITestContext context) {  
	// TODO Auto-generated method stub  
	}  
	  
	@Override  
	public void onFinish(ITestContext context) {  
	
		Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
		for(ITestResult testCase : failedTests) {
			ITestNGMethod testCaseMethod = testCase.getMethod();
			if(context.getFailedTests().getResults(testCaseMethod).size() > 1) {
				log.warningMSG("Updating testNG-results.xml for " + testCaseMethod.getMethodName() 
					+ " because this case has been retried and failed again.");
				failedTests.remove(testCase);
			} else if(context.getPassedTests().getResults(testCaseMethod).size() > 0) {
				log.warningMSG("Updating testNG-results.xml for " + testCaseMethod.getMethodName() 
					+ " because this case has been retried and passed in second run.");
				failedTests.remove(testCase);
			} else if(context.getSkippedTests().getResults(testCaseMethod).size() > 0) {
				log.warningMSG("Updating testNG-results.xml for " + testCaseMethod.getMethodName() 
					+ " because this case has been retried and skipped in second run.");
				failedTests.remove(testCase);
			}
		}
	}
}
