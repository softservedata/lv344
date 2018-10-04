package com.softserve.yeltsova.JavaStudyAutomation.demo2;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginFormErrorMessegeTest {

	@Test
	public void errorMessegeTest() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();// модификатор?

		driver.manage().window().maximize();
		driver.get("http://atqc-shop.epizy.com/");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("test-atqc-shop@ukr.net");
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("qwerty");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		driver.findElement(By.linkText("Change your password")).click();
		Thread.sleep(500);

		driver.findElement(By.id("input-password")).click(); // too short
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

		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("aaaaaaaaaaaaaaaaaaaaa");// too long
		Thread.sleep(1000);
		driver.findElement(By.id("input-confirm")).click();
		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys("aaaaaaaaaaaaaaaaaaaaa");

		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		Thread.sleep(500);

		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='password'] + div")).getText()
				.contains("Password must be between 4 and 20 characters!"));

		driver.findElement(By.id("input-password")).click(); //not equal
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

		driver.findElement(By.id("input-password")).click();// first empty
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
		
		driver.findElement(By.id("input-password")).click();// second empty
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

		driver.findElement(By.id("input-password")).click(); // both empty
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

		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Logout")).click();

		// логаут

		driver.quit();

	}
}
