package base;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junitx.util.PropertyManager;

public class DriverFactory {

    public enum BrowserType {
        FIREFOX("firefox"),
        CHROME("chrome"),
        IE("internet_explorer"),
        SAFARI("safari");
    	// TODO add other browser types

        private String value;

        BrowserType(String value) {
            this.value = value;
        }

        public String getBrowserName() {
            return this.value;
        }
    }

    public static WebDriver getDriver(BrowserType type) throws Exception {
		if(PropertyManager.getProperty("USE_GRID").equalsIgnoreCase("true")){
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setBrowserName(type.getBrowserName());
			desiredCapabilities.setPlatform(Platform.WINDOWS);
			return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
		}

        switch(type) {
            case FIREFOX:
                return new FirefoxDriver();
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                return new ChromeDriver();
            case IE:
            	System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/IEDriverServer.exe");
            	return new InternetExplorerDriver();
            default:
                return new FirefoxDriver();
        }
    }

    public static BrowserType getBrowserTypeByProperty() {
        BrowserType type = null;
        String browsername = PropertyManager.getProperty("BROWSER");
        for(BrowserType bType : BrowserType.values()){
            if(bType.getBrowserName().equalsIgnoreCase(browsername)){
                type = bType;
            }
        }
        return type;
    }

    public static BrowserType getBrowserTypeByProperty(String browserName) {
        BrowserType type = null;
        for(BrowserType bType : BrowserType.values()){
            if(bType.getBrowserName().equalsIgnoreCase(browserName)){
                type = bType;
            }
        }
        return type;
    }

}