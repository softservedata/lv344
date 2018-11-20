package com.softserve.edu.opencart.tests.password;

import org.testng.Assert;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.IMyAccountMessagePage;
import com.softserve.edu.opencart.pages.password.ChangePasswordPage;
import com.softserve.edu.opencart.tools.Application;
import com.softserve.edu.opencart.tools.ApplicationTestRunner;

public class SuccessMessageWhenChangePasswordTest extends ApplicationTestRunner {

	@DataProvider
	public Object[][] loginWithValidCredentials() {
		return new Object[][] {{ UserRepository.get().customerIra(), "qwerty", "qwerty" } };
	}

	@Test(dataProvider = "loginWithValidCredentials")
	public void successWhenChangePassword(IUser validUser, String password, String confirmPassword) {
			
			// Precondition
			// Steps
        log.info("info***");
		ChangePasswordPage changePasswordPage = Application.get().loadApplication() 
				.gotoLogin()
				.successLogin(validUser)
				.gotoChangePassword();
		
		IMyAccountMessagePage myAccountMessagePage = changePasswordPage.successfulChangePassword(password, confirmPassword);	

			// Check
		Assert.assertEquals(myAccountMessagePage.getMyAccountMessagePage().getAlertMessageText(),
				myAccountMessagePage.getMyAccountMessagePage().EXPECTED_SUCCESS_CHANGE_PASSWORD);		
		
		myAccountMessagePage.getMyAccountMessagePage().gotoLogout()
		        .gotoLogin()
		        .successLogin(validUser);
		

	}
}
