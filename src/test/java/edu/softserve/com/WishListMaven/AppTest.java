package edu.softserve.com.WishListMaven;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AppTest 
{
	private WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@AfterTest
	public void AfterTest() {
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://atqc-shop.epizy.com/");
		driver.findElement(By.id("wishlist-total")).click();

		driver.findElement(By.id("input-email")).click();
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("test@gmail.com");
		driver.findElement(By.id("input-password")).click();
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("qwerty");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

	}

	@AfterMethod
	public void afterMethod() {
		driver.findElement(By.xpath("//*[contains(@class, 'fa fa-user')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		//driver.quit();

	}

    @Test// Verify Remove Button from Wish List
	public void removebuttonfromwishlist() throws Exception {

		String countwishlist = driver.findElement(By.id("wishlist-total")).getAttribute("title");
		int countOld = Integer
				.parseInt(countwishlist.substring(countwishlist.indexOf("(") + 1, countwishlist.indexOf(")")));

		driver.findElement(By.xpath("//a[contains(@class, 'dropdown-toggle') and text() = 'Tablets']")).click();
		driver.findElement(By.xpath("//a[contains(@class, 'see-all') and text() = 'Show All Tablets']")).click();
		
		
		WebElement product = driver.findElement(By.xpath("//*[@class='product-thumb']")).findElement(By.xpath(".//img"));
		String productname;
		productname = product.getAttribute("title");
		
		//driver.findElement(By.xpath("//a[contains(text(),'"+productname+"')]/../../..//button[2]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'"+productname+"')]/../../..//button/..//i[contains(@class,'fa fa-heart')]")).click();

		WebElement message = driver.findElement(By.xpath("//*[@class= 'alert alert-success']"));
		String mes = "Success: You have added "+productname+" to your wish list!";
		Assert.assertTrue(message.getText().startsWith(mes));

//		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//	(new WebDriverWait(driver, 10)).until(
//			ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class= 'alert alert-success']")));
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Thread.sleep(500);

		driver.findElement(By.xpath("//*[@id='wishlist-total']")).click();
		
		countwishlist = driver.findElement(By.xpath("//*[@id='wishlist-total']")).getAttribute("title");
		int countNew = Integer
				.parseInt(countwishlist.substring(countwishlist.indexOf("(") + 1, countwishlist.indexOf(")")));
		int expected = countOld + 1;
		assertEquals(countNew, expected);

		//Thread.sleep(500);
		driver.findElement(By.xpath("//a[contains(text(),'"+productname+"')]/../following-sibling::td//a")).click();

		message = driver.findElement(By.xpath("//*[contains(@class, 'alert alert-success')]"));
		mes = "Success: You have modified your wish list!";

		Assert.assertTrue(message.getText().startsWith(mes));
		
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='wishlist-total']")));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.id("wishlist-total")).click();
		//Thread.sleep(500);

		countwishlist = driver.findElement(By.xpath("//*[@id='wishlist-total']")).getAttribute("title");
		countNew = Integer
				.parseInt(countwishlist.substring(countwishlist.indexOf("(") + 1, countwishlist.indexOf(")")));
		assertEquals(countNew, countOld);

	}

@Test // Verify Add to cart button from Wish List
	public void addcartbuttonfromwishlist() throws Exception {

		driver.findElement(By.xpath("//a[contains(@class, 'dropdown-toggle') and text() = 'Tablets']")).click();
		driver.findElement(By.xpath("//a[contains(@class, 'see-all') and text() = 'Show All Tablets']")).click();
		Thread.sleep(500);
	
		WebElement product = driver.findElement(By.xpath("//*[@class='product-thumb']")).findElement(By.xpath(".//img"));
		String productname;
		productname = product.getAttribute("title");
		
		driver.findElement(By.xpath("//a[contains(text(),'"+productname+"')]/../../..//button/..//i[contains(@class,'fa fa-heart')]")).click();

//		System.out.println("asdad");
//		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//		(new WebDriverWait(driver, 10)).until(
//				ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='wishlist-total']")));
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		System.out.println("asdad");
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id='wishlist-total']")).click();



		driver.findElement(By.xpath(
				"//a[contains(text(),'"+productname+"')]/../following-sibling::td//*[@class='fa fa-shopping-cart']"))
				.click();
		Thread.sleep(500);
		WebElement message = driver.findElement(By.xpath("//*[contains(@class, 'alert alert-success')]"));
		String mes = "Success: You have added "+productname+" to your shopping cart!";
		Assert.assertTrue(message.getText().startsWith(mes));
		
//		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//		(new WebDriverWait(driver, 10)).until(
//				ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title, 'Shopping Cart')]")));
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		

		driver.findElement(By.xpath("//a[contains(@title, 'Shopping Cart')]")).click();
		Thread.sleep(500);

		message = driver
				.findElement(By.xpath("//a[contains(text(),'"+productname+"')]/../following-sibling::td//input"));
		assertTrue(message.isDisplayed());
		driver.findElement(By.xpath("//a[contains(text(),'Amazon Fire Case')]/../following-sibling::td//button//i[@class='fa fa-times-circle']")).click();

		

		driver.findElement(By.id("wishlist-total")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//a[contains(text(),'"+productname+"')]/../following-sibling::td//a")).click();

	}

@Test // Verify AddToWishList button from main page of product
	public void addtowishlistbyproductpage() throws Exception {

		driver.findElement(By.xpath("//a[contains(@class, 'dropdown-toggle') and text() = 'Tablets']")).click();
		driver.findElement(By.xpath("//a[contains(@class, 'see-all') and text() = 'Show All Tablets']")).click();

		
		WebElement product = driver.findElement(By.xpath("//*[@class='product-thumb']")).findElement(By.xpath(".//img"));
		String productname;
		productname = product.getAttribute("title");
		
		driver.findElement(By.xpath("//*[contains(@class, 'product-thumb')]"))
				.findElement(By.xpath(".//a[contains(@href, *)]")).click();
		//Thread.sleep(500);
		driver.findElement(By.xpath("//*[@class='btn btn-default']//*[@class='fa fa-heart']")).click();
		//Thread.sleep(500);

		WebElement message = driver.findElement(By.xpath("//*[@class= 'alert alert-success']"));
		String mes = "Success: You have added "+productname+" to your wish list!";
		Assert.assertTrue(message.getText().startsWith(mes));

		driver.findElement(By.xpath("//*[@id='wishlist-total']")).click();
		//Thread.sleep(500);

		message = driver.findElement(By.xpath("//a[contains(text(),'"+productname+"')]/../following-sibling::td//a"));
		assertTrue(message.isDisplayed());
				

		//Thread.sleep(500);

		driver.findElement(By.xpath("//a[contains(text(),'"+productname+"')]/../following-sibling::td//a")).click();			

	}
	

}
