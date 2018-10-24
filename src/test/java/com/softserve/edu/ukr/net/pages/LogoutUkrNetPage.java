package com.softserve.edu.ukr.net.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LogoutUkrNetPage {
	
	private WebDriver driver;
	
	 private WebElement dropDownButton;
	 private WebElement linkLogout;
	
	public  LogoutUkrNetPage(WebDriver driver) {
		this.driver = driver;
		initLogoutComponent();
	}
	private void initLogoutComponent() {
		dropDownButton = driver.findElement(By.cssSelector(".login-button__user"));
		linkLogout = driver.findElement(By.id("login__logout"));

		
	}
	
	//dropDownButton;
	
	public WebElement getDropDownButton() {
        return dropDownButton;
    }

	 
	public void clickDropDownButton() {
		getDropDownButton().click();
    }

	// linkLogout
	
	public WebElement getLinkLogout() {
        return linkLogout;
    }

	 
	public void clickLinkLogout() {
		getLinkLogout().click();
    }



	
	// Business Logic
	


}
