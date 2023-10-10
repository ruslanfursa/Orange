package model.page;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {
    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        getWait5().until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }
}
