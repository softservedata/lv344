package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.pages.password.ForgottenPasswordPage;


public class LoginPage extends AUnloggedRighMenuComponent {

	private WebElement emailField;
	private WebElement passwordField;
	private WebElement loginButton;
	private WebElement linkForgottenPassword;

	public LoginPage(WebDriver driver) {
		super(driver);
		initLoginComponent();
	}

	private void initLoginComponent() {
		emailField = driver.findElement(By.id("input-email"));
		passwordField = driver.findElement(By.id("input-password"));
		loginButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
		linkForgottenPassword = driver.findElement(By.linkText("Forgotten Password"));
	}

	// PageObject Atomic Operation

	// emailField
	public WebElement getEmailField() {
		return emailField;
	}

	public String getEmailFieldText() {
		return getEmailField().getAttribute(TAG_ATTRIBUTE_VALUE);
	}

	public void setEmailField(String text) {
		getEmailField().sendKeys(text);
	}

	public void clearEmailField() {
		getEmailField().clear();
	}

	public void clickEmailField() {
		getEmailField().click();
	}

	public void fillEmailField(IUser user) {
		clickEmailField();
		clearEmailField();
		setEmailField(user.getEMail());
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

	// loginButton
	public WebElement getLoginButton() {
		return loginButton;
	}

	public String getLoginButtonText() {
		return getLoginButton().getAttribute(TAG_ATTRIBUTE_VALUE);
	}

	public void clickLoginButton() {
		getLoginButton().click();
	}
	// linkForgottenPassword

	public WebElement getLinkForgottenPassword() {
		return linkForgottenPassword;
	}

	public String getLinkForgottenPasswordText() {
		return getLinkForgottenPassword().getText();
	}

	public void clickLinkForgottenPassword() {
		getLinkForgottenPassword().click();
	}

	// Business Logic
	private void fillLoginForm(IUser user) {
		clickEmailField();
		clearEmailField();
		setEmailField(user.getEMail());
		clickPasswordField();
		clearPasswordField();
		setPasswordField(user.getPassword());
		clickLoginButton();
	}

	public MyAccountPage successLogin(IUser user) {
		fillLoginForm(user);
		loggedUser = true;
		return new MyAccountPage(driver);
	}

	public LoginMessagePageError unsuccessfullLogin(IUser invalidUser) {
		fillLoginForm(invalidUser);
		return new LoginMessagePageError(driver);

	}

	public ForgottenPasswordPage fillLoginFormAndClickForgottenPassword(IUser user) {
		fillEmailField(user);
		clickLinkForgottenPassword();
		return new ForgottenPasswordPage(driver);
	}

}
