package com.softserve.edu;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginFormTestChangePasswordTest {
	// private// baga the same password

	@Test
	public void changePasswordTest() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromedriver.exe"); // b c
		WebDriver driver = new ChromeDriver();// модификатор доступа?//bef c

		// impl

		driver.manage().window().maximize();// b c
		driver.get("http://atqc-shop.epizy.com/");// b m

		// driver.findElement(By.linkText("My Account")).click();
		// driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.cssSelector("a[title='My Account']")).click();		
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.findElement(By.id("input-email")).click();
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("test-atqc-shop@ukr.net");
		Thread.sleep(500);
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("qwerty");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		driver.findElement(By.linkText("Change your password")).click();

		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("qwerty");
		Thread.sleep(500);
		driver.findElement(By.id("input-confirm")).click();
		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys("qwerty");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Thread.sleep(500);

		Assert.assertTrue(driver.findElement(By.cssSelector("div.alert.alert-success")).getText()
				.contains("Success: Your password has been successfully updated."));

		driver.findElement(By.linkText("My Account")).click();		
		driver.findElement(By.linkText("Logout")).click();
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("test-atqc-shop@ukr.net");
		Thread.sleep(500);
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("qwerty");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Thread.sleep(1000);

		// log out after m
		driver.findElement(By.linkText("My Account")).click();		
		driver.findElement(By.linkText("Logout")).click();
		
		driver.quit();// after class

	}
}