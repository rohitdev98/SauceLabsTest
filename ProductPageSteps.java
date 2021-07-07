package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.pomwithpf;

public class ProductPageSteps {

    String baseUrl = "https://www.saucedemo.com/";
    public static WebDriver driver;
    pomwithpf pf;
    public void takeSS(String FileName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File(".\\Screenshots\\" + FileName + ".png"));
    }
    public static WebDriver launchApplication(int browser) {
		switch (browser) {
		case 1:
			System.setProperty("webdriver.chrome.driver","C:\\Users\\rrohi\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
			break;

		default:
			break;
		case 2:
			System.setProperty("webdriver.gecko.driver","C:\\Users\\rrohi\\Downloads\\geckodriver.exe");
			driver=new FirefoxDriver();
			break;
			
		case 3:
			System.setProperty("webdriver.ie.driver","C:\\Users\\rrohi\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver=new InternetExplorerDriver();
			break;
		}
			return driver;
		}

    //SCENARIO:Logging into Products Page

    @Given("user is on login page")
    public void user_is_on_login_page() {
        launchApplication(2);
        //New driver initialization
        driver.manage().window().maximize();
        //maximizing the browser window
        driver.get(baseUrl);
        //Web site is loaded in the browser


        System.out.println("Login screen displayed");
        pf = new pomwithpf(driver);
        //initializing new page factory object

    }


    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {

        pf.enterUsername("standard_user");
        pf.enterPassword("secret_sauce");
        //Entering valid credentials
        System.out.println("User entered valid credentials");
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        pf.clickLogin();
        //Clicking login button
        System.out.println("The login button is clicked");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Swag Labs"));
        //Waiting until the page is loaded
        String expTitle = "Swag Labs";
        Assert.assertEquals(driver.getTitle(), expTitle);
    }

    @Then("user should be on products page")
    public void user_should_be_on_products_page() throws InterruptedException, IOException {
        System.out.println(driver.getTitle());
        System.out.println("The user is on products page");

        takeSS("productpage");
        Thread.sleep(1800);
    }

    //SCENARIO:Sortbox in products page

    @Given("the user is on product page")
    public void the_user_is_on_product_page() {



        System.out.println("The user is on products page");
    }
    @When("user clicks on sort icon")
    public void user_clicks_on_sort_icon() {
        System.out.println("Sorting is initializing....");
        driver.findElement(By.className("product_sort_container")).click();
        //Clicking on sort box

    }


    @When("select sort option as required")
    public void select_sort_option_as_required() throws InterruptedException, IOException {

        Select sort = new Select(driver.findElement(By.className("product_sort_container")));
        sort.selectByVisibleText("Name (A to Z)");
        System.out.println("Sorted by Name(A-Z)");
        //Sorting by Name (A-Z)

        Thread.sleep(1800);
        takeSS("Name(A_Z)sortbox");
        Select sort1 = new Select(driver.findElement(By.className("product_sort_container")));
        sort1.selectByVisibleText("Name (Z to A)");
        //Sorting by Name(Z-A)
        System.out.println("Sorted by Name(Z-A)");
        Thread.sleep(1800);
        takeSS("Name(Z_A)sortbox");
        Select sort2 = new Select(driver.findElement(By.className("product_sort_container")));
        sort2.selectByVisibleText("Price (low to high)");
        System.out.println("Sorted by Price(Low-High)");
        //Sorting by Price(Low-High)
        Thread.sleep(1800);
        takeSS("Price(Low_High)sortbox");
        Select sort3 = new Select(driver.findElement(By.className("product_sort_container")));
        sort3.selectByVisibleText("Price (high to low)");
        System.out.println("Sorted by Price(High-Low)");
        //Sorting by Price(High-Low)
        takeSS("Price(High_Low)sortbox");
        Thread.sleep(1800);




    }

    @Then("products will be sorted")
    public void products_will_be_sorted() {
        System.out.println("The items are sorted ");
    }

    //SCENARIO:Adding items to cart


    @When("the user clicks add to cart under any product")
    public void the_user_clicks_add_to_cart_under_any_product() throws InterruptedException {
        Thread.sleep(1800);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        //Add to cart buttons are pressed
        System.out.println("Add to cart buttons are pressed");
        Thread.sleep(1800);
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
    }

    @Then("the item will be added to cart")
    public void the_item_will_be_added_to_cart() throws IOException {
        System.out.println("The items has been successfully added to cart");
        takeSS("Item added to cart");
    }

    //SCENARIO:Removing items from the cart


    @When("the user adds an item to the cart")
    public void the_user_adds_an_item_to_the_cart() throws InterruptedException {

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        //Item has been added to cart

        System.out.println("Items are added to the cart");
        Thread.sleep(1800);
    }

    @When("the user clicks on remove button")
    public void the_user_clicks_on_remove_button() throws InterruptedException, IOException {

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        //Remove button is pressed
        System.out.println("The remove button is pressed");
        takeSS("Item Removed");
        Thread.sleep(1800);
    }

    @Then("the item will be removed from the cart")
    public void the_item_will_be_removed_from_the_cart() {
        System.out.println("The items are removed from the cart");
    }

    //SCENARIO:Specific product page

    @When("the user clicks on any product name")
    public void the_user_clicks_on_any_product_name() throws InterruptedException {
        Thread.sleep(1800);
        driver.findElement(By.id("item_5_title_link")).click();
        //Clicking on product name
        System.out.println("Clicked on product name");
    }

    @Then("the user should be on that specific product page")
    public void the_user_should_be_on_that_specific_product_page() throws InterruptedException, IOException {
        System.out.println("The user is on specific product page");
        Thread.sleep(1800);
        takeSS("Specific product page");
    }

    @When("the user clicks on Back to products button")
    public void the_user_clicks_on_Back_to_products_button() throws InterruptedException {
        driver.findElement(By.id("back-to-products")).click();
        //Clicking back button
        System.out.println("Back button clicked");
        Thread.sleep(1800);
    }

    @Then("the user should come back to products page")
    public void the_user_should_come_back_to_products_page() {
        System.out.println("The user has returned to the product page");
    }

    //SCENARIO:Resetting the application state


    @When("the user clicks on hamburger menu")
    public void the_user_clicks_on_hamburger_menu() throws InterruptedException {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        //Clicking on hamburger menu
        System.out.println("The hamburger menu is selected");
        Thread.sleep(1800);
    }

    @When("clicks REST APP STATE")
    public void clicks_REST_APP_STATE() throws InterruptedException, IOException {
        driver.findElement(By.id("reset_sidebar_link")).click();
        System.out.println("The RESET APP STATE button is clicked");
        //Reset App state button is clicked
        Thread.sleep(1800);
        takeSS("App Reset");
    }

    @Then("the app is reset")
    public void the_app_is_reset() {
        System.out.println("The app is reset");
        driver.findElement(By.id("react-burger-cross-btn")).click();
    }


    //SCENARIO:Logout from the application


    @When("clicks Log Out button")
    public void clicks_Log_Out_button() throws InterruptedException {
        driver.findElement(By.id("logout_sidebar_link")).click();
        //Logout button is clicked
        System.out.println("The log out button is clicked");
        Thread.sleep(1800);
    }

    @Then("the user is logged out of the application")
    public void the_user_is_logged_out_of_the_application() throws IOException {
        System.out.println("The user has logged out of SAUCELABS");
        takeSS("LogOutScreen");
    }
    @Then("user closes the browser")
    public void user_closes_the_browser() {
        driver.quit();
        System.out.println("The browser is closed");
    }

}