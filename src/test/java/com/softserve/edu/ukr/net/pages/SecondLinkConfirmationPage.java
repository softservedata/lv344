package com.softserve.edu.ukr.net.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.pages.password.ResetPasswordPage;

public class SecondLinkConfirmationPage {

	private WebDriver driver;
	//
	private WebElement secondLink;

	public SecondLinkConfirmationPage(WebDriver driver) {
		this.driver = driver;
		initSecondLinkComponent();
	}

	private void initSecondLinkComponent() {
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			//TODO +++++
			if (driver.getTitle().toLowerCase().contains("Внимание")) {
				break;
			}
		}
		//
		secondLink = driver.findElement(By.cssSelector(".confirm__content-warn a"));
	}

	//secondLink
	public WebElement getSecondLink() {
		return secondLink;
	}

	public void clickSecondLink() {
		getSecondLink().click();
	}

	//business
	public ResetPasswordPage gotoResetPasswordPage() {
		clickSecondLink();
	    return new ResetPasswordPage(driver);
	}

}
