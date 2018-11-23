package com.softserve.edu.opencart.pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCartMessagePage extends ShoppingCartPage {
	public final String EXPECTED_MODIFIED_MESSAGE_CART = "Success: You have modified your shopping cart!";
	public final String EXPECTED_ERROR_MESSAGE_CART = "ERROR: You can't modified your shopping cart!";

	private WebElement alertMessage;

	public ShoppingCartMessagePage(WebDriver driver) {
		super(driver);
		initAlertMessage();
	}

	private void initAlertMessage() {
		alertMessage = driver.findElement(By.cssSelector(".alert.alert-success"));
	}
	
	public WebElement getAlertMessage() {return alertMessage;}
	public WebElement getAlertMessageCloseButton() {return getAlertMessage().findElement(By.cssSelector(".close"));}

    public String getAlertMessageText() {
    	String textMessage = getAlertMessage().getText();
    	// Remove x Symbol and space from Message
        return textMessage.substring(0, textMessage.length() - 2);
    }

    // Business Logic

    public ShoppingCartPage closeAlertMessage() {
    	getAlertMessageCloseButton().click();
    	return new ShoppingCartPage(driver);
    }
}