package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class pomwithpf {
public WebDriver driver;
	
	@CacheLookup
	@FindBy(how=How.ID,using="user-name") WebElement txtUserName;
	@FindBy(how=How.NAME,using="password")WebElement txtPassword;
	@FindBy(how=How.CSS,using="input[value='Login']") WebElement cmdLogin;
	
	public pomwithpf(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void enterUsername(String uName) {
		
		txtUserName.sendKeys(uName);
	}
	public void enterPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	public void clickLogin() {
		cmdLogin.click();
	}
	public String getPageTitle() {
		return driver.getTitle();
	}
	public void loginToApplication(String uName,String passwd) {
		this.enterUsername(uName);
		this.enterPassword(passwd);
		this.clickLogin();
	}

}
