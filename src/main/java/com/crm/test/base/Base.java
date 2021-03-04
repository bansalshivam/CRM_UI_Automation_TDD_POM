package com.crm.test.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.crm.test.config.Environment;
import com.crm.test.utility.LoggerUtility;

public class Base {

	public static Properties prop;
	public LoggerUtility log = new LoggerUtility();
	public ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
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
		this.driver.set(driver);
		this.log = log;
	}
	
	public String captureScreenShotInBase64String() {
		return "data:image/jpeg;base64," + ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.BASE64);
	}
}
