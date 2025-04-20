package com.qaCRM.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qaCRM.base.TestBase;

public class LoginPage extends TestBase {
	
	//Page Factory
	
		@FindBy(name="email")
		WebElement Email;
		
		@FindBy(name="password")
		WebElement Password;
		
		@FindBy(xpath="//div[text()='Login']")
		WebElement LoginBtn;
		
		@FindBy(xpath="//a[text()='Sign Up']")
		WebElement SignUpLnk;
		
		//initializing the page objects
		public LoginPage(){
			PageFactory.initElements(driver, this);
		}
		
		//actions
		public String pageTitleVal() {
			return driver.getTitle();
			
		}
		
		public HomePage login(String un, String pw) {
			Email.sendKeys(un);
			Password.sendKeys(pw);
			LoginBtn.click();
			
			return new HomePage();
		}
		

}
