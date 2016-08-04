package self.testing;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.DriverFactory;

public class DriverFactiryTest {
	
	@Parameters("webBrowser")
	@Test(groups={"selftest"})
	public void testDriverFactory(@Optional("") String webBrowser) {
		WebDriver driver;
		System.out.println(webBrowser);
		try {
			if(webBrowser.equals("")) {
				driver = DriverFactory.getDriver(DriverFactory.getBrowserTypeByProperty());
			}else {
				driver = DriverFactory.getDriver(DriverFactory.getBrowserTypeByProperty(webBrowser));
			}
            System.out.println("BROWSER = " + webBrowser);
			driver.quit();
		} catch (Exception e) {
			System.out.println("No, DriverFactory don't work");
			e.printStackTrace();
		}
			
	}

}