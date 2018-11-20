package com.softserve.edu.opencart.tests.search;

import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.ProductsListRepository;
import com.softserve.edu.opencart.data.SortCriterias;
import com.softserve.edu.opencart.data.User;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.LoginPage;
import com.softserve.edu.opencart.pages.MyAccountPage;
import com.softserve.edu.opencart.tools.SearchTestsRunner;

public class LoginResultTest extends SearchTestsRunner{
	@DataProvider(name = "mac")
	   public Object[][] resultListMac() {
	      return new Object[][] {{UserRepository.get().searchUser(),"mac", ProductsListRepository.getMacNamesList(SortCriterias.DEFAULT)}};
	   }
	
	@Test(dataProvider = "mac")
		public void testSearchLogined(User user, String request, ArrayList<String> expectedResultsList) {
			int delayTime = 1500; 
			// open home page
	        HomePage homePage = loadApplication();
	        delayExecution(delayTime);
	        
	        //open login page
	        LoginPage loginPage = homePage.gotoLogin();
	        
	        //login with valid credentials 
	        MyAccountPage myAccountPage = loginPage.successLogin(user);
	        
	        searchResultPage = myAccountPage.searchProduct(request);
	        
	        //check if result is correct
	        delayExecution(delayTime);
	        
	        assertEquals(searchResultPage.getResultNamesList(), expectedResultsList);
		}
}
