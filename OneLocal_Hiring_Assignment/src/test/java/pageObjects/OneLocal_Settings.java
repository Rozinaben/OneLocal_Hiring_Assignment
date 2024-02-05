package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OneLocal_Settings extends BasePage {

	public OneLocal_Settings(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h3[normalize-space()='Shared Logins and Services']")
	private WebElement sharedLoginsAndServices;

	public void clickSharedLoginsAndServices() {
		sharedLoginsAndServices.click();
	}
	
	
}
