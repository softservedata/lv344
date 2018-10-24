package com.softserve.edu.opencart.pages.password;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgottenPasswordMessagePage extends ForgottenPasswordPage {

	public final String WARNING_MESSAGE_NOT_FOUND_EMAIL= "Warning: The E-Mail Address was not found in our records, please try again!";

	private WebDriver driver;
	private WebElement alertMessage;

	public ForgottenPasswordMessagePage(WebDriver driver) {
		super(driver);
		initAlertMessage();
	}

	private void initAlertMessage() {
		alertMessage = driver.findElement(By.cssSelector(".alert.alert-danger"));
	}

	// PageObject

	 //dengerMessage
	public WebElement getAlertMessage() {
		return alertMessage;
	}

	public String getAlertMessageText() {
		return getAlertMessage().getText();
	}
}
