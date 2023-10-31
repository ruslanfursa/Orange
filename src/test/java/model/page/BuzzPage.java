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
    @FindBy(xpath = "//div[@class = 'orangehrm-buzz-post-header-config'][1]")
    private WebElement kebabMenu;
    @FindBy(xpath = "//div[@class = 'orangehrm-buzz-post-header-config'][1]//*[text() = 'Edit Post']")
    private WebElement editPostInKebabMenu;
    @FindBy(xpath = "//div[@class = 'oxd-buzz-post oxd-buzz-post--active oxd-buzz-post--composing']")
    private WebElement editedBuzzField;
    @FindBy(xpath = "//div[@role = 'document']//child::button[text() = ' Post ']")
    private WebElement postBtnInModalWindow;


    private final String newBuzzText = new Faker().chuckNorris().fact();
    private final String editedPart = new Faker().gameOfThrones().dragon();

    public BuzzPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }

    private BuzzPage fillInBuzzField() {
        new Actions(getDriver()).pause(3000).perform();
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

    public boolean isNewBuzzEdited() {
        for (String s : getBuzzTexts()) {
            if (s.contains(editedPart)) {
                return true;
            }
        }
        return false;
    }

    private List<String> getBuzzTexts() {
        List<String> buzzTexts = new ArrayList<>();
        new Actions(getDriver()).pause(3000).perform();
        List<WebElement> elements = getWait10().until(ExpectedConditions.visibilityOf(buzzTable)).findElements(By
                .xpath("//p[@class = 'oxd-text oxd-text--p orangehrm-buzz-post-body-text']"));
        elements.forEach(element -> buzzTexts.add(element.getText()));
        return buzzTexts;
    }

    public BuzzPage createBuzz() {
        fillInBuzzField();
        clickPostBtn();
        return this;
    }

    public BuzzPage clickKebabMenu() {
        new Actions(getDriver())
                .pause(1000)
                .perform();
        kebabMenu.click();
        return this;
    }

    public BuzzPage clickEditPostInKebabMenu() {
        getWait10().until(ExpectedConditions.elementToBeClickable(editPostInKebabMenu)).click();
        return this;
    }

    public BuzzPage editBuzz() {
        new Actions(getDriver()).pause(1000)
                .moveToElement(editedBuzzField)
                .click()
                .sendKeys(editedPart)
                .moveToElement(postBtnInModalWindow)
                .click()
                .perform();
        return this;
    }
}
