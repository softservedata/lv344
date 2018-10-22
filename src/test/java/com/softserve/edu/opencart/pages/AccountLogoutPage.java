package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountLogoutPage extends AUnloggedRighMenuComponent {
	public final String EXPECTED_TEXT_LOGOUT = "Account Logout";
	
	private WebElement accountLogoutLabel;
	
	public AccountLogoutPage(WebDriver driver) {
		super(driver);
		initLoginComponent();
	}

	private void initLoginComponent() {
		accountLogoutLabel = driver.findElement(By.cssSelector("#content h1"));
	}

	// PageObject Atomic Operation

	// emailField
	public WebElement getAccountLogoutLabel() {
        return accountLogoutLabel;
    }

	public String getAccountLogoutLabelText() {
        return getAccountLogoutLabel().getText();
    }

	// Business Logic

}
