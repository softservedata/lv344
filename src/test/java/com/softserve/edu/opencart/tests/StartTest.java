package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.Product;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.HomeMessagePage;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.tools.TestRunner;

public class StartTest extends TestRunner {

	@DataProvider//(parallel = true)
    public Object[][] currencyData() {
        // Read from ...
        return new Object[][] { 
            { Currencies.POUND_STERLING, "£" },
            { Currencies.EURO, "€" },
            { Currencies.US_DOLLAR, "$" },
            };
    }

    //@Test(dataProvider = "currencyData")
    public void checkCurrency(Currencies currency, String expectedCurrencyText) {
        //
        // Precondition
        HomePage homePage = loadApplication();
        delayExecution(1000);
        //
        // Steps
        homePage = homePage.chooseCurrency(currency);
        delayExecution(1000);
        //
        // Check
        Assert.assertEquals(homePage.getCurrencyText(), expectedCurrencyText);
        delayExecution(2000);
        //
        // Return to previous state
    }

	@DataProvider//(parallel = true)
    public Object[][] productCurrencyData0() {
        // Read from ...
        return new Object[][] { 
//            { Currencies.POUND_STERLING, "MacBook", 487.62 },
//            { Currencies.EURO, "MacBook", 560.94 },
//            { Currencies.US_DOLLAR, "MacBook", 602.00 },
            { Currencies.US_DOLLAR, "iPhone", 123.20 },
            { Currencies.US_DOLLAR, "Apple Cinema 30", 122.00 },
            };
    }

	@DataProvider//(parallel = true)
    public Object[][] productCurrencyData() {
        // Read from ...
        return new Object[][] { 
            { Currencies.US_DOLLAR, ProductRepository.iPhone() },
            { Currencies.US_DOLLAR, ProductRepository.macBook() },
            };
    }

    @Test(dataProvider = "productCurrencyData")
    public void checkProductCurrency(Currencies currency, Product product) {
        //
        // Precondition
        HomePage homePage = loadApplication();
        delayExecution(1000);
        //
        // Steps
        homePage = homePage.chooseCurrency(currency);
        delayExecution(1000);
        //
        // Check
        Assert.assertEquals(homePage.getProductPriceAmountByPartialName(product.getName()),
        		product.getPrice(currency), PRECISION);
        delayExecution(2000);
        //
        // Return to previous state
    }

	@DataProvider//(parallel = true)
    public Object[][] productNames() {
        // Read from ...
        return new Object[][] { 
            { "MacBook" },
            { "iPhone" },
            };
    }

    //@Test(dataProvider = "productNames")
    public void checkProductToCart(String partialProductName) {
        //
        // Precondition
        HomePage homePage = loadApplication();
        delayExecution(1000);
        //
        // Steps
        HomeMessagePage homeMessagePage = homePage.putToCartProductByPartialName(partialProductName);
        delayExecution(1000);
        //
        // Check
        Assert.assertEquals(homeMessagePage.getAlertMessageText(),
        		String.format(homeMessagePage.EXPECTED_MESSAGE_CART, partialProductName));
        delayExecution(1000);
        //
        // Return to previous state
        homePage = homeMessagePage.closeAlertMessage();
        delayExecution(4000);
    }

    @Test(dataProvider = "productNames")
    public void checkProductToWishUnlogged(String partialProductName) {
        //
        // Precondition
        HomePage homePage = loadApplication();
        delayExecution(1000);
        //
        // Steps
        HomeMessagePage homeMessagePage = homePage.putToWishProductByPartialName(partialProductName);
        delayExecution(1000);
        //
        // Check
        Assert.assertEquals(homeMessagePage.getAlertMessageText(),
        		String.format(homeMessagePage.EXPECTED_MESSAGE_WISH_UNLOGGED, partialProductName));
        delayExecution(1000);
        //
        // Return to previous state
        homePage = homeMessagePage.closeAlertMessage();
        delayExecution(4000);
    }

}
