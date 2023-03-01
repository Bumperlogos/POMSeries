package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
//pre condition --> Test --> actual vs expected result --> post step
//@BeforeTest --> @Test --> Assert --> @AfterTest
//launch browser, url --> test google title --> Google vs. Google --> close browser
import com.qa.hubspot.pages.LoginPage;

import com.qa.hubspot.utils.Constants;

//@Epic ("Epic - 101: design login feature")
//@feature ("US - 105: design test cases for login page feature")
public class LoginPageTest {
	
	Properties prop;
	WebDriver driver;
	
	BasePage basePage;
	LoginPage loginPage;
	
	@BeforeTest 
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
	}
	
	
	@Test(priority=1)
	//@Description ("verify login page title test...") 
	//@Severity (SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is : "+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "title is not found...");
	}
	
	@Test(priority=2)
	//@Description ("verify sign up link test...") 
	//@Severity (SeverityLevel.CRITICAL)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.checkSignUpLink(), "sign up link not present...");
	}
	
	@Test(priority=3)
	//@Description ("verify login feature test...") 
	//@Severity (SeverityLevel.BLOCKER)
	public void verifyForgotMyPasswordTest() {
		Assert.assertTrue(loginPage.checkForgotMyPasswordLink(), "forgot my password link not found...");
	}
	
	@Test(priority=4)
	public void verifyShowPasswordButtonTest() {
		Assert.assertTrue(loginPage.checkShowPasswordButton(), "show password button not found...");
	}
	
	@Test(priority=5)
	public void verifyLoginWithSSOButtonTest() {
		Assert.assertTrue(loginPage.checkLoginWithSSOButton(), "SSO button not found...");
	}
	
	@Test(priority=6)
	public void loginTest() {
		try {
		HomePage homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homePage.getAccountName(), prop.getProperty("accountname"), "login failed...");
		}catch(Exception e) {
		System.out.println("Assertion Error: "+ e.getMessage());
		}
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
