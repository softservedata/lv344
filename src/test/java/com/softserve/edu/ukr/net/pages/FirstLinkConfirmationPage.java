package com.softserve.edu.ukr.net.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FirstLinkConfirmationPage {

	private WebDriver driver;
	//
	private WebElement firstLink;

	public FirstLinkConfirmationPage(WebDriver driver) {
		this.driver = driver;
		initFirstLinkComponent();
	}

	private void initFirstLinkComponent() {
		firstLink = driver.findElement(By.cssSelector(".readmsg__body a"));
	}

	// firstLink
	public WebElement getFirstLink() {
		return firstLink;
	}

	public void clickFirstLink() {
		getFirstLink().click();
	}

	// business
	public SecondLinkConfirmationPage gotoSecondLinkConfirmationPage() {
		clickFirstLink();
		return new SecondLinkConfirmationPage(driver);
	}

}
