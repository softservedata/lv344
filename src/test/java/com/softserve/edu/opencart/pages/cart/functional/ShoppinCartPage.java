package com.softserve.edu.opencart.pages.cart.functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.pages.AStatusBarComponent;
import com.softserve.edu.opencart.tools.RegexUtils;

public class ShoppinCartPage extends AStatusBarComponent {

	private WebElement arrayList;
	private WebElement quantityField;
	private WebElement updateButton;
	private WebElement removeButton;
//	private WebElement cuponCodeField;
//	private WebElement applyCuponCpdeButton;
//	private WebElement estimateShippingAndTaxes;
//	private WebElement countryFieldInEstimateDropdown;
//	private WebElement stateFieldInEstimateDropdown;
//	private WebElement postCodeFieldInEstimateDropdown;
//	private WebElement giftCertificateField;
//	private WebElement applyGiftCertificateButton;
	private WebElement totalPrice;
//	private WebElement continueShoppingButton;
	private WebElement checkoutButton;

	public ShoppinCartPage(WebDriver driver) {
		super(driver);
		initEmptyCartPageText();
	}

//TODO ДОПИСАТИ ПЕРЕВІРКУ ЧИ МИ НА СТОРІНЦІ
	//TODO УТОЧНИТИ ЯК ПРАВИЛЬНО ШУКАТИ ЕЛЕМЕНТИ
	private void initEmptyCartPageText() {
//		arrayList
		// checkEmptyCartPage = driver.findElement(By.cssSelector("#content p"));
		quantityField = driver.findElement(By.xpath("//div[contains(@class,'table')]//a[text()='MacBook']/../..//div[contains(@class, 'input-group')]//input"));
		updateButton = driver.findElement(By.xpath("//div[contains(@class,'table')]//a[text()='MacBook']/../..//i[contains(@class,'fa-refresh')]"));
		removeButton = driver.findElement(By.xpath("//div[contains(@class,'table')]//a[text()='MacBook']/../..//i[contains(@class,'fa-times-circle')]"));
		totalPrice = driver.findElement(By.xpath("//td/strong[text()='Total:']/../../td[contains(text() , '')]"));
//		continueShoppingButton = driver.findElement(By.cssSelector("a.btn.btn-default"));
		checkoutButton = driver.findElement(By.cssSelector("a.btn.btn-primary"));
	}

	//AtomicOperation
	
	// QuantityField
	public WebElement getQuantityField() {return quantityField;}
	public String setQuantityField() {return getQuantityField().getAttribute(TAG_ATTRIBUTE_VALUE);}
	public void setQuantityField(String text) {getQuantityField().sendKeys(text);}
	public void clearQuantityField() {getQuantityField().clear();}
	public void clickQuantityField() {getQuantityField().click();}

	//UpdateButton
	public WebElement getUpdateButton() {return updateButton;}
	public String getUpdateButtonText() {return getUpdateButton().getAttribute(TAG_ATTRIBUTE_VALUE);}
	public void clickUpdateButton() {getUpdateButton().click();}
	
	//RemoveButton
	public WebElement getRemoveButton() {return removeButton;}
	public String getRemoveButtonText() {return getRemoveButton().getAttribute(TAG_ATTRIBUTE_VALUE);}
	public void clickRemoveButton() {getRemoveButton().click();}
	
	//TotalPrice
	public WebElement getTotalPrice() {return totalPrice;}
	public double getTotalPriceText() {return RegexUtils.extractFirstDouble(getTotalPrice().getText());}

	//CheckoutButton
	public WebElement getCheckoutButton() {return checkoutButton;}
	public String getCheckoutButtonText() {return getCheckoutButton().getAttribute(TAG_ATTRIBUTE_VALUE);}
	public void clickCheckouteButton() {getCheckoutButton().click();}

	// Business Logic

}
