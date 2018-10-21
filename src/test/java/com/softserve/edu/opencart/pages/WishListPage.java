package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class WishListPage extends AStatusBarComponent {
	public final String EXPECTED_MESSAGE_WISH_REMOVE = "Success: You have modified your wish list!";

	private WishListProductsListComponent wishListProductListComponent;
	private WebElement WishListButtonContinue;

	public WishListPage(WebDriver driver) {
		super(driver);
		initWishListPage();
	}

	private void initWishListPage() {
		WishListButtonContinue = driver.findElement(By.cssSelector(".pull-right > a"));
	}

	public WebElement getWishListButtonContinue() {
		return WishListButtonContinue;
	}

	public MyAccountPage clickWishListButtonContinue() {
		getWishListButtonContinue().click();
		return new MyAccountPage(driver);
	}
	
	// PageObject
	
	// productsListComponent
	public WishListProductsListComponent getWishListProductsListComponent() {
		return wishListProductListComponent;
	}

	//Business Logic
	public String getWishListProductNameByPartialName(String partialProductName) {
		return getWishListProductsListComponent()
				.getWishListProductComponentByPartialName(partialProductName)
				.getproductNameWishListText();
	}
	
	public HomeMessagePage putFromWishListToCartProductByPartialName(String partialProductName)
	{
		getWishListProductsListComponent()
		.addToCartProductFromWishListByPartialName(partialProductName);
		return new HomeMessagePage(driver); 
	}
	
	public HomeMessagePage removeFromWishListProductByPartialName(String partialProductName)
	{
		getWishListProductsListComponent()
		.removeProductFromWishListByPartialName(partialProductName);
		return new HomeMessagePage(driver); 
	}
		
	
}
