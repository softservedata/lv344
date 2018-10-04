package com.softserve.yeltsova.JavaStudyAutomation.demo2;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginFormCheckPost {

	@Test
	public void testPost() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://atqc-shop.epizy.com/");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("test-atqc-shop@ukr.net");
		driver.findElement(By.linkText("Forgotten Password")).click();
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("test-atqc-shop@ukr.net");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

	
		Assert.assertTrue(driver.findElement(By.cssSelector("div.alert.alert-success")).getText()
				.contains("An email with a confirmation link has been sent your email address."));
		
		
			// ukr.net
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.ukr.net/");
		//Switch To IFrame

        driver.switchTo().frame(driver.findElement(By.cssSelector("#login-frame-wraper > iframe")));
        driver.findElement(By.id("id-input-login")).click();
		driver.findElement(By.id("id-input-login")).clear();
		driver.findElement(By.id("id-input-login")).sendKeys("test-atqc-shop@ukr.net");
        
		
		
		driver.findElement(By.id("id-input-password")).click();
		driver.findElement(By.id("id-input-password")).clear();
		driver.findElement(By.id("id-input-password")).sendKeys("pridymaisam");

		driver.findElement(By.cssSelector("button.form__submit")).click();
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		// what to do?	
		driver.findElement(By.cssSelector("a.service__entry.service__entry_mail")).click();
		driver.findElement(By.linkText("Your Store")).click();
	   

	    driver.findElement(By.cssSelector("span.sidebar__list-link-name")).click();
	    driver.findElement(By.cssSelector("span.msglist__row-address-wrap")).click();

	    driver.findElement(By.linkText(
				"http://atqc-shop.epizy.com"))
				.click();
		driver.findElement(By.linkText(
				"http://atqc-shop.epizy.com"))
				.click();
		driver.findElement(By.linkText(
				"http://atqc-shop.epizy.com"))
				.click();

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("qwerty");
		driver.findElement(By.id("input-confirm")).click();
		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys("qwerty");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.alert.alert-success")).getText().contains
				("Success: Your password has been successfully updated."));
		
	

		//driver.findElement(By.id("command-button-pick")).click();// ??
		
		
	
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		
		driver.findElement(By.id("input-email")).click();
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("test-atqc-shop@ukr.net");
		Thread.sleep(500);
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("qwerty");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Logout")).click();
		
		// after m log out

		driver.quit();// a c
	}

}
