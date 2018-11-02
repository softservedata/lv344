package com.softserve.edu.opencart.tools;
import com.softserve.edu.ukr.net.pages.LoginUkrNetPage;

import com.softserve.edu.ukr.net.pages.LoginedUkrNetPage;

public class TestRunnerUkrNet extends TestRunner {

	protected LoginUkrNetPage loadUkrNet() {
		driver.get("https://www.ukr.net/");
		return new LoginUkrNetPage(driver);
	}

	protected LoginedUkrNetPage switchToLoginedUkrNet() {
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if (driver.getTitle().toLowerCase().contains("ukr.net")) {
				break;
			}
		}
		return new LoginedUkrNetPage(driver);
	}

}