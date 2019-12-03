package com.hubspot.test;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hubspot.base.BasePage;
import com.hubspot.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(com.hubspot.listeners.TestAllureListener.class)

public class LoginPageTest {

	Logger log = Logger.getLogger("LoginPageTest");

	WebDriver driver;
	BasePage basePage;
	LoginPage loginPage;
	Properties prop;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		log.info("browser is launching...");
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		loginPage = new LoginPage(driver);
		log.info("Setup is done");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	public void loginTitle() {
		String title = loginPage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, "HubSpot Login");
	}

	@Test(priority = 2, enabled = true, description = "Valid username and valid password")
	@Description("login test with correct username and password")
	@Severity(SeverityLevel.CRITICAL)
	public void login() {
		loginPage.doLogin("i.nalbantoglu@hotmail.com", "Tester19");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 4, enabled = false, description = "InValid username and Invalid password")
	public void inValidLogin() throws InterruptedException {
		loginPage.doLogin("lulu@hotmail.com", "12ester19");

		Thread.sleep(3000);

	}

	@Test(priority = 3, enabled = true, description = "Ismail Valid username and valid password")
	@Description("login test with correct username and password")
	@Severity(SeverityLevel.CRITICAL)
	public void loginValidInfo() throws InterruptedException {
		loginPage.doLogin(prop.getProperty("username"), "password");
		log.info("logintest : correct user and correct pass");
		Thread.sleep(3000);

	}

	@AfterMethod
	public void tearDown() {
		basePage.quitBrowser();
	}
}
