package model.page;

import com.github.javafaker.Faker;
import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ClaimPage extends BasePage {

    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;
    @FindBy(xpath = "//a[text() = 'Submit Claim']")
    private WebElement submitClaimTab;
    @FindBy(xpath = "//div[@class = 'oxd-grid-item oxd-grid-item--gutters'][1]//i")
    private WebElement eventDropDownArrow;
    @FindBy(xpath = "//div[@role = 'listbox']//*[text() = 'Accommodation']")
    private WebElement eventType;
    @FindBy(xpath = "//div[@class = 'oxd-grid-item oxd-grid-item--gutters'][2]//i")
    private WebElement currencyDropDownArrow;
    @FindBy(xpath = "//div[@role = 'listbox']//*[text() = 'Angolan New Kwanza']")
    private WebElement currencyType;
    @FindBy(xpath = "//textarea")
    private WebElement remarksField;
    @FindBy(xpath = "//button[text() = ' Create ']")
    private WebElement createBtn;
    @FindBy(xpath = "//label[text() = 'Reference Id']//following::div[1]//input")
    private WebElement claimIDField;

    @FindBy(xpath = "//button[text() = ' Submit ']")
    private WebElement submitBtn;
    @FindBy(xpath = "//a[text() = 'My Claims']")
    private WebElement myClaimsTab;
    @FindBy(xpath = "//div[@class = 'oxd-table-cell oxd-padding-cell'][1]")
    private WebElement claimIDTable;

    private String newClaimID;

    public ClaimPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public ClaimPage clickSubmitClaimTab() {
        getWait10().until(ExpectedConditions.elementToBeClickable(submitClaimTab)).click();
        return this;
    }

    public ClaimPage clickEventDropdownArrow() {
        getWait10().until(ExpectedConditions.elementToBeClickable(eventDropDownArrow)).click();
        return this;
    }

    public ClaimPage chooseEventType() {
        getWait10().until(ExpectedConditions.elementToBeClickable(eventType));
        eventType.click();
        return this;
    }

    public ClaimPage clickCurrencyDropdownArrow() {
        currencyDropDownArrow.click();
        return this;
    }

    public ClaimPage chooseCurrencyType() {
        currencyType.click();
        return this;
    }

    public ClaimPage fillInRemarksField() {
        remarksField.sendKeys(new Faker().chuckNorris().fact());
        return this;
    }

    public ClaimPage clickCreateBtn() {
        createBtn.click();
        newClaimID = getWait10().until(ExpectedConditions.visibilityOf(claimIDField)).getAttribute("value");
        return this;
    }

    public ClaimPage clickSubmitBtn() {
        submitBtn.click();
        return this;
    }

    public ClaimPage clickMyClaimsTab() {
        getWait10().until(ExpectedConditions.elementToBeClickable(myClaimsTab)).click();
        return this;
    }

    private List<String> getClaimsIDs() {
        List<String> ids = new ArrayList<>();
        List<WebElement> elements = getWait10().until(ExpectedConditions.visibilityOf(claimIDTable)).findElements(By
                .xpath("//div[@class = 'oxd-table-cell oxd-padding-cell'][1]"));
        elements.forEach(element -> ids.add(element.getText()));
        return ids;
    }

    public boolean isClaimCreated() {
        return getClaimsIDs().contains(newClaimID);
    }
}
