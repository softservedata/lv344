package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.pages.HomeMessagePage;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.cart.functional.EmptyShoppingCartPage;
import com.softserve.edu.opencart.pages.cart.functional.ShoppingCartMessagePage;
import com.softserve.edu.opencart.pages.cart.functional.ShoppingCartPage;
import com.softserve.edu.opencart.tools.TestRunner;

public class YStasivTest extends TestRunner {
	

	//@Test(enabled = true)
	public void SmokeTestOpenCart() {
		SoftAssert softAssert = new SoftAssert();
//Precondition: Load Application
		HomePage homePage = loadApplication();
		delayExecution(1000); //ForDemonstration
		
//Steps: Add product to cart
		homePage.getProductsListComponentTitleText();
		delayExecution(1000); //ForDemonstration

//Check if page contains curent data
		softAssert.assertEquals(homePage.getProductsListComponentTitleText(),homePage.EXPECT_PRODUCT_LIST_TITLE);
		delayExecution(1000); //ForDemonstration
		softAssert.assertEquals(homePage.getCartSum(), 0.0); softAssert.assertEquals(homePage.getCartAmount(), 0);
		delayExecution(1000); //ForDemonstration
		Assert.assertTrue(homePage.gotoLogin().getLastBreadcrumbText().contains("Login"));	
		delayExecution(2000); //ForDemonstration
		softAssert.assertAll();
	}//TODO Логер + репортер

	@DataProvider // (parallel = true)
	public Object[][] currenciesType() {
		// Read from ...
		return new Object[][] { 
			{ Currencies.US_DOLLAR }, 
//			{ Currencies.EURO }, 
//			{ Currencies.POUND_STERLING }, 
			};
	}

	//@Test(dataProvider = "currenciesType", enabled = true)
//CheckEmptyCartPageWithDifferentCurrencies
	public void CheckEmptyCartPage(Currencies currency) {
//Precondition: Load Application
		HomePage homePage = loadApplication();
		delayExecution(1000); //ForDemonstration
		
//Steps: Choose currencies and go to Shopping Cart
		homePage = homePage.chooseCurrency(currency);
		delayExecution(1000); //ForDemonstration
		
		EmptyShoppingCartPage emptyShoppingCartPage = homePage.gotoEmptyShoppingCartPage();
//Check if user see Empty Cart Page
		Assert.assertEquals(emptyShoppingCartPage.getEmptyCartText(), emptyShoppingCartPage.EXPECT_EMPTY_CART_TEXT);
		delayExecution(2000); //ForDemonstration
//TODO ПОМІНЯТИ НАЗАД НА ДОЛАР
	}//TODO Логер + репортер
	
	
	
		@DataProvider//(parallel = true)
	    public Object[][] productNames() {
	        // Read from ...
	        return new Object[][] { 
	            { "MacBook" },
//	            { "iPhone" },
	            };
	    }

	//@Test(dataProvider = "productNames", enabled = true, groups = {"addItemToCart"})
	public void AddItemToCart(String partialProductName) {
//Precondition: Load Application
        HomePage homePage = loadApplication();
        delayExecution(1000); //ForDemonstration
        
//Steps: Add product to cart
        HomeMessagePage homeMessagePage = homePage.putToCartProductByPartialName(partialProductName);
        delayExecution(1000); //ForDemonstration

//Check if AlertMessage contains current text
        Assert.assertTrue(homeMessagePage.getAlertMessageText().contains(String.format(homeMessagePage.EXPECTED_MESSAGE_CART, partialProductName)));
        //TODO УТОЧНИТИ ЧИ СОФТ АСЕРТ АКТУАЛЬНИЙ ТУТ
        delayExecution(1000); //ForDemonstration
        
//Go to ShoppingCart and check if goods was added
        ShoppingCartPage shoppingCartPage = homePage.gotoShoppinCartPage(); 
        Assert.assertTrue(shoppingCartPage.getProductsCartListComponent().getProductsCartNameList().contains(partialProductName));
        delayExecution(2000); //ForDemonstration
    }//TODO Логер + репортер
	
	
	//
	@Test(enabled = true)
	public void ChangeNumOfItemsInCart() throws Exception {
//Precondition: Load Application
        HomePage homePage = loadApplication();
        delayExecution(1000); //ForDemonstration
//Steps: Add product to cart
        HomeMessagePage homeMessagePage = homePage.putToCartProductByPartialName("MacBook");
        delayExecution(1000); //ForDemonstration
//Check if AlertMessage contains current text
        Assert.assertTrue(homeMessagePage.getAlertMessageText().contains(String.format(homeMessagePage.EXPECTED_MESSAGE_CART, "MacBook")));
//GoToShoppingCart
        ShoppingCartPage shoppingCartPage = homePage.gotoShoppinCartPage();
//Check if goods was added
        Assert.assertTrue(shoppingCartPage.getProductsCartListComponent().getProductsCartNameList().contains("MacBook"));
        delayExecution(1000); //ForDemonstration
//Set quantity and Update product
        ShoppingCartMessagePage cartMessagePage = shoppingCartPage.updateProductQuantityByPartialName("MacBook", "5");
        System.out.println("weeeee");
//Check if AlertMessage contains current text
        Assert.assertEquals(cartMessagePage.getAlertMessageText(), cartMessagePage.EXPECTED_UPDATE_MESSAGE_CART);
        System.out.println("weeee2");
        delayExecution(5000); //ForDemonstration
        
	}
	

	
}
