package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.HomeMessagePage;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.MyAccountPage;
import com.softserve.edu.opencart.pages.cart.functional.EmptyShoppingCartPage;
import com.softserve.edu.opencart.pages.cart.functional.ShoppingCartMessagePage;
import com.softserve.edu.opencart.pages.cart.functional.ShoppingCartPage;
import com.softserve.edu.opencart.tools.TestRunner;

public class YStasivTest extends TestRunner {
	

	
//=====================================================================================================
//											SmokeTestOpenCart
//=====================================================================================================
	@Test(enabled = true)
	public void smokeTestOpenCart() {
	log.info("SmokeTestOpenCart start");
//Precondition: Load Application
		HomePage homePage = loadApplication();
		delayExecution(1000); //ForDemonstration
		
//Steps: Add product to cart
		homePage.getProductsListComponentTitleText();
		delayExecution(1000); //ForDemonstration
		
//Check if page contains curent data
		softAssert.assertEquals(homePage.getProductsListComponentTitleText(),homePage.EXPECT_PRODUCT_LIST_TITLE);
	log.info("Element on main page was found...");//debug
		delayExecution(1000); //ForDemonstration
		softAssert.assertEquals(homePage.getCartSum(), 0.0); softAssert.assertEquals(homePage.getCartAmount(), 0);
	log.info("Current data on page was found...");
		delayExecution(1000); //ForDemonstration
		Assert.assertTrue(homePage.gotoLogin().getLastBreadcrumbText().contains("Login"));	
	log.info("Element on enother page was found...");
		delayExecution(3000); //ForDemonstration
		softAssert.assertAll();
	}//TODO  + репортер?
	
//=====================================================================================================
//											CheckEmptyCartPage
//=====================================================================================================
	@DataProvider // (parallel = true)
	public Object[][] currenciesType() {
		// Read from ...
		return new Object[][] { 
			{ Currencies.US_DOLLAR },  
			};
	}

	@Test(dataProvider = "currenciesType", dependsOnMethods = { "smokeTestOpenCart" }, enabled = true)
//CheckEmptyCartPageWithDifferentCurrencies
	public void checkEmptyCartPage(Currencies currency) {
	log.info("CheckEmptyCartPage start");
//Precondition: Load Application
		HomePage homePage = loadApplication();
		delayExecution(1000); //ForDemonstration
		
//Steps: Choose currencies and go to Shopping Cart
		homePage = homePage.chooseCurrency(currency);
	log.info("US_DOLLAR was chosen...");
		delayExecution(1000); //ForDemonstration
		
//Check if user see Empty Cart Page
		EmptyShoppingCartPage emptyShoppingCartPage = homePage.gotoEmptyShoppingCartPage();
		Assert.assertEquals(emptyShoppingCartPage.getEmptyCartText(), emptyShoppingCartPage.EXPECT_EMPTY_CART_TEXT);
	log.info("Expected message about cart is empty was displayed...");
		delayExecution(3000); //ForDemonstration
	}//TODO репортер?
	
//=====================================================================================================
//												AddItemToCart
//=====================================================================================================	
		@DataProvider//(parallel = true)
	    public Object[][] productNames() {
	        // Read from ...
	        return new Object[][] { 
	            { ProductRepository.macBook().getName()},
//	            { ProductRepository.iPhone().getName() },
	            };
	    }

