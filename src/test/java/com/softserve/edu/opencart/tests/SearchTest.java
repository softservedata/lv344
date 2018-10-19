package com.softserve.edu.opencart.tests;


import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.Product;
import com.softserve.edu.opencart.data.ProductSubcategories;
import com.softserve.edu.opencart.data.ProductsListRepository;
import com.softserve.edu.opencart.data.ShowLimits;
import com.softserve.edu.opencart.data.SortCriterias;
import com.softserve.edu.opencart.data.Views;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.search.SearchResultPage;
import com.softserve.edu.opencart.tools.TestRunner;

	public class SearchTest extends TestRunner {
		SearchResultPage searchResultPage;
		
		@DataProvider(name = "mac")
		   public static Object[][] resultListMac() {
		      return new Object[][] {{"mac", ProductsListRepository.getMacNamesList(SortCriterias.DEFAULT)}};
		   }
		
		@DataProvider(name = "hp")
		public static Object[][] resultListHP() {
			return new Object[][] {{"hp", ProductsListRepository.getHPNamesList(SortCriterias.DEFAULT)}};
		}
		
		@DataProvider(name = "hpWithFilters")
		public static Object[][] resultListHPWithFilters() {
			//do not sort list to check exact matching
			return new Object[][] {{"hp", ProductsListRepository.getHPNamesList(SortCriterias.PRICE_HIGH_LOW), SortCriterias.PRICE_HIGH_LOW, ShowLimits.x15, Views.LIST}};
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
	        searchResultPage.chooseProductCategory(ProductSubcategories.MP3_PLAYERS__Apple);
	        delayExecution(2000);

		}
		
		@Test(dataProvider = "mac")
		public void testSearchWithEnter(String request, ArrayList<String> expectedResultsList) 
				throws InterruptedException {
			// Precondition
	        HomePage homePage = loadApplication();
	        delayExecution(1000);
	        //search by top search form
	        homePage.setSearchProductField(request+Keys.ENTER);
	        delayExecution(1000);
	        //check if result is correct
	        searchResultPage = new SearchResultPage(driver);
	        delayExecution(1000);
	        List<String> actualResultsList = searchResultPage.getResultNamesList();
	        assertEquals(actualResultsList, expectedResultsList);
		}
		
		@Test(dataProvider = "hpWithFilters")
		public void testResultWithFilters(String request, ArrayList<String> expectedResultsList,
				SortCriterias sortCriteria, ShowLimits showLimit, Views view) 
				throws InterruptedException {
			//type one blank space to top text field
			// Precondition
	        HomePage homePage = loadApplication();
	        delayExecution(1000);
	        //search by top search form
	        homePage.setSearchProductField(" ");
	        homePage.clickSearchProductButton();
	        delayExecution(1000);
			//type search request to main search field
	        searchResultPage = new SearchResultPage(driver);
	        searchResultPage.getSearchCriteriaComponent().clickDescriptionCheckBox();
	        delayExecution(1000);
	        //click 'search in descriptions' checkbox
	        searchResultPage = (SearchResultPage)searchResultPage.searchWithMainForm(request);
	        delayExecution(1000);
			//click 'list' button
	        searchResultPage =  searchResultPage.setView(view);
			delayExecution(2000);//only for demonstration
			//click 'sort' drop-down list
			searchResultPage = searchResultPage.chooseSortCriteria(sortCriteria);
			delayExecution(2000);//only for demonstration
			//click 'show' drop-down list
			searchResultPage = searchResultPage.chooseShowLimit(showLimit);
			
		    delayExecution(1000);
		    List<String> actualResultsList = searchResultPage.getResultNamesList();
		    assertEquals(actualResultsList, expectedResultsList);
		}
		
	    @Test(dataProvider="hp")
	    public void testTopAndMainForms(String request, List<String> expectedResultsList) {
	        // Precondition
	        HomePage homePage = loadApplication();
	        delayExecution(1000);
	        //search by top search form
	        homePage.setSearchProductField(request);
	        homePage.clickSearchProductButton();
	        delayExecution(1000);
	        //check if result is correct
	        searchResultPage = new SearchResultPage(driver);
	        delayExecution(1000);
	        List<String> actualTopResultsList = searchResultPage.getResultNamesList();
	        Collections.sort(actualTopResultsList);
	        assertEquals(actualTopResultsList, expectedResultsList);
	        //search by main search form
	
	        searchResultPage.searchWithMainForm(request);
	        delayExecution(1000);
	        searchResultPage = new SearchResultPage(driver);
	        delayExecution(1000);
	        List<String> actualMainResultsList = searchResultPage.getResultNamesList();
	        assertEquals(actualMainResultsList, expectedResultsList);
	        
	        delayExecution(4000);
	    }
	
	
	}
	
