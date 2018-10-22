package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomeMessagePage extends HomePage {
	public final String EXPECTED_MESSAGE_CART = "Success: You have added %s to your shopping cart!";
	public final String EXPECTED_MESSAGE_WISH_UNLOGGED = "You must login or create an account to save %s to your wish list!";
	public final String EXPECTED_MESSAGE_WISH = "Success: You have added %s to your wish list!";
	
	private WebElement alertMessage;

	public HomeMessagePage(WebDriver driver) {
		super(driver);
		initAlertMessage();
	}

	private void initAlertMessage() {
		alertMessage = driver.findElement(By.cssSelector(".alert.alert-success"));
	}
	
	// PageObject

	// alertMessage
	public WebElement getAlertMessage() {
		return alertMessage;
	}

	public WebElement getAlertMessageCloseButton() {
		return getAlertMessage().findElement(By.cssSelector(".close"));
	}

    public String getAlertMessageText() {
    	String textMessage = getAlertMessage().getText();
    	// Remove x Symbol from Message
        return textMessage.substring(0, textMessage.length() - 2);
    }

    // Business Logic

    public HomePage closeAlertMessage() {
    	getAlertMessageCloseButton().click();
    	return new HomePage(driver);
    }

}
