package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LogniPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. private By locators
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By loginbtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");

	private By registerLink = By.linkText("Register");

	// 2. page constructor
	public LogniPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. page actions/methods
	@Step("......getting the login page title ......")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,
				AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login page title :" + title);
		return title;
	}

	@Step("......getting the login page URL ......")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,
				AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login page URL  :" + url);
		return url;
	}

	@Step("......getting the forgot password link ......")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}

	@Step("login with usernaem :{0} and password: {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("User name is   :" + un + "        password is  :" + pwd);
		eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginbtn);
		return new AccountsPage(driver);
	}

	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
