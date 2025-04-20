package com.qaCRM.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qaCRM.base.TestBase;
import com.qaCRM.pages.ContactsPage;
import com.qaCRM.pages.HomePage;
import com.qaCRM.pages.LoginPage;

public class HomePageTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	LoginApplication loginapp;
	ContactsPage contactspage;
	
	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		loginpage = new LoginPage();
		contactspage = new ContactsPage();
		homepage = new HomePage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
//		loginapp.loginTest();
		Thread.sleep(2000);
		
	}

   @Test(priority=1)

    public void verifyHomepageTitleTest() {
    	 String homePageTitle = homepage.verifyTitleHomepage();
	     Assert.assertEquals(homePageTitle, "Cogmento CRM", "home page title is not matched");
     }
     
     @Test(priority=1)
     public void verifyUserName() {
    	 String userLogged = prop.getProperty("userlogged");
    	String userName = homepage.verifyUserName();
    	Assert.assertEquals(userName, userLogged, "username not matched in the home page");
     }
     
     @Test(priority=3)
     
     public void clickOnContactsPage() {
    	 contactspage = homepage.clickOnContacts();
     }
     
 	@AfterMethod
 	public void tearDown() {
 		driver.quit();
 	}
}