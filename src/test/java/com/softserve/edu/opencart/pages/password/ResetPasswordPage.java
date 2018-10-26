package com.softserve.edu.opencart.pages.password;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.pages.ARighMenuComponent;
import com.softserve.edu.opencart.pages.LoginMessagePage;

public class ResetPasswordPage extends ARighMenuComponent {

	private WebElement passwordField;
	private WebElement passwordConfirmField;
	private WebElement continueButton;
	private WebElement backButton;

	public ResetPasswordPage(WebDriver driver) {
		super(driver);
		initResetComponent();
	}

	private void initResetComponent() {
		passwordField = driver.findElement(By.cssSelector("#input-password.form-control"));
		passwordConfirmField = driver.findElement(By.cssSelector("#input-confirm.form-control"));
		continueButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
		backButton = driver.findElement(By.cssSelector("a.btn.btn-default"));
	}

	// passwordField
	public WebElement getPasswordField() {
		return passwordField;
	}

	public String getPasswordFieldText() {
		return getPasswordField().getAttribute(TAG_ATTRIBUTE_VALUE);
	}

	public void setPasswordField(String text) {
		getPasswordField().sendKeys(text);
	}

	public void clearPasswordField() {
		getPasswordField().clear();
	}

	public void clickPasswordField() {
		getPasswordField().click();
	}

	public void fillPasswordField(String text) {
		getPasswordField().click();
		getPasswordField().clear();
		getPasswordField().sendKeys(text);
	}

	// passwordConfirmField
	public WebElement getPasswordConfirmField() {
		return passwordConfirmField;
	}

	public String getPasswordConfirmFieldText() {
		return getPasswordConfirmField().getAttribute(TAG_ATTRIBUTE_VALUE);
	}

	public void setPasswordConfirmField(String text) {
		getPasswordConfirmField().sendKeys(text);
	}

	public void clearPasswordConfirmField() {
		getPasswordConfirmField().clear();
	}

	public void clickPasswordConfirmField() {
		getPasswordConfirmField().click();
	}

	public void fillPasswordConfirmField(String text) {
		getPasswordConfirmField().click();
		getPasswordConfirmField().clear();
		getPasswordConfirmField().sendKeys(text);
	}

	// continueButton
	public WebElement getContinueButton() {
		return continueButton;
	}

	public void clickContinueButton() {
		getContinueButton().click();
	}
	
	// backButton
	public WebElement getBackButton() {
		return backButton;
	}

	public void clickBackButton() {
		getBackButton().click();
	}
	
	// bussnes logic

	private void fillPasswordFieldResetPassword(String password, String confirmPassword) {
		fillPasswordField(password);
		//clickPasswordField();
		//clearPasswordField();
		//setPasswordField(password);

		fillPasswordConfirmField(confirmPassword);
		//clickPasswordConfirmField();
		//clearPasswordConfirmField();
		//setPasswordConfirmField(confirmPassword);
	}

	public LoginMessagePage successResetPassword(String password, String confirmPassword) {
		fillPasswordFieldResetPassword(password, confirmPassword);
		clickContinueButton();
		return new LoginMessagePage(driver);
	}

}
