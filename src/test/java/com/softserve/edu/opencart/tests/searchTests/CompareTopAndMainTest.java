package com.softserve.edu.opencart.tests.searchTests;

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
        assertEquals(actualTopResultsList, expectedResultsList);
        //search by main search form

        searchResultPage.getISearchEmptyResultPage().searchWithMainForm(request);
        delayExecution(1000);
        searchResultPage = new SearchResultPage(driver);
        delayExecution(1000);
        List<String> actualMainResultsList = searchResultPage.getResultNamesList();
        assertEquals(actualMainResultsList, expectedResultsList);
        
        delayExecution(4000);
    }
}