	@Test(dataProvider = "productNames", dependsOnMethods = { "smokeTestOpenCart" }, enabled = true)
	public void addItemToCart(String partialProductName) {
	log.info("AddItemToCart start with test item \"" + partialProductName + "\"");
//Precondition: Load Application
        HomePage homePage = loadApplication();
        delayExecution(1000); //ForDemonstration
        
//Steps: Add product to cart
        HomeMessagePage homeMessagePage = homePage.putToCartProductByPartialName(partialProductName);
    log.info("\"" + partialProductName + "\" was added to cart...");
        delayExecution(1000); //ForDemonstration
        
//Check if AlertMessage contains current text
        Assert.assertTrue(homeMessagePage.getAlertMessageText()
        		.contains(String.format(homeMessagePage.EXPECTED_MESSAGE_CART, partialProductName)));
    log.info("User see correct message, \"" + partialProductName + "\" was added...");
        delayExecution(1000); //ForDemonstration
        
//Go to ShoppingCart and check if goods was added
        ShoppingCartPage shoppingCartPage = homePage.gotoShoppinCartPage(); 
        Assert.assertTrue(shoppingCartPage.getProductsCartListComponent().getProductsCartNameList().contains(partialProductName));
    log.info("\"" + partialProductName + "\" displayed correctly on cart page...");
        delayExecution(1000); //ForDemonstration
        
//Return to previous state: remove ProductName from cart List
        shoppingCartPage.removeProductQuantityByPartialName(partialProductName);
        log.info("The \"" + partialProductName + "\" was remove from cart page...");
        delayExecution(3000); //ForDemonstration
    }//TODO  репортер?
	
	
//=====================================================================================================
//												ChangeNumOfItemsInCart
//=====================================================================================================	
	@Test(dataProvider = "productNames", dependsOnMethods = { "smokeTestOpenCart", "addItemToCart" }, enabled = true)
	public void changeNumOfItemsInCart(String partialProductName) {
	log.info("ChangeNumOfItemsInCart start with test item \"" + partialProductName + "\"");
	
//Precondition: Load Application
        HomePage homePage = loadApplication();
        delayExecution(1000); //ForDemonstration
        
//Steps: Add product to cart
        HomeMessagePage homeMessagePage = homePage.putToCartProductByPartialName(partialProductName);
    log.info("\"" + partialProductName + "\" was added to cart...");
        delayExecution(1000); //ForDemonstration
        
//Check if AlertMessage contains current text
        Assert.assertTrue(homeMessagePage.getAlertMessageText()
        		.contains(String.format(homeMessagePage.EXPECTED_MESSAGE_CART, partialProductName)));
    log.info("User see correct message, \"" + partialProductName + "\" was added...");
    
//GoToShoppingCart
        ShoppingCartPage shoppingCartPage = homePage.gotoShoppinCartPage();
        
//Check if goods was added
        Assert.assertTrue(shoppingCartPage.getProductsCartListComponent().getProductsCartNameList().contains(partialProductName));
    log.info("\"" + partialProductName + "\" displayed correctly on cart page...");
        delayExecution(1000); //ForDemonstration
        
//Set quantity and Update product
        ShoppingCartMessagePage cartMessagePage = shoppingCartPage.updateProductQuantityByPartialName(partialProductName, "5");
    log.info("Count changed correctly...");
    	delayExecution(1000); //ForDemonstration
    	
//Check if AlertMessage contains current text
        Assert.assertEquals(cartMessagePage.getAlertMessageText(), cartMessagePage.EXPECTED_UPDATE_MESSAGE_CART);
    log.info("User see correct message...");
        delayExecution(1000); //ForDemonstration
        
 //Return to previous state: remove ProductName from cart List
        cartMessagePage.closeAlertMessage().removeProductQuantityByPartialName(partialProductName);
    log.info("The \"" + partialProductName + "\" was remove from cart page...");
        delayExecution(3000); //ForDemonstration
    }//TODO  репортер?
	
//=====================================================================================================
//												ErrorMessageChangeNumOfItemsInCart
//=====================================================================================================
	@Test(dataProvider = "productNames", dependsOnMethods = { "smokeTestOpenCart", "addItemToCart" }, enabled = true)
	public void errorMessageChangeNumOfItemsInCart(String partialProductName) {
	log.info("ErrorMessageChangeNumOfItemsInCart start with test item \"" + partialProductName + "\"");
	
//Precondition: Load Application
        HomePage homePage = loadApplication();
        delayExecution(1000); //ForDemonstration
        
//Steps: Add product to cart
        HomeMessagePage homeMessagePage = homePage.putToCartProductByPartialName(partialProductName);
    log.info("\"" + partialProductName + "\" was added to cart...");
    
        delayExecution(1000); //ForDemonstration
//Check if AlertMessage contains current text
        Assert.assertTrue(homeMessagePage.getAlertMessageText()
        		.contains(String.format(homeMessagePage.EXPECTED_MESSAGE_CART, partialProductName)));
    log.info("User see correct message, \"" + partialProductName + "\" was added...");
    
//GoToShoppingCart
        ShoppingCartPage shoppingCartPage = homePage.gotoShoppinCartPage();
        
//Check if goods was added
        Assert.assertTrue(shoppingCartPage.getProductsCartListComponent().getProductsCartNameList().contains(partialProductName));
    log.info("\"" + partialProductName + "\" displayed correctly on cart page...");
        delayExecution(1000); //ForDemonstration
        
//Set quantity and Update product
        ShoppingCartMessagePage cartMessagePage = shoppingCartPage.updateProductQuantityByPartialName(partialProductName, "test");
    log.info("Count changed correctly...");
    
//Check if AlertMessage contains current text
        Assert.assertEquals(cartMessagePage.getAlertMessageText(), cartMessagePage.EXPECTED_UPDATE_MESSAGE_CART);
    log.info("User see correct message...");
        delayExecution(1000); //ForDemonstration 
        
 //Return to previous state: remove ProductName from cart List
        cartMessagePage.closeAlertMessage().removeProductQuantityByPartialName(partialProductName);
    log.info("The \"" + partialProductName + "\" was remove from cart page...");
        delayExecution(3000); //ForDemonstration
	}
	
//=====================================================================================================
//												CartAfterRelogin
//=====================================================================================================	
	@DataProvider // (parallel = true)
	public Object[][] SomeProduct() {
		// Read from ...
		return new Object[][] { 
			{ UserRepository.get().yStasiv(), ProductRepository.macBook().getName() },
			};
	}
	@Test(dataProvider = "SomeProduct", dependsOnMethods = { "smokeTestOpenCart", "addItemToCart" }, enabled = true)
	public void cartAfterRelogin(IUser validUser, String partialProductName) {
	log.info("CartAfterRelogin start with test item \"" + partialProductName + "\" and validUser");
	
//Precondition: Login
		MyAccountPage myAccountPage = loadApplication().gotoLogin().successLogin(validUser);
		delayExecution(1000);
	log.info("The user was logged in...");
	
//back to main page
		HomePage homePage = myAccountPage.gotoHome();
		delayExecution(1000);
		
//Add product to cart 
		HomeMessagePage homeMessagePage = homePage.putToCartProductByPartialName(partialProductName);
        delayExecution(1000); //ForDemonstration
    log.info("The \"" + partialProductName + "\" was added to catr...");
//Check if AlertMessage contains current text
        Assert.assertTrue(homeMessagePage.getAlertMessageText()
        		.contains(String.format(homeMessagePage.EXPECTED_MESSAGE_CART, partialProductName)));
    log.info("The user see correct message, \"" + partialProductName + "\" was added...");
        delayExecution(1000); //ForDemonstration
        
//Close message
      	homePage = homeMessagePage.closeAlertMessage();
      	delayExecution(1000); //ForDemonstration

//Go to ShoppingCart and check if goods was added
        ShoppingCartPage shoppingCartPage = homePage.gotoShoppinCartPage(); 
        Assert.assertTrue(shoppingCartPage.getProductsCartListComponent().getProductsCartNameList().contains(partialProductName));
    log.info("The \"" + partialProductName + "\" displayed correctly on cart page...");
        delayExecution(1000); //ForDemonstration
                           
//Logout and login again
        shoppingCartPage = shoppingCartPage.clickFirstBreadcrumb().gotoLogout().gotoLogin().successLogin(validUser)
        		.gotoHome().gotoShoppinCartPage();
    log.info("The user was relogged in...");
    
//Check if goods left  	
       Assert.assertTrue(shoppingCartPage.getProductsCartListComponent().getProductsCartNameList().contains(partialProductName));
   log.info("The \"" + partialProductName + "\" left and displayed correctly on cart page...");
		delayExecution(1000);
		
//Return to previous state: remove ProductName from cart List
        shoppingCartPage.removeProductQuantityByPartialName(partialProductName);
    log.info("The \"" + partialProductName + "\" was remove from cart page...");
        homePage = shoppingCartPage.gotoEmptyShoppingCartPage().clickContinueButton();
		delayExecution(1000); //ForDemonstration

//Logout		
		homePage.gotoLogout().gotoHome();
		log.info("The user was logged out...");
		delayExecution(3000); //ForDemonstration
	}

}
