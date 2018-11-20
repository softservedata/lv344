package com.softserve.edu.opencart.tools;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.softserve.edu.opencart.pages.AHeadComponent;
import com.softserve.edu.opencart.pages.HomePage;

public abstract class TestRunner {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	protected SoftAssert softAssert = new SoftAssert();
	protected WebDriver driver;
	protected final double PRECISION = 0.001;
	
	@BeforeClass
    public void beforeClass(ITestContext context) {
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
	    driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
		driver.get("http://taqc-opencart.epizy.com");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {  
//Log test status
    	if (result.isSuccess()) {
    		log.info("test " + result.getName() + " completed successfully");
    		System.out.println();
    	} else {
    		log.error("test " + result.getName() + " failed" 
    				+ "\n\t" + result.getThrowable().toString());
    		System.out.println();
    	}
    	logoutApplication();
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

			System.err.println("Cannot thread sleep!");

		}
    }

}
