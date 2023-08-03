package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@DataProvider
	public Object[][] getProductImageTestDate() {
		return new Object[][] { { "Macbook", "MacBook Pro", 4 }, { "iMac", "iMac", 3 },
				{ "Apple", "Apple Cinema 30\"", 6 }, { "Samsung", "Samsung SyncMaster 941BW", 1 }, };
	}

	@Test(dataProvider = "getProductImageTestDate")
	public void productImagesCountTest(String searchKey, String productName, int imageCount) {
		searchPage = accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actualImageCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actualImageCount, imageCount);
	}

	@Test
	public void productInfoTest() {
		searchPage = accPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("productprice"), "$2,000.00");
		softAssert.assertAll();

	}

	@Test
	public void addToCartTest() {
		searchPage = accPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		productInfoPage.enterQuantity(2);
		String acctualCartmssg = productInfoPage.addProductToCart();
		softAssert.assertTrue(acctualCartmssg.indexOf("Success") >= 0);
		softAssert.assertTrue(acctualCartmssg.indexOf("MacBook Pro") >= 0);
		softAssert.assertEquals(acctualCartmssg, "Success: You have added MacBook Pro to your shopping cart!");
		softAssert.assertAll();
	}

}
