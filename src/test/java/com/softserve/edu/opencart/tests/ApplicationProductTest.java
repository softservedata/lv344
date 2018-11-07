package com.softserve.edu.opencart.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.tools.Application;
import com.softserve.edu.opencart.tools.ApplicationTestRunner;
import com.softserve.edu.opencart.tools.ListUtils;

public class ApplicationProductTest extends ApplicationTestRunner {

	@DataProvider//(parallel = true)
    public Object[][] validProduct() {
        // Read from ...
        return new Object[][] { 
            { ProductRepository.macBook(), Currencies.POUND_STERLING },
            //{ ProductRepository.iPhone(), Currencies.EURO },
            };
    }

	@DataProvider//(parallel = true)
    public Object[][] validCSVProducts() {
        return ListUtils.toMultiArray(ProductRepository.fromCsvProducts(),
        		Currencies.POUND_STERLING);
    }

	@DataProvider//(parallel = true)
    public Object[][] validExcelProducts() {
        return ListUtils.toMultiArray(ProductRepository.fromExcelProducts(),
        		Currencies.EURO);
    }

	@DataProvider//(parallel = true)
    public Object[][] validExternalProducts() {
		List<IProduct> commons = new ArrayList<>();
		commons.addAll(ProductRepository.fromExcelProducts());
		commons.addAll(ProductRepository.fromCsvProducts());
        return ListUtils.toMultiArray(commons,Currencies.EURO);
    }

	//@Test(dataProvider = "validProduct")
	//@Test(dataProvider = "validCSVProducts")
	//@Test(dataProvider = "validExcelProducts")
	@Test(dataProvider = "validExternalProducts")
    public void checkProductCurrency(IProduct product, Currencies currency) {
    	//logger.info("checkLogin start");
        //
        // Precondition
        // Steps
    	HomePage homePage = Application.get().loadApplication() 
        		.chooseCurrency(currency);
        delayExecution(1000);
        //
        // Check
        Assert.assertEquals(homePage
        		.getProductPriceAmountByPartialName(product.getName()),
        		product.getPrice(currency),
        		Application.PRICE_PRECISION);
        delayExecution(1000);
        //
        // Return to previous state
        // Steps
        delayExecution(1000);
        //
        //logger.info("checkLogin done");
    }

}
