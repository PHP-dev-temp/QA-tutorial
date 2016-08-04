package self.testing;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import base.DriverFactory;
import data.DataProviderSelfTesting;
import utilities.ExcelUtils;

public class DataProviderTest {
	private WebDriver driver;
	FacebookMainPage fbMainPage;
	static String[] reportArray = new String[8];
	static Integer rowCount = 0;
	
	HashMap<String, String> signupMap;
	
	@Factory(dataProvider = "signup", dataProviderClass = DataProviderSelfTesting.class)
	public DataProviderTest
			(String first, String last, String email, String reemail, 
			String password, String day, String month, String year) {
		this.signupMap = new HashMap<String, String>();
		this.signupMap.put("suFirstName", first);
		this.signupMap.put("suLastName", last);
		this.signupMap.put("suEmail", email);
		this.signupMap.put("suReEmail", reemail);
		this.signupMap.put("suPassword", password);
		this.signupMap.put("suDay", day);
		this.signupMap.put("suMonth", month);
		this.signupMap.put("suYear", year);
		
	}
		
	@Test(groups={"selftest"})
	public void testDataProviderExcel() {
		try {
			driver = DriverFactory.getDriver(DriverFactory.getBrowserTypeByProperty());
		} catch (Exception e) {
			e.printStackTrace();
		}
		fbMainPage = PageFactory.initElements(driver, FacebookMainPage.class);
		driver.manage().deleteAllCookies();
		fbMainPage.loadPage();	
		fbMainPage.sendText_FirstNameField(signupMap.get("suFirstName"));
		fbMainPage.sendText_LastNameField(signupMap.get("suLastName"));
		fbMainPage.sendText_EmailMobileField(signupMap.get("suEmail"));
		fbMainPage.sendText_ReEmailMobileField(signupMap.get("suReEmail"));
		fbMainPage.sendText_PasswordField(signupMap.get("suPassword"));
		
		fbMainPage.selectDay(signupMap.get("suDay"));
		fbMainPage.selectMonth(signupMap.get("suMonth"));
		fbMainPage.selectYear(signupMap.get("suYear"));
		

		

		reportArray[0] = signupMap.get("suFirstName");
		reportArray[1] = signupMap.get("suLastName");
		reportArray[2] = signupMap.get("suEmail");
		reportArray[3] = signupMap.get("suReEmail");
		reportArray[4] = signupMap.get("suPassword");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			ExcelUtils.addExcelRow (rowCount, reportArray, ".//src//test//resources//testDataProviderExcel.xlsx");
			rowCount++;		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}	

}
