package com.softserve.edu;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.internal.thread.ThreadTimeoutException;

public class AppTestReport {
	public static final Logger logger = LoggerFactory.getLogger(AppTestReport.class);
	private boolean isTestComplete;
	private SoftAssert softAssert;

	//@Test
	public void testApp1() throws Exception {
		System.out.println("surefire.reports.directory = " + System.getProperty("surefire.reports.directory"));
		System.out.println("selenium.version = " + System.getProperty("selenium.version"));
		System.out.println("database.password = " + System.getProperty("database.password"));
		System.out.println("System.getenv().database.password = " + System.getenv().get("DATABASE_PASSWORD"));
		//
		//System.out.println("System.getenv() = " + System.getenv());
	}

	//@BeforeMethod
	public void setUp() throws Exception {
		System.out.println("AppTestReport @Before setUp()");
		isTestComplete = false;
	}

	//@AfterMethod
	//public void tearDown() throws Exception {
	public void tearDown(ITestResult result) throws Exception {
		Reporter.setCurrentTestResult(result);
		System.out.println("CalcTest @After tearDown()");
		Reporter.log("<BR><FONT SIZE='4' COLOR='RED'>@AfterMethod Non Conditional.</FONT><BR>", true);
		Reporter.log("<BR><FONT SIZE='4' COLOR='BLUE'>@AfterMethod Level 3</FONT><BR>", 3, true);
		Reporter.log("<BR><FONT SIZE='4' COLOR='BLUE'>@AfterMethod Level 5</FONT><BR>", 5);
		Reporter.log("<BR><FONT SIZE='4' COLOR='BLUE'>@AfterMethod Level 7</FONT><BR>", 7, true);
		if (!isTestComplete) {
			Reporter.log("<BR><BR><FONT SIZE='4' COLOR='BLUE'>ALARM TEST FAIL</FONT><BR>", true);
			// Add Screenshot, etc.
		}
	}

	@BeforeClass
	public void beforeClass(ITestContext context) {
		logger.info("@BeforeClass for " + this.getClass().getName());
		//
		softAssert = new SoftAssert();
		//
		HashMap<String, String> allParameters = new HashMap<String, String>(
				context.getCurrentXmlTest().getAllParameters());
		for (String key : allParameters.keySet()) {
			System.out.println("*** parameter: key=" + key + " value=" + allParameters.get(key));
		}
		System.out.println("@BeforeClass");
	}

	//@AfterClass(alwaysRun = true)
	public void afterClass() {
		logger.info("@AfterClass for " + this.getClass().getName());
	}

	//@Test
	public void testApp2() {
		Reporter.log("<BR><FONT SIZE='4' COLOR='RED'>Non Conditional.</FONT><BR>", true);
		Reporter.log("<BR><FONT SIZE='4' COLOR='BLUE'>Level 3</FONT><BR>", 3, true);
		Reporter.log("<BR><FONT SIZE='4' COLOR='BLUE'>Level 5</FONT><BR>", 5);
		Reporter.log("<BR><FONT SIZE='4' COLOR='BLUE'>Level 7</FONT><BR>", 7, true);
		System.out.println("Running . . .");
		// ReporterWrapper.get().debug("DEBUG - messages");
		// ReporterWrapper.get().info("INFO - messages");
		// ReporterWrapper.get().warning("WARNING - messages");
		// ReporterWrapper.get().error("ERROR - messages");
		// Assert.assertTrue(false);
		Assert.assertTrue(true);
		isTestComplete = true;
	}

	@Test
	public void testApp3() {
		logger.info("\t+++testApp3() DONE");
		Reporter.log("\t+++testApp3() must be", true);
		Reporter.log("\t+++testApp3() 3", 3, true);
		Reporter.log("\t+++testApp3() 5", 5, true);
		Reporter.log("\t+++testApp3() 7", 7, true);
		Reporter.log("\t+++testApp3() 9", 9, true);
		//
		System.out.println("\t+++testApp3() Assert.assertEquals(1+2, 4) ...");
		// Assert.assertEquals(1 + 2, 4);
		// softAssert.assertEquals(1 + 2, 4);
		softAssert.assertEquals(1 + 2, 3);
		//softAssert.assertAll();
		//
		System.out.println("\t+++testApp3() Assert.assertEquals(2 + 2, 5) ...");
		// Assert.assertEquals(2 + 2, 5);
		// softAssert.assertEquals(2 + 2, 5);
		softAssert.assertEquals(2 + 2, 4);
		//
		System.out.println("\t+++testApp3() softAssert.assertAll() ...");
		softAssert.assertAll();
		//
		System.out.println("\t+++testApp3() DONE");
		isTestComplete = true;
	}

	// @Test(dependsOnMethods = { "testApp3" })
	public void testApp4() {
		System.out.println("\t***testApp4() softAssert.assertAll() ...");
		softAssert.assertAll();
		System.out.println("\t***testApp4() DONE");
		isTestComplete = true;
	}

	@Test
	public void testApp5() {
		System.out.println("\t***testApp5() Error Check ...");
		int i = 0;
		if (i > 0) {
			throw new RuntimeException("MY ERROR");
		}
		//Assert.assertEquals(1 + 2, 4);
		Assert.assertEquals(1 + 3, 4);
		isTestComplete = true;
	}

	//@AfterMethod
	public void tearDown2(ITestResult itr) {
		String methodName = itr.getMethod().getMethodName();
		boolean testPassed = itr.getStatus() == ITestResult.SUCCESS;
		String msg = "\n**********\n";
		//System.out.println("itr.getStatus() = " + itr.getStatus());
		//System.out.println("ITestResult.SUCCESS = " + ITestResult.SUCCESS);
		System.out.println("itr.getName() = "+itr.getName());
		System.out.println("itr.getMethod().getMethodName() = "
				+itr.getMethod().getMethodName());
		//
//		Object[] params = itr.getParameters();
//		System.out.println("params.length = " + params.length);
//		if (params.length > 0) {
//			String jsonFile = (String) params[0];
//			msg = msg + "Finished " + methodName + "(" + jsonFile + ") test - ";
//		}
		if (testPassed) {
			msg = msg + "passed.";
		} else {
			msg = msg + "FAILED: "+itr.getName();
			// If the test failed due to an exception, include the exception info in the log
			// message, unless it's a TestNG timeout exception
			Throwable t = itr.getThrowable();
			if (t != null) {
				msg = msg + "\n**" + t.toString();
				//
				// If it's not a thread timeout, include the stack trace too
				if (!(t instanceof ThreadTimeoutException)) {
					for (StackTraceElement e : t.getStackTrace()) {
						msg = msg + "\n**        at " + e.toString();
					}
				}
			}
		}
		System.out.println(msg);
		logger.error("@AfterMethod for:\n" + msg);
	}

}
