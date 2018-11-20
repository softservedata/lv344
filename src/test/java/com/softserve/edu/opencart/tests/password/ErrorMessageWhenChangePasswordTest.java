package com.softserve.edu.opencart.tests.password;

import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.password.ChangePasswordPage;
import com.softserve.edu.opencart.pages.password.IErrorMessageChangePasswordPage;
import com.softserve.edu.opencart.pages.password.IErrorMessageConfirmPasswordPage;
import com.softserve.edu.opencart.tools.Application;
import com.softserve.edu.opencart.tools.ApplicationTestRunner;

public class ErrorMessageWhenChangePasswordTest extends ApplicationTestRunner {

	 @DataProvider//(parallel = true)
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
		
	 ChangePasswordPage changePasswordPage = Application.get().loadApplication()
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

	 @DataProvider//(parallel = true)
	 public Object[][] errorMessageWhenNotMatchPassword() {
		 return new Object[][] {{UserRepository.get().customerIra(),"aaaa", "aaaaa"}};
	 }

	 @Test(priority = 1, dataProvider = "errorMessageWhenNotMatchPassword")
	 public void notMatchPassword(IUser validUser, String password, String confirmPassword) {
	
	// Precondition		 
	// Steps
	 ChangePasswordPage changePasswordPage = Application.get().loadApplication() 
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

	@DataProvider//(parallel = true)
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

		ChangePasswordPage changePasswordPage = Application.get().loadApplication() 
				.gotoLogin()
				.successLogin(validUser)
				.gotoChangePassword();
		
		IErrorMessageChangePasswordPage errorMessageChangePasswordPage = changePasswordPage.unsuccessfulChangePassword(password, confirmPassword);
		
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
