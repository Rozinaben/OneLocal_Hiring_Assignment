package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OneLocal_ThridPartyWebSitePages extends BasePage {

	public OneLocal_ThridPartyWebSitePages(WebDriver driver) {
		super(driver);
	}

	public String getThridPartyPageTitle() {
		return driver.getTitle();
	}
}
