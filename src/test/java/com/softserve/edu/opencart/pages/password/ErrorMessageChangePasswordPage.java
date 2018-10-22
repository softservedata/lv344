package com.softserve.edu.opencart.pages.password;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ErrorMessageChangePasswordPage extends ChangePasswordPage {

	public final String ERROR_MESSAGE_CHANGE_PASSWORD = "Password must be between 4 and 20 characters!";

	private WebElement alertMessage;

	public ErrorMessageChangePasswordPage(WebDriver driver) {
		super(driver);
		initAlertMessage();
	}

	private void initAlertMessage() {
		alertMessage = driver.findElement(By.cssSelector("input[name='password'] + div"));
	}

	public WebElement getAlertMessage() {
		return alertMessage;
	}

	public String getAlertMessageText() {
		return getAlertMessage().getText();
	}
	
	// busness logic
	public ErrorMessageConfirmPasswordPage gotoErrorMessageConfirmPasswordPage() {
		return new ErrorMessageConfirmPasswordPage(driver);
	}

}
