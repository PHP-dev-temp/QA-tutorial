package base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.CustomWaits;


public class BasePage {
		
    public WebDriver driver;
    public WebDriverWait wait;
	public String pageHandler;
	protected int timeOut = 5;
	protected String pageUrl;
	protected String pageTitle;

    public BasePage(WebDriver driver) {
        this.driver = driver;
		this.pageHandler = driver.getWindowHandle();
        wait = new WebDriverWait(driver, 5);        
    }

    public void loadPage() {
        driver.get(getPageUrl());
        Assert.assertEquals(driver.getTitle(), getPageTitle());
    }

    public boolean verifyElementIsPresent(WebElement element) {
        try{
            element.getTagName();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }
    
    public boolean verifyElementIsVisible(WebElement element) {
    	try{
    		wait.until(CustomWaits.visibilityOfElement(element));
    		return true;
    	}catch (NoSuchElementException e) {
    		return false;
    	}
    }

    public void clickElement(WebElement element) {
    	wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void setElementText(WebElement element, String text) {
    	wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
        Assert.assertEquals(element.getAttribute("value"), text);
    }

    public void selectValueInDropdown(WebElement dropdown, String value) {
    	wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        Select select = new Select(dropdown);
        select.selectByValue(value);
        Assert.assertEquals(dropdown.getAttribute("value"), value);
    }
    
    public void howerOver(WebElement element) {
    	wait.until(ExpectedConditions.visibilityOf(element));    	
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();    	
    }
    
	public boolean isTextPresent(WebElement element, String text) {
		boolean result = false;		
		try{
			result = wait.until(ExpectedConditions.textToBePresentInElement(element, text));
		} catch (TimeoutException toe) {
			result = false;
		}
		return result;
	}
	
	public boolean isTextPresentInValue(WebElement element, String text) {
		boolean result = false;		
		try{
			result = wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
		} catch (TimeoutException toe) {
			result = false;
		}
		return result;
	}

    public String getPageUrl() {
        return pageUrl;
    }

    public String getPageTitle() {
        return pageTitle;
    }
    
	public void setDefaultTimeOut(int timeout) {
		timeOut = timeout;
	}
	
	public int getDefaultTimeOut() {
		return timeOut;		
	}

}
