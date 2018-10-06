package com.softserve.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import UITests.RegexUtils;

public class Tests {
	WebDriver driver = null;
	String ststus1 = "Running>> ";
	private final String CART_BUTTON_ByXpath = "//div[contains(@class, 'product-layout')]//a[text()='%s']"
			+ "/../../following-sibling::div/button[contains(@onclick, 'cart')]";
	private final String ITEM_PRICE_ByXpath = "//div[contains(@class, 'product-layout')]//a[text()='%s']"
			+ "/../following-sibling::p[contains(@class, 'price')]";
	

	@BeforeMethod
	public void SetUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				Tests.class.getResource("/chromedriver-windows-32bit.exe").getPath());
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://atqc-shop.epizy.com");
	}

	@AfterMethod
	public void TearDown() throws Exception {
		driver.quit();
	}

	@Test(enabled = true)
	public void SmokeTestOpenCart() throws Exception {
		System.out.println("SmokeTestOpenCart start");
//Verify 1 text element
		//Thread.sleep(2000); // For demonstration
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='content']/h3")).getText(), "Featured");
		System.out.println(ststus1 + "Element on page was found...");
//Verify 1 button
		//Thread.sleep(2000); // For demonstration
		driver.manage().deleteAllCookies();
		Assert.assertEquals(driver.findElement(By.cssSelector("#cart-total")).getText(), "0 item(s) - $0.00");
		System.out.println(ststus1 + "Button on page was found...");
//Find and verify element at another page
		//Thread.sleep(2000); // For demonstration
		driver.findElement(By.linkText("Site Map")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='content']/h1")).getText(), "Site Map");
		System.out.println(ststus1 + "Element on enother page was found...");
//Message about all is great
		System.out.println("All is great, you can continue.");
	}

	@Test(dependsOnMethods = { "SmokeTestOpenCart" }, enabled = true)
	public void AddItemToCart() throws Exception {
		System.out.println("AddItemToCart start");
//Add McBook to cart
		driver.findElement(By.xpath(String.format(CART_BUTTON_ByXpath, "MacBook"))).click();
		System.out.println(ststus1 + "Good was added...");
//Check if user see correct message about adding good to cart
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success")).getText()
				.contains("Success: You have added MacBook to your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, good was added...");
		Thread.sleep(2000); // For demonstration
//Save current price this good
		double ItemPrice = RegexUtils.extractFirstDouble(driver.findElement(By.xpath(String.format(ITEM_PRICE_ByXpath, "MacBook"))).getText());
		System.out.println(ststus1 + "Price was find and saved...");
		Thread.sleep(2000); // For demonstration
//Back to main page
		driver.findElement(By.cssSelector("#logo")).click();
//Check if price on cart button is correct
		Assert.assertEquals(RegexUtils.extractFirstDouble(driver.findElement(By.cssSelector("#cart-total")).getText()), ItemPrice);
		System.out.println(ststus1 + "Goods price correct...");
		Thread.sleep(2000); // For demonstration
//Check if all sum is correct
		driver.findElement(By.cssSelector("#cart-total")).click();
		System.out.println(ststus1 + "Count display correct...");
		Assert.assertEquals(RegexUtils.extractFirstDouble(
				driver.findElement(By.xpath("//td/strong[text()='Total']/../../td[contains(text() , '$')]")).getText()), ItemPrice);// Total price
		System.out.println(ststus1 + "Price display correct...");
		Assert.assertEquals(RegexUtils.extractFirstDouble(
				driver.findElement(By.xpath("//td/strong[text()='Eco Tax (-2.00)']/../../td[contains(text() , '$')]")).getText()), 2.0);// eco tax		
		System.out.println(ststus1 + "Eco Tax display correct...");
		System.out.println("All data display correct.");
	}
	
	
	
	
	@Test(dependsOnMethods = { "SmokeTestOpenCart", "AddItemToCart" }, enabled = true)
	public void AddSingleItemToCart() throws Exception {
		System.out.println("AddSingleItemToCart start");
		Thread.sleep(4000); // For demonstration
//Check if cart is empty
		Assert.assertEquals(RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()), 0);
		System.out.println(ststus1 + "ACart is empty...");
//Add MacBook to cart
		driver.findElement(By.xpath(String.format(CART_BUTTON_ByXpath, "MacBook"))).click();
		System.out.println(ststus1 + "McBook was added to cart...");
		Thread.sleep(2000); // For demonstration
//Check if user see correct message about adding good to cart
		Assert.assertTrue(
				driver.findElement(By.cssSelector(".alert-success")).getText()
				.contains("Success: You have added MacBook to your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, good was added...");
		Thread.sleep(2000); // For demonstration
//Check if cart button contain correct data about MacBook price
		Assert.assertEquals(RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()), 1);
		Assert.assertEquals(RegexUtils.extractFirstDouble(
				driver.findElement(By.cssSelector("#cart-total")).getText()), 
				RegexUtils.extractFirstDouble(driver.findElement(By.xpath(String.format(ITEM_PRICE_ByXpath, "MacBook"))).getText()));
		System.out.println(ststus1 + "Cart contains correct data...");
		Thread.sleep(2000); // For demonstration
//Add iPhone to cart		
		driver.findElement(By.xpath(String.format(CART_BUTTON_ByXpath, "iPhone"))).click();
		System.out.println(ststus1 + "iPhone was added to cart(second item)...");
		Thread.sleep(2000); // For demonstration
