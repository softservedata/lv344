package com.softserve.edu.opencart.pages.wishlist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.tools.RegexUtils;

public class WishListProductComponent {
	
	private WebElement tr;
//
    private WebElement productNameWishList;
  //  private WebElement modelWishList;
  //  private WebElement stockWishList;
    private WebElement unitPriceWishList;
    private WebElement addToCartFromWishList;
    private WebElement removeFromWishListButton;
    
    public WishListProductComponent(WebElement tr) {  //не знаю що тут написати замість productLayout
        this.tr = tr;
        initWishListProductComponents();
    }

    private void initWishListProductComponents() {
    	productNameWishList = tr.findElement(By.cssSelector(".text-left a"));
    	unitPriceWishList = tr.findElement(By.cssSelector(".price"));
    	addToCartFromWishList = tr.findElement(By.cssSelector(".text-right .btn.btn-primary"));
    	removeFromWishListButton= tr.findElement(By.cssSelector(".text-right a.btn.btn-danger"));    
    }
    
    
    
    // PageObject

    // tr
    public WebElement gettr() {
        return tr;
    }

    // productNameWishList;
    public WebElement getproductNameWishList() {
        return productNameWishList;
    }
    
    public String getproductNameWishListText() {
        return getproductNameWishList().getText();
    }

    public void clickproductNameWishList() {
        getproductNameWishList().click();
    }

    // unitPriceWishList;
    public WebElement getunitPriceWishList() {
        return unitPriceWishList;
    }

    public String getunitPriceWishListText() {
        return getunitPriceWishList().getText();
    }

    public double getunitPriceWishListAmount() {
        return RegexUtils.extractFirstDouble(getunitPriceWishListText());
    }

    // addToCartFromWishList;
    public WebElement getaddToCartFromWishList() {
        return addToCartFromWishList;
    }
    
    public void clickaddToCartFromWishList() {
    	getaddToCartFromWishList().click();
    }

    // removeFromWishListButton;
    public WebElement getremoveFromWishListButton() {
        return removeFromWishListButton;
    }
    
    public void clickremoveFromWishListButton() {
    	System.out.println("removeProductFromWishListByPartialName+++++++++++++++wishListMessagePage.getAlertMessageText()");
        getremoveFromWishListButton().click();
    }
	

}

