package com.softserve.edu.opencart.pages.search;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.data.ProductSubcategories;
import com.softserve.edu.opencart.data.ShowLimits;
import com.softserve.edu.opencart.data.SortCriterias;
import com.softserve.edu.opencart.data.Views;
import com.softserve.edu.opencart.pages.ProductsListComponent;

public class SearchResultPage extends SearchEmptyResultPage{
	private SearchCriteriaComponent searchCriteriaComponent;
	private SearchFilterComponent searchFilterComponent;
	private ProductsListComponent productsListComponent;
	
	public SearchResultPage(WebDriver driver) {
		super(driver);
		searchCriteriaComponent = new SearchCriteriaComponent(driver);
		searchFilterComponent = new SearchFilterComponent(driver);
		productsListComponent = new ProductsListComponent(driver);
	}
	
	public SearchFilterComponent getSearchFilterComponent() {
		return searchFilterComponent;
	}
	
	public SearchCriteriaComponent getSearchCriteriaComponent() {
		return searchCriteriaComponent;
	}
	
	public ProductsListComponent getProductListComponent() {
		return productsListComponent;
	}

	public SearchResultPage setView(Views view) {
		if (view.equals(Views.LIST)) {
			getSearchFilterComponent().clickListButton();
		}else {
			getSearchFilterComponent().clickGridButton();
		}
		return new SearchResultPage(driver);
	}
	
	public SearchResultPage searchWithMainForm(String request) {
		getSearchCriteriaComponent().fillSearchTextField(request);
		getSearchCriteriaComponent().clickSearchButton();
		return new SearchResultPage(driver);
	}
	
	public void chooseProductCategory(ProductSubcategories productSubcategories) {
		getSearchCriteriaComponent().getSearchTexField().click();
		getSearchCriteriaComponent().chooseCategory(productSubcategories);
	}
	
	public SearchResultPage chooseSortCriteria(SortCriterias sortCriteria) {
		getSearchCriteriaComponent().getSearchTexField().click();
		return getSearchFilterComponent().chooseSortOrder(sortCriteria);
	}
	
	public SearchResultPage chooseShowLimit(ShowLimits showLimit) {
		getSearchCriteriaComponent().getSearchTexField().click();
		return getSearchFilterComponent().chooseShowLimit(showLimit);
	}
	
	
	
	
	public List<String> getResultNamesList(){
		return getProductListComponent().getProductsNameList();
	}
}
