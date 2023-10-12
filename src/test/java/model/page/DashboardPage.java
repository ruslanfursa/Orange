package model.page;

import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;
    @FindBy(xpath = "//span[@class = 'oxd-userdropdown-tab']")
    private WebElement userProfileTab;
    @FindBy(xpath = "//a[@href = '/web/index.php/auth/logout']")
    private WebElement logoutInDropdownMenu;
    @FindBy(xpath = "//div[@class = 'orangehrm-dashboard-widget-header']")
    private WebElement gridOfSheets;
    @FindBy(xpath = "//button[@title = 'Help']")
    private WebElement questionIcon;
    @FindBy(xpath = "//a[@target = '_blank']")
    private WebElement orangeLinkInFooter;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        getWait5().until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    public void clickLogout() {
        getWait5().until(ExpectedConditions.elementToBeClickable(userProfileTab)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(logoutInDropdownMenu)).click();
    }

    private ArrayList<String> takeSheetsNamesFromDashboard() {
        List<WebElement> sheets = getWait5().until(ExpectedConditions.visibilityOf(gridOfSheets)).findElements(By
                .xpath("//div[@class = 'orangehrm-dashboard-widget-header']"));
        ArrayList<String> names = new ArrayList<>();
        sheets.forEach(element -> names.add(element.getText()));
        return names;
    }

    public boolean isSheetsPresent(String name) {
        return takeSheetsNamesFromDashboard().contains(name);
    }

    public DashboardPage clickQuestionIcon() {
        getWait5().until(ExpectedConditions.elementToBeClickable(questionIcon)).click();
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

    public DashboardPage clickOrangeLinkInFooter() {
        getWait10().until(ExpectedConditions.visibilityOf(orangeLinkInFooter));
        Actions action = new Actions(getDriver());
        action
                .moveToElement(orangeLinkInFooter)
                .click()
                .perform();
        return this;
    }
}
