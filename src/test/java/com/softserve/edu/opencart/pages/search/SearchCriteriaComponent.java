package com.softserve.edu.opencart.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.ProductSubcategories;

public class SearchCriteriaComponent{
		private WebDriver driver;
	 	private WebElement searchTexField;
		private WebElement searchButton;
	    private WebElement categoryDropDown;
	    private WebElement descriptionCheckBox;
	    private WebElement subcategoryCheckBox;
	    
	    public SearchCriteriaComponent(WebDriver driver) {
	    	this.driver = driver;
	    	initializeSearchCriteriaComponent();
		}
	    
	    public void initializeSearchCriteriaComponent() {
	    	searchTexField = driver.findElement(By.id("input-search"));
	    	searchButton = driver.findElement(By.id("button-search"));
	    	categoryDropDown = driver.findElement(By.name("category_id"));
	    	descriptionCheckBox = driver.findElement(By.id("description"));
	    	subcategoryCheckBox = driver.findElement(By.cssSelector(".checkbox-inline"));
	    }
	    
	    public WebElement getSearchTexField() {
			return searchTexField;
		}
	    
	    public void clickSearchField() {
	    	getSearchTexField().click();
	    }

	    
		public WebElement getSearchButton() {
			return searchButton;
		}
		
		public void clickSearchButton() {
			getSearchButton().click();
		}

		
		public WebElement getCategoryDropDown() {
			return categoryDropDown;
		}
		
		public void clickCategoryDropDown() {
			getCategoryDropDown().click();
		}
		
	
		public WebElement getDescriptionCheckBox() {
			return descriptionCheckBox;
		}
		
		public void clickDescriptionCheckBox() {
			getDescriptionCheckBox().click();
		}

		
		public WebElement getSubcategoryCheckBox() {
			return subcategoryCheckBox;
		}
		
		public void clickSubcategoryCheckBox() {
			getSubcategoryCheckBox().click();
		}
		
		
		public void chooseCategory(ProductSubcategories subcategory) {
			getCategoryDropDown().click();
			getCategoryDropDown().findElement(By.cssSelector("["+subcategory.toString()+"]")).click();
		}
		
		public void fillSearchTextField(String text) {
		    	getSearchTexField().click();
		    	getSearchTexField().clear();
		    	getSearchTexField().sendKeys(text);
		}
		
	    
}
