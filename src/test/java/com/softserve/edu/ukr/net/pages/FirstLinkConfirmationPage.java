package com.softserve.edu.ukr.net.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FirstLinkConfirmationPage {
	private WebDriver driver;
	 
	private WebElement firstLink;
	
	public FirstLinkConfirmationPage(WebDriver driver) {
		driver = this.driver;
		initFirstLinkComponent();
	}
	private void initFirstLinkComponent() {
		firstLink = driver.findElement(By.id("id-input-login"));//??
	}
	
	public WebElement getFirstLink() {
        return firstLink;
    }

	 
	public void clickFirstLink() {
		getFirstLink().click();
    }

}
