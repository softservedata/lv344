<<<<<<< HEAD
package com.softserve.edu.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.data.Currencies;

public class HomePage extends AHeadComponent {

	private ProductsListComponent productsListComponent;
	
	public HomePage(WebDriver driver) {
		super(driver);
		initProductsListComponent();
	}
	
	private void initProductsListComponent() {
		productsListComponent = new  ProductsListComponent(driver);
	}
	
	// PageObject
	
	// productsListComponent
	public ProductsListComponent getProductsListComponent() {
		return productsListComponent;
	}
	
	// Business Logic

	public double getProductPriceAmountByPartialName(String partialProductName) {
		return getProductsListComponent()
				.getProductComponentByPartialName(partialProductName)
				.getPriceAmount();
	}

	public HomePage chooseCurrency(Currencies currency) {
        clickCurrencyByPartialName(currency.toString());
        return new HomePage(driver); 
    }

	public HomeMessagePage putToCartProductByPartialName(String partialProductName) {
		getProductsListComponent()
			.addToCartProductByPartialName(partialProductName);
        return new HomeMessagePage(driver); 
    }

	public HomeMessagePage putToWishProductByPartialName(String partialProductName) {
		getProductsListComponent()
			.addToWishProductByPartialName(partialProductName);
        return new HomeMessagePage(driver); 
    }

	public HomeMessagePage putToCompareProductByPartialName(String partialProductName) {
		getProductsListComponent()
			.addToCompareProductByPartialName(partialProductName);
        return new HomeMessagePage(driver); 
    }

}
=======
package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.Currencies;

public class HomePage extends AHeadComponent {

	public final String EXPECT_PRODUCT_LIST_TITLE = "Featured";
	
	private ProductsListComponent productsListComponent;
	private WebElement productsListComponentTitle;
	
	
	public HomePage(WebDriver driver) {
		super(driver);
		initProductsListComponent();
	}
	
	private void initProductsListComponent() {
		productsListComponent = new  ProductsListComponent(driver);
		productsListComponentTitle = driver.findElement(By.cssSelector("#content h3"));
	}
	
	// PageObject
	
	//productsListComponent
	public ProductsListComponent getProductsListComponent() {return productsListComponent;}
	
	//productsListComponentTitle
	public WebElement getProductsListComponentTitle() {return productsListComponentTitle;}
	public String getProductsListComponentTitleText() {return productsListComponentTitle.getText();}
	
	
	// Business Logic

	public double getProductPriceAmountByPartialName(String partialProductName) {
		return getProductsListComponent()
				.getProductComponentByPartialName(partialProductName)
				.getPriceAmount();
	}

	public HomePage chooseCurrency(Currencies currency) {
        clickCurrencyByPartialName(currency.toString());
        return new HomePage(driver); 
    }

	public HomeMessagePage putToCartProductByPartialName(String partialProductName) {
		getProductsListComponent()
			.addToCartProductByPartialName(partialProductName);
        return new HomeMessagePage(driver); 
    }

	public HomeMessagePage putToWishProductByPartialName(String partialProductName) {
		getProductsListComponent()
			.addToWishProductByPartialName(partialProductName);
        return new HomeMessagePage(driver); 
    }

	public HomeMessagePage putToCompareProductByPartialName(String partialProductName) {
		getProductsListComponent()
			.addToCompareProductByPartialName(partialProductName);
        return new HomeMessagePage(driver); 
    }

}
>>>>>>> yStasiv
