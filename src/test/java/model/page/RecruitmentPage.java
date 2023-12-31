package model.page;

import com.github.javafaker.Faker;
import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class RecruitmentPage extends BasePage {

    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;
    @FindBy(xpath = "//a[text() = 'Vacancies']")
    private WebElement vacanciesTab;
    @FindBy(xpath = "//button[text() = ' Add ']")
    private WebElement addBtn;
    @FindBy(xpath = "//label[text() = 'Vacancy Name']//following::input[1]")
    private WebElement vacancyNameField;
    @FindBy(xpath = "//label[text() = 'Job Title']//following::div[text() = '-- Select --']")
    private WebElement jobTitleField;
    @FindBy(xpath = "//textarea[@placeholder = 'Type description here']")
    private WebElement vacancyDescriptionField;
    @FindBy(xpath = "//input[@placeholder = 'Type for hints...']")
    private WebElement hiringManagerField;
    @FindBy(xpath = "//label[text() = 'Number of Positions']//following::input[1]")
    private WebElement numberOfPositionsField;
    @FindBy(xpath = "//button[text() = ' Save ']")
    private WebElement saveBtn;
    @FindBy(xpath = "//div[@class = 'oxd-table-cell oxd-padding-cell'][2]")
    private WebElement vacanciesTable;
    @FindBy(xpath = "//button[text() = ' Yes, Delete ']")
    private WebElement yesDeleteBtn;


    private final String newVacancyName = new Faker().name().username();
    private String managerName;


    public RecruitmentPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }

    private void clickVacanciesTab(boolean isNeedSaveManagerName) {
        Actions actions = new Actions(getDriver());
        actions
                .pause(2000)
                .perform();
        getWait10().until(ExpectedConditions.elementToBeClickable(vacanciesTab)).click();
        if (isNeedSaveManagerName) {
            managerName = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By
                    .xpath("//div[@class = 'oxd-table-cell oxd-padding-cell'][4]"))).getText();
        }
    }

    private void clickAddBtn() {
        getWait10().until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }

    private void fillInNewVacancyBlank() {
        fillInVacancyName();
        fillInJobTitle();
        fillInVacancyDescriptionField();
        fillInHiringManagerField();
        fillInNumberOfPositionsField();
    }

    private void fillInVacancyName() {
        getWait10().until(ExpectedConditions.visibilityOf(vacancyNameField)).sendKeys(newVacancyName);
    }

    private void fillInJobTitle() {
        getWait10().until(ExpectedConditions.elementToBeClickable(jobTitleField)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//div[@role = 'listbox']//*[text() = 'Content Specialist']"))).click();
    }

    private void fillInVacancyDescriptionField() {
        vacancyDescriptionField.sendKeys("We are looking for wonderful QA");
    }

    private void fillInHiringManagerField() {
        Actions actions = new Actions(getDriver());
        hiringManagerField.sendKeys(managerName);
        actions
                .pause(3000)
                .perform();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@role = 'listbox']"))).click();
    }

    private void fillInNumberOfPositionsField() {
        numberOfPositionsField.sendKeys("1");
    }

    private void clickSaveBtn() {
        saveBtn.click();
    }

    public boolean isVacancyCrated() {
        return getVacancyNames().contains(newVacancyName);
    }

    private Set<String> getVacancyNames() {
        Set<String> names = new HashSet<>();
        List<WebElement> elements = getWait10().until(ExpectedConditions.visibilityOf(vacanciesTable)).findElements(By
                .xpath("//div[@class = 'oxd-table-cell oxd-padding-cell'][2]"));
        elements.forEach(element -> names.add(element.getText()));
        return names;
    }

    public RecruitmentPage createNewVacancy() {
        clickVacanciesTab(true);
        clickAddBtn();
        fillInNewVacancyBlank();
        clickSaveBtn();
        clickVacanciesTab(false);
        return this;
    }

    public RecruitmentPage deleteVacancy() {
        getWait10().until(ExpectedConditions.visibilityOf(vacanciesTable));
        getDriver().findElement(By
                .xpath("//div[contains(text(), '" + newVacancyName + "')]//following::div[8]//button[1]")).click();
        clickYesDeleteBtn();
        System.out.println("knknkn");
        return this;
    }

    private void clickYesDeleteBtn() {
        getWait5().until(ExpectedConditions.elementToBeClickable(yesDeleteBtn)).click();
    }
}
