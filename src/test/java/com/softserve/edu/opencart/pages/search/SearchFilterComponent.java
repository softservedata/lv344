package com.softserve.edu.opencart.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.softserve.edu.opencart.data.ShowLimits;
import com.softserve.edu.opencart.data.SortCriterias;

public class SearchFilterComponent{
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
	 
	public void clickListButton() {
			getListButton().click();
	}
	 
	public WebElement getGridButton() {
			return gridButton;
	}
	
	public void clickSortByDropDown() {
		getSortByDropDown().click();
	}
	
	public void clickSortByOption(SortCriterias sortCriteria) {
		getSortByDropDown().findElement(By.cssSelector(sortCriteria.toString())).click();
	}
	
	public WebElement getSortByDropDown() {
			return sortByDropDown;
	}
	
	public void clickGridButton() {
		getGridButton().click();
	}
	
	public WebElement getShowDropDown() {
			return showDropDown;
	}
	
	public void clickShowDropDown() {
		getShowDropDown().click();
	}
	
	public void clickShowOption(ShowLimits showLimit) {
		getSortByDropDown().findElement(By.xpath(showLimit.toString())).click();
	}
	
}
