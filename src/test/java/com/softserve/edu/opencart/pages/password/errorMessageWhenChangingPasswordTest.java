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

public class errorMessageWhenChangingPasswordTest {

	static final String EMAIL_ADDRESS = "test-atqc-shop@ukr.net";
	static final String PASSWORD = "qwerty";

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				errorMessageWhenChangingPasswordTest.class.getResource("/chromedriver-windows-32bit.exe").getPath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void logInto() {
		driver.get("http://atqc-shop.epizy.com/");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys(EMAIL_ADDRESS);
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys(PASSWORD);
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

	/**
	 * The test checks that error message is displayed when changing password
	 */

	@Test
	public void errorMessegeTest() throws Exception {

		driver.findElement(By.linkText("Change your password")).click();
		Thread.sleep(500);

		// too short
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("aaa");
		Thread.sleep(1000);
		driver.findElement(By.id("input-confirm")).click();
		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys("aaa");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Thread.sleep(500);

		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='password'] + div")).getText()
				.contains("Password must be between 4 and 20 characters!"));

		// too long
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("aaaaaaaaaaaaaaaaaaaaa");
		Thread.sleep(1000);
		driver.findElement(By.id("input-confirm")).click();
		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys("aaaaaaaaaaaaaaaaaaaaa");

		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		Thread.sleep(500);

		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='password'] + div")).getText()
				.contains("Password must be between 4 and 20 characters!"));

		// not equal
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("aaaa");

		Thread.sleep(1000);
		driver.findElement(By.id("input-confirm")).click();
		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys("aaaaa");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		Thread.sleep(500);

		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='confirm'] + div")).getText()
				.contains("Password confirmation does not match password!"));

		// first empty
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("");
		Thread.sleep(1000);
		driver.findElement(By.id("input-confirm")).click();
		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys("aaa");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		Thread.sleep(500);

		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='password'] + div")).getText()
				.contains("Password must be between 4 and 20 characters!"));
		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='confirm'] + div")).getText()
				.contains("Password confirmation does not match password!"));

		// second empty
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("aaa");
		Thread.sleep(1000);
		driver.findElement(By.id("input-confirm")).click();
		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys("");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		Thread.sleep(500);

		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='password'] + div")).getText()
				.contains("Password must be between 4 and 20 characters!"));
		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='confirm'] + div")).getText()
				.contains("Password confirmation does not match password!"));

		// both empty
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("");
		Thread.sleep(1000);
		driver.findElement(By.id("input-confirm")).click();
		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys("");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Thread.sleep(500);

		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='password'] + div")).getText()
				.contains("Password must be between 4 and 20 characters!"));

	}
}
