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
				String password, String confirmPassword) {
           ForgottenPasswordPage forgottenPasswordPage = loadApplication()
    				.gotoLogin()
    				.fillLoginFormAndClickForgottenPassword(validUser);
                     delayExecution(1000);
           LoginMessagePage loginMessagePage =  forgottenPasswordPage.fillEmailAdressFieldResetPassword(validUser);
           Assert.assertEquals(loginMessagePage.getAlertMessageText(),
	        		   loginMessagePage.EXPECTED_CONFIRMATION_LINK_TO_CHANGE_PASSWORD);
           delayExecution(1000);
           //
           //LoginUkrNetPage loginUkrNetPage = loadUkrNet();
           loginMessagePage = loadUkrNet()
           		.successLoginUkrNet(ukrNetUser)
           		.gotoInboxLetterPage()
           		.gotoFirstLinkConfirmationPage()
           		.gotoSecondLinkConfirmationPage()
           		.gotoResetPasswordPage()
           		.successResetPassword(password, confirmPassword);
           delayExecution(1000);
           //
           Assert.assertEquals(loginMessagePage.getAlertMessageText(),
				loginMessagePage.EXPECTED_MESSAGE_PASSWORD_UPDATED);
           delayExecution(1000);
           //
           switchToLoginedUkrNet().logoutUkrNet();
           delayExecution(1000);
	/*
	 * the method looks for requested id of among the open pages to log out from
	 * post
	 */
//	for (String handle : driver.getWindowHandles()) {
//
//		driver.switchTo().window(handle);
//		List<WebElement> currentList = driver.findElements(By.cssSelector(".login-button__user"));
//
//		if (currentList.size() > 0) {
//
//			driver.findElement(By.cssSelector(".login-button__user")).click();
//
//			driver.findElement(By.id("login__logout")).click();
//
//			break;
//		}
//	}

	/*
	 * the method looks for requested id of among the open pages to return to the
	 * page to enter new credentials
	 */
//	for (String handle : driver.getWindowHandles()) {
//		driver.switchTo().window(handle);
//
//		List<WebElement> currentList = driver.findElements(By.id("input-password"));
//		if (currentList.size() > 0) {
//			break;
//		}
//	}
	
//	      	 LoginMessagePage loginMessagePage =  resetPasswordPage.fillPassworFieldResetPassword(password, confirmPassword);
////	           
//	           Assert.assertEquals(loginMessagePage.getAlertMessageText(),
//	        		   loginMessagePage.EXPECTED_MESSAGE_PASSWORD_UPDATED);
//	      	 delayExecution(1000);
//	    				

//	
}}
