package com.crm.test.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.crm.test.base.Base;

public class GenericTestUtilities extends Base {
	
	public synchronized void copyOutputReportFileFromInputFile(File srcFile, File destFile) {
		try {
			if(srcFile.exists()) {
				log.infoMSG("Initiating copy of " + srcFile.getPath() + " to " + destFile.getPath());
				FileUtils.copyFile(srcFile, destFile);
			} else {
				throw new FileNotFoundException();
			}
		} catch(FileNotFoundException fileExp) {
			log.infoMSG("File Not Exist : " + srcFile.getPath());
			log.debugMSG("Complete Stacktrace of exception : " + fileExp.toString());
		} catch(IOException ioExp) {
			log.infoMSG("Not able to copy file from " + srcFile.getPath() + " to " + destFile.getPath());
			log.debugMSG("Complete Stacktrace of exception : " + ioExp.toString());
		}
	}

}
