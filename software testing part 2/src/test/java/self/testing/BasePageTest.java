package self.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import junit.framework.Assert;
import base.DriverFactory;

public class BasePageTest {
	private WebDriver driver;
	FacebookMainPage fbMainPage;
	WolvesbaneacademyPage wbaPage;
	
	@Test(groups={"selftest"})
	public void testSignUpMainPage() {
		try {
			driver = DriverFactory.getDriver(DriverFactory.getBrowserTypeByProperty());
		} catch (Exception e) {
			e.printStackTrace();
		}
		fbMainPage = PageFactory.initElements(driver, FacebookMainPage.class);
		driver.manage().deleteAllCookies();
		fbMainPage.loadPage();	
		
		Assert.assertTrue(fbMainPage.verifyElementIsPresent(fbMainPage.field_FirstNameSignUp));
		fbMainPage.sendText_FirstNameField("QA");
		Assert.assertTrue(fbMainPage.isTextPresentInValue(fbMainPage.field_FirstNameSignUp, "QA"));
		
		fbMainPage.sendText_LastNameField("Youtube course");
		fbMainPage.sendText_EmailMobileField("0123456789");
		fbMainPage.sendText_ReEmailMobileField("0123456789");
		fbMainPage.sendText_PasswordField("password");
		
		fbMainPage.selectDay("25");
		fbMainPage.selectMonth("9");
		fbMainPage.selectYear("1980");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.quit();
	}	
	
	@Test(groups={"selftest"})
	public void testWolvesbaneacademy() {
		try {
			driver = DriverFactory.getDriver(DriverFactory.getBrowserTypeByProperty());
		} catch (Exception e) {
			e.printStackTrace();
		}
		wbaPage = PageFactory.initElements(driver, WolvesbaneacademyPage.class);
		driver.manage().deleteAllCookies();
		wbaPage.loadPage();	
		
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
		wbaPage.howerOverParentItem();
		Assert.assertTrue(wbaPage.verifyElementIsVisible(wbaPage.childMenu));
		wbaPage.clickChildItem();
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		Assert.assertEquals(driver.getTitle(), "Wolvesbane_Academy · GitLab");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.quit();
	}	
	
}