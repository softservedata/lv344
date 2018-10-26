package com.softserve.edu.opencart.tools;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.softserve.edu.ukr.net.pages.LoginUkrNetPage;
import com.softserve.edu.ukr.net.pages.LoginedUkrNetPage;

public class TestRunnerUkrNet extends TestRunner {

//	@BeforeMethod
//	public void beforeMethod() {
//		System.out.println("@BeforeMethod TestRunnerUkrNet");
//		driver.get("https://www.ukr.net/");
//	}

	protected LoginUkrNetPage loadUkrNet() {
		driver.get("https://www.ukr.net/");
		return new LoginUkrNetPage(driver);
	}

	protected LoginedUkrNetPage switchToLoginedUkrNet() {
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if (driver.getTitle().toLowerCase().contains("ukr.net")) {
				break;
			}
		}
		return new LoginedUkrNetPage(driver);
	}

//
//	@Override
//	protected LoginUkrNetPage loadApplication() {
//		return new LoginUkrNetPage(driver);
//	}

	protected void delayExecution(long miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {

			System.err.println("Cannot thread sleep!");

		}
	}

}