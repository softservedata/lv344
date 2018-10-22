package com.softserve.edu.opencart.tools;

import com.softserve.edu.opencart.pages.search.ISearchEmptyResultPage;
import com.softserve.edu.opencart.pages.search.ISearchResultPage;
import com.softserve.edu.opencart.pages.search.SearchEmptyResultPage;
import com.softserve.edu.opencart.pages.search.SearchResultPage;

public class SearchTestsRunner extends TestRunner{
	protected ISearchResultPage searchResultPage;
	protected ISearchEmptyResultPage searchEmptyResultPage;
	
	protected ISearchResultPage loadSearchResultPage() {
        return new SearchResultPage(driver);
    }
    
    protected ISearchEmptyResultPage loadEmptySearchResultPage() {
        return new SearchEmptyResultPage(driver);
    }
}
