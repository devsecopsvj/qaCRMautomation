package com.qaCRM.testcases;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qaCRM.base.TestBase;
import com.qaCRM.pages.ContactsPage;
import com.qaCRM.pages.HomePage;
import com.qaCRM.pages.LoginPage;
import com.qaCRM.utils.TestUtil;


public class ContactsPageTest extends TestBase{
	
	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactspage;
	HomePageTest homepagetest;
	//Properties prop;
	String sheetName = "CRM";
	static Logger logger = null; 
	
	public ContactsPageTest() {
		super();
		
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		try {
		initialization();
		loginpage = new LoginPage();
		contactspage = new ContactsPage();
		homepage = new HomePage();
		homepagetest = new HomePageTest();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		homepage.clickOnContacts();
		//sheetName = prop.getProperty("testdataSheet");
		//this.sheetName = sheetName;
		//Thread.sleep(3000);
		}catch(Exception e)
		{
			logger.error("error occured");
			logger.debug("error debug logs....");
			Assert.fail();
		}
		
	}
	
	@Test(priority=2)
	public void verifyContactLabel() 
	{
	 	//Thread.sleep(5000);
	 	Assert.assertTrue(contactspage.verifyContactsLabel(), "contacts page label is not available");
	 	logger.info("Contact page label is verified");
	}
	
	@DataProvider
	public Object[][] getFreeCRMData() {
		Object data [][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(priority=1, dataProvider="getFreeCRMData")  
	public void createContact(String status, String fname, String lname) throws InterruptedException{
		contactspage.createNewContactCreateBtn();
		contactspage.newContactCreation(status, fname, lname);
		logger.info("New Contact records are created");
	}
	
	@Test(priority=3)
	public void logOut() throws InterruptedException {
		contactspage.logOut();
		logger.info("User logged out successfully");
	}
	
 	@AfterMethod
 	public void tearDown() {
 		driver.quit();
 	}
}
