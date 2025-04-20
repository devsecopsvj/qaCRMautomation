package com.qaCRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qaCRM.base.TestBase;

public class ContactsPage extends TestBase {
	WebDriverWait wait;
	HomePage homepage;
	
	@FindBy (xpath ="//*[contains(text(),'Contacts')]")
	WebElement ContactsText;
	
	@FindBy (xpath ="//button[text()='Create']")
	WebElement CreateBtnContacts;
	
	@CacheLookup
	@FindBy (xpath ="//div[@name='status']")
	WebElement statusDropdown;
	
	@CacheLookup
	@FindBy (name ="first_name")
	WebElement firstName;
	
	@CacheLookup
	@FindBy (name ="last_name")
	WebElement lastName;
	
	@FindBy (xpath ="//*[@class='save icon']")
	WebElement saveBtn;
	
	@FindBy (xpath ="//div[@id='dashboard-toolbar']")
	WebElement upperBar;
	
	@FindBy (xpath ="//i[@class='settings icon']")
	WebElement settingsDropDownMenu;
	
	@FindBy (xpath ="//*[@class='settings icon']/parent::div/div/a[10]")
	WebElement logOutFromSettingsMenu;
	
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
		
	public boolean verifyContactsLabel() {
		return ContactsText.isDisplayed();
	}
	
	public void createNewContactCreateBtn() {
		CreateBtnContacts.click();
	}
	
	//div[@name='status']
	
//	public void newContactStatus(String title) {
//		Select status = new Select(driver.findElement(By.xpath("//div[@name='status']/span[text()='New']")));
//		status.selectByVisibleText(title);
//	}
	
	public void newContactCreation(String contactStatus, String fname, String lname) throws InterruptedException {
//		Select status = new Select(statusDropdown);
//		status.selectByVisibleText(contactStatus);
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		statusDropdown.click();
		driver.findElement(By.xpath("//div[@name='status']/span[text()='"+contactStatus+"']")).click();
		Thread.sleep(5000);
		//upperBar.click();
		saveBtn.click();
		Thread.sleep(2000);
		}
	public void logOut() throws InterruptedException {
		settingsDropDownMenu.click();
		Thread.sleep(2000);
		logOutFromSettingsMenu.click();
	}
}
