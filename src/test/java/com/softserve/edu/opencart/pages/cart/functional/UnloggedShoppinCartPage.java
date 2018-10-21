package com.softserve.edu.opencart.pages.cart.functional;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.pages.AStatusBarComponent;

public class UnloggedShoppinCartPage extends AStatusBarComponent {

	public UnloggedShoppinCartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
}


/*
package com.softserve.edu.opencart.pages.cart.functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.tools.RegexUtils;

public class ProductCartComponent {
	
	   	private WebElement cartProductLayout;
	   	//
	   	
		private WebElement quantityField;
		private WebElement updateButton;
		private WebElement removeButton;
//		private WebElement cuponCodeField;
//		private WebElement applyCuponCpdeButton;
//		private WebElement estimateShippingAndTaxes;
//		private WebElement countryFieldInEstimateDropdown;
//		private WebElement stateFieldInEstimateDropdown;
//		private WebElement postCodeFieldInEstimateDropdown;
//		private WebElement giftCertificateField;
//		private WebElement applyGiftCertificateButton;
	//	private WebElement totalPrice;
//		private WebElement continueShoppingButton;
		private WebElement checkoutButton;

		public ProductCartComponent(WebElement cartProductLayout) {
			this.cartProductLayout = cartProductLayout;
			initProductCartComponent();
		}

	//TODO ДОПИСАТИ ПЕРЕВІРКУ ЧИ МИ НА СТОРІНЦІ
		private void initProductCartComponent() {
			//productListComponent = productLayout.findElement(By.cssSelector(".table-responsive tbody tr"));
			quantityField = cartProductLayout.findElement(By.xpath("//div[contains(@class,'table')]//a[text()='%s']/../..//div[contains(@class, 'input-group')]//input"));
			updateButton = cartProductLayout.findElement(By.xpath("//div[contains(@class,'table')]//a[text()='MacBook']/../..//i[contains(@class,'fa-refresh')]"));
			removeButton = cartProductLayout.findElement(By.xpath("//div[contains(@class,'table')]//a[text()='MacBook']/../..//i[contains(@class,'fa-times-circle')]"));
	 //		totalPrice = productLayout.findElement(By.xpath("//td/strong[text()='Total:']/../../td[contains(text() , '%s')]"));
//			continueShoppingButton = productLayout.findElement(By.cssSelector("a.btn.btn-default"));
			checkoutButton = cartProductLayout.findElement(By.cssSelector("a.btn.btn-primary"));
		}

		//CartProductLayout
		public WebElement getCartProductLayout() {return cartProductLayout;}
		
		
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
//		public WebElement getTotalPrice() {return totalPrice;}
//		public double getTotalPriceText() {return RegexUtils.extractFirstDouble(getTotalPrice().getText());}

		//CheckoutButton
		public WebElement getCheckoutButton() {return checkoutButton;}
		public String getCheckoutButtonText() {return getCheckoutButton().getAttribute(TAG_ATTRIBUTE_VALUE);}
		public void clickCheckouteButton() {getCheckoutButton().click();}

		// Business Logic
	}
*/