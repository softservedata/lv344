package com.softserve.edu.opencart.data;

import java.sql.Driver;

public interface IApplicationSource {

	String getBrowserName();

	String getDriverPath();

	long getImplicitWaitTimeOut();

	boolean getConsoleOutput();
	
	String getBaseUrl();

	String getDatabaseUrl();

	String getDatabaseLogin();

	String getDatabasePassword();

	Driver getJdbcDriver();

}
