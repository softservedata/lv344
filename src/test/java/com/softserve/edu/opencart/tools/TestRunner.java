package com.softserve.edu.opencart.tools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public abstract class TestRunner {
	protected WebDriver driver;

	@BeforeClass
	public void beforeClass(ITestContext context) {
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://atqc-shop.epizy.com/");
		driver.findElement(By.xpath("//*[contains(@class, 'fa fa-user')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
		driver.findElement(By.id("input-email")).clear();	
		driver.findElement(By.id("input-email")).sendKeys("YuraStasiv@hotmail.com");
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("Q1w2e3r4" + Keys.ENTER);
		System.out.println("Login succesful");
		driver.findElement(By.cssSelector("#logo")).click();
	}

	@AfterMethod // (alwaysRun = true)
	public void afterMethod(ITestResult result) throws InterruptedException {
		if (RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()) != 0) {
			driver.findElement(By.name("search")).click();
			driver.findElement(By.cssSelector("#cart")).click();
			driver.findElement(By.cssSelector(".fa-times")).click();
			System.out.println("Cart is empty");
			
			driver.findElement(By.xpath("//*[contains(@class, 'fa fa-user')]")).click();
			driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
			System.out.println("User Logout");
		}
	}
	
//	protected HomePage loadApplication() {
//		return new HomePage(driver);
//	}

//	protected void delayExecution(long miliseconds) {
//		try {
//			Thread.sleep(miliseconds);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}