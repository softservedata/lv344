package com.softserve.edu.opencart.pages.cart.functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.pages.AStatusBarComponent;
import com.softserve.edu.opencart.pages.HomeMessagePage;
import com.softserve.edu.opencart.tools.RegexUtils;

public class ShoppinCartPage extends AStatusBarComponent {

	private ProductsListCartComponent productsCartListComponent;
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
		totalPrice = driver.findElement(By.xpath("//td/strong[text()='Total:']/../../td[contains(text() , '')]"));
//		continueShoppingButton = driver.findElement(By.cssSelector("a.btn.btn-default"));
		checkoutButton = driver.findElement(By.cssSelector("a.btn.btn-primary"));
	}

	//AtomicOperation
	private ProductsListCartComponent getProductsCartListComponent() {return productsCartListComponent;}
	
	//TotalPrice
	public WebElement getTotalPrice() {return totalPrice;}
	public double getTotalPriceText() {return RegexUtils.extractFirstDouble(getTotalPrice().getText());}

	//CheckoutButton
	public WebElement getCheckoutButton() {return checkoutButton;}
	public String getCheckoutButtonText() {return getCheckoutButton().getAttribute(TAG_ATTRIBUTE_VALUE);}
	public void clickCheckouteButton() {getCheckoutButton().click();}

	// Business Logic
	public ShoppingCartMessagePage updateProductQuantityByPartialName(String partialProductName) {
		getProductsCartListComponent()
			.updateProductCartByPartialName(partialProductName);
        return new ShoppingCartMessagePage(driver); 
    }
	

}
