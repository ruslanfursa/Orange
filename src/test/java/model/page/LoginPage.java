package model.page;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@name = 'username']")
    private WebElement userNameField;
    @FindBy(xpath = "//input[@name = 'password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement loginBtn;
    @FindBy(xpath = "//p[@class = 'oxd-text oxd-text--p oxd-alert-content-text']")
    private WebElement errorMessage;
    @FindBy(xpath = "//form[@class = 'oxd-form']//following::span")
    private WebElement userNameRequiredMessage;
    @FindBy(xpath = "//form[@class = 'oxd-form']//following::span[2]")
    private WebElement passwordRequiredMessage;
    @FindBy(xpath = "//div[@class = 'orangehrm-login-forgot']")
    private WebElement forgotYourPasswordLink;
    @FindBy(xpath = "//a[contains(@href,'linkedin')]")
    private WebElement linkedinIcon;



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
        getWait2().until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }

    public boolean isInvalidCredentialsMessageShown() {
        String expectedErrorMessage = "Invalid credentials";
        getWait5().until(ExpectedConditions.visibilityOf(errorMessage));
        String actualErrorMessage = errorMessage.getText();
        return expectedErrorMessage.equals(actualErrorMessage);
    }

    public boolean isRequiredMessagesShown(){
        String expectedMessage = "Required";
        getWait5().until(ExpectedConditions.visibilityOf(passwordRequiredMessage));
        return expectedMessage.equals(userNameRequiredMessage.getText()) &&
                expectedMessage.equals(passwordRequiredMessage.getText());
    }

    public ResetPasswordPage clickForgotYourPassword(){
        getWait5().until(ExpectedConditions.elementToBeClickable(forgotYourPasswordLink)).click();
        return new ResetPasswordPage(getDriver());
    }

    public LoginPage clickLinkedinIcon(){
        getWait10().until(ExpectedConditions.elementToBeClickable(linkedinIcon)).click();
        return this;
    }
    public LoginPage goToNewTab(){
        String currentTab = getDriver().getWindowHandle();
        Set<String> tabs = getDriver().getWindowHandles();
        for(String s : tabs){
            if(!currentTab.equals(s)){
                getDriver().switchTo().window(s);
            }
        }
        return this;
    }

    public boolean isLinkedinPageOpened(){
//        String currentUrl = getDriver().getCurrentUrl();
        return getDriver().getCurrentUrl().contains("linkedin");
  //      return getWait10().until(ExpectedConditions.urlMatches("https://ru.linkedin.com/company/orangehrm"));
    }
}
