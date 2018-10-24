package com.softserve.edu.ukr.net.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.LoginMessagePage;
import com.softserve.edu.opencart.pages.MyAccountPage;

public class LoginUkrNetPage {
	
	private WebDriver driver;
	private final WebElement iframe = (WebElement) driver.switchTo().frame(driver.findElement(By.cssSelector("#login-frame-wraper > iframe")));;
	
	private WebElement emailField;
	
	private WebElement passwordField;
	private WebElement loginButton;
	
	public LoginUkrNetPage(WebElement iframe) {
		iframe = this.iframe;
		initLoginComponent();
	}
	private void initLoginComponent() {
		emailField = iframe.findElement(By.id("id-input-login"));
		passwordField = iframe.findElement(By.id("id-input-password"));
		loginButton = iframe.findElement(By.cssSelector("button.form__submit"));
		
	}
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

	private void fillLoginForm(IUser user) {
        clickEmailField();
        clearEmailField();
        setEmailField(user.getEMail());
        clickPasswordField();
        clearPasswordField();
        setPasswordField(user.getPassword());
        clickLoginButton();
    }
	
//	public MyAccountPage successLogin(IUser user) {
//        fillLoginForm(user);
//        loggedUser = true;
//        return new MyAccountPage(driver);
//    }
//
//	public LoginMessagePage unsuccessfullLogin(IUser invalidUser) {
//        fillLoginForm(invalidUser);
//        return new LoginMessagePage(driver);
    }

