package model.page;

import com.github.javafaker.Faker;
import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;


public class AdminPage extends BasePage {

    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;
    @FindBy(xpath = "//button[text() = ' Add ']")
    private WebElement addBtn;
    @FindBy(xpath = "//label[contains(text(), 'User Role')]//..//..//div[@class = 'oxd-select-text--after']")
    private WebElement userRoleArrow;
    @FindBy(xpath = "//div[@role = 'listbox']//*[text() = 'Admin']")
    private WebElement userRoleType;
    @FindBy(xpath = "//input[@placeholder = 'Type for hints...']")
    private WebElement employeeNameField;
    @FindBy(xpath = "//div[@class = 'oxd-grid-item oxd-grid-item--gutters'][3]//child::i")
    private WebElement statusArrow;
    @FindBy(xpath = "//div[@role = 'listbox']//*[text() = 'Enabled']")
    private WebElement userStatusType;
    @FindBy(xpath = "//div[@class = 'oxd-grid-item oxd-grid-item--gutters'][4]//child::input")
    private WebElement userNameField;
    @FindBy(xpath = "//div[@class = 'oxd-grid-item oxd-grid-item--gutters user-password-cell']//input")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@class = 'oxd-input oxd-input--active']//following::input[4]")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//button[text() = ' Save ']")
    private WebElement saveBtn;


    private final String userName = new Faker().name().fullName();
    private final String password = new Faker().internet().password(8, 14, false);

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public AdminPage clickAddBtn() {
        getWait10().until(ExpectedConditions.elementToBeClickable(addBtn)).click();
        return this;
    }

    public AdminPage clickUserRoleArrow() {
        getWait10().until(ExpectedConditions.elementToBeClickable(userRoleArrow)).click();
        return this;
    }

    public AdminPage chooseUserRole() {
        getWait10().until(ExpectedConditions.elementToBeClickable(userRoleType)).click();
        return this;
    }

    public AdminPage fillInEmployeeNameField(String employeeName) {
        employeeNameField.sendKeys(employeeName);
        new Actions(getDriver())
                .pause(2000)
                .perform();
        getWait10().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//div[@role = 'listbox']"))).click();
        return this;
    }

    public AdminPage clickStatusArrow() {
        statusArrow.click();
        return this;
    }

    public AdminPage chooseUserStatus() {
        getWait10().until(ExpectedConditions.elementToBeClickable(userStatusType)).click();
        return this;
    }

    public AdminPage fillInUsername() {
        userNameField.sendKeys(userName);
        return this;
    }

    public AdminPage fillInPassword() {
        passwordField.sendKeys(password);
        return this;
    }

    public AdminPage confirmPassword() {
        confirmPasswordField.sendKeys(password);
        return this;
    }

    public AdminPage clickSaveBtn() {
        saveBtn.click();
        return this;
    }

    public boolean isUserExist() {
        return getUsersNames().contains(userName);
    }

    private List<String> getUsersNames() {
        List<String> names = new ArrayList<>();
        List<WebElement> elements = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//div[@class = 'oxd-table-cell oxd-padding-cell'][2]")))
                .findElements(By.xpath("//div[@class = 'oxd-table-cell oxd-padding-cell'][2]"));
        elements.forEach(element -> names.add(element.getText()));
        System.out.println(names);
        return names;
    }
}
