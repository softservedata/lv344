package com.softserve.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class checkChangePasswordTest {

	// This method of ad-hoc testing does not meet the requirements.
 
	static final String EMAIL_ADDRESS = "test-atqc-shop@ukr.net";
	static final String PASSWORD = "qwerty"; 
	
	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				checkChangePasswordTest.class.getResource("/chromedriver-windows-32bit.exe").getPath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void logInto() throws Exception {
		driver.get("http://atqc-shop.epizy.com/");

		// driver.findElement(By.cssSelector("a[title='My Account']")).click();
		// driver.findElement(By.xpath("//a[text()='Login']")).click();

		driver.findElement(By.linkText("My Account")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(500);
		driver.findElement(By.id("input-email")).click();
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys(EMAIL_ADDRESS);
		Thread.sleep(500);
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys(PASSWORD);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	}

	@AfterMethod
	public void logOut() {
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Logout")).click();
	}

	@AfterClass
	public void closeSession() {
		driver.quit();
	}

	// The test checks if it is possible to change the password.
	@Test
	public void changePasswordTest() throws Exception {

		driver.findElement(By.linkText("Change your password")).click();

		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys(PASSWORD);
		Thread.sleep(500);
		driver.findElement(By.id("input-confirm")).click();
		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys(PASSWORD);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Thread.sleep(500);

		Assert.assertTrue(driver.findElement(By.cssSelector("div.alert.alert-success")).getText()
				.contains("Success: Your password has been successfully updated."));

		driver.findElement(By.linkText("My Account")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("My Account")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(500);
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys(EMAIL_ADDRESS);
		Thread.sleep(500);
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys(PASSWORD);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Thread.sleep(1000);

	}
}