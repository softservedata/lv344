package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.AccountLogoutPage;
import com.softserve.edu.opencart.pages.HomeMessagePage;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.MyAccountPage;
import com.softserve.edu.opencart.pages.cart.functional.ShoppingCartMessagePage;
import com.softserve.edu.opencart.pages.cart.functional.ShoppingCartPage;
import com.softserve.edu.opencart.tools.TestRunner;
import com.softserve.edu.opencart.pages.wishlist.*;

public class WishTest extends TestRunner {
	@DataProvider // (parallel = true)
	public Object[][] validUsers() {
		// Read from ...
		return new Object[][] { { UserRepository.get().wishUser() }, };
	}

	@DataProvider // (parallel = true)
	public Object[][] SomeProduct() {
		// Read from ...
		return new Object[][] { { UserRepository.get().wishUser(), "MacBook" }, };
	}

	@Test(dataProvider = "validUsers")
	public void checkEmptyWishList(IUser validUser) {
		//
		// Precondition
		// Steps
		MyAccountPage myAccountPage = loadApplication().gotoLogin().successLogin(validUser);
		delayExecution(1000);

		myAccountPage.clickWishList();
		delayExecution(1000);

		// Check

		Assert.assertEquals(myAccountPage.gotoEmptyWishListPage().getEmptyWishListText(),
				myAccountPage.gotoEmptyWishListPage().EXPECT_EMPTY_WISH_TEXT);
		delayExecution(1000);

		myAccountPage = myAccountPage.gotoEmptyWishListPage().clickEmptyWishListButtonContinue();

		// Return to previous state
		// Steps
		AccountLogoutPage accountLogoutPage = myAccountPage.gotoLogout();
		accountLogoutPage.gotoHome();
		delayExecution(1000);
	}

	// Тест перевіряємо чи додається товар.
	@Test(dataProvider = "SomeProduct")
	public void addProductToWishList(IUser validUser, String partialProductName) {
		//
		// Precondition
		// Steps
		MyAccountPage myAccountPage = loadApplication().gotoLogin().successLogin(validUser);
		delayExecution(1000);

		HomePage homePage = myAccountPage.gotoHome();

		int countWishList = homePage.getWishListNumber();

		// Add product to Wish List
		HomeMessagePage homeMessagePage = homePage.putToWishProductByPartialName(partialProductName);
		delayExecution(1000);

		// Check Message about add to Wish List
		Assert.assertEquals(homeMessagePage.getAlertMessageText(),
				String.format(homeMessagePage.EXPECTED_MESSAGE_WISH, partialProductName));
		delayExecution(1000);

		// Close message
		homePage = homeMessagePage.closeAlertMessage();
		delayExecution(1000);
		// Check countWishList
		Assert.assertEquals(homePage.getWishListNumber(), countWishList + 1);

		// Go to Wish List
		homePage.clickWishList();
		delayExecution(1000);
		// Check in table to present ProductName
		Assert.assertEquals(homePage.gotoWishListPage().getWishListProductNameByPartialName(partialProductName),
				partialProductName);
		delayExecution(1000);

		WishListMessagePage wishListMessagePage = homePage.gotoWishListPage()
				.removeFromWishListProductByPartialName(partialProductName);
		Assert.assertEquals(wishListMessagePage.getAlertMessageText(),wishListMessagePage.EXPECTED_MESSAGE_WISH_REMOVE);
		delayExecution(1000);

		// Close message
		WishListPage wishListPage = wishListMessagePage.closeAlertMessage();

		// Go to myAccountPage and logout page
		myAccountPage = wishListPage.clickWishListButtonContinue();
		//delayExecution(1000);

		AccountLogoutPage accountLogoutPage = myAccountPage.gotoLogout();
		accountLogoutPage.gotoHome();
		delayExecution(1000);

		delayExecution(1000);

	}

	// Тест перевіряємо чи додається товар з віш ліста в шопінг.
	 @Test(dataProvider = "SomeProduct")
	public void addProductToCartFromWishList(IUser validUser, String partialProductName) {
		//
		// Precondition
		// Steps
		MyAccountPage myAccountPage = loadApplication().gotoLogin().successLogin(validUser);
		delayExecution(1000);

		HomePage homePage = myAccountPage.gotoHome();
		delayExecution(1000);

		// Add product to Wish List
		HomeMessagePage homeMessagePage = homePage.putToWishProductByPartialName(partialProductName);
		delayExecution(1000);

		// Close message
		homePage = homeMessagePage.closeAlertMessage();
		delayExecution(1000);

		// Go to Wish List
		homePage.clickWishList();
		delayExecution(1000);

		// Check in table to present ProductName
		//Assert.assertEquals(homePage.gotoWishListPage().getWishListProductNameByPartialName(partialProductName),
	//			partialProductName);
	//	delayExecution(1000);

		WishListMessagePage wishListMessagePage = homePage.gotoWishListPage()
				.putFromWishListToCartProductByPartialName(partialProductName);
		Assert.assertEquals(wishListMessagePage.getAlertMessageText(),
				String.format(homeMessagePage.EXPECTED_MESSAGE_CART, partialProductName));
		delayExecution(1000);

		wishListMessagePage = homePage.gotoWishListPage().removeFromWishListProductByPartialName(partialProductName);

		// Close message
		WishListPage wishListPage = wishListMessagePage.closeAlertMessage();

		// Go to myAccountPage and logout page
		myAccountPage = wishListPage.clickWishListButtonContinue();
		delayExecution(1000);

		ShoppingCartPage shoppingCartPage = myAccountPage.gotoShoppinCartPage();
		delayExecution(1000);
		ShoppingCartMessagePage cartMessagePage = shoppingCartPage.removeProductQuantityByPartialName(partialProductName);
		delayExecution(1000);

		AccountLogoutPage accountLogoutPage = cartMessagePage.gotoLogout();  //myAccountPage.gotoLogout();
		accountLogoutPage.gotoHome();
		delayExecution(1000);
	}

}
