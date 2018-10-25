package com.softserve.edu.opencart.tools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.softserve.edu.opencart.pages.AHeadComponent;
import com.softserve.edu.opencart.pages.HomePage;

public abstract class TestRunner {
	//protected static final Logger logger = LoggerFactory.getLogger(TestRunner.class);
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected WebDriver driver;
	protected final double PRECISION = 0.001;
	//protected boolean isTestSuccess;
	
	@BeforeClass
    public void beforeClass(ITestContext context) {
        System.out.println("@BeforeClass");
		System.out.println("PATH to Driver: " +
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("@AfterClass");
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
    	//isTestSuccess = false;
        System.out.println("@BeforeMethod");
		driver.get("http://atqc-shop.epizy.com/");
    }

    @AfterMethod//(alwaysRun = true)
    public void afterMethod(ITestResult result) {
    	//if (isTestSuccess) {
    	if (result.isSuccess()) {
    		logger.info("test " + result.getName() + " done");
    	} else {
    		logger.error("test " + result.getName() + " failed" 
    				+ "\n\t" + result.getThrowable().toString());
    	}
    	logoutApplication();
    	delayExecution(2000);
        System.out.println("@AfterMethod");
    }

    protected HomePage loadApplication() {
        return new HomePage(driver);
    }

    protected HomePage logoutApplication() {
        //return new HeadComponent(driver).gotoHomeWithLogout();
    	return (new AHeadComponent(driver){}).gotoHomeWithLogout();
    }
    
    protected void delayExecution(long miliseconds) {
        try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
