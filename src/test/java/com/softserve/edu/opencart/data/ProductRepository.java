package com.softserve.edu.opencart.data;

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
        		.addPrice(Currencies.EURO, 560.94)
        		.addPrice(Currencies.POUND_STERLING, 487.62)
        		.addPrice(Currencies.US_DOLLAR, 602.00)
        		.addPriceExTax(Currencies.EURO, 560.94)
        		.addPriceExTax(Currencies.POUND_STERLING, 405.00)
        		.addPriceExTax(Currencies.US_DOLLAR, 500.00);
    }

    public static Product iPhone() {
        return new Product("iPhone",
        		"iPhone is a revolutionary new mobile phone that allows you to make a call by simply tapping a nam..")
        		.addPrice(Currencies.EURO, 114.80)
        		.addPrice(Currencies.POUND_STERLING, 99.79)
        		.addPrice(Currencies.US_DOLLAR, 123.20)
        		.addPriceExTax(Currencies.EURO, 94.11)
        		.addPriceExTax(Currencies.POUND_STERLING, 81.81)
        		.addPriceExTax(Currencies.US_DOLLAR, 101.00);
    }

}
