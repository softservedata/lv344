package com.softserve.edu.opencart.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.AccountLogoutPage;
import com.softserve.edu.opencart.pages.HomeMessagePage;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.MyAccountPage;
import com.softserve.edu.opencart.tools.TestRunner;

public class KOlyaTest extends TestRunner {
	@DataProvider // (parallel = true)
	public Object[][] validUsers() {
		// Read from ...
		return new Object[][] { { UserRepository.get().testUser()}, };
	}
	

	@DataProvider // (parallel = true)
	public Object[][] addProduct() {
		// Read from ...
		return new Object[][] { { UserRepository.get().testUser(), "MacBook"  }, };
	}

	//@Test(dataProvider = "validUsers")
	public void checkEmptyWishList(IUser validUser) {
		//
		// Precondition
		// Steps
		MyAccountPage myAccountPage = loadApplication().gotoLogin().successLogin(validUser);
		delayExecution(1000);

		myAccountPage.clickWishList();
		delayExecution(1000);

		// Check

		Assert.assertEquals(gotoEmptyWishListPage().getEmptyWishListText(),
				gotoEmptyWishListPage().EXPECT_EMPTY_WISH_TEXT);
		delayExecution(1000);

		myAccountPage = gotoEmptyWishListPage().clickEmptyWishListButtonContinue();

		// Return to previous state
		// Steps
		AccountLogoutPage accountLogoutPage = myAccountPage.gotoLogout();
		accountLogoutPage.gotoHome();
     	delayExecution(1000);
	}
	
	
	
	@Test(dataProvider = "addProduct")
		public void addProductToWishList(IUser validUser, String partialProductName) {
			//
			// Precondition
			// Steps
			MyAccountPage myAccountPage = loadApplication().gotoLogin().successLogin(validUser);
			delayExecution(1000);
			
			HomePage homePage = myAccountPage.gotoHome();
		    delayExecution(1000);

		    int countWishList=homePage.getWishListNumber();
		   //Add product to Wish List
		    HomeMessagePage homeMessagePage = homePage.putToWishProductByPartialName(partialProductName);
	        delayExecution(1000);
	        
	        
//	        // Check
	        Assert.assertEquals(homeMessagePage.getAlertMessageText(),
	        		String.format(homeMessagePage.EXPECTED_MESSAGE_WISH, partialProductName));
	        delayExecution(2000);  
	        
	        // Close message
	        homePage = homeMessagePage.closeAlertMessage();
	        delayExecution(1000);
	        
	    //    Assert.assertEquals(homePage.getWishListNumber(),countWishList+1); 

	     //   go to Wish List
	        homePage.clickWishList();
	        delayExecution(1000);	        	      
	      System.out.println("test +++++++1");
	      
	    //  System.out.println("test +++++++1"+    gotoWishListPage().getWishListProductNameByPartialName(partialProductName));
	     //   Assert.assertEquals(gotoWishListPage().getWishListProductNameByPartialName(partialProductName),partialProductName); 	        
	    //    System.out.println("test +++++++2");
	      
	      
	      gotoWishListPage().removeFromWishListProductByPartialName(partialProductName);
	      System.out.println("test +++++++2");
	      
	    
	        
	        myAccountPage= gotoWishListPage().clickWishListButtonContinue();
	        delayExecution(1000);
	        System.out.println("test +++++++3");
			// Return to previous state
			// Steps
			AccountLogoutPage accountLogoutPage = myAccountPage.gotoLogout();
			accountLogoutPage.gotoHome();
	     	delayExecution(1000);
		}
	
	
	
}