package model.page;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResetPasswordPage extends BasePage {
    @FindBy(xpath = "//h6")
    private WebElement pageTitle;
    @FindBy(xpath = "//button[@type = 'button']")
    private WebElement cancelBtn;
    public ResetPasswordPage(WebDriver driver) {
        super(driver);
    }
    public String getPageTitle(){
        return getWait2().until(ExpectedConditions.visibilityOf(pageTitle)).getText();
    }

    public LoginPage clickCancelBtn(){
        cancelBtn.click();
        return new LoginPage(getDriver());
    }
}
