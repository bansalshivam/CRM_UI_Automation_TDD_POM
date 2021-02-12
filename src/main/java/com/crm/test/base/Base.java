package com.crm.test.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.crm.test.config.Environment;
import com.crm.test.utility.LoggerUtility;

public class Base {

	public static Properties prop;
	public LoggerUtility log;
	public WebDriver driver;
	
	static {
		try {
			prop = new Properties();
			FileInputStream fileInputStream = new FileInputStream(Environment.GLOBAL_PROPERTIES_PATH);
			prop.load(fileInputStream);
			Files.createDirectory(Paths.get(Environment.REPORTS_FOLDER_PATH));
		} catch(FileNotFoundException nfExp) {
			nfExp.printStackTrace();
		} catch(IOException ioExp) {
			ioExp.printStackTrace();
		}
	}
	
	public Base() {}
	
	public Base(WebDriver driver, LoggerUtility log) {
		this.driver = driver;
		this.log = log;
	}
}
