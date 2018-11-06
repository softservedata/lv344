package com.softserve.edu.opencart.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softserve.edu.opencart.tools.RegexUtils;

public class Product implements IProduct {

	private String name;
	private String description;
	private Map<Currencies, Double> price; 
	private Map<Currencies, Double> priceExTax;
	
	// TODO Develop Builder
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

	/*
    public static List<IProduct> getByList(List<List<String>> rows) {
    	List<IProduct> result = new ArrayList<>();
    	Map<Integer, Currencies> currencies = new HashMap<>();
    	String node;
    	for (List<String> currentRow : rows) {
    		node = currentRow.get(REQUIRED_COLUMN_NUMBER);
    		if (RegexUtils.isDoubleMatches(node)) {
    			IPartialProduct partialProduct = Product.get()
        		.setSearchKey(currentRow.get(REQUIRED_COLUMN_SEARCH_KEY))
        		.setName(currentRow.get(REQUIRED_COLUMN_NAME))
        		.setDescriptionNext(currentRow.get(REQUIRED_COLUMN_DESCRIPTION));
    			//
    			for (int i = REQUIRED_COLUMN_NUMBER; i < currentRow.size(); i++) {
    				partialProduct = partialProduct.setPrice(currencies.get(i),
    						RegexUtils.extractFirstDouble(currentRow.get(i)));  
    			}
    			result.add(((IBuild)partialProduct).buildProduct());
    		} else {
				for (int i = REQUIRED_COLUMN_NUMBER; i < currentRow.size(); i++) {
    				for (Currencies currency : Currencies.values()) {
    					if (currentRow.get(i).toLowerCase().equals(
    							currency.name().toLowerCase())) {
    						currencies.put(i, currency);
    						continue;
    					}
    				}
    			}
    		}
    	}
    	return result;
    }
*/
}
