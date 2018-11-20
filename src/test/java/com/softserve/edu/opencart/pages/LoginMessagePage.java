package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginMessagePage extends LoginPage {

//	public final String EXPECTED_WARNING_LOGIN = "Warning: No match for E-Mail Address and/or Password.";	
//	public final String EXPECTED_WARNING_BLOCK_ACCOUNT = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
	
	public final String EXPECTED_CONFIRMATION_LINK_TO_CHANGE_PASSWORD = "An email with a confirmation link has been sent your email address.";
	public final String EXPECTED_MESSAGE_PASSWORD_UPDATED = "Success: Your password has been successfully updated.";
	private WebElement alertMessage;

	public LoginMessagePage(WebDriver driver) {
		super(driver);
		initAlertMessage();
	}
	
	

//	private void initAlertMessage() {
//		alertMessage = driver.findElement(By.cssSelector(".alert.alert-danger"));
//	}
	private void initAlertMessage() {
		alertMessage = driver.findElement(By.cssSelector(".alert.alert-success"));

	}

	// PageObject

	// alertMessage
	public WebElement getAlertMessage() {
		return alertMessage;
	}

    public String getAlertMessageText() {
        return getAlertMessage().getText();
    }
    
  

    // Business Logic

}


