package com.softserve.edu.selen;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.softserve.edu.Parser;

/**
 * Class contains two test methods: add product to compare list and added
 * maximum amount of products to compare list
 * 
 * @author Mykhailo Levchun
 *
 */
public class ProductComparisonTest {
	/** Set reference driver to web driver */
	WebDriver driver;

	/**
	 * Initialize and set properties for web driver one time before all tests
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
	 * Close web driver after all tests ended
	 */
	@AfterClass
	public void testClose() {
		driver.quit();
	}

	/**
	 * Set started options for test: open necessary web page
	 */
	@BeforeMethod
	public void testSetUp() {
		driver.get("http://atqc-shop.epizy.com/");
	}

	/**
	 * Test method adds product to compare list, verifies product is added,
	 * deletes product from compare list, verifies product is deleted from
	 * compare list
	 */
	@Test(priority = 0)
	public void verifyAddProductToCompareList() {

		// Add Mac to compare list
		driver.findElement(By.xpath("//a[text()='Desktops']")).click();
		driver.findElement(By.xpath("//a[text()='Mac (1)']")).click();
		driver.findElement(By
				.xpath("//div[contains(@class,'product-layout')]//a[text()='iMac']/../../following-sibling::div/button[contains(@onclick,'compare')]"))
				.click();

		// Verify that Mac added to compare list
		Assert.assertTrue(driver.findElement(By.cssSelector("div.alert.alert-success")).getText()
				.contains("Success: You have added iMac to your product comparison!"));
		driver.findElement(By.className("close"));
		(new WebDriverWait(driver, 1))
				.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("alert alert-success")));
		int numberOfProduct = Parser.extractFirstNumber(driver.findElement(By.id("compare-total")).getText());
		Assert.assertEquals(numberOfProduct, 1);
		driver.findElement(By.id("compare-total")).click();

		// Delete Mac from compare list
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.tagName("footer")));
		driver.findElement(By.cssSelector("a.btn.btn-danger.btn-block")).click();

	}

	/**
	 * Test method adds four products to compare list, verifies products are
	 * added, adds fifth product to compare list, verifies amount of products is
	 * still four deletes all products from compare list, verifies products are
	 * deleted from compare list
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 1)
	public void verifyAddFiveProducts() throws InterruptedException {

		// Added four product to compare list
		driver.findElement(By.xpath("//a[text() = 'Components']")).click();
		driver.findElement(By.xpath("//a[text() = 'Scanners (4)']")).click();
		driver.findElement(By
				.xpath("//div[contains(@class,'product-layout')]//a[text()='Brother']/../../following-sibling::div/button[contains(@onclick,'compare')]"))
				.click();
		driver.findElement(By.className("close"));
		(new WebDriverWait(driver, 1))
				.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("alert alert-success")));
		driver.findElement(By
				.xpath("//div[contains(@class,'product-layout')]//a[text()='Canon Office Products']/../../following-sibling::div/button[contains(@onclick,'compare')]"))
				.click();
		driver.findElement(By.className("close"));
		(new WebDriverWait(driver, 1))
				.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("alert alert-success")));
		driver.findElement(By
				.xpath("//div[contains(@class,'product-layout')]//a[text()='Epson WorkForce']/../../following-sibling::div/button[contains(@onclick,'compare')]"))
				.click();
		driver.findElement(By.className("close"));
		(new WebDriverWait(driver, 1))
				.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("alert alert-success")));
		driver.findElement(By
				.xpath("//div[contains(@class,'product-layout')]//a[text()='Fujitsu ScanSnap']/../../following-sibling::div/button[contains(@onclick,'compare')]"))
				.click();
		driver.findElement(By.className("close"));
		(new WebDriverWait(driver, 1))
				.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("alert alert-success")));

		// Verify that four products added to compare list
		int numberOfProduct = Parser.extractFirstNumber(driver.findElement(By.id("compare-total")).getText());
		Assert.assertEquals(numberOfProduct, 4);
		driver.findElement(By.id("compare-total")).click();
		Thread.sleep(1000);

		// Added fifth product to compare list
		driver.findElement(By.xpath("//a[text()='Desktops']")).click();
		driver.findElement(By.xpath("//a[text()='Mac (1)']")).click();
		driver.findElement(By
				.xpath("//div[contains(@class,'product-layout')]//a[text()='iMac']/../../following-sibling::div/button[contains(@onclick,'compare')]"))
				.click();
		driver.findElement(By.className("close"));
		(new WebDriverWait(driver, 1))
				.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("alert alert-success")));

		// Verify that number of product in compare list is still four
		numberOfProduct = Parser.extractFirstNumber(driver.findElement(By.id("compare-total")).getText());
		Assert.assertNotEquals(numberOfProduct, 5);
		driver.findElement(By.id("compare-total")).click();

		// Deleted all product from compare list
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.tagName("footer")));
		List<WebElement> btns = driver.findElements(By.cssSelector("a.btn.btn-danger.btn-block"));
		while (btns.size() > 0) {
			btns.get(0).click();
			btns = driver.findElements(By.cssSelector("a.btn.btn-danger.btn-block"));
		}
	}

}
