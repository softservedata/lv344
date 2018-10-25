package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountMessagePage extends MyAccountPage implements IMyAccountMessagePage {

	public final String EXPECTED_SUCCESS_CHANGE_PASSWORD = "Success: Your password has been successfully updated.";

	private WebElement alertMessage;

	public MyAccountMessagePage(WebDriver driver) {
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

	public String getAlertMessageText() {
		return getAlertMessage().getText();
	}

	// Business Logic
	
	public MyAccountMessagePage getMyAccountMessagePage() {
	    return this;
	}
}
