package com.softserve.edu.opencart.data;

import java.util.Map;

public interface IProduct {

    String getName();

    String getDescription();

    Map<Currencies, Double> getPrice();
    
    Map<Currencies, Double> getPriceExTax();

    double getPrice(Currencies currency);
    
    double getPriceExTax(Currencies currency);
    
}
