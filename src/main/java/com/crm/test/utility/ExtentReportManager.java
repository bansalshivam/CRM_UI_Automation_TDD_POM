package com.crm.test.utility;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.crm.test.base.Base;
import com.crm.test.config.Environment;
import com.crm.test.constants.CommonLogConstants;


public class ExtentReportManager extends Base {

	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		if(extent == null) {
			ExtentSparkReporter reporter = new ExtentSparkReporter(
					Environment.REPORTS_FOLDER_PATH + Environment.EXTENT_REPORT_NAME);
			reporter.config().setDocumentTitle(prop.getProperty("report_title"));
			reporter.config().setReportName(CommonLogConstants.TAGE_NAME);
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo(CommonLogConstants.SYSTEM_INFO, Environment.fetchURL());
			extent.setSystemInfo(CommonLogConstants.BROWSER, Environment.fetchBrowser());
		}
		return extent;
	}
}
