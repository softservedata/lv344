package com.softserve.edu.opencart.pages.search;

import java.util.List;

import org.openqa.selenium.WebDriver;
import com.softserve.edu.opencart.data.ShowLimits;
import com.softserve.edu.opencart.data.SortCriterias;
import com.softserve.edu.opencart.data.Views;
import com.softserve.edu.opencart.pages.ProductsListComponent;

public class SearchResultPage extends SearchEmptyResultPage implements ISearchResultPage{
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
	
	//Business logic
	public SearchResultPage getSearchResultPage() {
		return this;
	}
	
	public ISearchEmptyResultPage getISearchEmptyResultPage() {
		return this;
	}
	
	public ISearchResultPage chooseView(Views view) {
		if (view.equals(Views.LIST)) {
			getSearchFilterComponent().clickListButton();
		}else {
			getSearchFilterComponent().clickGridButton();
		}
		return new SearchResultPage(driver);
	}
	
	public ISearchResultPage chooseSortOrder(SortCriterias sortCriteria) {
		getSearchFilterComponent().clickSortByDropDown();
		getSearchFilterComponent().clickSortByOption(sortCriteria);
		return new SearchResultPage(driver);
	}
	
	public ISearchResultPage chooseShowLimit(ShowLimits showLimit) {
		getSearchFilterComponent().clickShowDropDown();
		getSearchFilterComponent().clickShowOption(showLimit);
		return new SearchResultPage(driver);
	}
	
	//search all goods on the page and get their names list
	public List<String> getResultNamesList(){
		return getProductListComponent().getProductsNameList();
	}
}
