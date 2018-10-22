package com.softserve.edu.opencart.tests.password;

import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.password.ChangePasswordPage;
import com.softserve.edu.opencart.pages.password.ErrorMessageChangePasswordPage;
import com.softserve.edu.opencart.pages.password.ErrorMessageConfirmPasswordPage;
import com.softserve.edu.opencart.tools.TestRunner;

public class ErrorMessageWhenChangePasswordTest extends TestRunner {

	 @DataProvider
	 public Object[][]errorMessageWhenPasswordShortLong() {
		 
	 return new Object[][] {{ UserRepository.get().customerIra(),"aaa", "aaa"} ,
		                   /* { UserRepository.get().customerIra(),  "aaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaa"} */ };
	 }
	 
	
	 //@Test(dataProvider = "errorMessageWhenPasswordShortLong")
	 public void shortLongPassword(IUser validUser, String password, String confirmPassword) {
	
	// Precondition
	// Steps

	 ChangePasswordPage changePasswordPage = loadApplication()
	 .gotoLogin()
	 .successLogin(validUser)
	 .gotoChangePassword();
	 
	 changePasswordPage.fillPasswordWithInvalidCredentialsField(password, confirmPassword);
	 
    // Steps
		ErrorMessageChangePasswordPage errorMessageChangePasswordPage = changePasswordPage.clickChangeContinueButton();
		delayExecution(1000);

	 //Check
	 Assert.assertEquals(errorMessageChangePasswordPage.getAlertMessageText(),
	 errorMessageChangePasswordPage.ERROR_MESSAGE_CHANGE_PASSWORD);
	 delayExecution(1000);
	 
	// changePasswordPage.gotoLogout();
	 }

	 @DataProvider
	 public Object[][] ErrorMessageWhenNotMatchPassword() {
	 return new Object[][] {{ UserRepository.get().customerIra(),"aaaa", "aaaaa"}};
	 }

//	 @Test(dataProvider = "ErrorMessageWhenNotMatchPassword")
	 public void notMatchPassword(IUser validUser, String password, String confirmPassword) {
	
	// Precondition		 
	// Steps
	 ChangePasswordPage changePasswordPage = loadApplication()
	 .gotoLogin()
	 .successLogin(validUser)
	 .gotoChangePassword();	
	  
	 changePasswordPage.fillPasswordWithInvalidCredentialsField(password, confirmPassword);

	 // Steps
	ErrorMessageConfirmPasswordPage errorMessageConfirmPasswordPage = changePasswordPage.clickConfirmContinueButton();
	delayExecution(1000);

  //Check
    Assert.assertEquals(errorMessageConfirmPasswordPage.getAlertMessageText(),
    errorMessageConfirmPasswordPage.ERROR_MESSAGE_CONFIRM_PASSWORD);
    delayExecution(1000);
}

	@DataProvider
	public Object[][] errorMessageWhithEmptyPassword() {
		return new Object[][] { { UserRepository.get().customerIra(), "", "aaa" },
				/*{ UserRepository.get().customerIra(), "aaa", "" }*/ };
	}

	@Test(dataProvider = "errorMessageWhithEmptyPassword")
	public void emptyPassword(IUser validUser, String password, String confirmPassword) {
		//
		// Precondition
		// Steps

		ChangePasswordPage changePasswordPage = loadApplication()
				.gotoLogin()
				.successLogin(validUser)
				.gotoChangePassword();
		
		changePasswordPage.fillPasswordWithInvalidCredentialsField(password, confirmPassword);

		ErrorMessageChangePasswordPage errorMessageChangePasswordPage = changePasswordPage.clickChangeContinueButton();
		delayExecution(1000);

		// Check

		Assert.assertEquals(errorMessageChangePasswordPage.getAlertMessageText(),
				errorMessageChangePasswordPage.ERROR_MESSAGE_CHANGE_PASSWORD);
		delayExecution(1000);

		ErrorMessageConfirmPasswordPage errorMessageConfirmPasswordPage = errorMessageChangePasswordPage
				.gotoErrorMessageConfirmPasswordPage();

		Assert.assertEquals(errorMessageConfirmPasswordPage.getAlertMessageText(),
				errorMessageConfirmPasswordPage.ERROR_MESSAGE_CONFIRM_PASSWORD);
		delayExecution(1000);

	}
}
