package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OneLocal_LoginPage extends BasePage {
	public OneLocal_LoginPage(WebDriver driver) {
		super(driver);
	
	}

	
	@FindBy(xpath = "//input[@id='input_0']")
	private WebElement userIdTextBox;
	
	@FindBy(id="password")
	private WebElement passwordtextBox;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement submitButton;
	
	public void enterUsername(String username) {
		userIdTextBox.clear();
		userIdTextBox.sendKeys(username);
	}
	
	public void enterpassword(String password) {
		passwordtextBox.clear();
		passwordtextBox.sendKeys(password);
	}
	
	public void clickSubmit() {
		submitButton.click();
	}
	

}


