package com.softserve.edu.opencart.tests.search;


import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.ProductSubcategories;
import com.softserve.edu.opencart.data.ProductsListRepository;
import com.softserve.edu.opencart.data.ShowLimits;
import com.softserve.edu.opencart.data.SortCriterias;
import com.softserve.edu.opencart.data.Views;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.search.ISearchEmptyResultPage;
import com.softserve.edu.opencart.pages.search.ISearchResultPage;
import com.softserve.edu.opencart.pages.search.SearchResultPage;
import com.softserve.edu.opencart.tools.SearchTestsRunner;
import com.softserve.edu.opencart.tools.TestRunner;

	public class SearchWithEnterTest extends SearchTestsRunner{
		
		@DataProvider(name = "mac")
		   public static Object[][] resultListMac() {
		      return new Object[][] {{"mac", ProductsListRepository.getMacNamesList(SortCriterias.DEFAULT)}};
		   }
		
		//@Test
		public void regressTest() {
			HomePage homePage = loadApplication();
			delayExecution(1000);
			//search by top search form
	        homePage.setSearchProductField(" "+Keys.ENTER);
	        delayExecution(1000);
	        
	        //check if result is correct
	        searchResultPage = new SearchResultPage(driver);
	        searchResultPage.getISearchEmptyResultPage().chooseProductCategory(ProductSubcategories.MP3_PLAYERS__Apple);
	        delayExecution(2000);

		}
		
		
		@Test(dataProvider = "mac")
		public void testSearchWithEnter(String request, ArrayList<String> expectedResultsList) {
			int delayTime = 1500;
			// open home page
	        HomePage homePage = loadApplication();
	        delayExecution(delayTime);
	        
	        //search by top search form and press ENTER key
	        homePage.setSearchProductField(request+Keys.ENTER);
	        delayExecution(delayTime);
	        
	        searchResultPage = loadSearchResultPage();
	        delayExecution(delayTime);
	        
	        //check if result is correct
	        assertEquals(searchResultPage.getResultNamesList(), expectedResultsList);
		}
		
		
		
	    
	    
	   
	
	
	}
	