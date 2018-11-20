package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.AccountInformationPage;
import com.softserve.edu.opencart.pages.AccountLogoutPage;
import com.softserve.edu.opencart.tools.TestRunner;

public class LoginTest extends TestRunner {

	@DataProvider//(parallel = true)

    public Object[][] validUsers() {
        // Read from ...
        return new Object[][] { 
            { UserRepository.get() .customerHahaha()}
        };                     
    }

    @Test(dataProvider = "validUsers")
    public void checkLogin(IUser validUser) {
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
    }
}
