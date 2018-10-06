package com.softserve.edu.selen;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Class contains one test method that verifies possibility add and delete
 * non-default address to address book
 * 
 * @author Mykhailo Levchun
 */
public class AddressBookEntriesTest {
	/** Set reference to web driver */
	private WebDriver driver;

	/**
	 * Initialize and set properties for web driver
	 */
	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	/**
	 * Set started options for test: open necessary web page, login with valid
	 * credentials
	 */
	@BeforeMethod
	public void testSetUp() {
		driver.get("http://atqc-shop.epizy.com/");
		driver.findElement(By.cssSelector("a[title='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.findElement(By.id("input-email")).sendKeys("dc@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("2222");
		driver.findElement(By.cssSelector("input[value='Login']")).click();
	}

	/**
	 * Log out from web page after test
	 */
	@AfterMethod
	public void testSignOut() {
		driver.findElement(By.cssSelector("a[title='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}

	/**
	 * Close web driver after all tests ended
	 */
	@AfterClass
	public void testClose() {
		driver.quit();
	}

	/**
	 * Test method add new address to address book with valid data, verifies
	 * address is added, deletes address, verifies address is deleted
	 */
	@Test
	public void verifyAddDeleteAddress() {

		// Open new address link
		driver.findElement(By.xpath("//a[text()='Address Book']")).click();
		driver.findElement(By.xpath("//a[text()='New Address']")).click();

		// Add new address with valid data
		driver.findElement(By.id("input-firstname")).sendKeys("Kieran");
		driver.findElement(By.id("input-lastname")).sendKeys("Lee");
		driver.findElement(By.id("input-address-1")).sendKeys("Wall Streer, 21");
		driver.findElement(By.id("input-city")).sendKeys("Los Angeles");
		driver.findElement(By.id("input-postcode")).sendKeys("1001");
		Select select = new Select(driver.findElement(By.id("input-country")));
		select.selectByVisibleText("United States");
		select = new Select(driver.findElement(By.id("input-zone")));
		select.selectByVisibleText("California");
		driver.findElement(By.cssSelector("input[value='Continue']")).click();

		// Verify that address is added
		Assert.assertEquals(driver.findElement(By.cssSelector("div.alert.alert-success")).getText(),
				"Your address has been successfully inserted");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//a[text()='New Address']")));

		// Delete address
		driver.findElement(By.xpath("(//a[text()='Delete'])[last()]")).click();

		// Verify that address is deleted
		Assert.assertEquals(driver.findElement(By.cssSelector("div.alert.alert-success")).getText(),
				"Your address has been successfully deleted");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//a[text()='New Address']")));

	}
}
