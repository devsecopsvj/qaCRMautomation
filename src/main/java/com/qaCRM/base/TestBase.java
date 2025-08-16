package com.qaCRM.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qaCRM.utils.TestUtil;
import com.qaCRM.utils.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	static WebDriverWait wait;
	public static Logger logger;
	ExtentReports extent;

	public TestBase() {
		logger=LogManager.getLogger();
		
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\Vijay\\eclipse202503\\PractiveProj\\qaCRMautomation\\src\\main\\java\\com\\qaCRM\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void initialization() {
//		if (driver == null) {
	
			String browserName = prop.getProperty("browser");

			if (browserName.equalsIgnoreCase("chrome")) {
				String remote = System.getProperty("remoteUrl", System.getenv("REMOTE_URL"));
				String browser = System.getProperty("browser", System.getenv().getOrDefault("BROWSER","chrome"));

				if (remote != null && !remote.isBlank()) {
    // run on Selenium Docker
   				 if ("firefox".equalsIgnoreCase(browser)) {
       			 driver = new RemoteWebDriver(new URL(remote), new FirefoxOptions());
   				 } else {
       			 ChromeOptions opts = new ChromeOptions();
        		opts.addArguments("--headless=new","--no-sandbox","--disable-dev-shm-usage","--window-size=1280,800");
       			 driver = new RemoteWebDriver(new URL(remote), opts);
   				 }
					} else {
    // local fallback (Selenium Manager will fetch driver automatically if Chrome is installed)
   				 ChromeOptions opts = new ChromeOptions();
    			opts.addArguments("--headless=new","--no-sandbox","--disable-dev-shm-usage","--window-size=1280,800");
    			driver = new ChromeDriver(opts);
					}

			// } else if (browserName.equalsIgnoreCase("FF")) {
			// 	WebDriverManager.firefoxdriver().setup();
			// 	driver = new FirefoxDriver();
			// } else if (browserName.equalsIgnoreCase("edge")) {
			// 	WebDriverManager.edgedriver().setup();
			// 	driver = new EdgeDriver();
			// } else {
			// 	throw new RuntimeException("Browser not supported: " + browserName);
			// }
			logger.info("browser got initialized");
		
//		e_driver = new EventFiringWebDriver(driver);
//		// Now create object of EventListerHandler to register it with EventFiringWebDriver
//		eventListener = new WebEventListener();
//		e_driver.register(eventListener);
//		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		driver.get(prop.getProperty("url"));
		
//		String hubURL = "http://localhost:4444/wd/hub, http://192.168.1.221:4444/wd/hub";
//		DesiredCapabilities cap = new DesiredCapabilities();
//		cap.setPlatform(Platform.WIN11);
//		cap.setBrowserName("Chrome");
//		driver = new RemoteBrowser(new URL("http://localhost:4444/wd/hub"), cap));
//		driver.get(www.google.com);
//		System.out.Println(driver.getTitle());
		
		
		logger.info("URL is launched");
	}
//
//	@BeforeTest
//	public void config() {
//		
//		String path = System.getProperty("user.dir")+"\\Reports\\ExtentReport\\index.html";
//		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
//		reporter.config().setReportName("CRM app name");
//		reporter.config().setDocumentTitle("CRM Test Results");
//		
//		extent = new ExtentReports();
//		extent.attachReporter(reporter);
//		extent.setSystemInfo("tester","Vijay");
//		
//	}

	@AfterTest
	public void teardown() {
		if(driver!=null) {
		driver.quit();
		logger.info("Browser got closed");
		}
	}

}
