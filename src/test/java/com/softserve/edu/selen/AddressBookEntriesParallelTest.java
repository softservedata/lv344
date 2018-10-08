package com.softserve.edu.selen;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Class contains one test method verifies error messages appear when add
 * address with empty fields
 * 
 * @author Mykhailo Levchun
 */
public class AddressBookEntriesParallelTest {
	/**
	 * Data provider for test that runs parallel, @return xpath to error message
	 * and text error message contains
	 */
	@DataProvider(parallel = true)
	public Object[][] addressBookDataProvider() {
		return new Object[][] {
				{ "//div[text()='First Name must be between 1 and 32 characters!']",
						"First Name must be between 1 and 32 characters!" },
				{ "//div[text()='Last Name must be between 1 and 32 characters!']",
						"Last Name must be between 1 and 32 characters!" },
				{ "//div[text()='Address must be between 3 and 128 characters!']",
						"Address must be between 3 and 128 characters!" },
				{ "//div[text()='Address must be between 3 and 128 characters!']",
						"Address must be between 3 and 128 characters!" },
				{ "//div[text()='City must be between 2 and 128 characters!']",
						"City must be between 2 and 128 characters!" },
				{ "//div[text()='Please select a country!']", "Please select a country!" } };
	}

	/**
	 * Set location of web driver
	 */
	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				AddressBookEntriesParallelTest.class.getResource("/chromedriver-windows-32bit.exe").getPath());
	}

	/**
	 * Test method verifies error message is found by xpath @param
	 * pathToErrorMessage contains valid message @param message
	 */
	@Test(dataProvider = "addressBookDataProvider")
	public void confrimAddAddressWithEmptyFields(String pathToErrorMessage, String message) throws InterruptedException {

		// Set properties for web driver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Open OpenCart
		driver.get("http://atqc-shop.epizy.com/");

		// Login with valid credentials
		driver.findElement(By.cssSelector("a[title='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.findElement(By.id("input-email")).sendKeys("dc@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("2222");
		driver.findElement(By.cssSelector("input[value='Login']")).click();

		// Add new non-default address with empty fields
		driver.findElement(By.xpath("//a[text()='Address Book']")).click();
		driver.findElement(By.xpath("//a[text()='New Address']")).click();
		Select select = new Select(driver.findElement(By.id("input-country")));
		select.selectByVisibleText("--- Please Select ---");
		Thread.sleep(2000);
		select = new Select(driver.findElement(By.id("input-zone")));
		select.selectByVisibleText("--- Please Select ---");
		driver.findElement(By.cssSelector("input[value='Continue']")).click();

		// Verify that error messages appears
		Assert.assertTrue(driver.findElement(By.xpath(pathToErrorMessage)).getText().contains(message));

		// Log out from web page
		driver.findElement(By.cssSelector("a[title='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();

		// Close web driver
		driver.quit();
	}
}
