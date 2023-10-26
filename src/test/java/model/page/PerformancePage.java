package model.page;

import com.github.javafaker.Faker;
import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PerformancePage extends BasePage {

    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;
    @FindBy(xpath = "//a[text() = 'My Trackers']")
    private WebElement myTrackersTab;
    @FindBy(xpath = "//button[@name = 'view']")
    private WebElement viewBtn;
    @FindBy(xpath = "//button[text() = ' Add Log ']")
    private WebElement addLogBtn;
    @FindBy(xpath = "//div[@class = 'oxd-form-row'][1]//child::input")
    private WebElement logNameField;
    @FindBy(xpath = "//div[@class = 'oxd-form-row'][3]//child::textarea")
    private WebElement commentField;
    @FindBy(xpath = "//button[text() = ' Negative ']")
    private WebElement dislikeIcon;
    @FindBy(xpath = "//button[text() = ' Save ']")
    private WebElement saveBtn;
    @FindBy(xpath = "//div[@class = 'orangehrm-employee-tracker-log-title']//child::h6[1]")
    private WebElement newLogTitle;
    @FindBy(xpath = "//div[@class = 'orangehrm-employee-tracker-log-body']//child::p[1]")
    private WebElement newCommentTitle;

    private final String newLogName = "Good log";
    private final String comment = new Faker().chuckNorris().fact();


    public PerformancePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public PerformancePage clickMyTrackersTab() {
        getWait5().until(ExpectedConditions.elementToBeClickable(myTrackersTab)).click();
        return this;
    }

    public PerformancePage clickViewBtn() {
        getWait10().until(ExpectedConditions.visibilityOf(viewBtn)).click();
        return this;
    }

    public PerformancePage clickAddLogBtn() {
        getWait10().until(ExpectedConditions.visibilityOf(addLogBtn)).click();
        return this;
    }

    public PerformancePage fillInLogName() {
        getWait10().until(ExpectedConditions.visibilityOf(logNameField)).sendKeys(newLogName);
        return this;
    }

    public PerformancePage fillInCommentField() {
        commentField.sendKeys(comment);
        return this;
    }

    public PerformancePage clickDislikeIcon() {
        dislikeIcon.click();
        return this;
    }

    public PerformancePage clickSaveBtn() {
        saveBtn.click();
        return this;
    }

    public boolean isNewLogAndCommentSaved() {
        return (isNameDisplayed() && isCommentDisplayed());
    }

    private boolean isNameDisplayed() {
        return getWait10().until(ExpectedConditions.visibilityOf(newLogTitle)).getText().equals(newLogName);
    }

    private boolean isCommentDisplayed() {
        return getWait10().until(ExpectedConditions.visibilityOf(newCommentTitle)).getText().equals(comment);
    }
}
