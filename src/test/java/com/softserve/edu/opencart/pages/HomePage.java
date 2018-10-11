package com.softserve.edu.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.data.Currencies;

public class HomePage extends AHeadComponent {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	// PageObject
	
	// Business Logic
	
	public HomePage chooseCurrency(Currencies currencies) {
        clickCurrencyByPartialName(currencies.toString());
        return new HomePage(driver); 
    }
	
}