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
    @FindBy(xpath = "//p[@class = 'oxd-text oxd-text--p oxd-alert-content-text']")
    private WebElement errorMessage;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage fillInUserNameField(String userName) {
        getWait10().until(ExpectedConditions.visibilityOf(userNameField));
        userNameField.sendKeys(userName);
        return this;
    }

    public LoginPage fillInPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginBtn() {
        loginBtn.click();
        return this;
    }

    public boolean isErrorMessageShown() {
        String expectedErrorMessage = "Invalid credentials";
        getWait5().until(ExpectedConditions.visibilityOf(errorMessage));
        String actualErrorMessage = errorMessage.getText();
        return expectedErrorMessage.equals(actualErrorMessage);
    }
}
