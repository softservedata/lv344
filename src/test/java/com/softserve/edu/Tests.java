package com.softserve.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.tools.RegexUtils;

/**
 * Checking the Cart Functionality
 * 
 * @author Yurii Stasiv
 */
public class Tests {
	WebDriver driver = null;
	String ststus1 = "Running>> ";
	private final String CART_BUTTON_ByXpath = "//div[contains(@class, 'product-layout')]//a[text()='%s']"
			+ "/../../following-sibling::div/button[contains(@onclick, 'cart')]";
	private final String ITEM_PRICE_ByXpath = "//div[contains(@class, 'product-layout')]//a[text()='%s']"
			+ "/../following-sibling::p[contains(@class, 'price')]";
	String testItem = "MacBook";
	String testItem2 = "iPhone";
	//========================================================================================================
	//				From TestRunnet
	//========================================================================================================
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
	//========================================================================================================
	
	/** 
	 * Test for further testing
	 */
	@Test(enabled = true)
	public void SmokeTestOpenCart() throws Exception {
		System.out.println("SmokeTestOpenCart start");
		Thread.sleep(2000); // For demonstration
//Verify 1 text element
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='content']/h3")).getText(), "Featured");
		System.out.println(ststus1 + "Element on main page was found...");
		Thread.sleep(1000); // For demonstration
//Verify 1 button
		driver.manage().deleteAllCookies();
		Assert.assertEquals(driver.findElement(By.cssSelector("#cart-total")).getText(), "0 item(s) - $0.00");
		System.out.println(ststus1 + "Button on page was found...");
		Thread.sleep(1000); // For demonstration
//Find and verify element at another page
		//Thread.sleep(2000); // For demonstration
		driver.findElement(By.linkText("Site Map")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='content']/h1")).getText(), "Site Map");
		System.out.println(ststus1 + "Element on enother page was found...");
		Thread.sleep(1000); // For demonstration
//Message about all is great
		System.out.println("All is great, you can continue.");
		Thread.sleep(2000); // For demonstration
	}
	
	/** 
	 * The test checks the addition of the product to the cart
	 */
	@Test(dependsOnMethods = { "SmokeTestOpenCart" }, enabled = true)
	public void AddItemToCart() throws Exception {
		System.out.println("AddItemToCart start");
		Thread.sleep(2000); // For demonstration
//Add McBook to cart
		driver.findElement(By.xpath(String.format(CART_BUTTON_ByXpath, testItem))).click();
		System.out.println(ststus1 + "Good was added...");
		Thread.sleep(1000); // For demonstration
//Check if user see correct message about adding good to cart
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success")).getText()
				.contains("Success: You have added "+ testItem +" to your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, good was added...");
		Thread.sleep(1000); // For demonstration
//Save current price this good
		double ItemPrice = RegexUtils.extractFirstDouble(driver.findElement(By.xpath(String.format(ITEM_PRICE_ByXpath, testItem))).getText());
		System.out.println(ststus1 + "Price was find and saved...");
		Thread.sleep(1000); // For demonstration
//Back to main page
		driver.findElement(By.cssSelector("#logo")).click();
//Check if price on cart button is correct
		Assert.assertEquals(RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()), 1);
		Assert.assertEquals(RegexUtils.extractFirstDouble(driver.findElement(By.cssSelector("#cart-total")).getText()), ItemPrice);
		System.out.println(ststus1 + "Count and price is correct...");
		Thread.sleep(1000); // For demonstration
//Check if all sum is correct
		driver.findElement(By.cssSelector("#cart-total")).click();
		System.out.println(ststus1 + "Count display correct...");
		Assert.assertEquals(RegexUtils.extractFirstDouble(
				driver.findElement(By.xpath("//td/strong[text()='Total']/../../td[contains(text() , '$')]")).getText()), ItemPrice);// Total price
		System.out.println(ststus1 + "Price display correct...");
//		Assert.assertEquals(RegexUtils.extractFirstDouble(
//				driver.findElement(By.xpath("//td/strong[text()='Eco Tax (-2.00)']/../../td[contains(text() , '$')]")).getText()), 2.0);// eco tax		
//		System.out.println(ststus1 + "Eco Tax display correct...");
		System.out.println("All data display correct.");
		Thread.sleep(2000); // For demonstration
	}
	
	/** 
	 * The test checks the addition of several items to the cart
	 */
	@Test(dependsOnMethods = { "SmokeTestOpenCart", "AddItemToCart" }, enabled = true)
	public void AddSingleItemToCart() throws Exception {
		System.out.println("AddSingleItemToCart start");
		Thread.sleep(4000); // For demonstration
//Check if cart is empty
		Assert.assertEquals(RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()), 0);
		System.out.println(ststus1 + "ACart is empty...");
