package com.softserve.edu.ukr.net.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SecondLinkConfirmationPage {
	
	private WebDriver driver;
	 
	private WebElement secondLink;
	
	public SecondLinkConfirmationPage(WebDriver driver) {
		driver = this.driver;
		initSecondLinkComponent();
	}
	private void initSecondLinkComponent() {
		secondLink = driver.findElement(By.id(".confirm__content-warn a"));
	}
	
	public WebElement getSecondLink() {
        return secondLink;
    }

	 
	public void clickSecondLink() {
		getSecondLink().click();
    }

}
