package com.softserve.edu.opencart.data;

import java.util.List;

import com.softserve.edu.opencart.tools.CSVReader;
import com.softserve.edu.opencart.tools.ExcelReader;

//Using static methods
public final class ProductRepository {

	private ProductRepository() {
	}

    public static Product defaultProduct() {
        return macBook();
    }

    public static Product macBook() {
        return new Product("MacBook",
        		"Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1..")
        		.addPrice(Currencies.EURO, 472.33)
        		.addPrice(Currencies.POUND_STERLING, 368.73)
        		.addPrice(Currencies.US_DOLLAR, 602.00)
        		.addPriceExTax(Currencies.EURO, 392.30)
        		.addPriceExTax(Currencies.POUND_STERLING, 306.25)
        		.addPriceExTax(Currencies.US_DOLLAR, 500.00);
    }

    public static Product iPhone() {
        return new Product("iPhone",
        		"iPhone is a revolutionary new mobile phone that allows you to make a call by simply tapping a nam..")
        		.addPrice(Currencies.EURO, 96.66)
        		.addPrice(Currencies.POUND_STERLING, 75.46)
        		.addPrice(Currencies.US_DOLLAR, 123.20)
        		.addPriceExTax(Currencies.EURO, 79.24)
        		.addPriceExTax(Currencies.POUND_STERLING, 61.86)
        		.addPriceExTax(Currencies.US_DOLLAR, 101.00);
    }

    public static List<IProduct> fromCsvProducts(String filename) {
    	return Product.getByList(new CSVReader(filename).getAllCells());
    }

    public static List<IProduct> fromCsvProducts() {
    	return fromCsvProducts("products.csv");
    }

    public static List<IProduct> fromExcelProducts(String filename) {
    	return Product.getByList(new ExcelReader(filename).getAllCells());
    }

    public static List<IProduct> fromExcelProducts() {
    	return fromExcelProducts("products.xlsx");
    }

}
