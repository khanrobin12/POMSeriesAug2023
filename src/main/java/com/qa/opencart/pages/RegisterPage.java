package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By subscriberYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	private By subscriberNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");

	private By registerSuccessMesg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean registerUser(String fname, String lName, String email, String phn, String pssword,
			String subcriber) {
		eleUtil.waitForElementVisible(firstName, AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(fname);
		eleUtil.doSendKeys(lastName, lName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(telephone, phn);
		eleUtil.doSendKeys(password, pssword);
		eleUtil.doSendKeys(confirmPassword, pssword);

		if (subcriber.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscriberYes);
		} else {
			eleUtil.doClick(subscriberNo);
		}

		eleUtil.doActionsCick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		String successMessg = eleUtil.waitForElementVisible(registerSuccessMesg, AppConstants.DEFAULT_SHORT_TIME_OUT)
				.getText();
		System.out.println(successMessg);
		if (successMessg.contains(AppConstants.USER_REG_SUCCESS_MESSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}

}
