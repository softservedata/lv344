package com.softserve.edu.opencart.pages.search;

import com.softserve.edu.opencart.data.ProductSubcategories;

public interface ISearchEmptyResultPage {
	public void clickDescriptionCheckBox();
	public void clickSubcategoryCheckBox();
	public void chooseProductCategory(ProductSubcategories subcategory);
	public ISearchResultPage searchWithMainForm(String request);
	public SearchEmptyResultPage emptySearchWithMainForm(String searchRequest);

}
