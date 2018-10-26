package com.softserve.edu.opencart.data;

import java.util.Comparator;

public enum SortCriterias {
	DEFAULT("[value*='sort_order&order=ASC']",defaultComparator()), 	
	NAME_A_Z("[value*='name&order=ASC']",nameComparator(Order.ASC)), 			
	NAME_Z_A("[value*='name&order=DESC']",nameComparator(Order.DESC)),			
	PRICE_LOW_HIGH("[value*='price&order=ASC']",priceComparator(Order.ASC)),	
	PRICE_HIGH_LOW("[value*='price&order=DESC']",priceComparator(Order.DESC)),
	RATING_HIGHEST("[value*='rating&order=DESC']",defaultComparator()),
	RATING_LOWEST("[value*='rating&order=ASC']",defaultComparator()),		
	MODEL_A_Z("[value*='model&order=ASC']",defaultComparator()),		
	MODEL_Z_A("[value*='model&order=DESC']",defaultComparator());	
	
	private String name;
	private Comparator<Product> comparator;
	
	private SortCriterias(String name, Comparator<Product> comparator) {
		this.name = name;
		this.comparator = comparator;
	}
	
	private static Comparator<Product> defaultComparator(){
		return new Comparator<Product>(){
			public int compare(Product a, Product b) {
				return 0;
				};
			};
	}
	
	private static Comparator<Product> priceComparator(final Order order){
		return new Comparator<Product>(){
			public int compare(Product a, Product b) {
				return (order.order)*(new Double(a.getPrice(Currencies.US_DOLLAR))
						.compareTo(b.getPrice(Currencies.US_DOLLAR)));
				};
			};
	}
	
	private static Comparator<Product> nameComparator(final Order order){
				return new Comparator<Product>(){
					public int compare(Product a, Product b) {
						return (order.order)*(a.getName().compareTo(b.getName()));
					};
				};
			
	}
	
	public Comparator<Product> getComparator(){
		return this.comparator;
	}

	@Override
	public String toString() {
		return name;
	}
}
