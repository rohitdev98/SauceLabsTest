package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PageFactoryClass {
public WebDriver driver;
	
	@CacheLookup
	@FindBy(how=How.ID,using="user-name") WebElement txtUserName;
	@FindBy(how=How.NAME,using="password")WebElement txtPassword;
	@FindBy(how=How.CSS,using="input[value='Login']") WebElement cmdLogin;
	@FindBy(how=How.ID,using="add-to-cart-sauce-labs-backpack")WebElement addBackpack;
	@FindBy(how=How.ID,using="add-to-cart-sauce-labs-bolt-t-shirt")WebElement addShirt;
	@FindBy(how=How.ID,using="remove-sauce-labs-backpack")WebElement removeBackpack;
	@FindBy(how=How.ID,using="item_5_title_link")WebElement productLink;
	@FindBy(how=How.ID,using="back-to-products")WebElement back;
	@FindBy(how=How.ID,using="react-burger-menu-btn")WebElement hambrgr;
	@FindBy(how=How.ID,using="reset_sidebar_link")WebElement reset;
	@FindBy(how=How.ID,using="logout_sidebar_link")WebElement logout;
	public PageFactoryClass(WebDriver driver) {
		
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
	public void addItem_backpack() {
		addBackpack.click();
	}
	public void removeItem_backpack() {
		removeBackpack.click();
	}
	public void addItem_Shirt() {
		addShirt.click();
	}
	public void clickOnProductName() {
		productLink.click();
	}
	public void backbutton() {
		back.click();
	}
	public void hamburger() {
		hambrgr.click();
	}
	public void resetbtn() {
		reset.click();
	}
	public void clickLogout() {
		logout.click();
	}
}
