package com.softserve.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AppTest 
{
	@BeforeClass
	public void createService() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				AppTest.class.getResource("/chromedriver-windows-32bit.exe").getPath());
	}
	@AfterClass
	public void StopService() {
		
	}
	
	// list of tabs which expected on site
		@DataProvider(parallel = true)
		public Object[][] tabNames() {
			return new Object[][] { { "Desktops", "Show All Desktops", 0, 0 },
					{ "Laptops & Notebooks", "Show All Laptops & Notebooks", 50, 50 },
					{ "Components", "Show All Components", 100, 100 },
					{ "Tablets", "Show All Tablets", 150, 150 },
					{ "Software", "Show All Software", 200, 200 },
					{ "Phones & PDAs", "Show All Phones & PDAs", 250, 250 },
					{ "Cameras", "Show All Cameras", 300, 300 },
					{ "MP3 Players", "Show All MP3 Players", 350, 350 } 
									};
		}

		/*
		 * Confirm availability certain tab on horizontal menu such as on vertical menu.
		 */
		@Test(dataProvider = "tabNames")
		public void testHorizontalTabEquivalentVerticalTab(String tabName, String showAll, int x, int y) throws Exception {
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			//for opening new Chrome window as cascade
			driver.manage().window().setPosition(new Point(x, y));
			// Go to main page of tested site
			driver.get("http://atqc-shop.epizy.com/index.php?route=common/home");
						
			driver.findElement(By.linkText(tabName)).click();
			Thread.sleep(1000);// for demonstration
			
			//open vertical menu
			driver.findElement(By.linkText(showAll)).click();
			Thread.sleep(1000);// for demonstration
			
			driver.findElement(By.cssSelector("a.list-group-item.active")).click();
			Thread.sleep(1000);// for demonstration
			/*
			 * on horizontal menu tabs have only names, but on vertical menu they have
			 * quantity of products near names. We have to check are the names on horizontal
			 * menu the same as on vertical that is why we have check it using function contains()
			 */			
			// check horizontal menu	
			Assert.assertTrue(driver.findElement(By.linkText(tabName))
					.getText().contains(tabName));
			
			//check vertical menu 
			Assert.assertTrue(driver.findElement(By.cssSelector("a.list-group-item.active"))
					.getText().contains(tabName));
			driver.quit();
		}

		
		@DataProvider//(parallel = true)
		public Object[][] dropdownList() {
			return new Object[][] { {"Desktops", "PC", 0, 0},
									{"Desktops", "Mac", 10, 10},
									{"Laptops & Notebooks", "Acer", 20, 20},
									{"Laptops & Notebooks", "Apple", 30, 30},
									{"Laptops & Notebooks", "HP", 40, 40},
									{"Laptops & Notebooks", "Others", 50, 50},
									{"Components", "Mice and Trackballs", 60, 60},
									{"Components", "Monitors", 70, 70},
									{"Components", "Printers", 80, 80},
									{"Components", "Scanners", 90, 90},
									{"Components", "Web Cameras", 100, 100},
									{"Tablets", "Apple", 110 , 110},
									{"Tablets", "Other", 120, 120},
									{"Tablets", "Samsung", 130, 130},
									{"Software", "Apple", 140, 140},
									{"Software", "Microsoft", 150, 150},
									{"Software", "Other", 160, 160},
									{"Phones & PDAs", "Apple", 170, 170},
									{"Phones & PDAs", "HTC", 180, 180},
									{"Phones & PDAs", "Others", 190, 190},
									{"Phones & PDAs", "Samsung", 200, 200},
									{"Cameras", "Canon", 210, 210},
									{"Cameras", "Nikon", 220, 220},
									{"MP3 Players", "Apple", 230, 230},
									{"MP3 Players", "Others", 240, 240}
				};
		}

		/*
		 * Confirm that dropdown list on horizontal menu
		 *  is equivalent to list on vertical menu and
		 *  “Refine Search” menu of certain tab.
		 */
		@Test(dataProvider = "dropdownList")  
		public void testHorizontalDropdownListEquivalentVerticalDropdownList(String tabName, String element, int x, int y)
																	throws Exception {

			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			// Go to main page of tested site
			driver.get("http://atqc-shop.epizy.com/index.php?route=common/home");
			// find tab
			driver.findElement(By.linkText(tabName)).click();
			Thread.sleep(1000);// for demonstration
			
			/*
			 * if horizontal dropdown list == references
			 * if vertical dropdown list == references
			 * if "Refine Search" list == references
			 * than horizontal dropdown list = vertical dropdown list = "Refine Search" list
			 */
			// check equivalence horizontal dropdown list due references
			if (tabName == "MP3 Players") {
				Assert.assertTrue(driver.findElement(By.cssSelector("li.dropdown.open > div > div"))
						.getText()
						.contains(element));
			} else {
			Assert.assertTrue(driver.findElement(By.cssSelector("li.dropdown.open > div > div > ul"))
						.getText()
						.contains(element));
			}
			// check equivalence vertical dropdown list due references	
			// open vertical menu
			driver.findElement(By.cssSelector("li.dropdown.open > div > a")).click();
			Thread.sleep(1000);
			Assert.assertTrue(driver.findElement(By.cssSelector("#column-left > div.list-group"))
					.getText()
					.contains(element));
			
			// check equivalence "Refine Search" list due references
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='content']/div/div/ul"))
					.getText()
					.contains(element));
			
			driver.quit();
			
		}
		/*
		 * Confirm that quantity of products in dropdown list
		 * on horizontal menu is equivalent to list on vertical
		 * and “Refine Search” menus of certain tab
		 */
		@DataProvider(parallel = true)
		public Object[][] quantityOfProducts1() {
			return new Object[][] { {"Desktops", "PC", "Mac", 0, 0, 1},
									{"Cameras", "Canon", "Nikon", 300, 300, 4},
									{"MP3 Players", "Apple", "Others", 350, 350, 4}
				};
		}
		
		@Test(dataProvider = "quantityOfProducts1")
		public void testEquivalenceQuantityOfProductsOfHorizontalVerticalRefineSearchMenu1(String tabName, 
											String element1, String element2, 
											int x, int y, int expectedQuantity)
				throws Exception {

			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			//for opening new Chrome window as cascade
			driver.manage().window().setPosition(new Point(x, y));
			//variable for counting sum of products in horizontal menu
			int sumOfProductsHor = 0;
			//variable for counting sum of products in vertical menu
			int sumOfProductsVer = 0;
			//variable for counting sum of products in "Refine Search" menu
			int sumOfProductsRef = 0;
			String myXPathVer = "//*[@id='column-left']/div/a[contains(text(), '%s')]";
			String myXPathRef = "//*[@id='content']/div/div/ul/li/a[contains(text(), '%s')]";
			
			// Go to main page of tested site
			driver.get("http://atqc-shop.epizy.com/index.php?route=common/home");
			// find tab
			driver.findElement(By.linkText(tabName)).click();
			Thread.sleep(1000);// for demonstration	
		
			// count sum quantity of products in horizontal menu
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element1))
					.getText());
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element2))
					.getText());
			// check equivalence sum of products in horizontal due to references	
			Assert.assertEquals(sumOfProductsHor, expectedQuantity);
			
			//open vertical menu
			driver.findElement(By.cssSelector("li.dropdown.open > div > a")).click();
			
			// count sum quantity of products in vertical menu
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element1)))
					.getText());
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element2)))
					.getText());
			// check equivalence sum of products in vertical menu  due to references
			Assert.assertEquals(sumOfProductsVer, expectedQuantity);
			
			// count sum quantity of products in "Refine Search" menu
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element1)))
					.getText());
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element2)))
					.getText());
			// check equivalence sum of products in "Refine Search" menu  due to references
			Assert.assertEquals(sumOfProductsRef, expectedQuantity);
			
			driver.quit();	
		}
		@DataProvider(parallel = true)
		public Object[][] quantityOfProducts2() {
			return new Object[][] { {"Laptops & Notebooks", "Acer", "Apple","HP", "Others", 50, 50, 11},
				                    {"Phones & PDAs", "Apple", "HTC", "Others", "Samsung", 250, 250, 7}								
				};
		}
		
		@Test(dataProvider = "quantityOfProducts2")
		public void testEquivalenceQuantityOfProductsOfHorizontalVerticalRefineSearchMenu2(String tabName, 
											String element1, String element2, String element3, String element4,
											int x, int y, int expectedQuantity)
				throws Exception {
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			//for opening new Chrome window as cascade
			driver.manage().window().setPosition(new Point(x, y));
			//variable for counting sum of products in horizontal menu
			int sumOfProductsHor = 0;
			//variable for counting sum of products in vertical menu
			int sumOfProductsVer = 0;
			//variable for counting sum of products in "Refine Search" menu
			int sumOfProductsRef = 0;
			String myXPathVer = "//*[@id='column-left']/div/a[contains(text(), '%s')]";
			String myXPathRef = "//*[@id='content']/div/div/ul/li/a[contains(text(), '%s')]";	
			
			// Go to main page of tested site
			driver.get("http://atqc-shop.epizy.com/index.php?route=common/home");
			// find tab
			driver.findElement(By.linkText(tabName)).click();
			Thread.sleep(1000);// for demonstration	
		
			// count sum quantity of products in horizontal menu
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element1))
					.getText());
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element2))
					.getText());
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element3))
					.getText());
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element4))
					.getText());
			// check equivalence sum of products in horizontal due to references	
			Assert.assertEquals(sumOfProductsHor, expectedQuantity);
			
			//open vertical menu
			driver.findElement(By.cssSelector("li.dropdown.open > div > a")).click();
			
			// count sum quantity of products in vertical menu
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element1)))
					.getText());
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element2)))
					.getText());
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element3)))
					.getText());
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element4)))
					.getText());
			// check equivalence sum of products in vertical menu  due to references
			Assert.assertEquals(sumOfProductsVer, expectedQuantity);
				
			
			// count sum quantity of products in "Refine Search" menu
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element1)))
					.getText());
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element2)))
					.getText());
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element3)))
					.getText());
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element4)))
					.getText());
			// check equivalence sum of products in "Refine Search" menu  due to references
			Assert.assertEquals(sumOfProductsRef, expectedQuantity);
			
			driver.quit();	
		}
		@DataProvider(parallel = true)
		public Object[][] quantityOfProducts3() {
			return new Object[][] {{"Tablets", "Apple", "Other", "Samsung", 150, 150, 8},
								   {"Software", "Apple",  "Microsoft", "Other", 200, 200, 4},
				};
		}
		
		@Test(dataProvider = "quantityOfProducts3")
		public void testEquivalenceQuantityOfProductsOfHorizontalVerticalRefineSearchMenu3(String tabName, 
											String element1, String element2, String element3,
											int x, int y, int expectedQuantity)
				throws Exception {

			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			//for opening new Chrome window as cascade
			driver.manage().window().setPosition(new Point(x, y));
			//variable for counting sum of products in horizontal menu
			int sumOfProductsHor = 0;
			//variable for counting sum of products in vertical menu
			int sumOfProductsVer = 0;
			//variable for counting sum of products in "Refine Search" menu
			int sumOfProductsRef = 0;
			String myXPathVer = "//*[@id='column-left']/div/a[contains(text(), '%s')]";
			String myXPathRef = "//*[@id='content']/div/div/ul/li/a[contains(text(), '%s')]";
			
			// Go to main page of tested site
			driver.get("http://atqc-shop.epizy.com/index.php?route=common/home");
			// find tab
			driver.findElement(By.linkText(tabName)).click();
			Thread.sleep(1000);// for demonstration	
		
			// count sum quantity of products in horizontal menu
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element1))
					.getText());
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element2))
					.getText());
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element3))
					.getText());
			// check equivalence sum of products in horizontal due to references	
			Assert.assertEquals(sumOfProductsHor, expectedQuantity);
			
			//open vertical menu
			driver.findElement(By.cssSelector("li.dropdown.open > div > a")).click();
			
			// count sum quantity of products in vertical menu
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element1)))
					.getText());
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element2)))
					.getText());
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element3)))
					.getText());
			// check equivalence sum of products in vertical menu  due to references
			Assert.assertEquals(sumOfProductsVer, expectedQuantity);
				
			
			// count sum quantity of products in "Refine Search" menu
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element1)))
					.getText());
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element2)))
					.getText());
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element3)))
					.getText());
			// check equivalence sum of products in "Refine Search" menu  due to references
			Assert.assertEquals(sumOfProductsRef, expectedQuantity);
			
			driver.quit();	
		}
		@DataProvider
		public Object[][] quantityOfProducts4() {
			return new Object[][] {{"Components", "Mice and Trackballs", "Monitors", "Printers", "Scanners", "Web Cameras", 16}
				};
		}
		
		@Test(dataProvider = "quantityOfProducts4")
		public void testEquivalenceQuantityOfProductsOfHorizontalVerticalRefineSearchMenu4(String tabName, 
											String element1, String element2, String element3, String element4, String element5,
											int expectedQuantity)
				throws Exception {

			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			//variable for counting sum of products in horizontal menu
			int sumOfProductsHor = 0;
			//variable for counting sum of products in vertical menu
			int sumOfProductsVer = 0;
			//variable for counting sum of products in "Refine Search" menu
			int sumOfProductsRef = 0;
			String myXPathVer = "//*[@id='column-left']/div/a[contains(text(), '%s')]";
			String myXPathRef = "//*[@id='content']/div/div/ul/li/a[contains(text(), '%s')]";
			
			// Go to main page of tested site
			driver.get("http://atqc-shop.epizy.com/index.php?route=common/home");
			// find tab
			driver.findElement(By.linkText(tabName)).click();
			Thread.sleep(1000);// for demonstration	
		
			// count sum quantity of products in horizontal menu
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element1))
					.getText());
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element2))
					.getText());
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element3))
					.getText());
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element4))
					.getText());
			sumOfProductsHor += RegexUtils.extractFirstNumber(driver.findElement(By.partialLinkText(element5))
					.getText());
			// check equivalence sum of products in horizontal due to references	
			Assert.assertEquals(sumOfProductsHor, expectedQuantity);
			
			//open vertical menu
			driver.findElement(By.cssSelector("li.dropdown.open > div > a")).click();
			
			// count sum quantity of products in vertical menu
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element1)))
					.getText());
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element2)))
					.getText());
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element3)))
					.getText());
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element4)))
					.getText());
			sumOfProductsVer += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathVer, element5)))
					.getText());
			// check equivalence sum of products in vertical menu  due to references
			Assert.assertEquals(sumOfProductsVer, expectedQuantity);
				
			
			// count sum quantity of products in "Refine Search" menu
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element1)))
					.getText());
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element2)))
					.getText());
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element3)))
					.getText());
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element4)))
					.getText());
			sumOfProductsRef += RegexUtils.extractFirstNumber(driver.findElement(By.xpath(String.format(myXPathRef, element5)))
					.getText());
			// check equivalence sum of products in "Refine Search" menu  due to references
			Assert.assertEquals(sumOfProductsRef, expectedQuantity);
			
			driver.quit();	
		}
}
