package runner.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    private WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--window-size=1920,1080"));
    private WebDriverWait wait2;
    private WebDriverWait wait10;

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait2() {
        if (wait2 == null) {
            wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        }
        return wait2;
    }

    protected WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait10;
    }

    @BeforeSuite
    protected void openSite() {
        getDriver().get("https://opensource-demo.orangehrmlive.com/");
    }

    @AfterSuite
    protected void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait2 = null;
            wait10 = null;
        }
    }
}
