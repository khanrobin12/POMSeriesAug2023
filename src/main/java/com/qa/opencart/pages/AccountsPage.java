package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By accHeader = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		String accTitle = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,
				AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
		System.out.println("Account Page Title Is :   " + accTitle);
		return accTitle;
	}

	public String getAccPageURL() {
		String accPageUrl = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,
				AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
		System.out.println("Account page URL is :  " + accPageUrl);
		return accPageUrl;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}

	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}

	public List<String> getAccountsHeaderList() {

		List<WebElement> accHeadersList = eleUtil.waitForElementsVisible(accHeader,
				AppConstants.DEFAULT_SHORT_TIME_OUT);
		List<String> accheaderValueList = new ArrayList<String>();
		for (WebElement e : accHeadersList) {
			String text = e.getText();
			accheaderValueList.add(text);
		}
		return accheaderValueList;
	}

	public SearchPage performSearch(String searchkey) {
		if (isSearchExist()) {
			eleUtil.doSendKeys(search, searchkey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
		} else {
			System.out.println("Search fieal is not present on the page....");
			return null;
		}
	}
}
