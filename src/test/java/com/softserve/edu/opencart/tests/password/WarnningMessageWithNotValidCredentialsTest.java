package com.softserve.edu.opencart.tests.password;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.LoginMessagePageError;
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
	    
	   LoginMessagePageError loginMessagePageError = loadApplication()
	    		.gotoLogin()
	            .unsuccessfullLogin(invalidUser);
	            delayExecution(1000);
	    //
	    // Check
	  //  Assert.assertEquals(loginMessagePage.getAlertMessageText(), loginMessagePage.EXPECTED_WARNING_LOGIN);

	    delayExecution(1000);

	}
}