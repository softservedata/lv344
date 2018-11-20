package com.softserve.edu.opencart.tests.password;

import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.ILoginMessageErrorPage;
import com.softserve.edu.opencart.tools.Application;
import com.softserve.edu.opencart.tools.ApplicationTestRunner;

public class WarnningMessageWithNotValidCredentialsTest extends ApplicationTestRunner {

	@DataProvider
	public Object[][] loginWithNotValidCredentials() {
		return new Object[][] { { UserRepository.get().notExist() }, };
	}

	@Test(dataProvider = "loginWithNotValidCredentials")
	public void warningMessage(IUser invalidUser) {
		//
		// Precondition
		// Steps
		ILoginMessageErrorPage loginMessagePageError = Application.get().loadApplication() 
				.gotoLogin()
				.unsuccessfullLogin(invalidUser);

		// Check
		Assert.assertEquals(loginMessagePageError.getLoginMessageErrorPage().getAlertMessageText(),
				loginMessagePageError.getLoginMessageErrorPage().EXPECTED_WARNING_LOGIN);
  log.info("finish");
	}
}