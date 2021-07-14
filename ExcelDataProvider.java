package testNG;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageFactory.PageFactoryClass;

public class ExcelDataProvider {

    WebDriver driver = null;
    static FileReader reader;
    Properties property;
    public static PageFactoryClass pf;
    public static ExcelUtils excel;

    @BeforeTest
    public void setUpTest() throws IOException {
    	reader = new FileReader("C:\\Users\\rrohi\\eclipse-workspace\\Sprint2\\src\\test\\java\\properties\\config.properties");
        property = new Properties();
        property.load(reader);
        System.setProperty("webdriver.chrome.driver", property.getProperty("chromepath"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    @Test(dataProvider = "test1data")
    public void test1(String username, String password) throws Exception {
        System.out.println(username + " | " + password);
        pf = new PageFactoryClass(driver);
        driver.get(property.getProperty("baseUrl"));
        pf.enterUsername(username);
        pf.enterPassword(password);
        pf.clickLogin();
        
        //failsafe
        //surefire




    }

    @DataProvider(name = "test1data")
    public Object[][] getData() {
        Object data[][] = testData(property.getProperty("excelpath"), "LoginTestData");
        return data;

    }


    public Object[][] testData(String excelPath, String sheetName) {

        excel = new ExcelUtils(excelPath, sheetName);

        int rowCount = excel.getRowCount();
        int colCount = excel.getColCount();


        Object data[][] = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {

                String cellData = ExcelUtils.getCellDataString(i, j);
                System.out.print(cellData + " | ");
                data[i - 1][j] = cellData;

            }
            System.out.println();
        }
        return data;

    }
    @AfterTest
    public void quit() {
        driver.quit();
    }
}