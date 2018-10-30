package com.softserve.edu.opencart.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.softserve.edu.opencart.data.ApplicationSourceRepository;

public class ApplicationTestRunner {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @BeforeClass
    public void beforeClass(ITestContext context) {
        log.info("@BeforeClass start");
        Application.get(ApplicationSourceRepository.EpizyChrome());
        log.info("@BeforeClass done");
    }

    
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        log.info("@AfterClass start");
        Application.remove();
        log.info("@AfterClass done");
    }

    @BeforeMethod
    public void beforeMethod() {
        log.info("@BeforeMethod start, ThreadId = " + Thread.currentThread().getId());
        Application.get().loadApplication();
        log.info("@BeforeMethod done");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResult) {
        log.info("@AfterMethod start, ThreadId = " + Thread.currentThread().getId());
        //Reporter.setCurrentTestResult(result);
        log.info("@AfterMethod done" + TestResultUtils.testResultMessage(testResult));
    }

    // TODO Remove method. For Demo Only
    protected void delayExecution(long miliseconds) {
        try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
