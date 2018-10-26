package com.softserve.edu.ukr.net.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginedUkrNetPage {

	private WebDriver driver;
	//
	private WebElement inboxLink;
	private WebElement logoutLink;

	public LoginedUkrNetPage(WebDriver driver) {
		this.driver = driver;
		initLoginComponent();
	}

	private void initLoginComponent() {
		//driver.switchTo().frame(driver.findElement(By.cssSelector("#login-frame-wraper > iframe")));
		//
		inboxLink = driver.findElement(By.cssSelector(".service__entry.service__entry_mail"));
		logoutLink = driver.findElement(By.id("id-logout"));
	}
	
	//inboxLink
	public WebElement getInboxLink() {
		return inboxLink;
	}

	public void clickInboxLink() {
		getInboxLink().click();
	}

	//logoutLink
	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public void clickLogoutLink() {
		getLogoutLink().click();
	}
	
	//business
	
	public InboxLetterPage gotoInboxLetterPage() {
		clickInboxLink();
		// TODO DELETE
	      try {
	  		Thread.sleep(4000);
	        } catch (InterruptedException e) {
	        }
	    return new InboxLetterPage(driver);
	}

	public void logoutUkrNet() {
		clickLogoutLink();
	    //return new InboxLetterPage(driver);
	}

}
