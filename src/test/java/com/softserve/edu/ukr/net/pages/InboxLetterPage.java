package com.softserve.edu.ukr.net.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InboxLetterPage {
	
	private WebDriver driver;
	 
	private WebElement newLetter;
	
	public InboxLetterPage(WebDriver driver) {
		this.driver = driver;
		initLoginComponent();
	}
	private void initLoginComponent() {
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if (driver.getTitle().toLowerCase().contains("test-atqc-shop")) {
				break;
			}
		}
		//
		//newLetter = driver.findElement(By.cssSelector("tbody tr.msglist__row.unread.icon0.ui-draggable:first-child()"));
		newLetter = driver.findElements(By.xpath("//tbody/tr[contains(@class,'msglist__row unread')]")).get(0);
	}
	
	//newLetter
	public WebElement getNewLetter() {
        return newLetter;
    }

	public void clickNewLetter() {
		getNewLetter().click();
    }
	
	//business
	
	public FirstLinkConfirmationPage gotoFirstLinkConfirmationPage() {
		clickNewLetter();
	    return new FirstLinkConfirmationPage(driver);
	}
	
}
