package com.softserve.edu.opencart.tools;

import org.testng.ITestResult;
import org.testng.internal.thread.ThreadTimeoutException;

public final class TestResultUtils {

	private TestResultUtils() {
	}

	public static String testResultMessage(ITestResult testResult) {
		String message = "\n";
		if (testResult.getStatus() == ITestResult.SUCCESS) {
        	message = message + "PASSED: " + testResult.getName();
        } else {
        	message = message + "FAILED: " + testResult.getName()
        		+ testResultStackTrace(testResult.getThrowable());
        }
		return message;
	}

	public static String testResultStackTrace(Throwable throwable) {
		String message = new String();
		if (throwable != null) {
			message = message + "\n" + throwable.toString();
			//
			// If it's not a thread timeout, include the stack trace too
			if (!(throwable instanceof ThreadTimeoutException)) {
				for (StackTraceElement e : throwable.getStackTrace()) {
					message = message + "\n        at " + e.toString();
				}
			}
		}
		return message;
	}

}
