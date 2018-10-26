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

import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.ukr.net.pages.LoginUkrNetPage;

public class TestRunnerUkrNet extends TestRunner {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	protected SoftAssert softAssert = new SoftAssert();
	protected WebDriver driver;
	protected final double PRECISION = 0.001;

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("@BeforeMethod");
		driver.get("https://www.ukr.net/");
	}

	
//
//	@Override
//	protected LoginUkrNetPage loadApplication() {
//		return new LoginUkrNetPage(driver);
//	}

	protected void delayExecution(long miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {

			System.err.println("Cannot thread sleep!");

		}
	}

}