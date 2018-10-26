package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountInformationPage extends ARighMenuComponent {

	private WebElement firstnameField;
	public AccountInformationPage(WebDriver driver) {
		super(driver);
		initLoginComponent();
	}

	private void initLoginComponent() {
		firstnameField = driver.findElement(By.id("input-firstname"));
	}

	// PageObject Atomic Operation

	// firstnameField
	public WebElement getFirstnameField() {
        return firstnameField;
    }

	public String getFirstnameFieldText() {
        return getFirstnameField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
	
	public void setFirstnameField(String text) {
		getFirstnameField().sendKeys(text);
    }
	 
	public void clearFirstnameField() {
		getFirstnameField().clear();
    }
	 
	public void clickFirstnameField() {
		getFirstnameField().click();
    }

}

