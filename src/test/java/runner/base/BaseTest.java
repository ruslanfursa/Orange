package runner.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import runner.LoginTest;

import java.time.Duration;

@Listeners(screenshots.Listener.class)
public class BaseTest {

    //    protected Logger log = Logger.getLogger(this.getClass());

    public WebDriver driver;
    private WebDriverWait wait10;

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait10;
    }

    protected void createDriver() {
        //driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));
        driver = new ChromeDriver(new ChromeOptions().addArguments("--window-size=1920,1080"));

    }

    @BeforeMethod
    protected void openSite() {
        getDriver().get("https://opensource-demo.orangehrmlive.com/");
        System.out.println("openSite " + this);
        if ((!(this instanceof LoginTest))) {
            OrangeUtils.login(getDriver(), getWait10());
        } else {
            System.out.println("This is LoginTest beforeMethod");
        }
    }

    @AfterMethod
    protected void logout() {
        if ((!(this instanceof LoginTest))) {
            OrangeUtils.logoutAfterTest(getWait10());
        } else {
            System.out.println("This is LoginTest afterMethod");
        }
    }

    @AfterClass
    protected void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
//            log.log(Level.DEBUG, "closeDriver");
            System.out.println("closeDriver " + this);
        }
    }

//    @BeforeSuite
//    void initLogger() {
//        BasicConfigurator.configure();
//    }

    @BeforeClass
    protected void init() {
        createDriver();
//        log.log(Level.DEBUG, "init");
        System.out.println("init " + this);
    }
}