// Check if user see correct message about adding good to cart
		Assert.assertTrue(
				driver.findElement(By.cssSelector(".alert-success")).getText()
						.contains("Success: You have added iPhone to your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, good was added(second item)...");
		Thread.sleep(2000); // For demonstration
//Check if cart button contain correct data
		Assert.assertEquals(RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()), 2);
		Assert.assertEquals(RegexUtils.extractFirstDouble(
				driver.findElement(By.cssSelector("#cart-total")).getText()), 
				RegexUtils.extractFirstDouble(driver.findElement(By.xpath(String.format(ITEM_PRICE_ByXpath, "MacBook"))).getText()) + 
				RegexUtils.extractFirstDouble(driver.findElement(By.xpath(String.format(ITEM_PRICE_ByXpath, "iPhone"))).getText()));
		driver.findElement(By.cssSelector("#cart")).click();
		System.out.println(ststus1 + "Cart contains correct data(MacBook + iPhone price)...");
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
	
	
	@Test(dependsOnMethods = { "SmokeTestOpenCart", "AddItemToCart" }, enabled = true)
	public void ChangeNumOfItemsInCart() throws Exception {
		System.out.println("ChangeNumOfItemsInCart start");
//Add MacBook to cart
		driver.findElement(By.linkText("MacBook")).click();
		driver.findElement(By.cssSelector("#button-cart")).click();
		System.out.println(ststus1 + "McBook was added to cart...");
		 Thread.sleep(2000); // For demonstration
// Check if user see correct message about adding good to cart
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
				.getText().contains("Success: You have added MacBook to your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, good was added...");
		Thread.sleep(2000); // For demonstration
//Check if cart button contain correct data
		Assert.assertEquals(driver.findElement(By.cssSelector("#cart")).getText(), "1 item(s) - $602.00");
//Open cart
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Shopping Cart")).click();
		System.out.println(ststus1 + "Shopping Cart page was opened...");
		Thread.sleep(2000); // For demonstration
//Edit quantity
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")).sendKeys("5");
		driver.findElement(By.cssSelector(".fa-refresh")).click();
//Check if user see correct message about modified cart
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
				.getText().contains("Success: You have modified your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, cart was modified...");
//Check if quantity in cart display correctly
		driver.findElement(By.cssSelector("#cart")).click();
		Assert.assertEquals(driver
				.findElement(By.cssSelector(
						"#cart > ul > li:nth-child(2) > div > table > tbody > tr:nth-child(2) > td:nth-child(2)")).getText(), "$10.00");// eco tax
		System.out.println(ststus1 + "Quantity in cart display correct...");
		System.out.println("Positiv quantity editing worc correctly");
	}
	@Test(dependsOnMethods = { "SmokeTestOpenCart" }, enabled = true)
	public void ErrorMessageChangeNumOfItemsInCart() throws Exception {
		System.out.println("ErrorMessageChangeNumOfItemsInCart start");
		//Add MacBook to cart
		driver.findElement(By.linkText("MacBook")).click();
		driver.findElement(By.cssSelector("#button-cart")).click();
		System.out.println(ststus1 + "McBook was added to cart...");
		 Thread.sleep(2000); // For demonstration
// Check if user see correct message about adding good to cart
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
				.getText().contains("Success: You have added MacBook to your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, good was added...");
		Thread.sleep(2000); // For demonstration
//Check if cart button contain correct data
		Assert.assertEquals(driver.findElement(By.cssSelector("#cart")).getText(), "1 item(s) - $602.00");
//Open cart
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Shopping Cart")).click();
		System.out.println(ststus1 + "Shopping Cart page was opened...");
		Thread.sleep(2000); // For demonstration
//Edit quantity
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")).sendKeys("test");
		Thread.sleep(2000); // For demonstration
//Check Error message
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
				.getText().contains("Warning: Input valid data!"));
		System.out.println("User see correct messageabout warning.");
	}
	@Test(dependsOnMethods = { "SmokeTestOpenCart", "AddItemToCart" }, enabled = true)
	public void CartAfterRelogin() throws Exception {
		System.out.println("CartAfterRelogin start");
//Open Login page
		driver.findElement(By.linkText("My Account")).click();
//Input logged data
		//Thread.sleep(2000); // For demonstration
		driver.findElement(By.id("input-email")).clear();	
		driver.findElement(By.id("input-email")).sendKeys("YuraStasiv@hotmail.com");
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("Q1w2e3r4" + Keys.ENTER);
		System.out.println(ststus1 + "Login succesful...");
		//Thread.sleep(2000); // For demonstration
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
		driver.findElement(By.linkText("MacBook")).click();
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
		Assert.assertTrue(driver.findElement(By.cssSelector("div > table > tbody > tr > td:nth-child(2) > a"))//need to edit<<<<<<
				.getText().contains("MacBook"));
		System.out.println(ststus1 + "Cart contain added good");
		Thread.sleep(2000); // For demonstration
//Final clear cart and logout
		driver.findElement(By.cssSelector("#cart")).click();
		driver.findElement(By.cssSelector(".fa-times")).click();
		System.out.println("Cart is empty...");
		//Thread.sleep(2000); // For demonstration
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logout succesful.");
		Thread.sleep(2000); // For demonstration
	}
}
