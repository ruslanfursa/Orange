package model.page;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@name = 'username']")
    private WebElement userNameField;
    @FindBy(xpath = "//input[@name = 'password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage fillInUserNameField() {
        String userName = "Admin";
        getWait10().until(ExpectedConditions.visibilityOf(userNameField));
        userNameField.sendKeys(userName);
        return this;
    }

    public LoginPage fillInPasswordField() {
        String password = "admin123";
        passwordField.sendKeys(password);
        return this;
    }

    public DashboardPage clickLoginBtn() {
        loginBtn.click();
        return new DashboardPage(getDriver());
    }
}
