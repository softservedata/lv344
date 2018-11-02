package com.softserve.edu.ukr.net.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InboxLetterPage {

	private WebDriver driver;

	private WebElement newLetter;

	public InboxLetterPage(WebDriver driver) {
		this.driver = driver;
		initLoginComponent();
	}

	private void initLoginComponent() {
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if (driver.getTitle().toLowerCase().contains("test-atqc-shop")) {
				break;
			}
		}
		newLetter = driver.findElements(By.xpath("//tbody/tr[contains(@class,'msglist__row unread')]")).get(0);
	}

	// newLetter
	public WebElement getNewLetter() {
		return newLetter;
	}

	public void clickNewLetter() {
		getNewLetter().click();
	}

	// business

	public FirstLinkConfirmationPage gotoFirstLinkConfirmationPage() {
		new WebDriverWait(driver, 5).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//tbody/tr[contains(@class,'msglist__row unread')]")));		
		clickNewLetter();
		return new FirstLinkConfirmationPage(driver);
	}

}
