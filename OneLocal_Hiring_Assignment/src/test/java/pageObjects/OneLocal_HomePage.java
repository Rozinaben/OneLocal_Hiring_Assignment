package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OneLocal_HomePage extends BasePage {

	public OneLocal_HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[text()='Settings']")
	private WebElement settingsLink;

	public void clickSettingsLink() {
		settingsLink.click();
	}
}

