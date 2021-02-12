package com.crm.test.config;

import java.util.Date;

import com.crm.test.base.Base;
import com.crm.test.constants.CommonLogConstants;

public class Environment extends Base {
	
	public static String EXTENT_REPORT_NAME = (new Date()).toString().replace(":", "_").replace(" ", "_") + ".html";
	public static String GLOBAL_PROPERTIES_PATH = System.getProperty("user.dir") + "/src/main/resources/global.properties";
	public static String REPORTS_FOLDER_PATH = System.getProperty("user.dir") + "/target/Reports/";
	public static int IS_RETRY = 0;
	
	public static String fetchURL() {
		
		if(!System.getProperty(CommonLogConstants.URL).equals("")) {
			return System.getProperty(CommonLogConstants.URL);
		}
		return prop.getProperty(CommonLogConstants.URL);
	}
	
	public static String fetchBrowser() {
		if(!System.getProperty(CommonLogConstants.BROWSER).equals("")) {
			return System.getProperty(CommonLogConstants.BROWSER);
		}
		return prop.getProperty(CommonLogConstants.BROWSER);
	}
}
