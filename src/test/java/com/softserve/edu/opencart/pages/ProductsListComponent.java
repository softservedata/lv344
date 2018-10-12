package com.softserve.edu.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsListComponent {

	private WebDriver driver;
	private List<ProductComponent> productComponents;
	
	public ProductsListComponent(WebDriver driver) {
        this.driver = driver;
        initProductsListComponents();
	}

    private void initProductsListComponents() {
    	productComponents = new ArrayList<>(); 
    	for (WebElement current : driver.findElements(By.cssSelector(".product-layout"))) {
            productComponents.add(new ProductComponent(current));
        }
    }

	// PageObject Atomic Operation

	//productComponents
	public List<ProductComponent> getProductComponents() {
		return productComponents;
	}
	
    public ProductComponent getProductComponentByPartialName(String partialProductName) {
//        WebElement result = null;
//        for (WebElement current : getListOptions()) {
//            if (current.getText().toLowerCase().contains(optionName.toLowerCase())) {
//                result = current;
//                break;
//            }
//        }
//        return result;
    	return null;
    }

    public List<String> getProductsNameList() {
//        List<String> result = new ArrayList<>();
//        for (WebElement current : getListOptions()) {
//            result.add(current.getText());
//        }
//        return result;
    	return null;
    }
	
}