//Add MacBook to cart
		driver.findElement(By.xpath(String.format(CART_BUTTON_ByXpath, testItem))).click();
		System.out.println(ststus1 + "McBook was added to cart...");
		Thread.sleep(2000); // For demonstration
//Check if user see correct message about adding good to cart
		Assert.assertTrue(
				driver.findElement(By.cssSelector(".alert-success")).getText()
				.contains("Success: You have added "+ testItem +" to your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, good was added...");
		Thread.sleep(2000); // For demonstration
//Check if cart button contain correct data about MacBook price
		Assert.assertEquals(RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()), 1);
		Assert.assertEquals(RegexUtils.extractFirstDouble(
				driver.findElement(By.cssSelector("#cart-total")).getText()), 
				RegexUtils.extractFirstDouble(driver.findElement(By.xpath(String.format(ITEM_PRICE_ByXpath, testItem))).getText()));
		System.out.println(ststus1 + "Cart contains correct data...");
		Thread.sleep(2000); // For demonstration
//Add iPhone to cart		
		driver.findElement(By.xpath(String.format(CART_BUTTON_ByXpath, testItem2))).click();
		System.out.println(ststus1 + testItem2 + " was added to cart(second item)...");
		Thread.sleep(2000); // For demonstration
// Check if user see correct message about adding good to cart
		Assert.assertTrue(
				driver.findElement(By.cssSelector(".alert-success")).getText()
						.contains("Success: You have added " + testItem2 + " to your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, good was added(second item)...");
		Thread.sleep(2000); // For demonstration
//Check if cart button contain correct data
		Assert.assertEquals(RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()), 2);
		Assert.assertEquals(RegexUtils.extractFirstDouble(
				driver.findElement(By.cssSelector("#cart-total")).getText()), 
				RegexUtils.extractFirstDouble(driver.findElement(By.xpath(String.format(ITEM_PRICE_ByXpath, testItem))).getText()) + 
				RegexUtils.extractFirstDouble(driver.findElement(By.xpath(String.format(ITEM_PRICE_ByXpath, testItem2))).getText()));
		driver.findElement(By.cssSelector("#cart")).click();
		System.out.println(ststus1 + "Cart contains correct data(" + testItem + " + " + testItem2 + " price)...");
		Thread.sleep(2000); // For demonstration
//Wait when web element will be visible
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("View Cart")));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//Check if the tax is counted correctly
		Assert.assertEquals(RegexUtils.extractFirstDouble(
				driver.findElement(By.xpath("//td/strong[text()='Eco Tax (-2.00)']/../../td[contains(text() , '$')]")).getText()), 4.0);// eco tax		
		System.out.println(ststus1 + "Eco Tax display correct...");
		System.out.println("All data display correct.");	
		Thread.sleep(4000); // For demonstration	
	}	
	
		/** 
		 * The test checks the change in the number of items in the cart
		 */
	@Test(dependsOnMethods = { "SmokeTestOpenCart", "AddItemToCart" }, enabled = true)
	public void ChangeNumOfItemsInCart() throws Exception {
		System.out.println("ChangeNumOfItemsInCart start");
		Thread.sleep(4000); // For demonstration
//Add McBook to cart
		driver.findElement(By.xpath(String.format(CART_BUTTON_ByXpath, testItem))).click();
		System.out.println(ststus1 + "Good was added...");
		Thread.sleep(2000); // For demonstration
//Check if user see correct message about adding good to cart
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success")).getText()
				.contains("Success: You have added " + testItem +" to your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, good was added...");
		Thread.sleep(2000); // For demonstration
//Check if cart button contain correct data
		Assert.assertEquals(RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()), 1);
		System.out.println(ststus1 + "One good was added to cart ...");
		Thread.sleep(2000); // For demonstration
//Open cart
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Shopping Cart")).click();
		System.out.println(ststus1 + "Shopping Cart page was opened...");
		Thread.sleep(2000); // For demonstration
//Edit quantity
		driver.findElement(By.xpath("//div[contains(@class, 'input')]//input[contains(@name, 'quantity')]")).clear();
		driver.findElement(By.xpath("//div[contains(@class, 'input')]//input[contains(@name, 'quantity')]")).sendKeys("5");
		driver.findElement(By.cssSelector(".fa-refresh")).click();
//Check if user see correct message about modified cart
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
				.getText().contains("Success: You have modified your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, cart was modified...");
		Thread.sleep(2000); // For demonstration
