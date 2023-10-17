package model.page;

import com.github.javafaker.Faker;
import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class PimPage extends BasePage {

    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;
    @FindBy(xpath = "//button[@class = 'oxd-button oxd-button--medium oxd-button--secondary']")
    private WebElement addBtn;
    @FindBy(xpath = "//input[@placeholder = 'First Name']")
    private WebElement firstNameField;
    @FindBy(xpath = "//input[@placeholder = 'Last Name']")
    private WebElement lastNameField;
    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement saveBtn;
    private final String firstName = new Faker().name().firstName();
    private final String lastName = new Faker().name().lastName();

    public PimPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public PimPage clickAddBtn() {
        getWait5().until(ExpectedConditions.elementToBeClickable(addBtn)).click();
        return this;
    }

    public PimPage fillInFirstName() {
        getWait5().until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys(firstName);
        return this;
    }

    public PimPage fillInLastName() {
        getWait5().until(ExpectedConditions.visibilityOf(lastNameField)).sendKeys(lastName);
        return this;
    }

    public PimPage clickSaveBtn() {
        getWait5().until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        return this;
    }

    private String getNameFromUserCard() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//h6[@class = 'oxd-text oxd-text--h6 --strong']"))).getText();
    }

    public boolean isUserCreated() {
        return (firstName + " " + lastName).equals(getNameFromUserCard());
    }
}
