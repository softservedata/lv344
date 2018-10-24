package com.softserve.edu.opencart.pages.password;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.pages.ARighMenuComponent;
import com.softserve.edu.opencart.pages.LoginMessagePage;
import com.softserve.edu.opencart.pages.LoginMessagePageError;

public class ForgottenPasswordPage extends ARighMenuComponent{
	
	private WebElement emailAdressField;
	private WebElement continueButton;
	private WebElement backButton;

	public ForgottenPasswordPage(WebDriver driver) {
		super(driver);
		initPasswordComponent();
		
	}
	
	private void initPasswordComponent() {
		emailAdressField = driver.findElement(By.cssSelector("#input-email.form-control"));
		continueButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
		backButton = driver.findElement(By.cssSelector("a.btn.btn-default"));
	}
	
	
     //emailAdressField
	public WebElement getEmailAdressField() {
		return emailAdressField;
	}

	public String getEmailAdressFieldText() {
		return getEmailAdressField().getAttribute(TAG_ATTRIBUTE_VALUE);
	}

	
	public void setEmailAdressField(String text) {
		getEmailAdressField().sendKeys(text);
	}

	public void clearEmailAdressField() {
		getEmailAdressField().clear();
	}

	public void clickEmailAdressField() {
		getEmailAdressField().click();
	}
	
	 public void fillEmailAdressField(IUser user) {
		 clickEmailAdressField();
		 clearEmailAdressField();
		 setEmailAdressField(user.getEMail());
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
	//bussnes logic
	
	  public LoginMessagePage fillEmailAdressFieldResetPassword(IUser user) {
		  fillEmailAdressField(user);  
		  clickContinueButton();
          return new LoginMessagePage(driver);
	
	  }

}
