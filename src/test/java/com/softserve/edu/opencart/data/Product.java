package com.softserve.edu.opencart.data;

import java.util.HashMap;
import java.util.Map;

public class Product {

	private String name;
	private String description;
	private Map<Currencies, Double> price; 
	private Map<Currencies, Double> priceExTax;
	
	public Product(String name, String description) {
		this.name = name;
		this.description = description;
		price = new HashMap<>();
		priceExTax = new HashMap<>();
	}

	public Product addPrice(Currencies currency, double price) {
		getPrice().put(currency, price);
		return this;
	}

	public Product addPriceExTax(Currencies currency, double price) {
		getPriceExTax().put(currency, price);
		return this;
	}

	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Map<Currencies, Double> getPrice() {
		return price;
	}

	public double getPrice(Currencies currency) {
		return getPrice().get(currency);
	}

	public Map<Currencies, Double> getPriceExTax() {
		return priceExTax;
	}

	public double getPriceExTax(Currencies currency) {
		return getPriceExTax().get(currency);
	}

}
