package self.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import junit.framework.Assert;
import base.DriverFactory;
import utilities.ListenerClass;

@Listeners({ListenerClass.class})
public class ListenerAfterBeforeDependeciesTest {
	private WebDriver driver;
	WolvesbaneacademyPage wbaPage;	
	
	@BeforeClass(alwaysRun = true)
	public void setUp(){
		try {
			driver = DriverFactory.getDriver(DriverFactory.getBrowserTypeByProperty());
		} catch (Exception e) {
			e.printStackTrace();
		}
		wbaPage = PageFactory.initElements(driver, WolvesbaneacademyPage.class);	
	}	

	@Test(groups={"selftest"})
	public void loadWolvesbaneacademy() {
		driver.manage().deleteAllCookies();
		wbaPage.loadPage();	
	}
	
	@Test(groups={"selftest"}, dependsOnMethods = { "loadWolvesbaneacademy" })
	public void isPageLoaded(){
		Assert.assertEquals(wbaPage.getPageTitle(), driver.getTitle());
	}
	
	@Test(groups={"selftest"}, dependsOnMethods = { "isPageLoaded" })
	public void checkVisibility() {
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
	}
	
	@Test(groups={"selftest"}, dependsOnMethods = { "isPageLoaded" })
	public void checkVisibilityFailTest() {
		// This test should fail
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES NOT EXIST"));
	}	
	
	@Test(groups={"selftest"}, dependsOnMethods = { "checkVisibilityFailTest" })
	public void checkVisibilitySkippTest1() {
		// This test should skipped
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
	}
	
	@Test(groups={"selftest"}, dependsOnMethods = { "checkVisibilityFailTest" })
	public void checkVisibilitySkippTest2() {
		// This test should skipped
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
	}	
	
	@Test(groups={"selftest"}, dependsOnMethods = { "isPageLoaded" })
	public void testHoverOver() {
		wbaPage.howerOverParentItem();
		Assert.assertTrue(wbaPage.verifyElementIsVisible(wbaPage.childMenu));
	}		

	@Test(groups={"selftest"}, dependsOnMethods = { "testHoverOver" })
	public void clickChildTest(){
		wbaPage.clickChildItem();
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		Assert.assertEquals(driver.getTitle(), "Wolvesbane_Academy · GitLab");		
	}		

	@AfterClass(alwaysRun = true)
	public void teardown(){
		driver.quit();
	}	
	
	@Test(groups={"selftest"}, dependsOnMethods = { "isPageLoaded" })
	public void checkVisibility1() {
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
	}
	
	@Test(groups={"selftest"}, dependsOnMethods = { "isPageLoaded" })
	public void checkVisibility2() {
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
	}
	
	@Test(groups={"selftest"}, dependsOnMethods = { "isPageLoaded" })
	public void checkVisibility3() {
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
	}
	
	@Test(groups={"selftest"}, dependsOnMethods = { "isPageLoaded" })
	public void checkVisibility4() {
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
	}
	
	@Test(groups={"selftest"}, dependsOnMethods = { "isPageLoaded" })
	public void checkVisibility5() {
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
	}
	
	@Test(groups={"selftest"}, dependsOnMethods = { "isPageLoaded" })
	public void checkVisibility6() {
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
	}
	
	@Test(groups={"selftest"}, dependsOnMethods = { "isPageLoaded" })
	public void checkVisibility7() {
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
	}
	
	@Test(groups={"selftest"}, dependsOnMethods = { "isPageLoaded" })
	public void checkVisibility8() {
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
	}
	
	@Test(groups={"selftest"}, dependsOnMethods = { "isPageLoaded" })
	public void checkVisibility9() {
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
	}
	
	@Test(groups={"selftest"}, dependsOnMethods = { "isPageLoaded" })
	public void checkVisibility10() {
		Assert.assertTrue(wbaPage.isTextPresent(wbaPage.parrentMenu, "CODE REPOSITORIES"));
	}

}
