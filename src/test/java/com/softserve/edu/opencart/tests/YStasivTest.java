package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.pages.HomeMessagePage;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.cart.functional.ShoppingCartMessagePage;
import com.softserve.edu.opencart.pages.cart.functional.ProductCartComponent;
import com.softserve.edu.opencart.pages.cart.functional.ProductsListCartComponent;
import com.softserve.edu.opencart.tools.TestRunner;

public class YStasivTest extends TestRunner {
	
//TestData
	String testItem1 = "MacBook";
	String testItem2 = "iPhone";
	
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
		Assert.assertEquals(homePage.getCartSum(), 0 & homePage.getCartAmount(), 0);
		
		//TODO ЗАПИТАТИ ЗА СОФТ АСЕРТ, ЧОГО ВІН НЕ КИДАЄ ЕКСПШИН, ЧИ ВІН І НЕ МАЄ КИДАТИ, І ПОЧИТАТИ ПРО НЬОГО ТРОХИ ШОСЬ
		//плюс уточнити за футер
		
		delayExecution(1000); //ForDemonstration
		Assert.assertTrue(homePage.gotoLogin().getLastBreadcrumbText().contains("Login"));
		
		//TODO СПИТАТИ ЧИ КРАЩЕ ЗАЮЗАТИ ISDISPLAYED
		
		delayExecution(2000); //ForDemonstration
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
	            { testItem1 },
	            { testItem2 },
	            };
	    }

	//@Test(dataProvider = "productNames", enabled = true, groups = {"addItemToCart"})//TODO УТОЧНИТИ ЯК ПОПРАВИТИ ЦЮ ФІГНЮ!!!
	public void AddItemToCart(String partialProductName) {
//Precondition: Load Application
        HomePage homePage = loadApplication();
        delayExecution(1000); //ForDemonstration
        
//Steps: Add product to cart
        HomeMessagePage homeMessagePage = homePage.putToCartProductByPartialName(partialProductName);
        delayExecution(1000); //ForDemonstration

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
	
	
	
	@Test(enabled = true)
	public void ChangeNumOfItemsInCart() throws Exception {
//Precondition: Load Application
        HomePage homePage = loadApplication();
        delayExecution(1000); //ForDemonstration
//Steps: Add product to cart
        HomeMessagePage homeMessagePage = homePage.putToCartProductByPartialName(testItem1);
        delayExecution(1000); //ForDemonstration
//Check if AlertMessage contains current text
        Assert.assertTrue(homeMessagePage.getAlertMessageText().contains(String.format(homeMessagePage.EXPECTED_MESSAGE_CART, testItem1)));
//GoToShoppingCart
        homePage.clickShoppingCart();
//Check if goods was added
        Assert.assertTrue(gotoProductsListCartComponent().getProductsCartNameList().contains(testItem1));
        delayExecution(1000); //ForDemonstration
//Set quantity
        
//Update product
        ShoppingCartMessagePage cartMessagePage = gotoShoppinCartPage().updateProductQuantityByPartialName(testItem1);
//Check if AlertMessage contains current text
        Assert.assertTrue(homeMessagePage.getAlertMessageText().contains(cartMessagePage.EXPECTED_UPDATE_MESSAGE_CART));
        
	}
	

	
}
