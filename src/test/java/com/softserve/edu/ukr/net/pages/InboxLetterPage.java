package com.softserve.edu.ukr.net.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InboxLetterPage {
	
	private WebDriver driver;
	 
	private WebElement newLetter;
	
	public InboxLetterPage(WebDriver driver) {
		driver = this.driver;
		initLoginComponent();
	}
	private void initLoginComponent() {
		newLetter = driver.findElement(By.id("td.msglist__row-unread.noselect"));//??
	}
	
	public WebElement getNewLetter() {
        return newLetter;
    }

	 
	public void clickNewLetter() {
		getNewLetter().click();
    }
	

}
