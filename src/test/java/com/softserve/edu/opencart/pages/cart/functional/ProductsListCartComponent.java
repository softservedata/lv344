package com.softserve.edu.opencart.pages.cart.functional;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsListCartComponent{
	
	private WebDriver driver;
	private List<ProductCartComponent> productCartComponents;
	
	public ProductsListCartComponent(WebDriver driver) {
        this.driver = driver;
        initProductsListCartComponent();
	}
	
	private void initProductsListCartComponent() {
		productCartComponents = new ArrayList<>(); 
    	for (WebElement current : driver.findElements(By.cssSelector(".table-responsive tbody tr"))) {
    		productCartComponents.add(new ProductCartComponent(current));
        }
    }
	
	//ProductCartComponent
	public List<ProductCartComponent> getProductCartComponents() {
		return productCartComponents;
		}
	
	public ProductCartComponent getProductCartComponentByPartialName(String partialProductCartName) {
		ProductCartComponent result = null;
        for (ProductCartComponent current : getProductCartComponents()) {
            if (current.getNameText().toLowerCase()
            		.contains(partialProductCartName.toLowerCase())) {
                result = current;
                break;
            }
        }
        return result;
    }
	
	public List<String> getProductsCartNameList() {
        List<String> result = new ArrayList<>();
        for (ProductCartComponent current : getProductCartComponents()) {
            result.add(current.getNameText());
        }
        return result;
    }
	
	// Business Logic
	public void clickQuantityProductCartByPartialName(String partialProductCartName) {
		getProductCartComponentByPartialName(partialProductCartName)
			.clickQuantityField();		
	}
	
	public void clearQuantityProductCartByPartialName(String partialProductCartName) {
		getProductCartComponentByPartialName(partialProductCartName)
			.clearQuantityField();		
	}
	
	public void setQuantityProductCartByPartialName(String partialProductCartName, String numOfItems) {
		getProductCartComponentByPartialName(partialProductCartName)
			.setQuantityField(numOfItems);
	}
	
	public void updateProductCartByPartialName(String partialProductCartName) {
		getProductCartComponentByPartialName(partialProductCartName)
			.clickUpdateButton();
	}
	
	public void removeProductCartByPartialName(String partialProductCartName) {
		getProductCartComponentByPartialName(partialProductCartName)
			.clickRemoveButton();
	}
	
	
	
	
	
	
}