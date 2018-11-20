package com.softserve.edu.opencart.pages;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.softserve.edu.opencart.pages.cart.EmptyShoppingCartPage;
import com.softserve.edu.opencart.pages.cart.ShoppingCartPage;
import com.softserve.edu.opencart.pages.search.SearchEmptyResultPage;
import com.softserve.edu.opencart.pages.search.SearchResultPage;
import com.softserve.edu.opencart.pages.wishlist.EmptyWishListPage;
import com.softserve.edu.opencart.pages.wishlist.WishListPage;
import com.softserve.edu.opencart.tools.RegexUtils;

public abstract class AHeadComponent {

	private class DropdownOptions {

		private List<WebElement> listOptions;
        
		public DropdownOptions(By searchLocator) {
            initListOptions(searchLocator);
        }

//        public DropdownOptions(By searchLocator, By lastLocator) {
//            initListOptions(searchLocator);
//            listOptions.add(driver.findElement(lastLocator));
//            //listOptions.add(search.getWebElement(lastLocator));
//        }
        
        private void initListOptions(By searchLocator){
            listOptions = driver.findElements(searchLocator);
            //listOptions = search.getWebElements(searchLocator);
            //listOptions = driver.findElements(By.cssSelector(".list-inline > li > a.dropdown-toggle + ul > li > a"));
            //listOptions = myAccount.findElements(By.cssSelector("+ ul > li > a"));
        }
        
        public List<WebElement> getListOptions() {
            return listOptions;
        }

        public WebElement getDropdownOptionByPartialName(String optionName) {
            WebElement result = null;
            for (WebElement current : getListOptions()) {
                if (current.getText().toLowerCase().contains(optionName.toLowerCase())) {
                    result = current;
                    break;
                }
            }
            return result;
        }
        
        public List<String> getListOptionsText() {
            List<String> result = new ArrayList<>();
            for (WebElement current : getListOptions()) {
                result.add(current.getText());
            }
            return result;
        }
        
        public void clickDropdownOptionByPartialName(String optionName) {
            getDropdownOptionByPartialName(optionName).click();
        }
    }

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	private final String OPTION_NOT_FOUND_MESSAGE = "Option %s not found in %s";
	private final String LOGIN_ERROR = "Login Error";
	protected final String TAG_ATTRIBUTE_VALUE = "value";
	//
	//protected static boolean loggedUser = false;
	protected static HashMap<Long, Boolean> loggedUsers = new HashMap<>();
	protected WebDriver driver;

	//	

	private WebElement currency;
	private WebElement myAccount;
	private WebElement wishList;
	private WebElement shoppingCart;
	private WebElement checkout;
	private WebElement logo;
	private WebElement searchProductField;
	private WebElement searchProductButton;
	private WebElement cartButton;
	private List<WebElement> menuTop;
	//
	//protected List<ProductComponent> productComponents;
	private DropdownOptions dropdownOptions;
	// private DropdownCart dropdownCart;
	
	protected AHeadComponent(WebDriver driver) {
        this.driver = driver;
        //
        currency = driver.findElement(By.cssSelector(".btn.btn-link.dropdown-toggle"));
        myAccount = driver.findElement(By.cssSelector(".list-inline > li > a.dropdown-toggle"));
        wishList = driver.findElement(By.id("wishlist-total"));
         shoppingCart=driver.findElement(By.partialLinkText("Shopping Cart"));
        // Do not Work with CSS ver. 3.x
        //shoppingCart = driver.findElement(By.cssSelector("a:has('.fa.fa-shopping-cart')")); // may be null
        shoppingCart = driver.findElement(By.cssSelector("a[title='Shopping Cart']"));
        //checkout=driver.findElement(By.partialLinkText("Checkout"));
        // Do not Work with CSS ver. 3.x
        //checkout = driver.findElement(By.cssSelector("a:has('.fa.fa-share')")); // may be null
        checkout = driver.findElement(By.cssSelector("a[title='Checkout']"));
        logo = driver.findElement(By.cssSelector("#logo > a"));
        searchProductField = driver.findElement(By.name("search"));
        searchProductButton = driver.findElement(By.cssSelector(".btn.btn-default.btn-lg"));
        cartButton = driver.findElement(By.cssSelector("#cart > button"));
        menuTop = driver.findElements(By.cssSelector("ul.nav.navbar-nav > li"));
    }

