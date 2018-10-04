package com.softserve.edu;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestSearch {
	private WebDriver driver;
	
	@DataProvider(name = "request=mac")
	   public static Object[][] resultListMac() {
		  ArrayList<String> list = new ArrayList<String>();
		  list.add("MacBook Pro");
		  list.add("iMac");
		  list.add("MacBook Air");
		  list.add("MacBook");
		  Collections.sort(list);
	      return new Object[][] {{"mac", list}};
	   }
	
	
	@DataProvider(name = "request=apple")
	   public static Object[][] resultListApple() {
		ArrayList<String> list = new ArrayList<String>();
		  list.add("Apple Cinema 30\"");
		  list.add("Apple iPad 2");
		  list.add("Apple iPad 3");
		  list.add("Apple iPad 4");
		  list.add("Apple iPad Air");
		  list.add("Apple iPhone SE 64GB");
		  list.add("Apple Magic Mouse");
		  Collections.sort(list);
	      return new Object[][] {{"apple", list}};
	   }
	
	
	@DataProvider(name = "request=hp")
	   public static Object[][] resultListHP() {
		List<String> list = new ArrayList<>();
		  list.add("HP 15-ay168sa");
		  list.add("HP Pavilion x360");
		  list.add("HP 250 G5");
		  list.add("HP LaserJet Pro");
		  list.add("HP Sprocket");	
		  list.add("HP LP3065");
		  list.add("HP x3000 Wireless Mouse");
	      return new Object[][] {{"hp", list}};
	   }
	
	
	@BeforeTest
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Andrii\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		//chromeOptions.addArguments("--headless");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	
	@AfterTest
	public void logoutAndClose() throws InterruptedException{
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("[title='My Account']")).click();					
		driver.findElement(By.cssSelector("[href*='/logout']")).click();
		driver.close();
		driver = null;
	}
	
	
  	@Test(dataProvider = "request=mac")
	public void testSearchWithEnter(String request, ArrayList<String> resultList) throws NoSuchElementException,InterruptedException {
		driver.get("http://atqc-shop.epizy.com/");
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).click();//find 'search' text field
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).clear();
		Thread.sleep(1000);
		//type search request and press 'Enter' key
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).sendKeys(request+Keys.ENTER);
		Thread.sleep(1000);
		List<String> actualResults = getActualResultList();
		Collections.sort(actualResults);
		Assert.assertEquals(resultList, actualResults);	
		}
	
  	
	@Test(dataProvider = "request=hp")
		public void testResultWithFilters(String request, ArrayList<String> resultList) throws NoSuchElementException,InterruptedException {
			driver.get("http://atqc-shop.epizy.com/");
			driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).click();
			driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).clear();
			driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).sendKeys(" ");
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".btn.btn-default.btn-lg[type='button']")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("input-search")).click();
			driver.findElement(By.id("input-search")).clear();
			driver.findElement(By.id("input-search")).sendKeys(request);
			Thread.sleep(1000);
			driver.findElement(By.id("description")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("button-search")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".fa.fa-th-list")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("input-sort")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("[value*='price&order=DESC']")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("input-limit")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("[selected='selected']")).click();
			Thread.sleep(1000);
			Assert.assertEquals(resultList, getActualResultList());	
			
		}
	
	
		@Test(dataProvider = "request=mac")
		public void testTopAndMainForms(String request, ArrayList<String> resultList) throws NoSuchElementException,InterruptedException {
			driver.get("http://atqc-shop.epizy.com/");
			driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).click();
			driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).clear();
			driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).sendKeys("mac");
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".btn.btn-default.btn-lg[type='button']")).click();
			Thread.sleep(1000);
			List<String> actualResults = getActualResultList();
			Collections.sort(actualResults);
			Assert.assertEquals(resultList, actualResults);							
			driver.findElement(By.id("input-search")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("input-search")).clear();
			driver.findElement(By.id("input-search")).sendKeys("mac");;
			driver.findElement(By.id("button-search")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".btn.btn-default.btn-lg[type='button']")).click();
			Thread.sleep(1000);
			actualResults = getActualResultList();
			Collections.sort(actualResults);
			Assert.assertEquals(resultList, actualResults);				}
		
	
	@Test(dataProvider = "request=apple")
	public void testLogedInAndGuest(String request, ArrayList<String> resultList) throws NoSuchElementException,InterruptedException {
		driver.get("http://atqc-shop.epizy.com/");
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).click();	
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).clear();
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).sendKeys(request);																		
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".btn.btn-default.btn-lg[type='button']")).click();
		Thread.sleep(1000);
		List<String> actualResults = getActualResultList();
		Collections.sort(actualResults);
		Assert.assertEquals(resultList, actualResults);	
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("[title='My Account']")).click();					
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("[href*='/login']")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("email")).click();				
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("search@test.mail");		
		Thread.sleep(1000);
		driver.findElement(By.name("password")).click();									
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("search"+ Keys.ENTER);			
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).click();
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).clear();
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']"))
		.sendKeys(request);																		
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".btn.btn-default.btn-lg[type='button']")).click();
		Thread.sleep(1000);
		actualResults = getActualResultList();
		Collections.sort(actualResults);
		Assert.assertEquals(resultList, actualResults);		}
  	
	/**
	 * 
	 * @param items - list of items to check presence
	 * @return true if all items from list are present on the page
	 */
	public List<String> getActualResultList() {
		List<WebElement> elementsList = driver.findElements(By.xpath("//*[@class='caption']//following::h4"));
		List<String> itemNames = new ArrayList<>();
		System.out.println();
		for (WebElement webElement : elementsList) {
			itemNames.add(webElement.getText());
			System.out.println(webElement.getText());
		}
		return itemNames;
			
	}
}
