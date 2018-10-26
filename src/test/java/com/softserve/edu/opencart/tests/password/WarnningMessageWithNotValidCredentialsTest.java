package com.softserve.edu.opencart.tests.password;

import org.testng.Assert;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.ILoginMessageErrorPage;
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
	    
	   ILoginMessageErrorPage loginMessagePageError = loadApplication()
	    		.gotoLogin()
	            .unsuccessfullLogin(invalidUser);
	            delayExecution(1000);
	    //
	    // Check
	    Assert.assertEquals(loginMessagePageError.getLoginMessageErrorPage().getAlertMessageText(),
	    		loginMessagePageError.getLoginMessageErrorPage().EXPECTED_WARNING_LOGIN);
	    delayExecution(1000);

	 
	     
	  
	
	}
}