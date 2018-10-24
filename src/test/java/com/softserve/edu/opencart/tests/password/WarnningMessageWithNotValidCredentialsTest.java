package com.softserve.edu.opencart.tests.password;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.LoginMessagePage;

import com.softserve.edu.opencart.tools.TestRunner;

public class WarnningMessageWithNotValidCredentialsTest extends TestRunner {

	@DataProvider
	  public Object[][] loginWithNotValidCredentials() {
	    return new Object[][] {{UserRepository.get().notExist() }, };
	  }

	  @Test(dataProvider = "loginWithNotValidCredentials")
	  public void warningMessage(IUser invalidUser) {
	    //
	    // Precondition
	    // Steps  
	   HomePage homePage = loadApplication();           
	    
	   LoginMessagePage loginMessagePage =  
	    		homePage.gotoLogin()
	           .unsuccessfullLogin(invalidUser);
	            delayExecution(1000);
	    //
	    // Check
	    Assert.assertEquals(loginMessagePage.getAlertMessageText(), loginMessagePage.EXPECTED_WARNING_LOGIN);
	    delayExecution(1000);

	 
	     
	  
	
	}
}