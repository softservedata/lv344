package com.softserve.edu.opencart.pages.search;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.data.ProductSubcategories;
import com.softserve.edu.opencart.pages.AStatusBarComponent;

public class SearchEmptyResultPage extends AStatusBarComponent implements ISearchEmptyResultPage{
	
	SearchCriteriaComponent searchCriteriaComponent ;
	
	public SearchEmptyResultPage(WebDriver driver) {
		super(driver);
		searchCriteriaComponent = new SearchCriteriaComponent(driver);
	}
	
	public SearchCriteriaComponent getSearchCriteriaComponent() {
		return searchCriteriaComponent;
	}
	
	//Business logic
	public void clickDescriptionCheckBox() {
		getSearchCriteriaComponent().clickDescriptionCheckBox();
	}
	
	public void clickSubcategoryCheckBox() {
		getSearchCriteriaComponent().clickSubcategoryCheckBox();
	}
	
	public void chooseProductCategory(ProductSubcategories subcategory) {
		getSearchCriteriaComponent().getSearchTexField().click();
		getSearchCriteriaComponent().chooseCategory(subcategory);
	}
	
	public ISearchResultPage searchWithMainForm(String request) {
		getSearchCriteriaComponent().fillSearchTextField(request);
		getSearchCriteriaComponent().clickSearchButton();
		return new SearchResultPage(driver);
	}
	
	
	public SearchEmptyResultPage emptySearchWithMainForm(String searchRequest) {
		getSearchCriteriaComponent().fillSearchTextField(searchRequest);
		getSearchCriteriaComponent().clickSearchButton();
		return new SearchEmptyResultPage(driver);
	}
}
