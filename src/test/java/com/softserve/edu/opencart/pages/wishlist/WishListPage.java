package com.softserve.edu.opencart.pages.wishlist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.pages.AStatusBarComponent;
import com.softserve.edu.opencart.pages.MyAccountPage;


public class WishListPage extends AStatusBarComponent {
	public final String EXPECTED_MESSAGE_WISH_REMOVE = "Success: You have modified your wish list!";

	private WishListProductsListComponent wishListProductListComponent;
	private WebElement wishListButtonContinue;

	public WishListPage(WebDriver driver) {
		super(driver);
		initWishListPage();
	}

	private void initWishListPage() {
		wishListButtonContinue = driver.findElement(By.cssSelector(".pull-right > a"));
		wishListProductListComponent = new  WishListProductsListComponent(driver);
	}

	public WebElement getWishListButtonContinue() {
		return wishListButtonContinue;
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
	
	public WishListMessagePage putFromWishListToCartProductByPartialName(String partialProductName)  //HomeMessagePage
	{
		getWishListProductsListComponent()
		.addToCartProductFromWishListByPartialName(partialProductName);
		return new WishListMessagePage(driver); 
	}
	
	public WishListMessagePage removeFromWishListProductByPartialName(String partialProductName)
	{
			getWishListProductsListComponent()
			.removeProductFromWishListByPartialName(partialProductName);
		return new WishListMessagePage(driver); 
	}
		
	
}

