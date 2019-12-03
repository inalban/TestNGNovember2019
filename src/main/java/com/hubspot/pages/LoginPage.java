package com.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hubspot.base.BasePage;
import com.hubspot.util.ElementUtil;

public class LoginPage extends BasePage {
WebDriver driver;
ElementUtil elementUtil;
	// locators non page factory
	By emailId = By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("loginBtn");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil=new ElementUtil(driver);
	}

	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public HomePage doLogin(String user, String pass) {
		elementUtil.doSendKeys(emailId, user);
		elementUtil.doSendKeys(password, pass);
		elementUtil.doClick(loginBtn);
		//driver.findElement(emailId).sendKeys(user);
		//driver.findElement(password).sendKeys(pass);
		//driver.findElement(loginBtn).click();

		return new HomePage(driver);
	}
}
