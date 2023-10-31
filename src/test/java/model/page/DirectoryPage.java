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

public class DirectoryPage extends BasePage {

    @FindBy(xpath = "//h6[@class = 'oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement pageTitle;
    @FindBy(xpath = "//div[@class = 'oxd-grid-item oxd-grid-item--gutters'][2]//child::div[text() = '-- Select --']")
    private WebElement jobFilterField;
    @FindBy(xpath = "//button[text() = ' Search ']")
    private WebElement searchBtn;
    @FindBy(xpath = "//span[@class = 'oxd-text oxd-text--span']")
    private WebElement searchResult;
    @FindBy(xpath = "//div[@class = 'oxd-sheet oxd-sheet--rounded oxd-sheet--white orangehrm-directory-card']" +
            "//child::p[@class = 'oxd-text oxd-text--p orangehrm-directory-card-subtitle --break-words']")
    private WebElement searchTable;


    public DirectoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public DirectoryPage fillInJobTitle(String jobName) {
        getWait10().until(ExpectedConditions.visibilityOf(jobFilterField)).click();
        WebElement element = getDriver().findElement(By
                .xpath("//div[@role = 'listbox']//*[text() = '" + jobName + "']"));
        Actions actions = new Actions(getDriver());
        actions
                .pause(1000)
                .moveToElement(element)
                .click()
                .perform();
        return this;
    }

    public DirectoryPage clickSearchBtn() {
        searchBtn.click();
        return this;
    }

    private boolean isRecordFound() {
        String noRecords = "No Records Found";
        String resultSearch = getWait10().until(ExpectedConditions.visibilityOf(searchResult)).getText();
        if (noRecords.equals(resultSearch)) {
            System.out.println("No record found for this job in DB");
//            return false;
        }
        return true;
    }

    public boolean isJobPresentInSearchResult(String jobName) {
        if (isRecordFound()) {
            if(getListOfJobsFromSearchResult().size() < 1){
                System.out.println("No record found for this job in DB");
                return true;
            }
            for (String s : getListOfJobsFromSearchResult()) {
                if (!jobName.equals(s)) {
                    System.out.println(s);
                    return false;
                }
            }
        }
        return true;
    }

    private List<String> getListOfJobsFromSearchResult() {
        List<WebElement> elements = getWait10().until(ExpectedConditions.visibilityOf(searchTable)).findElements(By
                .xpath("//div[@class = 'oxd-sheet oxd-sheet--rounded oxd-sheet--white orangehrm-directory-card']" +
                        "//child::p[@class = 'oxd-text oxd-text--p orangehrm-directory-card-subtitle --break-words']"));
        ArrayList<String> jobs = new ArrayList<>();
        elements.forEach(element -> jobs.add(element.getText()));
        return jobs;
    }
}
