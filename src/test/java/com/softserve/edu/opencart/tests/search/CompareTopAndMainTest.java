package com.softserve.edu.opencart.tests.search;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.ProductsListRepository;
import com.softserve.edu.opencart.data.SortCriterias;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.search.SearchResultPage;
import com.softserve.edu.opencart.tools.SearchTestsRunner;

public class CompareTopAndMainTest extends SearchTestsRunner{
	@DataProvider(name = "hp")
	public static Object[][] resultListHP() {
		return new Object[][] {{"hp", ProductsListRepository.getHPNamesList(SortCriterias.DEFAULT)}};
	}
	
	@Test(dataProvider="hp")
    public void testTopAndMainForms(String request, ArrayList<String> expectedResultsList) {
        int delayTime = 1500;
		// open home page
        HomePage homePage = loadApplication();
        delayExecution(delayTime);
        
        //search by top search form
        homePage.setSearchProductField(request);//TODO add these methods as one method to HomePage
        homePage.clickSearchProductButton();
        delayExecution(delayTime);
        
        //check if result list is correct
        searchResultPage = new SearchResultPage(driver);
        delayExecution(delayTime);
        assertEquals(searchResultPage.getResultNamesList(), expectedResultsList);
        
        //search by main search form
        searchResultPage.getISearchEmptyResultPage().searchWithMainForm(request);
        delayExecution(delayTime);
        
        loadSearchResultPage();
		delayExecution(delayTime);
		
        assertEquals(searchResultPage.getResultNamesList(), expectedResultsList);
        delayExecution(delayTime);
    }
}
