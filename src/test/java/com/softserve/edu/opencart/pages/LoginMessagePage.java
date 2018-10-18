package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginMessagePage extends LoginPage {
	public final String EXPECTED_WARNING_LOGIN = "Warning: No match for E-Mail Address and/or Password.";

	private WebElement alertMessage;

	public LoginMessagePage(WebDriver driver) {
		super(driver);
		initAlertMessage();
	}

	private void initAlertMessage() {
		alertMessage = driver.findElement(By.cssSelector(".alert.alert-danger"));
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
