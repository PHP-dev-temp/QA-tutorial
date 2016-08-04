package self.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class FacebookMainPage extends BasePage {
	
	@FindBy(name="firstname") WebElement field_FirstNameSignUp;
	@FindBy(name="lastname") WebElement field_LastNameSignUp;
	@FindBy(name="reg_email__") WebElement field_EmailMobileSignUp;
	@FindBy(name="reg_email_confirmation__") WebElement field_ReEmailMobileSignUp;
	@FindBy(name="reg_passwd__") WebElement field_PasswordSignUp;

	@FindBy(id="month") WebElement dropdown_Mont;
	@FindBy(id="day") WebElement dropdown_Day;
	@FindBy(id="year") WebElement dropdown_Year;		

	@FindBy(css="input[type='radio'][value='2'][name='sex']") WebElement radio_Male;

	public FacebookMainPage(WebDriver driver) {
		super(driver);
		this.pageUrl = "https://www.facebook.com";
		this.pageTitle = "Facebook - Log In or Sign Up";
	}
	
	public void sendText_FirstNameField(String text) {
		setElementText(field_FirstNameSignUp, text);
	}
	
	public void sendText_LastNameField(String text) {
		setElementText(field_LastNameSignUp, text);
	}
	
	public void sendText_EmailMobileField(String text) {
		setElementText(field_EmailMobileSignUp, text);
	}
	
	public void sendText_ReEmailMobileField(String text) {
		setElementText(field_ReEmailMobileSignUp, text);
	}
	
	public void sendText_PasswordField(String text) {
		setElementText(field_PasswordSignUp, text);
	}
	
	public void selectMonth(String value) {
		selectValueInDropdown(dropdown_Mont, value);
	}
	
	public void selectDay(String value) {
		selectValueInDropdown(dropdown_Day, value);
	}
	
	public void selectYear(String value) {
		selectValueInDropdown(dropdown_Year, value);
	}
	
	public void option_selectMale() {
		clickElement(radio_Male);
	}

}