	// PageObject Atomic Operation
	
	//currency
	public WebElement getCurrency() {
		log.trace("getCurrency() running return currency;");
        return currency;
    }
	
	public String getCurrencyText() {
        return getCurrency().getText().substring(0, 1);
    }
	
	public void clickCurrency() {
        getCurrency().click();
    }
	
	public void clickCurrencyByPartialName(String optionName) {
		clickSearchProductField();
        clickCurrency();
        createDropdownOptions(By.cssSelector("div.btn-group.open ul.dropdown-menu li"));
        clickDropdownOptionByPartialName(optionName);
    }
	
	//myAccount
	public WebElement getMyAccount() {
        return myAccount;
    }
	
	public String getMyAccountText() {
        return getMyAccount().getText();
    }
	
	public void clickMyAccount() {
        getMyAccount().click();
    }
	
	public void clickAccountOptionByPartialName(String optionName) {
		clickSearchProductField();
		clickMyAccount();
        createDropdownOptions(By.cssSelector(".dropdown-menu.dropdown-menu-right li"));
        clickDropdownOptionByPartialName(optionName);
    }
	
	//wishList
	public WebElement getWishList() {
        return wishList;
    }
	
	public String getWishListText() {
        return getWishList().getText();
    }
	
	public int getWishListNumber() {
		return RegexUtils.extractFirstNumber(getWishListText());
	}

	public void clickWishList() {
        getWishList().click();
    }
	
	//shoppingCart
	public WebElement getShoppingCart() {
        return shoppingCart;
    }
	
	public String getShoppingCartText() {
        return getShoppingCart().getText();
    }
	
	 public void clickShoppingCart() {
         getShoppingCart().click();
     }

	//checkout
	public WebElement getCheckout() {
        return checkout;
    }
	
	public String getCheckoutText() {
        return getCheckout().getText();
    }    
		
	public void clickCheckout() {
        getCheckout().click();
    }
	
	//logo
	public WebElement getLogo() {
        return logo;
    }
	
	public void clickLogo() {
        getLogo().click();
    }
	
	//searchProductField
	public WebElement getSearchProductField() {
        return searchProductField;
    }
	
