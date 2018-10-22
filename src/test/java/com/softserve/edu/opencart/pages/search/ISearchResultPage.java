package com.softserve.edu.opencart.pages.search;

import java.util.List;

import com.softserve.edu.opencart.data.ShowLimits;
import com.softserve.edu.opencart.data.SortCriterias;
import com.softserve.edu.opencart.data.Views;

public interface ISearchResultPage {
	
	public SearchResultPage getSearchResultPage();
	public ISearchResultPage chooseView(Views view);
	public ISearchResultPage chooseSortOrder(SortCriterias sortCriteria);
	public ISearchResultPage chooseShowLimit(ShowLimits showLimit);
	public List<String> getResultNamesList();
	public ISearchEmptyResultPage getISearchEmptyResultPage();
}
