package pageObjects;

import java.time.Duration;

import javax.sql.CommonDataSource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.CommonUtil;

public class OneLocal_LoginManagement extends BasePage {

	public OneLocal_LoginManagement(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='model_username']")
	private WebElement loginIdTxtBox;

	@FindBy(xpath = "//input[@id='model_password']")
	private WebElement loginPasswordTxtBox;
	
	

	@FindBy(xpath = "//button[normalize-space()='Submit Credentials']")
	private WebElement submitCredentialsBtn;

	public void clickUpdateButton(String requiredLogin) {
		CommonUtil.pauseExecution(2);
		String xpath = "//div[normalize-space()='" + requiredLogin + "']/../../div//button[normalize-space()='Update']";
		System.out.println(xpath);
		WebElement updateBtn = driver.findElement(By.xpath(xpath));
		Actions a = new Actions(driver);
		a.moveToElement(updateBtn).perform();
		updateBtn = driver.findElement(By.xpath(xpath));
		a.click(updateBtn).perform();
	}

	
	
	public void clickThridPartySiteConnectBtn(String thirdPartySite) {
		CommonUtil.pauseExecution(2);
		String xpath = "//div[normalize-space()='" + thirdPartySite + "']/../following-sibling::div/button[normalize-space()='Connect']";
		System.out.println(xpath);
		WebElement connect = driver.findElement(By.xpath(xpath));
		//connect.click();
		Actions a = new Actions(driver);
		a.moveToElement(connect).perform();
		connect = driver.findElement(By.xpath(xpath));
		a.click(connect).perform();
	}
	
	public void enterLoginIdText(String un) {
		CommonUtil.pauseExecution(2);
		loginIdTxtBox.clear();
		loginIdTxtBox.sendKeys(un);
	}

	public void enterPasswordText(String pw) {
		loginPasswordTxtBox.clear();
		loginPasswordTxtBox.sendKeys(pw);
	}

	public void clicksubmitCredentialsBtn() {
		FluentWait<WebDriver> ww = new WebDriverWait(driver, Duration.ofSeconds(100));
		ww.pollingEvery(Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(submitCredentialsBtn));
		submitCredentialsBtn.click();
	}
	
	public boolean validateRequiredLogin(String requiredLogin) {
		String regionLoginXpath = "//div[@layout='row']/div[normalize-space()='" + requiredLogin + "']";
		System.out.println(regionLoginXpath);
		WebElement dd = driver.findElement(By.xpath(regionLoginXpath));
		System.out.println(dd.getText() + " - " + dd.isDisplayed());

		return dd.isDisplayed();
	}
//
	public boolean validateSubmitCredentialsBtnDisplay() {

		return submitCredentialsBtn.isEnabled();
	}
}
