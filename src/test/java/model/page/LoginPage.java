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
    @FindBy(xpath = "//a[contains(@href,'facebook')]")
    private WebElement facebookIcon;
    @FindBy(xpath = "//a[contains(@href,'twitter')]")
    private WebElement twitterIcon;
    @FindBy(xpath = "//a[contains(@href,'youtube')]")
    private WebElement youtubeIcon;


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

    public boolean isRequiredMessagesShown() {
        String expectedMessage = "Required";
        getWait5().until(ExpectedConditions.visibilityOf(passwordRequiredMessage));
        return expectedMessage.equals(userNameRequiredMessage.getText()) &&
                expectedMessage.equals(passwordRequiredMessage.getText());
    }

    public ResetPasswordPage clickForgotYourPassword() {
        getWait5().until(ExpectedConditions.elementToBeClickable(forgotYourPasswordLink)).click();
        return new ResetPasswordPage(getDriver());
    }

    public LoginPage clickLinkedinIcon() {
        getWait10().until(ExpectedConditions.elementToBeClickable(linkedinIcon)).click();
        return this;
    }

    public NewTabPage goToNewTab() {
        String currentTab = getDriver().getWindowHandle();
        Set<String> tabs = getDriver().getWindowHandles();
        for (String s : tabs) {
            if (!currentTab.equals(s)) {
                getDriver().switchTo().window(s);
            }
        }
        return new NewTabPage(getDriver());
    }

    public LoginPage clickFacebookIcon() {
        getWait10().until(ExpectedConditions.elementToBeClickable(facebookIcon)).click();
        return new LoginPage(getDriver());
    }

    public LoginPage clickTwitterIcon() {
        getWait10().until(ExpectedConditions.elementToBeClickable(twitterIcon)).click();
        return this;
    }

    public LoginPage clickYoutubeIcon() {
        getWait10().until(ExpectedConditions.elementToBeClickable(youtubeIcon)).click();
        return this;
    }
}
