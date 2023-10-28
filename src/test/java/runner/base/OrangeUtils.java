package runner.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangeUtils {

    static void login(WebDriver driver, WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//input[@name = 'username']"))).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
    }

    static void logoutAfterTest(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//span[@class = 'oxd-userdropdown-tab']"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//a[@href = '/web/index.php/auth/logout']"))).click();
        System.out.println("Logged out");
    }
}
