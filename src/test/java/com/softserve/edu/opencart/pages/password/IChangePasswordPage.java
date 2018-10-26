package com.softserve.edu.opencart.pages.password;

import com.softserve.edu.opencart.pages.IMyAccountMessagePage;

public interface IChangePasswordPage {

	public ChangePasswordPage getChangePasswordPage ();
	public IMyAccountMessagePage successfulChangePassword(String password, String confirmPassword);
	public IErrorMessageChangePasswordPage unsuccessfulChangePassword(String password, String confirmPassword);
	public IErrorMessageConfirmPasswordPage unsuccessfulConfirmPassword(String password, String confirmPassword);
}