	public String getSearchProductFieldText() {
        return getSearchProductField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
	
	public void setSearchProductField(String text) {
        getSearchProductField().sendKeys(text);
    }
	 
	public void clearSearchProductField() {
         getSearchProductField().clear();
    }
	 
	public void clickSearchProductField() {
        getSearchProductField().click();
    }
	
	//searchProductButton
	public WebElement getSearchProductButton() {
        return searchProductButton;
    }
	
	public void clickSearchProductButton() {
        getSearchProductButton().click();
    }
	
	//cartButton
	public WebElement getCartButton() {
        return cartButton;
    }

	public String getCartButtonText() {
		return getCartButton().getText();
	}

	public void clickCartButton() {
        getCartButton().click();
    }
	
	public WebElement getCartTotal() {
        return getCartButton().findElement(By.id("cart-total"));
    }
	
	public String getCartTotalText() {
		return getCartTotal().getText();
	}
	
	public int getCartAmount() {
        return RegexUtils.extractFirstNumber(getCartTotalText());
    }

    public double getCartSum() {
        return RegexUtils.extractFirstDouble(getCartTotalText());
    }
    
	//menuTop
	public List<WebElement> getMenuTop() {
        return menuTop;
    }
	
	public List<String> getMenuTopTexts() {
        List<String> result = new ArrayList<>();
        for (WebElement current : getMenuTop()) {
            result.add(current.findElement(By
                    .cssSelector("a.dropdown-toggle")).getText());
        }
        return result;
    }
	
	public WebElement getMenuTopByCategoryPartialName(String categoryName) {
        WebElement result = null;
        for (WebElement current : getMenuTop()) {
            if (current.findElement(By.cssSelector("a.dropdown-toggle")).getText()
                    .toLowerCase().contains(categoryName.toLowerCase())) {
                result = current;
                break;
            }
        }
        return result;
    }
	
	// dropdownOptions
	protected DropdownOptions getDropdownOptions() {
        return dropdownOptions;
    }

	private void createDropdownOptions(By searchLocator) {
		dropdownOptions = new DropdownOptions(searchLocator);
	}

//	private void createDropdownOptions(By searchLocator, By lastLocator) {
//             dropdownOptions = new DropdownOptions(searchLocator, lastLocator);
//    }
	
	private boolean findDropdownOptionByPartialName(String optionName) {
        boolean isFound = false;
        if(getDropdownOptions() == null) {
        	throw new RuntimeException("DropdownOption is null");
        }
        for (String current : getDropdownOptions().getListOptionsText() ) {
            if (current.toLowerCase().contains(optionName.toLowerCase())) {
            	isFound = true;
            }
        }
        return isFound;
	}
	
	private void clickDropdownOptionByPartialName(String optionName) {
        if (!findDropdownOptionByPartialName(optionName)) {
        	throw new RuntimeException(String.format(OPTION_NOT_FOUND_MESSAGE,
        			optionName, dropdownOptions.getListOptionsText().toString()));
        }
        getDropdownOptions().clickDropdownOptionByPartialName(optionName);
        dropdownOptions = null;
    }
	
	    // TODO Move to Class
		// loggedUsers
		public boolean isLoggedUser() {
			Boolean isLogged = loggedUsers.get(Thread.currentThread().getId());
	        return (isLogged != null) && isLogged;
	    }

		public void setLoggedUser() {
			loggedUsers.put(Thread.currentThread().getId(), true);
	    }

		public void setUnloggedUser() {
			loggedUsers.put(Thread.currentThread().getId(), false);
	    }
		
	// Business Logic

	public LoginPage gotoLogin() {

		if (isLoggedUser()) {
			throw new RuntimeException(LOGIN_ERROR);
		}
		clickAccountOptionByPartialName("Login");
        return new LoginPage(driver);
    }
	
	
	
	public HomePage gotoHomeWithLogout() {
		AHeadComponent headComponent = this;
		if (isLoggedUser()) {
			headComponent = gotoLogout();
		}
		return headComponent.gotoHome();
    }
	
	public MyAccountPage gotoMyAccount() {
		if (!isLoggedUser()) {
			throw new RuntimeException(LOGIN_ERROR);
		}
		clickAccountOptionByPartialName("My Account");  // TODO enum
        return new MyAccountPage(driver);
    }
	
	public AccountLogoutPage gotoLogout() {
		log.debug("gotoLogout() start");
		if (!isLoggedUser()) {
			log.error("gotoLogout() throw new RuntimeException(" + LOGIN_ERROR + ");");
			throw new RuntimeException(LOGIN_ERROR);
		}
		log.trace("gotoLogout() running clickAccountOptionByPartialName();");
		clickAccountOptionByPartialName("Logout");
		log.trace("gotoLogout() running loggedUser = false;");
		//loggedUser = false;
		setUnloggedUser();
		log.debug("gotoLogout() done");
        return new AccountLogoutPage(driver);
    }


 	public HomePage gotoHome() {
 		clickLogo();
		return new HomePage(driver);
}
 	
 	public EmptyShoppingCartPage gotoEmptyShoppingCartPage() {
 		clickShoppingCart();
    	return new EmptyShoppingCartPage(driver);
    }
 
 	public ShoppingCartPage gotoShoppinCartPage() {
    	clickShoppingCart();
    	return new ShoppingCartPage(driver);
    }	
 	
    public WishListPage gotoWishListPage() {
    	return new WishListPage(driver);
    }
 	
    public EmptyWishListPage gotoEmptyWishListPage() {
   	return new EmptyWishListPage(driver);
   }
    
   public SearchEmptyResultPage emptySearchProduct() {
		clickSearchProductField();
		clearSearchProductField();
		clickSearchProductButton();
		return new SearchEmptyResultPage(driver);
	    }
   
   public SearchResultPage searchProduct(String request) {
		clickSearchProductField();
		clearSearchProductField();
		setSearchProductField(request);
		clickSearchProductButton();
		return new SearchResultPage(driver);
	    }

}
