package model.page;


import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class TimePage extends BasePage {

    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;
    @FindBy(xpath = "//span[text() = 'Attendance ']")
    private WebElement attendanceTab;
    @FindBy(xpath = "//a[text() = 'Configuration']")
    private WebElement configurationInDropDownList;
    @FindBy(xpath = "//span[@class = 'oxd-switch-input oxd-switch-input--active --label-right']")
    private WebElement toggles;
    @FindBy(xpath = "//button[text() = ' Save ']")
    private WebElement saveBtn;


    public TimePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public TimePage clickAttendanceTab() {
        getWait10().until(ExpectedConditions.elementToBeClickable(attendanceTab)).click();
        return this;
    }

    public TimePage clickConfigurationInDropdownList() {
        getWait10().until(ExpectedConditions.elementToBeClickable(configurationInDropDownList)).click();
        return this;
    }

    public TimePage clickToggles() {
        getWait5().until(ExpectedConditions.visibilityOf(toggles));
        List<WebElement> elements = getDriver().findElements(By
                .xpath("//span[@class = 'oxd-switch-input oxd-switch-input--active --label-right']"));
        elements.forEach(element -> element.click());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public boolean isTogglesDisabled() {
        getWait5().until(ExpectedConditions.visibilityOf(toggles));
        List<WebElement> elements = getDriver().findElements(By
                .xpath("//input[@type = 'checkbox']"));
        for (WebElement element : elements) {
            if (element.isSelected()) {
                return false;
            }
        }
        return true;
    }

    public TimePage clickSaveBtn() {
        saveBtn.click();
        return this;
    }
}
