package com.qaCRM.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qaCRM.base.TestBase;
import com.qaCRM.pages.HomePage;
import com.qaCRM.pages.LoginPage;

//@Listeners(com.qaCRM.base.MyListener.class)
public class LoginApplication extends TestBase {
	
	LoginPage loginpage;
	HomePage homepage;
	Logger log;
	
	public LoginApplication() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		log = LogManager.getLogger(LoginApplication.class);
		loginpage  = new LoginPage();
		
		
	}
	
	
	@Test(priority=2, groups = {"Regression"})
	public void loginPageTitleTest() {
		String title = loginpage.pageTitleVal();
		Assert.assertEquals(title, "Cogmento CRM");
		log.info("page got verified");
	}
	
	@Test(priority=1, groups = {"Regression"})
	public void loginTest() {
		
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		log.info("login successful");
		 
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
