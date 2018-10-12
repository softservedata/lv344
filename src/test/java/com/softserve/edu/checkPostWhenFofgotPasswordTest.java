package com.softserve.edu;

//#msg15390981934138804291 > td:nth-child(3) > a:nth-child(1) > span:nth-child(1) > span:nth-child(1)// #msg15390909830923303040 > td:nth-child(2) > span:nth-child(1)
//.readmsg__body > pre:nth-child(1) > a:nth-child(1)
//.confirm__content-warn > a:nth-child(1)

import java.util.List;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class checkPostWhenFofgotPasswordTest {

	static final String EMAIL_ADDRESS = "test-atqc-shop@ukr.net";
	static final String PASSWORD = "qwerty";

	private WebDriver driver;

	@BeforeClass
	public void setUp() {

		System.setProperty("webdriver.chrome.driver",
				checkPostWhenFofgotPasswordTest.class.getResource("/chromedriver-windows-32bit.exe").getPath());
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

		driver.findElement(By.linkText("Forgotten Password")).click();

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
	 * Test check or a letter was sent with a link to update the password at the
	 * specified email address
	 * 
	 * @throws FindFailed
	 */

	@Test
	public void checkPostTest() throws FindFailed {
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).click();
		driver.findElement(By.id("input-email")).sendKeys(EMAIL_ADDRESS);

		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		Assert.assertTrue(driver.findElement(By.cssSelector("div.alert.alert-success")).getText()
				.contains("An email with a confirmation link has been sent your email address."));

		// go to the post (ukr.net)

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.ukr.net/");

		// switch To Iframe for log into post

		driver.switchTo().frame(driver.findElement(By.cssSelector("#login-frame-wraper > iframe")));
		driver.findElement(By.id("id-input-login")).click();
		driver.findElement(By.id("id-input-login")).clear();
		driver.findElement(By.id("id-input-login")).sendKeys(EMAIL_ADDRESS);

		driver.findElement(By.id("id-input-password")).click();
		driver.findElement(By.id("id-input-password")).clear();
		driver.findElement(By.id("id-input-password")).sendKeys("pridymaisam");

		driver.findElement(By.cssSelector("button.form__submit")).click();

		driver.findElement(By.cssSelector("a.service__entry.service__entry_mail")).click();

		// use sikuli to check the last incoming letter

//		Screen sc = new Screen();
//		sc.wait("src//test//resources//yourStore.png");
//
//		sc.click("src//test//resources//yourStore.png");
//
//		sc.click("src//test//resources//linkFirst.png");
//		sc.click("src//test//resources//linkTwo.png");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("td.msglist__row-unread.noselect.unseen")).click();

		driver.findElement(By.cssSelector(
				"#readmsg > div.screen__content > section > div.readmsg__content > div.readmsg__body > pre > a"))
				.click();
		driver.findElement(By.cssSelector(
				"#readmsg > div.screen__content > section > div.readmsg__content > div.readmsg__body > pre > a"))
				.click();

		/*
		 * the method looks for requested id of among the open pages to log out from
		 * post
		 */
		for (String handle : driver.getWindowHandles()) {

			driver.switchTo().window(handle);
			List<WebElement> currentList = driver.findElements(By.cssSelector(".login-button__user"));

			if (currentList.size() > 0) {

				driver.findElement(By.cssSelector(".login-button__user")).click();

				driver.findElement(By.id("login__logout")).click();

				break;
			}
		}

		/*
		 * the method looks for requested id of among the open pages to return to the
		 * page to enter new credentials
		 */
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);

			List<WebElement> currentList = driver.findElements(By.id("input-password"));
			if (currentList.size() > 0) {
				break;
			}
		}

		// enter new credentials

		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys(PASSWORD);

		driver.findElement(By.id("input-confirm")).click();
		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys(PASSWORD);

		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		Assert.assertTrue(driver.findElement(By.cssSelector("div.alert.alert-success")).getText()
				.contains("Success: Your password has been successfully updated."));

		// check new credentials

		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("input-email")).click();
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys(EMAIL_ADDRESS);

		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys(PASSWORD);

		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

	}
}
