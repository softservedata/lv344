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
        searchResultPage = loadSearchResultPage();
        searchResultPage.getISearchEmptyResultPage().clickDescriptionCheckBox();;
        delayExecution(1000);
        //click 'search in descriptions' checkbox
        searchResultPage = searchResultPage.getISearchEmptyResultPage().searchWithMainForm(request);
        delayExecution(1000);
		//click 'list' button
        searchResultPage =  searchResultPage.chooseView(view);
		delayExecution(2000);//only for demonstration
		//click 'sort' drop-down list
		searchResultPage = searchResultPage.chooseSortOrder(sortCriteria);
		delayExecution(2000);//only for demonstration
		//click 'show' drop-down list
		searchResultPage = searchResultPage.chooseShowLimit(showLimit);
		
	    delayExecution(1000);
	    List<String> actualResultsList = searchResultPage.getResultNamesList();
	    assertEquals(actualResultsList, expectedResultsList);
	}
}
