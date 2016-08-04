package self.testing;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import junit.framework.Assert;
import base.DriverFactory;
import utilities.TakeScreenShot;

public class ScreenShotTesti {
	private WebDriver driver;
	WolvesbaneacademyPage wbaPage;
		
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
		try {
			TakeScreenShot.TakeScreenshot(driver, "screenshot1.png");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Assert.assertTrue(wbaPage.verifyElementIsVisible(wbaPage.childMenu));
		wbaPage.clickChildItem();
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		
		try {
			Thread.sleep(1000);
			TakeScreenShot.TakeScreenshot(driver, "screenshot2.png");
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(driver.getTitle(), "Wolvesbane_Academy · GitLab");
		
		driver.quit();
	}	
	
}