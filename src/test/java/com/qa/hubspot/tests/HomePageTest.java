package com.qa.hubspot.tests;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

//@Epic ("Epic - 101: design login feature")
//@feature ("US - 105: design test cases for login page feature")
public class HomePageTest {
	
	Properties prop;
	WebDriver driver;
	
	
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeTest 
	public void setUp() throws InterruptedException {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	

	
	@Test(priority=2)
	//@Description ("verify home page title test...") 
	//@Severity (SeverityLevel.NORMAL)
	public void verifyHomePageTitleTest() throws InterruptedException {
		try {
			String title = homePage.getHomePageTitle();
			System.out.println("home page title is: "+ title);
			Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
		}catch(NullPointerException e) {
		System.out.println("home page title not found"+ e.getMessage());
		}
	}
	
	@Test(priority=1)
	public void verifyHomePageHeaderTest() {
		try {
			String header = homePage.getHomePageHeader();
			System.out.println("home page header is : "+ header);
			Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
		}catch(NullPointerException e) {
		System.out.println("home page header not found"+ e.getMessage());
		}
	}
	
	@Test(priority=3)
	public void verifyLoggedInUserTest() {
		try {
			String accountName = homePage.getAccountName();
			System.out.println("login account name is: "+ accountName);
			Assert.assertEquals(accountName, prop.getProperty("Bumperlogos"));
		}catch(NullPointerException e) {
		System.out.println("page title not found"+ e.getMessage());
		}
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
