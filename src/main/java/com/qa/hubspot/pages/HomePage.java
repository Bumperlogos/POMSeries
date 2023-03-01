package com.qa.hubspot.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

//@Epic ("Epic - 102: design home page features")
//@feature ("US - 105: design test cases for home page features")
public class HomePage extends BasePage {
	WebDriver driver;
	ElementUtil elementUtil;

	
	By header = By.xpath("//h1/i18n-string[text()='User Guide']");
	By accountName = By.xpath("//span[contains(text(), 'Bumperlogos')]");
	By contactsLinkPrimary = By.xpath("//a[@id='nav-primary-contacts-branch']");
	By contactsLinkSecondary = By.xpath("//a[@id='nav-secondary-contacts']");
	
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);
	}
	
	public String getHomePageHeader() {
		elementUtil.waitForElementToBePresent(header, 10);
		if(elementUtil.doIsDisplayed(header)){
			return elementUtil.doGetText(header);
		}
		return null;
	}
	
	public String getAccountName() {
		elementUtil.waitForElementToBePresent(accountName, 10);
		if(elementUtil.doIsDisplayed(accountName)){
			return elementUtil.doGetText(accountName);
		}
		return null;
	}
	
	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}
	
	private void clickOnContacts() {
		elementUtil.waitForElementToBePresent(contactsLinkPrimary, 10).click();
		elementUtil.waitForElementToBePresent(contactsLinkSecondary, 10).click();
	}
}
