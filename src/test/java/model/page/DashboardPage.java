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
    @FindBy(xpath = "//div[@class = 'orangehrm-dashboard-widget-header']")
    private WebElement gridOfSheets;
    @FindBy(xpath = "//button[@title = 'Help']")
    private WebElement questionIcon;
    @FindBy(xpath = "//a[@target = '_blank']")
    private WebElement orangeLinkInFooter;
    @FindBy(xpath = "//input[@placeholder = 'Search']")
    private WebElement searchField;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getPageName() {
        getWait5().until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
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

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public DashboardPage typeSymbolInSearchField(String s) {
        getWait10().until(ExpectedConditions.visibilityOf(searchField)).sendKeys(s);
        return this;
    }

    public boolean isSymbolPresentInSearchResult(String str) {
        for (String s : takeSearchResult()) {
            if (s.contains(str)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<String> takeSearchResult() {
        List<WebElement> elements = getDriver().findElements(By.xpath("//ul[@class = 'oxd-main-menu']"));
        ArrayList<String> searchResults = new ArrayList<>();
        elements.forEach(element -> searchResults.add(element.getText()));
        return searchResults;
    }
}
