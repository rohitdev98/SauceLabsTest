package testNG;

import org.testng.annotations.Test;

import pageFactory.PageFactoryClass;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class productTest {
    public static WebDriver driver;
    static FileReader reader;
    Properties property;
    public static PageFactoryClass pf;

    @BeforeClass

    public void beforeClass() throws IOException {



        reader = new FileReader("C:\\Users\\rrohi\\eclipse-workspace\\Sprint2\\src\\test\\java\\properties\\config.properties");
        //Reading configuration file


        property = new Properties();
        property.load(reader);
        System.setProperty("webdriver.chrome.driver", property.getProperty("chromepath"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.saucedemo.com/");





    }
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("-------------New Test Case------------------------");
        pf = new PageFactoryClass(driver);
        pf.enterUsername(property.getProperty("username"));
        pf.enterPassword(property.getProperty("password"));
        pf.clickLogin();
    }


    @Test(priority = 1)
    public void homepage() {
    	String expTitle = "Swag Labs";
        Assert.assertEquals(driver.getTitle(), expTitle);
        System.out.println("Login page is displayed");
    }
    @Test(priority = 2)
    public void sortbox() {
        Select sort = new Select(driver.findElement(By.className("product_sort_container")));
        sort.selectByVisibleText("Name (A to Z)");
        System.out.println("Sorted by Name(A-Z)");

        Select sort1 = new Select(driver.findElement(By.className("product_sort_container")));
        sort1.selectByVisibleText("Name (Z to A)");
        //Sorting by Name(Z-A)
        System.out.println("Sorted by Name(Z-A)");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        Select sort2 = new Select(driver.findElement(By.className("product_sort_container")));
        sort2.selectByVisibleText("Price (low to high)");
        System.out.println("Sorted by Price(Low-High)");
        //Sorting by Price(Low-High)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Select sort3 = new Select(driver.findElement(By.className("product_sort_container")));
        sort3.selectByVisibleText("Price (high to low)");
        System.out.println("Sorted by Price(High-Low)");
        //Sorting by Price(High-Low)

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Test(priority = 3)
    public void the_user_clicks_add_to_cart_under_any_product() throws InterruptedException {
    	pf.addItem_backpack();
    	pf.addItem_Shirt();
    	pf.removeItem_backpack();
        //Add to cart buttons are pressed
        System.out.println("Add to cart buttons are pressed");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }
    @Test(priority = 4)
    public void the_user_clicks_on_remove_button() throws InterruptedException,
        IOException {
    		pf.addItem_backpack();
    		pf.removeItem_backpack();
            //Remove button is pressed
            System.out.println("The remove button is pressed");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }

    @Test(priority = 5)
    public void the_user_clicks_on_any_product_name() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        pf.clickOnProductName();
        //Clicking on product name
        System.out.println("Clicked on product name");
        System.out.println("Product page is displayed");
    }
    @Test(priority = 6)
    public void the_user_clicks_on_Back_to_products_button() throws InterruptedException {

        pf.clickOnProductName();
        pf.backbutton();
        //Clicking back button
        System.out.println("Back button clicked");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }
    @Test(priority = 7)
    public void clicks_REST_APP_STATE() throws InterruptedException,
        IOException {

           	pf.hamburger();
            pf.resetbtn();
            System.out.println("The RESET APP STATE button is clicked");
            driver.navigate().refresh();
            //Reset App state button is clicked
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }

    @AfterMethod
    public void AfterMethod() {

        pf.hamburger();
        pf.clickLogout();
        System.out.println("---------------Test case completed------------------");

    }

    @AfterClass
    public void afterMethod() {
        driver.quit();
    }



}