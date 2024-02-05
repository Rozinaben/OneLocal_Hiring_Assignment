package testCases;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.OneLocal_HomePage;
import pageObjects.OneLocal_LoginManagement;
import pageObjects.OneLocal_LoginPage;
import pageObjects.OneLocal_Settings;
import pageObjects.OneLocal_ThridPartyWebSitePages;
import utilities.BrowserFactory;
import utilities.CommonUtil;
import utilities.TestDataReader;

public class OneLocal_TestRunner {
	public WebDriver driver;

	@BeforeMethod
	public void setup() {
//		System.out.println("setup");
		driver = BrowserFactory.getDriver("edge");
		try {
			String url = CommonUtil.getPropertyValue("config.properties", "url");
			driver.get(url);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	@Test(dataProvider = "onelocaldata", dataProviderClass = TestDataReader.class)
	public void VerifyRequiredLoginsText(String... dataFields) throws FileNotFoundException {
		OneLocal_LoginPage olp = new OneLocal_LoginPage(driver);
		OneLocal_HomePage ohp = new OneLocal_HomePage(driver);
		OneLocal_Settings ols = new OneLocal_Settings(driver);
		OneLocal_LoginManagement olm = new OneLocal_LoginManagement(driver);

		try {
			String userid = CommonUtil.getPropertyValue("config.properties", "userid");
			System.out.println(userid);

			String pwd = CommonUtil.getPropertyValue("config.properties", "password");
			System.out.println(pwd);

			olp.enterUsername(userid);
			olp.enterpassword(pwd);
			olp.clickSubmit();
			ohp.clickSettingsLink();
			ols.clickSharedLoginsAndServices();

			boolean regionLoginVisibleStatus = olm.validateRequiredLogin(dataFields[0]);
			Assert.assertTrue(regionLoginVisibleStatus);
		} catch (Throwable e) {
			CommonUtil.takeScreenShotscreenshots(driver);
			e.printStackTrace();
			throw e;
		}
	}

	@Test(dataProvider = "onelocaldata", dataProviderClass = TestDataReader.class)
	public void VerifyRequiredLoginsUpdateFunctionality(String... dataFields) throws Throwable {
		OneLocal_LoginPage olp = new OneLocal_LoginPage(driver);
		OneLocal_HomePage ohp = new OneLocal_HomePage(driver);
		OneLocal_Settings ols = new OneLocal_Settings(driver);
		OneLocal_LoginManagement olm = new OneLocal_LoginManagement(driver);

		try {
			String userid = CommonUtil.getPropertyValue("config.properties", "userid");
			System.out.println(userid);

			String pwd = CommonUtil.getPropertyValue("config.properties", "password");
			System.out.println(pwd);

			olp.enterUsername(userid);
			olp.enterpassword(pwd);
			olp.clickSubmit();
			ohp.clickSettingsLink();
			ols.clickSharedLoginsAndServices();

			System.out.println("going to Click update button");
			olm.clickUpdateButton(dataFields[0]);

			System.out.println("clicked update button");
			boolean submitCredButton = olm.validateSubmitCredentialsBtnDisplay();
			System.out.println(submitCredButton);
			Assert.assertFalse(submitCredButton);
			olm.enterLoginIdText(dataFields[1]);
			System.out.println("entered user id");

			olm.enterPasswordText(dataFields[2]);

			submitCredButton = olm.validateSubmitCredentialsBtnDisplay();
			System.out.println(submitCredButton);

			Assert.assertTrue(submitCredButton);
			olm.clicksubmitCredentialsBtn();

		} catch (Throwable e) {
			CommonUtil.takeScreenShotscreenshots(driver);
			e.printStackTrace();
			throw e;
		}

		System.out.println(Arrays.deepToString(dataFields));
	}

	@Test(dataProvider = "onelocaldata", dataProviderClass = TestDataReader.class)
	public void VerifyThridPartySiteConnectFunctionality(String... dataFields) throws Throwable {
		OneLocal_LoginPage olp = new OneLocal_LoginPage(driver);
		OneLocal_HomePage ohp = new OneLocal_HomePage(driver);
		OneLocal_Settings ols = new OneLocal_Settings(driver);
		OneLocal_LoginManagement olm = new OneLocal_LoginManagement(driver);
		OneLocal_ThridPartyWebSitePages olt = new OneLocal_ThridPartyWebSitePages(driver);
		try {
			String userid = CommonUtil.getPropertyValue("config.properties", "userid");
			System.out.println(userid);

			String pwd = CommonUtil.getPropertyValue("config.properties", "password");
			System.out.println(pwd);

			olp.enterUsername(userid);
			olp.enterpassword(pwd);
			olp.clickSubmit();
			ohp.clickSettingsLink();
			ols.clickSharedLoginsAndServices();

			System.out.println("going to Click update button");
			olm.clickThridPartySiteConnectBtn(dataFields[0]);

			String pageTitle = olt.getThridPartyPageTitle();
			boolean matchFlag = pageTitle.equalsIgnoreCase(dataFields[1]);
			Assert.assertTrue(matchFlag);
		} catch (Throwable e) {
			CommonUtil.takeScreenShotscreenshots(driver);
			e.printStackTrace();
			throw e;
		}

		System.out.println(Arrays.deepToString(dataFields));
	}

}
