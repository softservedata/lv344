package com.softserve.edu.opencart.data;

import java.util.Map;

public class Product {

	private String name;
	private String description;
	private String сurrency;
	private Map<Currencies, Double> price; 
	private Map<Currencies, Double> priceExTax;
	
}