//Check if quantity in cart display correctly
		driver.findElement(By.cssSelector("#cart")).click();
		Assert.assertEquals(RegexUtils.extractFirstDouble(
				driver.findElement(By.xpath("//td/strong[text()='Eco Tax (-2.00)']/../../td[contains(text() , '$')]")).getText()), 10.0);// eco tax
		System.out.println(ststus1 + "Quantity in cart display correct...");
		System.out.println("Positiv quantity editing work correctly");
		Thread.sleep(4000); // For demonstration
	}
	
	/**
	 * The test checks the error message when trying to enter non-valid data
	 */
	@Test(dependsOnMethods = { "SmokeTestOpenCart" }, enabled = true)
	public void ErrorMessageChangeNumOfItemsInCart() throws Exception {
		System.out.println("ErrorMessageChangeNumOfItemsInCart start");
		Thread.sleep(4000); // For demonstration
//Add McBook to cart
		driver.findElement(By.xpath(String.format(CART_BUTTON_ByXpath, testItem))).click();
		System.out.println(ststus1 + "Good was added...");
		Thread.sleep(2000); // For demonstration
//Check if user see correct message about adding good to cart
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success")).getText()
				.contains("Success: You have added " + testItem + " to your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, good was added...");
		Thread.sleep(2000); // For demonstration
//Check if cart button contain correct data
		Assert.assertEquals(RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()), 1);
		System.out.println(ststus1 + "One good was added to cart ...");
		Thread.sleep(2000); // For demonstration
//Open cart
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Shopping Cart")).click();
		System.out.println(ststus1 + "Shopping Cart page was opened...");
		Thread.sleep(2000); // For demonstration
//Edit quantity
		driver.findElement(By.xpath("//div[contains(@class, 'input')]//input[contains(@name, 'quantity')]")).clear();
		driver.findElement(By.xpath("//div[contains(@class, 'input')]//input[contains(@name, 'quantity')]")).sendKeys("test");
		Thread.sleep(2000); // For demonstration
//Check Error message
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
				.getText().contains("Warning: Input valid data!"));
		System.out.println("User see correct messageabout warning.");
		Thread.sleep(4000); // For demonstration	
	}
	
	/**
	 * The test checks if the product is stored after re-entering
	 */
	@Test(dependsOnMethods = { "SmokeTestOpenCart", "AddItemToCart" }, enabled = true)
	public void CartAfterRelogin() throws Exception {
		System.out.println("CartAfterRelogin start");
		Thread.sleep(4000); // For demonstration
//Open Login page
		driver.findElement(By.linkText("My Account")).click();
//Input logged data
		//Thread.sleep(2000); // For demonstration
//		driver.findElement(By.id("input-email")).clear();	
//		driver.findElement(By.id("input-email")).sendKeys("YuraStasiv@hotmail.com");
//		driver.findElement(By.id("input-password")).clear();
//		driver.findElement(By.id("input-password")).sendKeys("Q1w2e3r4" + Keys.ENTER);
//		System.out.println(ststus1 + "Login succesful...");
		Thread.sleep(2000); // For demonstration
//Clear cart if we have one or more goods there
		if(RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()) != 0) {
			do{
				driver.findElement(By.cssSelector("#cart")).click();
				driver.findElement(By.cssSelector(".fa-times")).click();
			}while(RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()) != 0);
		}
		System.out.println(ststus1 + "Cart is clear...");
//Back to main page
		driver.findElement(By.cssSelector("#logo")).click();	
//Add MacBook to cart
		driver.findElement(By.linkText(testItem)).click();
		driver.findElement(By.cssSelector("#button-cart")).click();
//Wait when web element will be visible
		driver.findElement(By.cssSelector("#cart")).click();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("View Cart")));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#cart")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#cart")).getText(), "1 item(s) - $500.00");
		System.out.println(ststus1 + "McBook was added to cart...");
		Thread.sleep(2000); // For demonstration
//Log out and sign in again
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Logout")).click();
		System.out.println(ststus1 + "Logout succesful...");
		Thread.sleep(2000); // For demonstration
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.id("input-email")).clear();	
		driver.findElement(By.id("input-email")).sendKeys("YuraStasiv@hotmail.com");
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("Q1w2e3r4" + Keys.ENTER);
		System.out.println(ststus1 + "Login succesful...");
		Thread.sleep(2000); // For demonstration
//Check if cart contain added good
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Shopping Cart")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'table')]//a[text()='"+ testItem +"']"))
				.getText().contains(testItem));
		System.out.println(ststus1 + "Cart contain added good");
		Thread.sleep(2000); // For demonstration
//Final clear cart and logout
//		driver.findElement(By.cssSelector("#cart")).click();
//		driver.findElement(By.cssSelector(".fa-times")).click();
//		System.out.println("Cart is empty...");
//		Thread.sleep(2000); // For demonstration
//		driver.findElement(By.linkText("My Account")).click();
//		driver.findElement(By.linkText("Logout")).click();
//		System.out.println("Logout succesful.");
		Thread.sleep(4000); // For demonstration
	}
}