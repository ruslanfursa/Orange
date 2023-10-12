package model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface SideBarMenu {

    By adminLink = By.xpath("//a[@href = '/web/index.php/admin/viewAdminModule']");
    By pimLink = By.xpath("//a[@href = '/web/index.php/pim/viewPimModule']");
    By leaveLink = By.xpath("//a[@href = '/web/index.php/leave/viewLeaveModule']");
    By timeLink = By.xpath("//a[@href = '/web/index.php/time/viewTimeModule']");
    By recruitmentLink = By.xpath("//a[@href = '/web/index.php/recruitment/viewRecruitmentModule']");
    By myInfoLink = By.xpath("//a[@href = '/web/index.php/pim/viewMyDetails']");
    By performanceLink = By.xpath("//a[@href = '/web/index.php/performance/viewPerformanceModule']");
    By dashboardLink = By.xpath("//a[@href = '/web/index.php/dashboard/index']");
    By directoryLink = By.xpath("//a[@href = '/web/index.php/directory/viewDirectory']");
    By maintenanceLink = By.xpath("//a[@href = '/web/index.php/maintenance/viewMaintenanceModule']");
    By claimLink = By.xpath("//a[@href = '/web/index.php/claim/viewClaimModule']");
    By buzzLink = By.xpath("//a[@href = '/web/index.php/buzz/viewBuzz']");

    enum LinkFromSidebarMenu {
        ADMIN(adminLink),
        PIM(pimLink),
        LEAVE(leaveLink),
        TIME(timeLink),
        RECRUITMENT(recruitmentLink),
        MY_INFO(myInfoLink),
        PERFORMANCE(performanceLink),
        DASHBOARD(dashboardLink),
        DIRECTORY(directoryLink),
        MAINTENANCE(maintenanceLink),
        CLAIM(claimLink),
        BUZZ(buzzLink);

        private final By locator;

        LinkFromSidebarMenu(By locator) {
            this.locator = locator;
        }

        public WebElement getLocator(WebDriverWait driver) {
            return driver.until(ExpectedConditions.elementToBeClickable(locator));
        }
    }

    <Page extends BasePage> Page clickLinkFromSidebarMenu(LinkFromSidebarMenu link, Page page);
}
