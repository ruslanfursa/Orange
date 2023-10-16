package model.page;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AdminPage extends BasePage {

    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;
    @FindBy(xpath = "//div[@class = 'orangehrm-horizontal-padding orangehrm-vertical-padding']")
    private WebElement searchStatus;
    @FindBy(xpath = "//div[@class = 'oxd-table-cell oxd-padding-cell'][2]")
    private WebElement username;
    @FindBy(xpath = "//button[text() = ' Search ']")
    private WebElement searchBtn;
    @FindBy(xpath = "//div[@class = 'oxd-input-group oxd-input-field-bottom-space']//input[@class = 'oxd-input oxd-input--active']")
    private WebElement userNameField;

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }

    private String getUserName() {
        return getWait5().until(ExpectedConditions.visibilityOf(username)).getText();
    }

    public AdminPage fillInUserName() {
        getWait10().until(ExpectedConditions.visibilityOf(userNameField)).sendKeys(getUserName());
        return this;
    }

    public AdminPage clickSearchBtn() {
        searchBtn.click();
        return this;
    }

    public boolean isUserFound() {
        String actualSearchStatus = getWait5().until(ExpectedConditions.visibilityOf(searchStatus)).getText();
        return "(1) Record Found".equals(actualSearchStatus);
    }
}
