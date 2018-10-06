package com.softserve.edu;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
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
	static final String EMAIL_ADDRESS = "search@test.mail";
	static final String PASSWORD = "search";
	
	private WebDriver driver;
	
	@DataProvider(name = "request=mac")
	   public static Object[][] resultListMac() {
		  ArrayList<String> list = new ArrayList<String>();
		  list.add("MacBook Pro");
		  list.add("iMac");
		  list.add("MacBook Air");
		  list.add("MacBook");
		  //sort the list in order to right comparison
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
		  //do not sort list to check exact matching
	      return new Object[][] {{"hp", list}};
	   }
	
	
	@BeforeTest
	public void createDriver() {
		//setting path to chromedriver.exe
		System.setProperty("webdriver.chrome.driver",
				TestSearch.class.getResource("/chromedriver-windows-32bit.exe").getPath());
        //        "C:\\Users\\Andrii\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		//chromeOptions.addArguments("--headless");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//set implicitly wait
	}
	
	
	@AfterTest
	public void logoutAndClose() throws InterruptedException{
		Thread.sleep(1000);//only for demonstration
		//search and click 'My account' drop-down list by css selector
		driver.findElement(By.cssSelector("[title='My Account']")).click();					
		driver.findElement(By.cssSelector("[href*='/logout']")).click();
		//closing browser window
		driver.close();
	}
	
	
  	@Test(dataProvider = "request=mac")
	public void testSearchWithEnter(String request, ArrayList<String> expectedResultList) 
			throws NoSuchElementException,InterruptedException {
		driver.get("http://atqc-shop.epizy.com/");
		//find, click and clear 'search' text field
		
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).click();
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).clear();
		Thread.sleep(1000);//only for demonstration 
		
		//type search request and press 'Enter' key
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).sendKeys(request+Keys.ENTER);
		Thread.sleep(1000);//only for demonstration
		
		//search all items on the page and put them in the list
		List<String> actualResultList = getActualResultList();
		  //sort the list in order to right comparison
		Collections.sort(actualResultList);
		//check if results are right
		Assert.assertEquals(expectedResultList, actualResultList);	
		}
	
  	
	@Test(dataProvider = "request=hp")
		public void testResultWithFilters(String request, ArrayList<String> expectedResultList) 
				throws NoSuchElementException,InterruptedException {
			driver.get("http://atqc-shop.epizy.com/");
			
			driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).click();
			driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).clear();
			driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).sendKeys(" ");
			Thread.sleep(1000);//only for demonstration
			
			driver.findElement(By.cssSelector(".btn.btn-default.btn-lg[type='button']")).click();
			Thread.sleep(1000);//only for demonstration
			
			driver.findElement(By.id("input-search")).click();
			driver.findElement(By.id("input-search")).clear();
			driver.findElement(By.id("input-search")).sendKeys(request);
			Thread.sleep(1000);//only for demonstration
			
			driver.findElement(By.id("description")).click();
			Thread.sleep(1000);//only for demonstration
			
			driver.findElement(By.id("button-search")).click();
			Thread.sleep(1000);//only for demonstration
			
			driver.findElement(By.cssSelector(".fa.fa-th-list")).click();
			Thread.sleep(1000);//only for demonstration
			
			driver.findElement(By.id("input-sort")).click();
			Thread.sleep(1000);//only for demonstration
			
			//search element by partial value
			driver.findElement(By.cssSelector("[value*='price&order=DESC']")).click();
			Thread.sleep(1000);//only for demonstration
			
			driver.findElement(By.id("input-limit")).click();
			Thread.sleep(1000);//only for demonstration
			
			driver.findElement(By.cssSelector("[selected='selected']")).click();
			Thread.sleep(1000);//only for demonstration
			
			Assert.assertEquals(expectedResultList, getActualResultList());	
			
		}
	
	
		@Test(dataProvider = "request=mac")
		public void testTopAndMainForms(String request, ArrayList<String> expectedResultList) 
				throws NoSuchElementException,InterruptedException {
			driver.get("http://atqc-shop.epizy.com/");
			
			driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).click();
			driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).clear();
			driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).sendKeys("mac");
			Thread.sleep(1000);//only for demonstration
			
			driver.findElement(By.cssSelector(".btn.btn-default.btn-lg[type='button']")).click();
			Thread.sleep(1000);//only for demonstration
			
			List<String> actualResultList = getActualResultList();
			Collections.sort(actualResultList);
			Assert.assertEquals(expectedResultList, actualResultList);	
			
			driver.findElement(By.id("input-search")).click();
			Thread.sleep(1000);//only for demonstration
			
			driver.findElement(By.id("input-search")).clear();
			driver.findElement(By.id("input-search")).sendKeys("mac");;
			driver.findElement(By.id("button-search")).click();
			Thread.sleep(1000);//only for demonstration
			
			driver.findElement(By.cssSelector(".btn.btn-default.btn-lg[type='button']")).click();
			Thread.sleep(1000);//only for demonstration
			
			actualResultList = getActualResultList();
			Collections.sort(actualResultList);
			Assert.assertEquals(expectedResultList, actualResultList);
		}
			
	
	@Test(dataProvider = "request=apple")
	public void testLogedInAndGuest(String request, ArrayList<String> expectedResultList) 
			throws NoSuchElementException,InterruptedException {
		driver.get("http://atqc-shop.epizy.com/");
		
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).click();	
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).clear();
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).sendKeys(request);																		
		Thread.sleep(1000);//only for demonstration
		
		driver.findElement(By.cssSelector(".btn.btn-default.btn-lg[type='button']")).click();
		Thread.sleep(1000);//only for demonstration
		
		List<String> actualResultList = getActualResultList();
		Collections.sort(actualResultList);
		Assert.assertEquals(expectedResultList, actualResultList);	
		Thread.sleep(1000);//only for demonstration
		
		driver.findElement(By.cssSelector("[title='My Account']")).click();					
		Thread.sleep(1000);//only for demonstration
		
		driver.findElement(By.cssSelector("[href*='/login']")).click();
		Thread.sleep(1000);//only for demonstration
		
		driver.findElement(By.name("email")).click();				
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(EMAIL_ADDRESS);		
		Thread.sleep(1000);//only for demonstration
		
		driver.findElement(By.name("password")).click();									
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(PASSWORD+ Keys.ENTER);			
		Thread.sleep(1000);//only for demonstration
		
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).click();
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).clear();
		driver.findElement(By.cssSelector(".form-control.input-lg[name='search']")).sendKeys(request);																		
		Thread.sleep(1000);//only for demonstration
		
		driver.findElement(By.cssSelector(".btn.btn-default.btn-lg[type='button']")).click();
		Thread.sleep(1000);//only for demonstration
		
		actualResultList = getActualResultList();
		Collections.sort(actualResultList);
		Assert.assertEquals(expectedResultList, actualResultList);		}
  	
	/**
	 * 
	 * @param items - list of items to check presence
	 * @return true if all items from list are present on the page
	 */
	public List<String> getActualResultList() {
		//find all item names on the page
		List<WebElement> elementsList = driver.findElements(By.xpath("//*[@class='caption']//following::h4"));
		List<String> itemNames = new ArrayList<>();
		for (WebElement webElement : elementsList) {
			itemNames.add(webElement.getText());
		}
		return itemNames;
			
	}
}