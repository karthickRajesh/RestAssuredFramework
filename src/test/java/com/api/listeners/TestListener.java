package com.api.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener{
	
		private static final Logger logger = LogManager.getLogger(TestListener.class);
	
		public void onTestStart(ITestResult result) {
	        logger.info("Test Started: {}", result.getName());
	        
		  }
		
		public void onTestSuccess(ITestResult result) {
	        logger.info("Test Success: {}", result.getName());
		  }
		
		public void onTestFailure(ITestResult result) {
	        logger.error("Test failed: {}", result.getName());
	        logger.fatal("Exception is :" + result.getThrowable());
		  }
		
		public void onTestSkipped(ITestResult result) {
	        logger.warn("Test Skipped: {}", result.getName());
		  }
		
		public void onStart(ITestContext context) {
	        logger.info("Test suite Started:");
		  }
		
		public void onFinish(ITestContext context) {
			logger.info("Test suite Ended:");
		  }

}
