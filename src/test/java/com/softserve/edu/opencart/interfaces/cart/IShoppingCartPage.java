package com.softserve.edu.opencart.interfaces.cart;

import com.softserve.edu.opencart.pages.cart.ShoppingCartPage;

public interface IShoppingCartPage {
	
	public ShoppingCartPage getShoppingCartPage();
	public IShoppingCartPage updateProductQuantityByPartialName(String partialProductCartName, String numOfItems);
	public IShoppingCartPage clickProductQuantityByPartialName(String partialProductName);
	public IShoppingCartPage removeProductQuantityByPartialName(String partialProductName);
	public IShoppingCartPage gotoProductsListCartComponent();
}
