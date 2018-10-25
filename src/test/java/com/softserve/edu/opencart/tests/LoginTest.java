package com.softserve.edu.opencart.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.AccountInformationPage;
import com.softserve.edu.opencart.pages.AccountLogoutPage;
import com.softserve.edu.opencart.tools.TestRunner;
import com.softserve.training.Calc;

public class LoginTest extends TestRunner {
	//private static final Logger logger = LoggerFactory.getLogger(LoginTest.class); // LoggerFactory

	@DataProvider//(parallel = true)
    public Object[][] validUsers() {
        // Read from ...
        return new Object[][] { 
            { UserRepository.get().customerHahaha() },
            };
    }

    //@Test(dataProvider = "validUsers")
    public void checkLogin(IUser validUser) {
    	//logger.info("checkLogin start");
        //
        // Precondition
        // Steps
    	AccountInformationPage accountInformationPage = loadApplication()
        		.gotoLogin()
        		.successLogin(validUser)
        		.gotoAccountInformation();
        delayExecution(1000);
        //
        // Check
        Assert.assertEquals(accountInformationPage.getFirstnameFieldText(),
        		validUser.getFirstName());
        delayExecution(1000);
        //
        // Return to previous state
        // Steps
        AccountLogoutPage accountLogoutPage = accountInformationPage.gotoLogout();
        //
        // Check
        Assert.assertEquals(accountLogoutPage.getAccountLogoutLabelText(),
        		accountLogoutPage.EXPECTED_TEXT_LOGOUT);
        delayExecution(1000);
        //
        // Return to previous state
        accountLogoutPage.gotoHome();
        delayExecution(1000);
        //
        //logger.info("checkLogin done");
        //isTestSuccess = true;
    }

    @Test(dataProvider = "validUsers")
    public void checkLoginLogout(IUser validUser) {
        // Precondition
        // Steps
    	AccountInformationPage accountInformationPage = loadApplication()
        		.gotoLogin()
        		.successLogin(validUser)
        		.gotoAccountInformation();
        delayExecution(1000);
        //
        // Check
        Assert.assertEquals(accountInformationPage.getFirstnameFieldText(),
        		validUser.getFirstName());
        delayExecution(1000);
        //
        accountInformationPage.clickWishList();
        delayExecution(2000);
        // Return to previous state
    }
    
}
