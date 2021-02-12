package com.crm.test.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryAnnotationListener implements IAnnotationTransformer {

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation testAnnotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		Class<? extends IRetryAnalyzer> retry = testAnnotation.getRetryAnalyzerClass();
	
		if(retry == null) {
			testAnnotation.setRetryAnalyzer(retry);
		}
	}
}
