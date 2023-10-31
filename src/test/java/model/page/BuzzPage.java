package model.page;

import com.github.javafaker.Faker;
import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class BuzzPage extends BasePage {
    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;
    @FindBy(xpath = "//textarea[@class = 'oxd-buzz-post-input']")
    private WebElement buzzField;
    @FindBy(xpath = "//button[text() = ' Post ']")
    private WebElement postBtn;
    @FindBy(xpath = "//p[@class = 'oxd-text oxd-text--p orangehrm-buzz-post-body-text']")
    private WebElement buzzTable;

    private final String newBuzzText = new Faker().chuckNorris().fact();

    public BuzzPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public BuzzPage fillInBuzzField() {
        new Actions(getDriver()).pause(2000).perform();
        getWait10().until(ExpectedConditions.elementToBeClickable(buzzField)).sendKeys(newBuzzText);
        return this;
    }

    public BuzzPage clickPostBtn() {
        postBtn.click();
        return this;
    }

    public boolean isNewBuzzCreated() {
        return getBuzzTexts().contains(newBuzzText);
    }

    private List<String> getBuzzTexts() {
        List<String> buzzTexts = new ArrayList<>();
        new Actions(getDriver()).pause(2000).perform();
        List<WebElement> elements = getWait10().until(ExpectedConditions.visibilityOf(buzzTable)).findElements(By
                .xpath("//p[@class = 'oxd-text oxd-text--p orangehrm-buzz-post-body-text']"));
        elements.forEach(element -> buzzTexts.add(element.getText()));
        return buzzTexts;
    }
}
