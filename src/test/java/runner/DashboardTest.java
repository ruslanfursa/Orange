package runner;

import model.base.SideBarMenu;
import model.page.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class DashboardTest extends BaseTest {

    @DataProvider(name = "expectedNames")
    public Object[][] providerExpectedNames() {
        return new Object[][]{{"Time at Work"}, {"My Actions"}, {"Quick Launch"}, {"Employee Distribution by Sub Unit"},
                {"Buzz Latest Posts"}, {"Employees on Leave Today"}, {"Employee Distribution by Location"}};
    }

    @DataProvider(name = "searchSymbols")
    public Object[][] providerSearchSymbols() {
        return new Object[][]{{"a"}, {"m"}, {"o"}};
    }

    @Test(dataProvider = "expectedNames")
    public void testDashboardSheetsNames(String expectedName) {
        boolean isNamePresent = new LoginPage(getDriver())
                .login()
                .isSheetsPresent(expectedName);
        Assert.assertTrue(isNamePresent);
        new DashboardPage(getDriver())
                .clickLogout();
    }

    @Test
    public void testQuestionIcon() {
        boolean isHelpPageOpened = new LoginPage(getDriver())
                .login()
                .clickQuestionIcon()
                .goToNewTab()
                .isPageOpened("starterhelp");
        new DashboardPage(getDriver())
                .clickLogout();
        Assert.assertTrue(isHelpPageOpened);
    }

    @Test
    public void testOrangeLinkInFooter() {
        boolean isOrangeProductSiteOpened = new LoginPage(getDriver())
                .login()
                .clickOrangeLinkInFooter()
                .goToNewTab()
                .isPageOpened("https://www.orangehrm.com/");
        new DashboardPage(getDriver())
                .clickLogout();
        Assert.assertTrue(isOrangeProductSiteOpened);
    }

    @Test(dataProvider = "searchSymbols")
    public void testSearchInSidebarMenu(String symbol) {
        boolean isSymbolPresent = new LoginPage(getDriver())
                .login()
                .typeSymbolInSearchField(symbol)
                .isSymbolPresentInSearchResult(symbol);
        new DashboardPage(getDriver())
                .clickLogout();
        Assert.assertTrue(isSymbolPresent);
    }

    @Test
    public void testTransitionToAdminPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.ADMIN, new AdminPage(getDriver()))
                .isTitleCorrect("Admin");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test(dependsOnMethods = "testTransitionToAdminPage")
    public void testTransitionToPIMpage() {
        boolean isCorrectTitle = new AdminPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PIM, new PimPage(getDriver()))
                .isTitleCorrect("PIM");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test(dependsOnMethods = {"testTransitionToAdminPage", "testTransitionToPIMpage"})
    public void testTransitionToLeavePage() {
        boolean isCorrectTitle = new PimPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.LEAVE, new LeavePage(getDriver()))
                .isTitleCorrect("Leave");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test(dependsOnMethods = {"testTransitionToAdminPage", "testTransitionToPIMpage", "testTransitionToLeavePage"})
    public void testTransitionToTimePage() {
        boolean isCorrectTitle = new LeavePage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.TIME, new TimePage(getDriver()))
                .isTitleCorrect("Time");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test(dependsOnMethods = {"testTransitionToAdminPage", "testTransitionToPIMpage", "testTransitionToLeavePage",
            "testTransitionToTimePage"})
    public void testTransitionToRecruitmentPage() {
        boolean isCorrectTitle = new TimePage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.RECRUITMENT, new RecruitmentPage(getDriver()))
                .isTitleCorrect("Recruitment");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test(dependsOnMethods = {"testTransitionToAdminPage", "testTransitionToPIMpage", "testTransitionToLeavePage",
            "testTransitionToTimePage", "testTransitionToRecruitmentPage"})
    public void testTransitionToMyInfoPage() {
        boolean isCorrectTitle = new RecruitmentPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.MY_INFO, new MyInfoPage(getDriver()))
                .isTitleCorrect("PIM");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test(dependsOnMethods = {"testTransitionToAdminPage", "testTransitionToPIMpage", "testTransitionToLeavePage",
            "testTransitionToTimePage", "testTransitionToRecruitmentPage", "testTransitionToMyInfoPage"})
    public void testTransitionToPerformancePage() {
        boolean isCorrectTitle = new MyInfoPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PERFORMANCE, new PerformancePage(getDriver()))
                .isTitleCorrect("Performance");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test(dependsOnMethods = {"testTransitionToAdminPage", "testTransitionToPIMpage", "testTransitionToLeavePage",
            "testTransitionToTimePage", "testTransitionToRecruitmentPage", "testTransitionToMyInfoPage",
            "testTransitionToPerformancePage"})
    public void testTransitionToDashboardPage() {
        boolean isCorrectTitle = new PerformancePage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.DASHBOARD, new DashboardPage(getDriver()))
                .isTitleCorrect("Dashboard");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test(dependsOnMethods = {"testTransitionToAdminPage", "testTransitionToPIMpage", "testTransitionToLeavePage",
            "testTransitionToTimePage", "testTransitionToRecruitmentPage", "testTransitionToMyInfoPage",
            "testTransitionToPerformancePage", "testTransitionToDashboardPage"})
    public void testTransitionToDirectoryPage() {
        boolean isCorrectTitle = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.DIRECTORY, new DirectoryPage(getDriver()))
                .isTitleCorrect("Directory");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test(dependsOnMethods = {"testTransitionToAdminPage", "testTransitionToPIMpage", "testTransitionToLeavePage",
            "testTransitionToTimePage", "testTransitionToRecruitmentPage", "testTransitionToMyInfoPage",
            "testTransitionToPerformancePage", "testTransitionToDashboardPage", "testTransitionToDirectoryPage"})
    public void testTransitionToMaintenancePage() {
        new DirectoryPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.MAINTENANCE, new LoginPage(getDriver()))
                .fillInValidPassword()
                .clickLoginBtn();
        boolean isCorrectTitle = new MaintenancePage(getDriver())
                .isTitleCorrect("Maintenance");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test(dependsOnMethods = {"testTransitionToAdminPage", "testTransitionToPIMpage", "testTransitionToLeavePage",
            "testTransitionToTimePage", "testTransitionToRecruitmentPage", "testTransitionToMyInfoPage",
            "testTransitionToPerformancePage", "testTransitionToDashboardPage", "testTransitionToDirectoryPage",
            "testTransitionToMaintenancePage"})
    public void testTransitionToClaimPage() {
        boolean isCorrectTitle = new MaintenancePage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.CLAIM, new ClaimPage(getDriver()))
                .isTitleCorrect("Claim");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test(dependsOnMethods = {"testTransitionToAdminPage", "testTransitionToPIMpage", "testTransitionToLeavePage",
            "testTransitionToTimePage", "testTransitionToRecruitmentPage", "testTransitionToMyInfoPage",
            "testTransitionToPerformancePage", "testTransitionToDashboardPage", "testTransitionToDirectoryPage",
            "testTransitionToMaintenancePage", "testTransitionToClaimPage"})
    public void testTransitionToBuzzPage() {
        boolean isCorrectTitle = new ClaimPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.BUZZ, new BuzzPage(getDriver()))
                .isTitleCorrect("Buzz");
        Assert.assertTrue(isCorrectTitle);
    }

}
