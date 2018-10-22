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
        ProductComponent result = null;
        for (ProductComponent current : getProductComponents()) {
            if (current.getNameText().toLowerCase()
            		.contains(partialProductName.toLowerCase())) {
                result = current;
                break;
            }
        }
        return result;
    }

    public List<String> getProductsNameList() {
        List<String> result = new ArrayList<>();
        for (ProductComponent current : getProductComponents()) {
            result.add(current.getNameText());
        }
        return result;
    }
	
	// Business Logic

	public void addToCartProductByPartialName(String partialProductName) {
		getProductComponentByPartialName(partialProductName)
			.clickAddToCartButton();
	}

	public void addToWishProductByPartialName(String partialProductName) {
		getProductComponentByPartialName(partialProductName)
			.clickAddToWishButton();
	}

	public void addToCompareProductByPartialName(String partialProductName) {
		getProductComponentByPartialName(partialProductName)
			.clickAddToCompareButton();
	}

}
