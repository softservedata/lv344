package com.softserve.edu.opencart.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softserve.edu.opencart.tools.RegexUtils;

public class Product implements IProduct {
	private final static int COLUMN_EURO = 2;
	private final static int COLUMN_POUND_STERLING = 3;
	private final static int COLUMN_US_DOLLAR = 4;
	private final static int COLUMN_NAME = 0;
	private final static int COLUMN_DESCRIPTION = 1;

	private String name;
	private String description;
	private Map<Currencies, Double> price; 
	private Map<Currencies, Double> priceExTax;
	
	// TODO Develop Builder
	public Product(String name, String description) {
		this.name = name;
		this.description = description;
		price = new HashMap<Currencies, Double>();
		priceExTax = new HashMap<Currencies, Double>();
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

    public static List<IProduct> getByList(List<List<String>> rows) {
    	List<IProduct> result = new ArrayList<IProduct>();
    	//
    	if (!RegexUtils.isDoubleMatches(rows.get(0).get(COLUMN_EURO))) {
    		rows.remove(0);
    	}
    	for (List<String> currentRow : rows) {
    		Product currentProduct = new Product(currentRow.get(COLUMN_NAME),
    				currentRow.get(COLUMN_DESCRIPTION));
    		// TODO use cycle
    		currentProduct.addPrice(Currencies.EURO,
    				RegexUtils.extractFirstDouble(currentRow.get(COLUMN_EURO)));
    		currentProduct.addPrice(Currencies.POUND_STERLING,
    				RegexUtils.extractFirstDouble(currentRow.get(COLUMN_POUND_STERLING)));
    		currentProduct.addPrice(Currencies.US_DOLLAR,
    				RegexUtils.extractFirstDouble(currentRow.get(COLUMN_US_DOLLAR)));
    		//
    		result.add(currentProduct);
    	}
    	return result;
    }

}
