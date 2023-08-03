package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regPageSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	public String getRendomEmail() {
		// Random random = new Random();
		// String email = "automation" + random.nextInt(1000) + "@gmail.com";
		String email = "automation" + System.currentTimeMillis() + "@gamil.com";
		return email;
	}

	@DataProvider
	public Object[][] getRegTestData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider = "getRegTestData")
	public void userRegTest(String fname, String lName, String phn, String pssword, String subcriber) {
		Assert.assertTrue(registerPage.registerUser(fname, lName, getRendomEmail(), phn, pssword, subcriber));
	}

}
