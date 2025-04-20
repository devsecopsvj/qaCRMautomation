package com.qaCRM.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qaCRM.base.TestBase;

public class HomePage extends TestBase {
	ContactsPage contactspage;
	
	@FindBy(xpath="//div[@class='right menu']/span")
	WebElement usrNameHomepage;
	
	@FindBy(xpath="//*[@class='users icon']")
	WebElement contactsLink;
	
	@FindBy(xpath="//*[@class='money icon']")
	WebElement dealsLink;
	
	@FindBy(xpath="//*[@class='tasks icon']")
	WebElement tasksLink;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyUserName() {
		return usrNameHomepage.getText();
	}
	
	public String verifyTitleHomepage() {
			return driver.getTitle();
	}
	
	public ContactsPage clickOnContacts() {
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDeals() {
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasks() {
		tasksLink.click();
		return new TasksPage();
	}
}
