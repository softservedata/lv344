package com.softserve.edu.opencart.interfaces.cart;

import com.softserve.edu.opencart.pages.cart.ProductsListCartComponent;

public interface IProductsListCartComponent {
	
	public ProductsListCartComponent getProductsListCartComponent();
	public IProductsListCartComponent clickQuantityProductCartByPartialName(String partialProductCartName);
	public IProductsListCartComponent clearQuantityProductCartByPartialName(String partialProductCartName);
	public IProductsListCartComponent setQuantityProductCartByPartialName(String partialProductCartName, String numOfItems);
	public IProductsListCartComponent updateQuantityProductCartByPartialName(String partialProductCartName);
	public IProductsListCartComponent removeQuantityProductCartByPartialName(String partialProductCartName);
	
}
