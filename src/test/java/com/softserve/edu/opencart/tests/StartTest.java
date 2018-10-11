package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.Currencies;
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

    @Test(dataProvider = "currencyData")
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
}
