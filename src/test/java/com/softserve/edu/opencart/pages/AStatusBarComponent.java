package com.softserve.edu.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AStatusBarComponent extends AHeadComponent {

	private final String STATUS_BAR_ERROR = "StatusBar Error";
	//
	private List<WebElement> breadcrumbs;
	
	public AStatusBarComponent(WebDriver driver) {
		super(driver);
		breadcrumbs = driver.findElements(By.cssSelector(".breadcrumb li")); 
	}

	// PageObject Atomic Operation

	// breadcrumbs
	public List<WebElement> getBreadcrumbs() {
		return breadcrumbs;
	}

	public int getCountBreadcrumbs() {
		return getBreadcrumbs().size();
	}

	public WebElement getBreadcrumb(int breadcrumbNumber) {
		if (breadcrumbNumber >= getCountBreadcrumbs()) {
			throw new RuntimeException(STATUS_BAR_ERROR);
		}
		return getBreadcrumbs().get(breadcrumbNumber);
	}

	public WebElement getLastBreadcrumb() {
		return getBreadcrumbs().get(getCountBreadcrumbs() - 1);
	}

	public String getBreadcrumbText(int breadcrumbNumber) {
        return getBreadcrumb(breadcrumbNumber).getText();
    }

	public String getLastBreadcrumbText() {
		return getLastBreadcrumb().getText();
	}

	public void clickBreadcrumb(int breadcrumbNumber) {
		getBreadcrumb(breadcrumbNumber).click();
    }
	
	public HomePage clickFirstBreadcrumb() {
		getBreadcrumb(0).click();
		return new HomePage(driver);
		
    }

	public void clickLastBreadcrumb() {
		getLastBreadcrumb().click();
    }

	// Business Logic

}

