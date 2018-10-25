package com.softserve.edu.opencart.pages.password;

import com.softserve.edu.opencart.pages.IMyAccountMessagePage;
import com.softserve.edu.opencart.pages.MyAccountMessagePage;

public interface IChangePasswordPage {

	public ChangePasswordPage getChangePasswordPage ();
	public IMyAccountMessagePage sucesessfulChangePassword(String password, String confirmPassword);
	public IErrorMessageChangePasswordPage unsucesessfulChangePassword(String password, String confirmPassword);
	public IErrorMessageConfirmPasswordPage unsucesessfulConfirmPassword(String password, String confirmPassword);
}
