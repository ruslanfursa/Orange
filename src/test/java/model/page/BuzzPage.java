package model.page;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuzzPage extends BasePage {
    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;

    public BuzzPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }
}
