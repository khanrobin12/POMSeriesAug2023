package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		String accpagetitle = accPage.getAccPageTitle();
		Assert.assertEquals(accpagetitle, AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
	}

	@Test(priority = 2)
	public void accPageURLTest() {
		String accpageurl = accPage.getAccPageURL();
		Assert.assertTrue(accpageurl.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}

	@Test(priority = 3)
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test(priority = 4)
	public void accPageHeadersCountTest() {
		List<String> actualAccPageHeaderListcount = accPage.getAccountsHeaderList();
		System.out.println("Acc Page Headers total count" + actualAccPageHeaderListcount);
		Assert.assertEquals(actualAccPageHeaderListcount.size(), AppConstants.ACCOUNT_PAGE_HEADERS_COUNT);
	}

	@Test(priority = 5)
	public void accPageHeadersValuesTest() {
		List<String> actualAccPageHeaderLists = accPage.getAccountsHeaderList();
		System.out.println("Acc Page actual Headers total List " + actualAccPageHeaderLists);
		System.out.println("Acc Page expected Headers total List " + AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADER_LIST);
		Assert.assertEquals(actualAccPageHeaderLists, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADER_LIST);
	}

	@DataProvider
	public Object[][] getProductDate() {
		return new Object[][] { { "Macbook" }, { "iMac" }, { "Apple" }, { "Samsung" }

		};
	}

	@Test(priority = 6, dataProvider = "getProductDate")
	public void SearchProductCountTest(String searchKey) {
		searchPage = accPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductsCount() > 0);
	}

	@DataProvider
	public Object[][] getProductTestDate() {
		return new Object[][] { { "Macbook", "MacBook Pro" }, { "Macbook", "MacBook" }, { "iMac", "iMac" }
//				{ "Apple", "Apple Cinema 30\"" }, { "Samsung", "Samsung SyncMaster 941BW" },
//				{ "Samsung", "Samsung Galaxy Tab 10.1" },

		};
	}

	@Test(priority = 7, dataProvider = "getProductTestDate")
	public void SearchProductTest(String searchkey, String productName) {
		searchPage = accPage.performSearch(searchkey);
		if (searchPage.getSearchProductsCount() > 0) {
			productInfoPage = searchPage.selectProduct(productName);
			String actualProductHeader = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actualProductHeader, productName);
		}

	}

}
