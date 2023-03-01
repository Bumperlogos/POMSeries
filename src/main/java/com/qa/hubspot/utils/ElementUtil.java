package com.qa.hubspot.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	WebDriver driver;
	JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		jsUtil.flash(element);
		return element;
	}
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	public void doActionsSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.sendKeys(element, value).perform();
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public void doActionsClick(By locator) {
		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.click(element).perform();
	}
		
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	
	/**
	 * 
	 * @param locator
	 * @param index
	 */
	public void getDropDownValues(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println("total values in drop down: " + optionsList.size());
		for(int i=0; i<optionsList.size(); i++) {
			System.out.println(optionsList.get(i).getText());
		}
	}

	/**
	 * 
	 * @param locator
	 * @param index
	 */
	public void selectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> valuesList = select.getOptions();
		System.out.println(valuesList.size());
		for(int i=0; i<valuesList.size(); i++) {
			if (valuesList.get(i).getText().equals(value)) {
				valuesList.get(i).click();
				break;
			}
		}
	}
	
	/**
	 * 
	 * @param locator
	 * @param index
	 */
	public List<String> getDropDownValuesList(By locator) {
		List<String> ar = new ArrayList<String>();
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for(int i=0; i<optionsList.size(); i++) {
			ar.add(optionsList.get(i).getText());
		}
		return ar;
	}
	
	/**
	 * 
	 * @param locator
	 * @param index
	 */
	public void selectDropDown(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText("value");
	}
	
	/**
	 * 
	 * @param locator
	 * @param index
	 */
	public void selectDropDown(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText("index");
	}
	
	/**
	 * this method is used to select the value form the drop down - without using Select class in Selenium
	 * @param locator
	 * @param locatorValue
	 * @param value
	 */
	public void doSelectValueFromDropDownWithOutSelect(String locator, String locatorValue, String value) {
		
		List<WebElement> valuesList = null;
		
		if(locator.equals("xpath")) {
			valuesList = driver.findElements(By.xpath(locator));
		}
		else if (locator.equals("css")) {
			valuesList = driver.findElements(By.cssSelector(locator));
		}
		
		System.out.println(valuesList.size());

		for (int i = 0; i < valuesList.size(); i++) {
			if (valuesList.get(i).getText().equals(value)) {
				valuesList.get(i).click();
				break;
			}
		}
	}
	

	/*
	 * Wait custom methods: wait utilities
	 */
	
	public void visibilityOfAllElements(List<WebElement> elements, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		
	}
	
	//Wait custom method: By locator
	public WebElement waitForElementToBePresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return getElement(locator);
	}
	
	//Wait custom method: wait for element to be clickable
	public WebElement waitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return getElement(locator);
	}
	
	//Wait custom method: wait for element to be visible
	public WebElement waitForElementToBeVisible(By locator, int timeout) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
		
	//Wait custom method: wait for element locator to be visible
	public WebElement waitForElementToBeVisiblityLocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return getElement(locator);
	}
		
	//Wait custom method: wait for Url 
	public String waitForUrl(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlContains(url));
		return driver.getCurrentUrl();
	}
	
	//Wait custom method: wait for Alert
	public boolean waitAlertToBePresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.alertIsPresent());
		return true;
	}	
		
	//wait custom method: click when Ready
	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		getElement(locator).click();
	}
	
	//wait custom method: get title
	public String waitForTitleToBePresent(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
	
	

}
