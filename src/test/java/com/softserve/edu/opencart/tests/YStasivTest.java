package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.pages.HomeMessagePage;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.tools.TestRunner;

public class YStasivTest extends TestRunner{

    @DataProvider//(parallel = true)
    public Object[][] productNames() {
        // Read from ...
        return new Object[][] { 
            { "MacBook" },
            { "iPhone" },
            };
    }

    @Test(dataProvider = "productNames")
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
}
