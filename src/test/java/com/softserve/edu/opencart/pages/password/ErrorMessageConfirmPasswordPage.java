package com.softserve.edu.opencart.pages.password;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ErrorMessageConfirmPasswordPage extends ChangePasswordPage implements IErrorMessageConfirmPasswordPage {

	public final String ERROR_MESSAGE_CONFIRM_PASSWORD = "Password confirmation does not match password!";

	
	private WebElement alertMessage;


	public ErrorMessageConfirmPasswordPage(WebDriver driver) {
		super(driver);
		initAlertMessage();
	}

	private void initAlertMessage() {
		alertMessage = driver.findElement(By.cssSelector("input[name='confirm'] + div"));
	}

	public WebElement getAlertMessage() {
		return alertMessage;
	}

	public String getAlertMessageText() {
		return getAlertMessage().getText();
	}

	// busness logic
	
	public ErrorMessageConfirmPasswordPage getErrorMessageConfirmPasswordPage() {
		return this;
	}

	public ErrorMessageChangePasswordPage gotoErrorMessageChangePasswordPage() {
		return new ErrorMessageChangePasswordPage(driver);
	}
	
}
