package model.page;

import com.github.javafaker.Faker;
import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Ignore;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class LeavePage extends BasePage {

    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;
    @FindBy(xpath = "//span[text() = 'Configure ']")
    private WebElement configureTab;
    @FindBy(xpath = "//a[text() = 'Holidays']")
    private WebElement holidaysInDropDownList;
    @FindBy(xpath = "//i[@class = 'oxd-icon bi-pencil-fill'][1]")
    private WebElement editIcon;
    @FindBy(xpath = "//div[@class = 'oxd-input-group oxd-input-field-bottom-space'][1]//child::input")
    private WebElement holidayNameForm;
    @FindBy(xpath = "//button[text() = ' Save ']")
    private WebElement saveBtn;
    @FindBy(xpath = "//div[@class = 'oxd-table-cell oxd-padding-cell']")
    private WebElement holidayTable;

    private final String editionToHolidayName = new Faker().funnyName().name();





    public LeavePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public LeavePage clickConfigureTab() {
        getWait10().until(ExpectedConditions.visibilityOf(configureTab)).click();
        return this;
    }

    public LeavePage clickHolidaysInDropDownList() {
        getWait10().until(ExpectedConditions.visibilityOf(holidaysInDropDownList)).click();
        return this;
    }

    public LeavePage clickEditIcon() {
        getWait10().until(ExpectedConditions.visibilityOf(editIcon)).click();
        return this;
    }
    public LeavePage editHolidayName() {
        getWait10().until(ExpectedConditions.visibilityOf(holidayNameForm)).sendKeys(editionToHolidayName);
        return this;
    }

    public LeavePage clickSaveBtn() {
        saveBtn.click();
        return this;
    }

    public boolean isHolidayEdited() {
        for(String s : getHolidayNames()){
            if(s.contains(editionToHolidayName)){
                return true;
            }
        }
        return false;
    }

    private Set<String> getHolidayNames() {
        Set<String> holidayNames = new HashSet<>();
        List<WebElement> elements = getWait10().until(ExpectedConditions.visibilityOf(holidayTable)).findElements(By
                .xpath("//div[@class = 'oxd-table-cell oxd-padding-cell']"));
        elements.forEach(element->holidayNames.add(element.getText()));
        return holidayNames;
    }
}
