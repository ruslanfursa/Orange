package model.page;

import com.github.javafaker.Faker;
import model.base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyInfoPage extends BasePage {

    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;
    @FindBy(xpath = "//input[@name = 'firstName']")
    private WebElement firstNameField;
    @FindBy(xpath = "//p//following::button[2]")
    private WebElement saveBtnInPersonalDetailsSection;
    @FindBy(xpath = "//p[@class = 'oxd-userdropdown-name']")
    private WebElement userProfileTab;


    private final String newUserName = new Faker().funnyName().name();

    public MyInfoPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public MyInfoPage clearFirstNameField() {
        getWait5().until(ExpectedConditions.visibilityOf(firstNameField));
        while (!firstNameField.getAttribute("value").equals("")) {
            firstNameField.sendKeys(Keys.BACK_SPACE);
        }
        return this;
    }

    public MyInfoPage fillInNewName() {
        firstNameField.sendKeys(newUserName);
        return this;
    }

    public MyInfoPage clickSaveBtn() {
        saveBtnInPersonalDetailsSection.click();
        getDriver().navigate().refresh();
        return this;
    }

    public boolean isUserNameChanged() {
        new Actions(getDriver())
                .pause(3000)
                .perform();
        return userProfileTab.getText().contains(newUserName);
    }
}
