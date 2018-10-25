package com.softserve.edu.opencart.pages.cart.functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.pages.AStatusBarComponent;

public class EmptyShoppingCartPage extends AStatusBarComponent {
	
	public final String EXPECT_EMPTY_CART_TEXT = "Your shopping cart is empty!";
	
	public WebElement checkoutButton;
	private WebElement checkEmptyCartPage;

	public EmptyShoppingCartPage(WebDriver driver) {
		super(driver);
		initEmptyCartPageText();
	}
	
	private void initEmptyCartPageText() {
		checkEmptyCartPage = driver.findElement(By.cssSelector("#content p"));
		checkoutButton = driver.findElement(By.cssSelector("a.btn.btn-primary"));
	}
	
	// CheckEmptyCartPage
	public WebElement getEmptyCartPage() {return checkEmptyCartPage;}
	public String getEmptyCartText() {return getEmptyCartPage().getText();}
	
	//CheckoutButton
	public WebElement getCheckoutButton() {return checkoutButton;}
	public String getCheckoutButtonText() {return getCheckoutButton().getAttribute(TAG_ATTRIBUTE_VALUE);}
	public void clickCheckouteButton() {getCheckoutButton().click();}
	
	
}
