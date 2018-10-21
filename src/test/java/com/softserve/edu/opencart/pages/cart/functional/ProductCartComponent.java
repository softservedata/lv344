package com.softserve.edu.opencart.pages.cart.functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.tools.RegexUtils;

public class ProductCartComponent {
	
	   	private WebElement cartProductLayout;
	   	//
	   	private WebElement name;
	    //private WebElement unitPrice;
	    private WebElement totalPrice;
		private WebElement quantityField;
		private WebElement updateButton;
		private WebElement removeButton;


		public ProductCartComponent(WebElement cartProductLayout) {
			this.cartProductLayout = cartProductLayout;
			initProductCartComponent();
		}

	//TODO ДОПИСАТИ ПЕРЕВІРКУ ЧИ МИ НА СТОРІНЦІ
		private void initProductCartComponent() {
			//productListComponent = productLayout.findElement(By.cssSelector(".table-responsive tbody tr"));
			name = cartProductLayout.findElement(By.cssSelector("form .text-left a"));
			//unitPrice = cartProductLayout.findElement(By.cssSelector(".price"));
			//totalPrice = cartProductLayout.findElement(By.xpath("//div[contains(@class,'table')]//a[text()='MacBook']/../..//td[contains(text(),'$')]"));
			quantityField = cartProductLayout.findElement(By.cssSelector(".input-group.btn-block input"));
			updateButton = cartProductLayout.findElement(By.cssSelector(".fa.fa-refresh"));
			removeButton = cartProductLayout.findElement(By.cssSelector(".fa.fa-times-circle"));

		}

		//CartProductLayout
		public WebElement getCartProductLayout() {return cartProductLayout;}
		
		//Name
		public WebElement getName() {return name;}
		public String getNameText() {return getName().getText();}
		public void clickName() {getName().click();}
		
		// QuantityField
		public WebElement getQuantityField() {return quantityField;}
		public void setQuantityField(String text) {getQuantityField().sendKeys(text);}
		public void clickQuantityField() {getQuantityField().click();}
		public void clearQuantityField() {getQuantityField().clear();}
		
		//UpdateButton
		public WebElement getUpdateButton() {return updateButton;}
		public void clickUpdateButton() {getUpdateButton().click();}
		
		//RemoveButton
		public WebElement getRemoveButton() {return removeButton;}
		public void clickRemoveButton() {getRemoveButton().click();}
		
		// TotalPrice;
	    public WebElement getTotalPrice() {return totalPrice;}
	    public String getTotalPriceText() {return getTotalPrice().getText();}
	    public double getTotalPriceAmount() {return RegexUtils.extractFirstDouble(getTotalPriceText());}

		// Business Logic
	}
