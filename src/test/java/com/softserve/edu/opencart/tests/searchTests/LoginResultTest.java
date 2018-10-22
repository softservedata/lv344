package com.softserve.edu.opencart.tests.searchTests;

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
	   public static Object[][] resultListMac() {
	      return new Object[][] {{UserRepository.get().searchUser(),"mac", ProductsListRepository.getMacNamesList(SortCriterias.DEFAULT)}};
	   }
	
	@Test(dataProvider = "mac")
		public void testSearchLogined(User user, String request, ArrayList<String> expectedResultsList) {
			// Precondition
	        HomePage homePage = loadApplication();
	        delayExecution(1000);
	        //search by top search form
	        LoginPage loginPage = homePage.gotoLogin();
	        MyAccountPage myAccountPage = loginPage.successLogin(user);
	        myAccountPage.setSearchProductField(request);
	        myAccountPage.clickSearchProductButton();
	        //check if result is correct
	        searchResultPage = loadSearchResultPage();
	        delayExecution(1000);
	        List<String> actualResultsList = searchResultPage.getResultNamesList();
	        assertEquals(actualResultsList, expectedResultsList);
		}
}