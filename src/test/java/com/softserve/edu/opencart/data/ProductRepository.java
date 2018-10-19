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

    public static Product iMac() {
        return new Product("iMac",
        		"Just when you thought iMac had everything, now there´s even more. More powerful Intel Core 2 Duo .."); 
    }
    public static Product macBookAir() {
        return new Product("MacBook Air",
        		"MacBook Air is ultrathin, ultraportable, and ultra unlike anything else. But you don’t lose..");
    }
    public static Product macBookPro() {
        return new Product("MacBook Pro",
        		"Latest Intel mobile architecture Powered by the most advanced mobile processors ..");
    }
    
    public static Product hp_lp3065() {
    	return new Product("HP LP3065",
        		"Stop your co-workers in their tracks with the "
        		+ "stunning new 30-inch diagonal HP LP3065 Flat Panel .."); 
    }
    public static Product hp_15_ay168sa() {
    	return new Product("HP 15-ay168sa",
        		"Designed for everyday tasks, this HP laptop provides "
        		+ "powerful performance and the tools to get every.."); 
    }
    public static Product hp_250_g5() {
    	return new Product("HP 250 G5",
        		"Take your work with you and quickly get the job done without "
        		+ "stress or waiting around with the speed.."); 
    }
    public static Product hp_laserjet_pro() {
    	return new Product("HP LaserJet Pro",
        		"Print onlyPrint speed letter: Up to 40 ppm (black)Auto "
        		+ "duplex printing; 2 paper trays (standard)Uses.."); 
    }
    public static Product hp_pavilion_x360() {
    	return new Product("HP Pavilion x360",
        		"A1 are open boxed units which have been returned usually "
        		+ "due to been unwanted. The units may have at.."); 
    }
    public static Product hp_sprocket() {
    	return new Product("HP Sprocket",
        		"Print photos directly from your smartphone or tabletPortable"
        		+ " - about the size of a cell phoneBluetoo.."); 
    }
    public static Product hp_x3000_wireless_mouse() {
    	return new Product("HP x3000 Wireless Mouse",
        		"Brand new, and authentic HP products ship from, and are "
        		+ "sold by Amazon.com (check above)Stylish, att.."); 
    }

}
