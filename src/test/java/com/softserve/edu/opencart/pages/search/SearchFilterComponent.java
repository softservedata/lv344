package com.softserve.edu.opencart.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.ProductSubcategories;
import com.softserve.edu.opencart.data.ShowLimits;
import com.softserve.edu.opencart.data.SortCriterias;

public class SearchFilterComponent {
	private WebDriver driver;
	
	private WebElement listButton;
	private WebElement gridButton;
    private WebElement sortByDropDown;
    private WebElement showDropDown;
	
	public SearchFilterComponent(WebDriver driver) {
		this.driver = driver;
        initSearchFilterComponent();
	}
	private  void initSearchFilterComponent() {
		listButton = driver.findElement(By.id("list-view"));
		gridButton = driver.findElement(By.id("grid-view"));
		sortByDropDown = driver.findElement(By.id("input-sort"));
		showDropDown = driver.findElement(By.id("input-limit"));
		
	}
	 public WebElement getListButton() {
			return listButton;
	}
	 
	public WebElement getGridButton() {
			return gridButton;
	}
	
	public WebElement getSortByDropDown() {
			return sortByDropDown;
	}
	
	public WebElement getShowDropDown() {
			return showDropDown;
	}
	
	public void clickListButton() {
		getListButton().click();
	}
	
	public void clickGridButton() {
		getGridButton().click();
	}
	
	
	
	public SearchResultPage chooseSortOrder(SortCriterias sortCriteria) {
		getSortByDropDown().click();
		getSortByDropDown().findElement(By.cssSelector(sortCriteria.toString())).click();
		return new SearchResultPage(driver);
	}
	
	public SearchResultPage chooseShowLimit(ShowLimits showLimit) {
		getSortByDropDown().click();
		getSortByDropDown().findElement(By.xpath(showLimit.toString())).click();
		return new SearchResultPage(driver);
	}
}
