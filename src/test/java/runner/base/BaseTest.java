package runner.base;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.*;


import java.time.Duration;

public class BaseTest {
    protected Logger log = Logger.getLogger(this.getClass());
    private WebDriver driver;
    private WebDriverWait wait2;
    private WebDriverWait wait10;

    protected WebDriver getDriver() {
        return driver;
    }

    protected void createDriver() {
      driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));
       //driver = new ChromeDriver(new ChromeOptions().addArguments("--window-size=1920,1080"));

    }

    @BeforeMethod
    protected void openSite() {
        getDriver().get("https://opensource-demo.orangehrmlive.com/");
        log.log(Level.DEBUG, "openSite");
    }

    @AfterClass
    protected void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait2 = null;
            wait10 = null;
            log.log(Level.DEBUG, "closeDriver");
        }
    }
    @BeforeSuite
    void initLogger() {
        BasicConfigurator.configure();
    }

    @BeforeClass
    protected void init() {
        createDriver();
        log.log(Level.DEBUG, "init");
    }
}
