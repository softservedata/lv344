package com.softserve.edu.opencart.tests.password;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.IUserUkrNet;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.data.UserUkrNetRepository;
import com.softserve.edu.opencart.pages.LoginMessagePage;
import com.softserve.edu.opencart.pages.password.ForgottenPasswordPage;
import com.softserve.edu.opencart.tools.TestRunnerUkrNet;
import com.softserve.edu.ukr.net.pages.LoginedUkrNetPage;

public class CheckPostWhenFofgotPasswordTest extends TestRunnerUkrNet {

	@DataProvider
	public Object[][] loginWithValidCredentials() {
		return new Object[][] {
			{ UserRepository.get().customerIra(),
			  UserUkrNetRepository.testAtqcShop(),
				"qwerty", "qwerty" }
		};
	}

	@Test(dataProvider = "loginWithValidCredentials")
	public void loginWhenFofgotPassword(IUser validUser, IUserUkrNet ukrNetUser, 
				String password, String confirmPassword){
           ForgottenPasswordPage forgottenPasswordPage = loadApplication()
    				.gotoLogin()
    				.fillLoginFormAndClickForgottenPassword(validUser);
                     
                     
           LoginMessagePage loginMessagePage =  forgottenPasswordPage.fillEmailAdressFieldResetPassword(validUser);
           Assert.assertEquals(loginMessagePage.getAlertMessageText(),
	        		   loginMessagePage.EXPECTED_CONFIRMATION_LINK_TO_CHANGE_PASSWORD);           
          
           loginMessagePage = loadUkrNet()
           		.successLoginUkrNet(ukrNetUser)
           		.gotoInboxLetterPage()
           		.gotoFirstLinkConfirmationPage()
           		.gotoSecondLinkConfirmationPage()
           		.gotoResetPasswordPage()
           		.successResetPassword(password, confirmPassword);
                      
            Assert.assertEquals(loginMessagePage.getAlertMessageText(),
				loginMessagePage.EXPECTED_MESSAGE_PASSWORD_UPDATED);
             switchToLoginedUkrNet().logoutUkrNet();
                   
           loginMessagePage.gotoLogin().successLogin(validUser).gotoLogout();
           
	}
}
