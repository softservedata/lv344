package com.softserve.edu.opencart.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Andrii Koliadiuk
 *
 */
public class ProductsList {
	private List<Product> products;
	
	public ProductsList() {
		products = new ArrayList<>();
	}
	//copy constructor
	public ProductsList(ProductsList productList) {
		products = new ArrayList<>();
		this.products.addAll(productList.getProducts()) ; 
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void sort(Comparator<Product> comparator) {
		Collections.sort(getProducts(),comparator);
	}
	

	public ProductsList addProduct(Product product) {
		getProducts().add(product);
		return this;
	}
	
}
