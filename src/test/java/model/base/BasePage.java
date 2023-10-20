package model.base;

import model.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage implements SideBarMenu {
    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;
    private final WebDriver driver;

    @FindBy(xpath = "//span[@class = 'oxd-userdropdown-tab']")
    private WebElement userProfileTab;
    @FindBy(xpath = "//a[@href = '/web/index.php/auth/logout']")
    private WebElement logoutInDropdownMenu;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }
        return wait5;
    }

    protected WebDriverWait getWait2() {
        if (wait2 == null) {
            wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        }
        return wait2;
    }

    protected WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait10;
    }

    @Override
    public <Page extends BasePage> Page clickLinkFromSidebarMenu(LinkFromSidebarMenu link, Page page) {
        WebElement element = link.getLocator(getWait10());
        element.click();
        return page;
    }

    public LoginPage clickLogout() {
        getWait10().until(ExpectedConditions.elementToBeClickable(userProfileTab)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(logoutInDropdownMenu)).click();
        return new LoginPage(getDriver());
    }

    public boolean isTitleCorrect(String str) {
        getWait10().until(ExpectedConditions.visibilityOf(getPageTitle()));
        return str.equals(getPageTitle().getText());
    }

    public abstract WebElement getPageTitle();

}
