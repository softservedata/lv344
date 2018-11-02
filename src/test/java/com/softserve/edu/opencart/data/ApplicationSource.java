package com.softserve.edu.opencart.data;

import java.sql.Driver;

public class ApplicationSource implements IApplicationSource {

	// Browser Data
	private String browserName;
	private String driverPath;

	// private String browserPath;
	// private String defaulProfile;
	//
	// Implicit and Explicit Waits
	private long implicitWaitTimeOut;
	// private long implicitLoadTimeOut;
	// private long implicitScriptTimeOut;
	// private long explicitTimeOut;
	//
	// Localization Strategy
	// private String language;
	//
	// Search Strategy
	// private String searchStrategy;
	//
	// Logger Strategy
	// private String loggerStrategy;
	//
	// Reporter Console Output
	private boolean consoleOutput;
	//
	// URLs
	private String baseUrl;
	// private String userLoginUrl;
	// private String userLogoutUrl;
	//
	// private String adminLoginUrl;
	// private String adminLogoutUrl;
	//
	// Database Connection
	private String databaseUrl;
	private String databaseLogin;
	private String databasePassword;
	// TODO Must be String. Develop JdbcDriverWrapper (ConnectionManager) class
	private Driver jdbcDriver;

	// TODO Develop Builder
	public ApplicationSource(String browserName, String driverPath, long implicitWaitTimeOut,
			boolean consoleOutput, String baseUrl,
			String databaseUrl, String databaseLogin, String databasePassword, Driver jdbcDriver) {
		this.browserName = browserName;
		this.driverPath = driverPath;
		this.implicitWaitTimeOut = implicitWaitTimeOut;
		this.consoleOutput = consoleOutput;
		this.baseUrl = baseUrl;
		//
		this.databaseUrl = databaseUrl;
		this.databaseLogin = databaseLogin;
		this.databasePassword = databasePassword;
		this.jdbcDriver = jdbcDriver;
	}

	// setters

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public void setDriverPath(String driverPath) {
		this.driverPath = driverPath;
	}

	public void setImplicitWaitTimeOut(long implicitWaitTimeOut) {
		this.implicitWaitTimeOut = implicitWaitTimeOut;
	}

	public void setConsoleOutput(boolean consoleOutput) {
		this.consoleOutput = consoleOutput;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}

	public void setDatabaseLogin(String databaseLogin) {
		this.databaseLogin = databaseLogin;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	public void setJdbcDriver(Driver jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	// getters

	public String getBrowserName() {
		return browserName;
	}

	public String getDriverPath() {
		return driverPath;
	}

	public long getImplicitWaitTimeOut() {
		return implicitWaitTimeOut;
	}

	public boolean getConsoleOutput() {
		return consoleOutput;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public String getDatabaseLogin() {
		return databaseLogin;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public Driver getJdbcDriver() {
		return jdbcDriver;
	}

}