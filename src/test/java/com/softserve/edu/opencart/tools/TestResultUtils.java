package com.softserve.edu.opencart.tools;

import org.testng.ITestResult;
import org.testng.internal.thread.ThreadTimeoutException;

public final class TestResultUtils {
	private static final String NEW_LINE = "\n";
    private static final String BREAK_LINE = "<BR>";
    
	private TestResultUtils() {
	}

	public static void testResultReport(String reportMessage) {
		ReporterWrapper reporterWrapper = Application.get().reporter();
		reporterWrapper.error(reportMessage.replaceAll(NEW_LINE, BREAK_LINE));
        reporterWrapper.addHtmlSourceCode();
        reporterWrapper.addScreenShot();
	}
	
	public static String testResultMessage(ITestResult testResult) { 
		String message = "\n";
		if (testResult.getStatus() == ITestResult.SUCCESS) {
        	message = message + "PASSED: " + testResult.getName();
        } else {
        	message = message + "FAILED: " + testResult.getName()
        		+ testResultStackTrace(testResult.getThrowable());
        }
		testResultReport(message);
		return message;
	}

	public static String testResultStackTrace(Throwable throwable) {// что за класс 
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