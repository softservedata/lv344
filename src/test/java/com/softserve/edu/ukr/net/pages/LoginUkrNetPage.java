package com.softserve.edu.ukr.net.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.IUserUkrNet;

public class LoginUkrNetPage {

	private WebDriver driver;
	//
	private WebElement emailField;
	private WebElement passwordField;
	private WebElement loginButton;

	public LoginUkrNetPage(WebDriver driver) {
		this.driver = driver;
		initLoginComponent();
	}

	private void initLoginComponent() {
		driver.switchTo().frame(driver.findElement(By.cssSelector("#login-frame-wraper > iframe")));
		//
		emailField = driver.findElement(By.id("id-input-login"));
		passwordField = driver.findElement(By.id("id-input-password"));
		loginButton = driver.findElement(By.cssSelector("button.form__submit"));
	}

	//emailField
	public WebElement getEmailField() {
		return emailField;
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

	// passwordField
	public WebElement getPasswordField() {
		return passwordField;
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

	public void clickLoginButton() {
		getLoginButton().click();
	}

	//business
	private void fillLoginForm(IUserUkrNet user) {
		clickEmailField();
		clearEmailField();
		setEmailField(user.geteMail());
		clickPasswordField();
		clearPasswordField();
		setPasswordField(user.getPassword());
		clickLoginButton();
	}

	public LoginedUkrNetPage successLoginUkrNet(IUserUkrNet user)  {
      fillLoginForm(user);
//      driver.navigate().refresh();
      // refresh page
      try {
		Thread.sleep(4000);
      } catch (InterruptedException e) {
      }
      return new LoginedUkrNetPage(driver);
	}
	
}
