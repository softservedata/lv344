package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.pages.EmptyShoppingCartPage;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.tools.TestRunner;

public class YStasivTest extends TestRunner{

	@DataProvider//(parallel = true)
    public Object[][] currencyData() {
        // Read from ...
        return new Object[][] { 
            { Currencies.US_DOLLAR, "$" },
            };
    }

    @Test(dataProvider = "currencyData")
    public void t1(Currencies currency, String expectedCurrencyText) {
        //
        // Precondition
        HomePage homePage = loadApplication();
        delayExecution(1000);
        //
        // Steps
        homePage.clickShoppingCart();
        delayExecution(1000);
        //
        // Check
        Assert.assertEquals(gotoEmptyShoppingCartPage().getEmptyCartText(), gotoEmptyShoppingCartPage().EXPECT_EMPTY_CART_TEXT);
        System.out.println("Все ЗБС");
        delayExecution(2000);
        //
        // Return to previous state
        gotoEmptyShoppingCartPage().clickLogo();
    }
}
