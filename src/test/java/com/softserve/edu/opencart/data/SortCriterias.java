package com.softserve.edu.opencart.data;

import java.util.Comparator;

public enum SortCriterias {
	DEFAULT("[value*='sort_order&order=ASC']",new Comparator<Product>(){
		public int compare(Product a, Product b) {
			return 0;
			};
		}), 	
	NAME_A_Z("[value*='name&order=ASC']",new Comparator<Product>(){
		public int compare(Product a, Product b) {
			return a.getName().compareTo(b.getName());
			};
		}), 			//	Name(A-Z)
	NAME_Z_A("[value*='name&order=DESC']",new Comparator<Product>(){
		public int compare(Product a, Product b) {
			return (-1)*a.getName().compareTo(b.getName());
			};
		}),			//	Name(Z-A)
	PRICE_LOW_HIGH("[value*='price&order=ASC']",new Comparator<Product>(){
		public int compare(Product a, Product b) {
			return new Double(a.getPrice(Currencies.US_DOLLAR)).compareTo(b.getPrice(Currencies.US_DOLLAR));
			};
		}),	
	PRICE_HIGH_LOW("[value*='price&order=DESC']",new Comparator<Product>(){
		public int compare(Product a, Product b) {
			return (-1)*(new Double(a.getPrice(Currencies.US_DOLLAR))
					.compareTo(b.getPrice(Currencies.US_DOLLAR)));
			};
		}),
	RATING_HIGHEST("[value*='rating&order=DESC']",new Comparator<Product>(){
		public int compare(Product a, Product b) {
			return 0;
			};
		}),
	RATING_LOWEST("[value*='rating&order=ASC']",new Comparator<Product>(){
		public int compare(Product a, Product b) {
			return 0;
			};
		}),		//	Rating(Lowest)
	MODEL_A_Z("[value*='model&order=ASC']",new Comparator<Product>(){
		public int compare(Product a, Product b) {
			return a.getName().compareTo(b.getName());
			};
		}),			//	Model(A-Z)
	MODEL_Z_A("[value*='model&order=DESC']",new Comparator<Product>(){
		public int compare(Product a, Product b) {
			return (-1)*a.getName().compareTo(b.getName());
			};
		});		//	Model(Z-A)

	private String name;
	private Comparator<Product> comparator;

	private SortCriterias(String name, Comparator<Product> comparator) {
		this.name = name;
		this.comparator = comparator;
	}
	public Comparator<Product> getComparator(){
		return this.comparator;
	}

	@Override
	public String toString() {
		return name;
	}
}
