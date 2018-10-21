package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.pages.HomeMessagePage;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.tools.TestRunner;

public class YStasivTest extends TestRunner {

	@DataProvider // (parallel = true)
	public Object[][] currenciesType() {
		// Read from ...
		return new Object[][] { 
			{ Currencies.US_DOLLAR }, 
//			{ Currencies.EURO }, 
//			{ Currencies.POUND_STERLING }, 
			};
	}

	@Test(dataProvider = "currenciesType")
//CheckEmptyCartPageWithDifferentCurrencies
	public void CheckEmptyCartPage(Currencies currency) {
//Precondition: Load Application
		HomePage homePage = loadApplication();
		delayExecution(1000); //ForDemonstration
//Steps: Choose currencies and go to Shopping Cart
		homePage.chooseCurrency(currency).clickShoppingCart();;
		delayExecution(1000); //ForDemonstration
//Check if user see Empty Cart Page
		Assert.assertEquals(gotoEmptyShoppingCartPage().getEmptyCartText(),
				gotoEmptyShoppingCartPage().EXPECT_EMPTY_CART_TEXT);
		delayExecution(2000); //ForDemonstration
//TODO ПОМІНЯТИ НАЗАД НА ДОЛАР
	}//TODO Логер + репортер
	
	
	
		@DataProvider//(parallel = true)
	    public Object[][] productNames() {
	        // Read from ...
	        return new Object[][] { 
	            { "MacBook" },
	            { "iPhone" },
	            };
	    }

	@Test(dataProvider = "productNames", groups = {"addItemToCart"})//TODO УТОЧНИТИ ЯК ПОПРАВИТИ ЦЮ ФІГНЮ!!!
	public void AddItemToCart(String partialProductName) {
//Precondition: Load Application
        HomePage homePage = loadApplication();
        delayExecution(1000); //ForDemonstration
//Steps: Add product to cart
        HomeMessagePage homeMessagePage = homePage.putToCartProductByPartialName(partialProductName);
        delayExecution(1000); //ForDemonstration
        //
//Check if AlertMessage contains current text
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homeMessagePage.getAlertMessageText().contains(String.format(homeMessagePage.EXPECTED_MESSAGE_CART, partialProductName)));
        //TODO УТОЧНИТИ ЧИ СОФТ АСЕРТ АКТУАЛЬНИЙ ТУТ
        delayExecution(1000); //ForDemonstration
// Go to ShoppingCart and check if goods was added
        homePage.clickShoppingCart();
        Assert.assertTrue(gotoProductsListCartComponent().getProductsCartNameList().contains(partialProductName));
        delayExecution(2000); //ForDemonstration
    }//TODO Логер + репортер
}
