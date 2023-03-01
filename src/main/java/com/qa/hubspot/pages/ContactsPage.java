package com.qa.hubspot.pages;

import java.awt.Frame;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class ContactsPage {
	
	WebDriver driver;
	ElementUtil elementUtil;

	By createContact = By.xpath("//span[text()='Create contact']");
	By emailID = By.xpath("//input[@id='UIFormControl-1']");
	By firstName = By.xpath("//input[@id='UIFormControl-3']");
	By lastName = By.xpath("//input[@id='UIFormControl-5']");
	By jobTitle = By.xpath("//textarea[@data-test-id='jobtitle-input']");
	By create = By.xpath("//span/i18n-string[text()='Create']");
	By contactsNavigationLink = By.xpath("(//i18n-string[contains(text(), 'Contacts')])[2]");
	
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getContactsPageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.CONTACTS_PAGE_TITLE, 10);
	}
	
	public String createNewContact(String email, String firstname, String lastname, String jobtitle) {
		elementUtil.waitForElementToBeClickable(createContact, 30);
		elementUtil.doClick(createContact);
		driver.switchTo().frame("object-builder-ui");
		elementUtil.waitForElementToBePresent(emailID, 15).sendKeys(email);
		elementUtil.waitForElementToBePresent(firstName, 15).sendKeys(firstname);
		elementUtil.waitForElementToBePresent(lastName, 15).sendKeys(lastname);
		elementUtil.waitForElementToBePresent(jobTitle, 15).sendKeys(jobtitle);
		elementUtil.waitForElementToBeClickable(create, 15);
		elementUtil.doClick(create);
		driver.switchTo().defaultContent();
		String fullName = firstname+" "+lastname;
		String nameXpath = "//span[text()='"+fullName+"']";
		elementUtil.waitForElementToBePresent(contactsNavigationLink, 15);
		String contactName = elementUtil.doGetText(By.xpath(nameXpath)).trim();
		
		elementUtil.doClick(contactsNavigationLink);
		
		return contactName;
	}
	
}
