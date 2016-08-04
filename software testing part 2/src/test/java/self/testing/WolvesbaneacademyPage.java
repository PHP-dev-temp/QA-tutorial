package self.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class WolvesbaneacademyPage extends BasePage {
	
	@FindBy(id="menu-item-179") WebElement parrentMenu;
	@FindBy(linkText="GITLAB (ACTIVE)") WebElement childMenu;

	public WolvesbaneacademyPage(WebDriver driver) {
		super(driver);
		this.pageUrl = "http://wolvesbaneacademy.xyz/";
		this.pageTitle = "Wolvesbane Academy | A Fount of Knowledge for the Perpetual Student";
	}
	
	public void howerOverParentItem() {
		howerOver(parrentMenu);		
	}
	
	public void clickChildItem() {
		clickElement(childMenu);
	}

}
