package model.page;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {
    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;
    @FindBy(xpath = "//span[@class = 'oxd-userdropdown-tab']")
    private WebElement userProfileTab;
    @FindBy(xpath = "//a[@href = '/web/index.php/auth/logout']")
    private WebElement logoutInDropdownMenu;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        getWait5().until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    public void clickLogout(){
        userProfileTab.click();
        getWait5().until(ExpectedConditions.elementToBeClickable(logoutInDropdownMenu)).click();
    }
}
