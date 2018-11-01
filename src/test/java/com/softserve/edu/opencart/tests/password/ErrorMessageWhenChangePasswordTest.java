package com.softserve.edu.opencart.tests.password;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.password.ChangePasswordPage;
import com.softserve.edu.opencart.pages.password.IErrorMessageChangePasswordPage;
import com.softserve.edu.opencart.pages.password.IErrorMessageConfirmPasswordPage;
import com.softserve.edu.opencart.tools.TestRunner;

public class ErrorMessageWhenChangePasswordTest extends TestRunner {

	 @DataProvider
	 public Object[][]errorMessageWhenPasswordShortLong()
	 {
	 return new Object[][] {{ UserRepository.get().customerIra(), "aaa", "aaa"} ,
		                   { UserRepository.get().customerIra(),  "aaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaa"} 
	 	};
	 }
	 
	
	@Test(priority = 0, dataProvider = "errorMessageWhenPasswordShortLong")
	 public void shortLongPassword(IUser validUser, String password, String confirmPassword) {
	
	// Precondition
	// Steps
		
	 ChangePasswordPage changePasswordPage = loadApplication()
			 .gotoLogin()
			 .successLogin(validUser)
			 .gotoChangePassword();
	 
    // Steps
	 IErrorMessageChangePasswordPage errorMessageChangePasswordPage = changePasswordPage.unsuccessfulChangePassword(password, confirmPassword);
	
	 //Check
	 Assert.assertEquals(errorMessageChangePasswordPage.getErrorMessageChangePasswordPage().getAlertMessageText(),
			 errorMessageChangePasswordPage.getErrorMessageChangePasswordPage().ERROR_MESSAGE_CHANGE_PASSWORD);	 
	 
	 errorMessageChangePasswordPage.getErrorMessageChangePasswordPage();	 
 }

	 @DataProvider
	 public Object[][] errorMessageWhenNotMatchPassword() {
		 return new Object[][] {{UserRepository.get().customerIra(),"aaaa", "aaaaa"}};
	 }

	 @Test(priority = 1, dataProvider = "errorMessageWhenNotMatchPassword")
	 public void notMatchPassword(IUser validUser, String password, String confirmPassword) {
	
	// Precondition		 
	// Steps
	 ChangePasswordPage changePasswordPage = loadApplication()
			 .gotoLogin()
			 .successLogin(validUser)
			 .gotoChangePassword();		  

	 // Steps
	IErrorMessageConfirmPasswordPage errorMessageConfirmPasswordPage = changePasswordPage.unsuccessfulConfirmPassword(password, confirmPassword);
	
  //Check
    Assert.assertEquals(errorMessageConfirmPasswordPage.getErrorMessageConfirmPasswordPage().getAlertMessageText(),
    		errorMessageConfirmPasswordPage.getErrorMessageConfirmPasswordPage().ERROR_MESSAGE_CONFIRM_PASSWORD);
    
    errorMessageConfirmPasswordPage.getErrorMessageConfirmPasswordPage();    
}

	@DataProvider
	public Object[][] errorMessageWhithEmptyPassword() {
		return new Object[][] {{ UserRepository.get().customerIra(), "", "aaa" },
				               { UserRepository.get().customerIra(), "aaa", "" }
		};
	}

	@Test(priority = 2, dataProvider = "errorMessageWhithEmptyPassword")
	public void emptyPassword(IUser validUser, String password, String confirmPassword) {
		//
		// Precondition
		// Steps

		ChangePasswordPage changePasswordPage = loadApplication()
				.gotoLogin()
				.successLogin(validUser)
				.gotoChangePassword();
		
		IErrorMessageChangePasswordPage errorMessageChangePasswordPage = changePasswordPage.unsuccessfulChangePassword(password, confirmPassword);
		delayExecution(1000);

		// Check

		Assert.assertEquals(errorMessageChangePasswordPage.getErrorMessageChangePasswordPage().getAlertMessageText(),
				errorMessageChangePasswordPage.getErrorMessageChangePasswordPage().ERROR_MESSAGE_CHANGE_PASSWORD);

		IErrorMessageConfirmPasswordPage errorMessageConfirmPasswordPage = errorMessageChangePasswordPage.getErrorMessageChangePasswordPage()
				.gotoErrorMessageConfirmPasswordPage();

		Assert.assertEquals(errorMessageConfirmPasswordPage.getErrorMessageConfirmPasswordPage().getAlertMessageText(),
				errorMessageConfirmPasswordPage.getErrorMessageConfirmPasswordPage().ERROR_MESSAGE_CONFIRM_PASSWORD);		
		
		errorMessageChangePasswordPage.getErrorMessageChangePasswordPage();

	}
}
