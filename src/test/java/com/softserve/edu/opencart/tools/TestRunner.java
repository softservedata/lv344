package com.softserve.edu.opencart.tools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.cart.functional.EmptyShoppingCartPage;
import com.softserve.edu.opencart.pages.cart.functional.ProductsListCartComponent;
import com.softserve.edu.opencart.pages.cart.functional.ShoppingCartPage;

public abstract class TestRunner {
	protected WebDriver driver;
	protected final double PRECISION = 0.001;
	
	@BeforeClass
    public void beforeClass(ITestContext context) {
        System.out.println("@BeforeClass");
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("@AfterClass");
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("@BeforeMethod");
		driver.get("http://atqc-shop.epizy.com/");
    }

    @AfterMethod//(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        System.out.println("@AfterMethod");
    }
    @AfterGroups(groups = {"addItemToCart"})
    protected void AfterGroup() {
    	//Clear cart if we have one or more goods there
  		if(RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()) != 0) {
  			do{
  				driver.findElement(By.cssSelector("#cart")).click();
  				driver.findElement(By.cssSelector(".fa-times")).click();
  			}while(RegexUtils.extractFirstNumber(driver.findElement(By.cssSelector("#cart-total")).getText()) != 0);
  		}
    }
    
    protected HomePage loadApplication() {
        return new HomePage(driver);
    }
    
    protected void delayExecution(long miliseconds) {
        try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
