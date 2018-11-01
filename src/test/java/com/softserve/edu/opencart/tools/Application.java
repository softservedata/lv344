package com.softserve.edu.opencart.tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;

import com.softserve.edu.opencart.data.ApplicationSourceRepository;
import com.softserve.edu.opencart.data.IApplicationSource;
import com.softserve.edu.opencart.pages.HeadComponent;
import com.softserve.edu.opencart.pages.HomePage;

public class Application {

	public static final double PRICE_PRECISION = 0.001;
	private final String Decimal_Format_2 = ".##";
	//
	// TODO Develop JdbcDriverWrapper (ConnectionManager) class
	private final String DB_CONNECTION_ERROR = "DB Connection Error, %s";
	//
    // Use Singleton, Repository
    private static volatile Application instance;
    //
    // Fields
    //
    // TODO Change for parallel work
    private IApplicationSource applicationSource;
    private CaptureUtils captureUtils;
    private ReporterWrapper reporter;
    //private FlexAssert flexAssert;
    //
    ////private BrowserWrapper browser;
    private HashMap<Long, BrowserWrapper> browsers;
    //
    //private ISearch search;
    //private ConnectionManager connectionManager;
    private Connection connection;
    // etc.

    private Application(IApplicationSource applicationSource) {
        this.applicationSource = applicationSource;
        browsers = new HashMap<>();
    }

    public static Application get() {
        return get(null);
    }

    public static Application get(IApplicationSource applicationSource) {
        if (instance == null) {
            synchronized (Application.class) {
                if (instance == null) {
                    if (applicationSource == null) {
                        applicationSource = ApplicationSourceRepository.defaultParameters();
                    }
                    instance = new Application(applicationSource);
                    //instance.initCaptureUtils();
                    //instance.initReporter(applicationSource);
                    //instance.initFlexAssert();
                    ////instance.initBrowser(applicationSource);
                    //instance.initSearch(applicationSource);
                    // initAccessToDB();
                    //instance.initConnectionManager(applicationSource);
                }
            }
        }
        return instance;
    }

    public static void remove() {
        if (instance != null) {
            // TODO Change for parallel work
            //instance.getBrowser().quit();
            //
            for (Long key : instance.browsers.keySet()) {
                BrowserWrapper browserWrapper = instance.browsers.get(key);
                if (browserWrapper != null) {
                    browserWrapper.quit();
                    instance.browsers.put(key, null);
                }
   
            }
            //
            //instance.connectionManager().closeAllConnections();
            if (instance.connection != null) {
            	// TODO Develop JdbcDriverWrapper (ConnectionManager) class
            	try {
					instance.connection.close();
				} catch (SQLException e) {
					//e.printStackTrace();
					throw new RuntimeException(String.format(instance.DB_CONNECTION_ERROR, e));
				}
            }
            instance = null;
        }
    }

    // getters

    public DecimalFormat getDecimalFormat2() {
    	return new DecimalFormat(Decimal_Format_2); 
    }

    // TODO Change for parallel work
    // TODO remove get
    public IApplicationSource getApplicationSource() {
        return applicationSource;
    }

    public CaptureUtils captureUtils() {
    	if (captureUtils == null) {
    		initCaptureUtils();
    	}
        return captureUtils;
    }

    public ReporterWrapper reporter() {
    	if (reporter == null) {
    		initReporter(getApplicationSource());
    	}
        return reporter;
    }

//    public FlexAssert flexAssert() {
//        return flexAssert;
//    }

    public BrowserWrapper getBrowser() {
        BrowserWrapper browserWrapper = browsers.get(Thread.currentThread().getId());
        if (browserWrapper == null) {
            browserWrapper = new BrowserWrapper(applicationSource);
            browsers.put(Thread.currentThread().getId(), browserWrapper);
        }
        return browserWrapper;
        //return browser;
    }

//    public ISearch search() {
//        return search;
//    }

//    public ConnectionManager connectionManager() {
//        return connectionManager;
//    }

	public Connection getConnection() {
		if (connection == null) {
			// TODO Develop JdbcDriverWrapper (ConnectionManager) class
			try {
				DriverManager.registerDriver(getApplicationSource().getJdbcDriver());
				connection = DriverManager.getConnection(getApplicationSource().getDatabaseUrl(),
						getApplicationSource().getDatabaseLogin(),
						getApplicationSource().getDatabasePassword());
			} catch (SQLException e) {
				//e.printStackTrace();
				throw new RuntimeException(String.format(DB_CONNECTION_ERROR, e));
			}
		}
		return connection;
	}

    // Initialization

    // TODO Change for parallel work
    private void initCaptureUtils() {
        // TODO Add parameters to applicationSource
        this.captureUtils = new CaptureUtils();
    }

    private void initReporter(IApplicationSource applicationSource) {
        this.reporter = new ReporterWrapper(applicationSource);
    }

//    private void initFlexAssert() {
//        this.flexAssert = new FlexAssert(reporter());
//    }

//    private void initBrowser(IApplicationSource applicationSource) {
//        //this.browser = new BrowserWrapper(applicationSource);
//        BrowserWrapper browser = new BrowserWrapper(applicationSource);
//        browsers.put(Thread.currentThread().getId(), browser);
//    }

//    private void initSearch(IApplicationSource applicationSource) {
//        this.search = new Search(applicationSource);
//    }

//    private void initConnectionManager(IApplicationSource applicationSource) {
//        this.connectionManager = new ConnectionManager(applicationSource);
//    }

    public double roundTo2digits(double number) {
    	return Double.parseDouble(roundTo2digitsToString(number));
    }

    public String roundTo2digitsToString(double number) {
    	return getDecimalFormat2().format(number + PRICE_PRECISION);
    }

    // Pages

    public HomePage loadApplication() {
        getBrowser().openUrl(getApplicationSource().getBaseUrl());
        // TODO Remove getBrowser().getDriver()
        return new HomePage(getBrowser().getDriver());
        //return new HomePage();
        //return HomePage.load(getBrowser().getDriver(), applicationSource.getBaseUrl());
    }

    public HomePage logoutApplication() {
    	// TODO move getDriver() to Search class 
        return new HeadComponent(getBrowser().getDriver()).gotoHomeWithLogout();
    	//return (new AHeadComponent(getBrowser().getDriver()){}).gotoHomeWithLogout();
    }
    
    // public LoginPage login() {
    // getBrowser().openUrl(applicationSource.getUserLoginUrl());
    // return new LoginPage();
    // }

    // public LogoutPage logout() {
    // getBrowser().openUrl(applicationSource.getUserLogoutUrl());
    // return new LogoutPage();
    // }

}