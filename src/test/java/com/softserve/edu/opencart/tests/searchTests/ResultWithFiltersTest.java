package com.softserve.edu.opencart.tests.searchTests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.ProductsListRepository;
import com.softserve.edu.opencart.data.ShowLimits;
import com.softserve.edu.opencart.data.SortCriterias;
import com.softserve.edu.opencart.data.Views;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.tools.SearchTestsRunner;

public class ResultWithFiltersTest extends SearchTestsRunner{
	@DataProvider(name = "hpWithFilters")
	public static Object[][] resultListHPWithFilters() {
		//do not sort list to check exact matching
		return new Object[][] {{"hp", 
			ProductsListRepository.getHPNamesList(SortCriterias.PRICE_HIGH_LOW), 
			SortCriterias.PRICE_HIGH_LOW, ShowLimits.x15, Views.LIST}};
	}
	
	@Test(dataProvider = "hpWithFilters")
	public void testResultWithFilters(String request, ArrayList<String> expectedResultsList,
			SortCriterias sortCriteria, ShowLimits showLimit, Views view)  {
		int delayTime = 1500; 
		// Precondition
        HomePage homePage = loadApplication();
        delayExecution(delayTime);
        
        //search by top search form
        homePage.setSearchProductField(" ");//TODO add these methods as one method to HomePage
        homePage.clickSearchProductButton();
        delayExecution(delayTime);
        
		//type search request to main search field
        searchResultPage = loadSearchResultPage();
        //check 'description' textbox
        searchResultPage.getISearchEmptyResultPage().clickDescriptionCheckBox();
        delayExecution(delayTime);
        
        //search with main form
        searchResultPage = searchResultPage.getISearchEmptyResultPage().searchWithMainForm(request);
        delayExecution(delayTime);
        
		//choose number of goods on the page 
        searchResultPage =  searchResultPage.chooseView(view);
		delayExecution(delayTime);
		
		searchResultPage = searchResultPage.chooseSortOrder(sortCriteria);
		delayExecution(delayTime);
		
		searchResultPage = searchResultPage.chooseShowLimit(showLimit);
	    delayExecution(delayTime);
	    
	    assertEquals(searchResultPage.getResultNamesList(), expectedResultsList);
	}
}
