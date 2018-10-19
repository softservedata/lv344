package com.softserve.edu.opencart.pages.search;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.pages.AStatusBarComponent;
import com.softserve.edu.opencart.pages.ProductsListComponent;

public class SearchEmptyResultPage extends AStatusBarComponent{
	
	SearchCriteriaComponent searchCriteriaComponent ;
	
	public SearchEmptyResultPage(WebDriver driver) {
		super(driver);
		searchCriteriaComponent = new SearchCriteriaComponent(driver);
	}
	
	public SearchCriteriaComponent getSearchCriteriaComponent() {
		return searchCriteriaComponent;
	}
	public SearchEmptyResultPage emptySearchWithMainForm(String searchRequest) {
		getSearchCriteriaComponent().fillSearchTextField(searchRequest);
		getSearchCriteriaComponent().clickSearchButton();
		return new SearchEmptyResultPage(driver);
		
	}
}
