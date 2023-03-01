package com.qa.hubspot.pages;

import java.util.Properties;

import org.asynchttpclient.config.AsyncHttpClientConfigHelper.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.TimeUtil;

public class LoginPage extends BasePage{
	
	//Key Methods
	WebDriver driver;
	ElementUtil elementUtil;
	Properties prop;
	
	//1. By Locators:
	By username = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	By showMyPassword = By.xpath("//button/span[text()='Show Password']");
	By forgotMyPasswordLink = By.linkText("Forgot my password");
	By loginWithSSO = By.id("ssoBtn");
	By loginCode = By.xpath("//input[@id='code']");
	By codeLoginButton = By.xpath("//i18n-string[contains(text(), 'Log in')]");
	
	//2. constructor of the page class:
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		Properties prop = new Properties();
	}
	
	//3. page actions/methods;
	

	public String getLoginPageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	public boolean checkSignUpLink() {
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	public boolean checkForgotMyPasswordLink() {
		return elementUtil.doIsDisplayed(forgotMyPasswordLink);
	}
	
	public boolean checkShowPasswordButton() {
		return elementUtil.doIsDisplayed(showMyPassword);
	}
	
	public boolean checkLoginWithSSOButton() {
		return elementUtil.doIsDisplayed(loginWithSSO);
	}
	
	//@Step annotation comes from Maven dependency 
	//@Step("login with - username: {0} and password: {1}")
	public HomePage doLogin(String un, String pwd) {	
		elementUtil.doActionsSendKeys(username, un);
		elementUtil.doActionsSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		TimeUtil.shortWait();
		elementUtil.doSendKeys(loginCode, Constants.LOGIN_CODE);
		elementUtil.doClick(codeLoginButton);
		TimeUtil.shortWait();
		return new HomePage(driver);
	}

}
