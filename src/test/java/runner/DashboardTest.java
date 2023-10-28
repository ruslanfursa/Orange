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
        boolean isNamePresent = new DashboardPage(getDriver())
                .isSheetsPresent(expectedName);
        Assert.assertTrue(isNamePresent);
    }

    @Test
    public void testQuestionIcon() {
        boolean isHelpPageOpened = new DashboardPage(getDriver())
                .clickQuestionIcon()
                .goToNewTab()
                .isPageOpened("starterhelp");
        Assert.assertTrue(isHelpPageOpened);
    }

    @Test
    public void testOrangeLinkInFooter() {
        boolean isOrangeProductSiteOpened = new DashboardPage(getDriver())
                .clickOrangeLinkInFooter()
                .goToNewTab()
                .isPageOpened("https://www.orangehrm.com/");
        Assert.assertTrue(isOrangeProductSiteOpened);
    }

    @Test(dataProvider = "searchSymbols")
    public void testSearchInSidebarMenu(String symbol) {
        boolean isSymbolPresent = new DashboardPage(getDriver())
                .typeSymbolInSearchField(symbol)
                .isSymbolPresentInSearchResult(symbol);
        Assert.assertTrue(isSymbolPresent);
    }

    @Test
    public void testTransitionToAdminPage() {
        boolean isCorrectTitle = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.ADMIN, new AdminPage(getDriver()))
                .isTitleCorrect("Admin");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToPIMpage() {
        boolean isCorrectTitle = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PIM, new PimPage(getDriver()))
                .isTitleCorrect("PIM");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToLeavePage() {
        boolean isCorrectTitle = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.LEAVE, new LeavePage(getDriver()))
                .isTitleCorrect("Leave");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToTimePage() {
        boolean isCorrectTitle = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.TIME, new TimePage(getDriver()))
                .isTitleCorrect("Time");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToRecruitmentPage() {
        boolean isCorrectTitle = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.RECRUITMENT, new RecruitmentPage(getDriver()))
                .isTitleCorrect("Recruitment");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToMyInfoPage() {
        boolean isCorrectTitle = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.MY_INFO, new MyInfoPage(getDriver()))
                .isTitleCorrect("PIM");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToPerformancePage() {
        boolean isCorrectTitle = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PERFORMANCE, new PerformancePage(getDriver()))
                .isTitleCorrect("Performance");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToDashboardPage() {
        boolean isCorrectTitle = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.DASHBOARD, new DashboardPage(getDriver()))
                .isTitleCorrect("Dashboard");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToDirectoryPage() {
        boolean isCorrectTitle = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.DIRECTORY, new DirectoryPage(getDriver()))
                .isTitleCorrect("Directory");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToMaintenancePage() {
        new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.MAINTENANCE, new LoginPage(getDriver()))
                .fillInValidPassword()
                .clickLoginBtn();
        boolean isCorrectTitle = new MaintenancePage(getDriver())
                .isTitleCorrect("Maintenance");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToClaimPage() {
        boolean isCorrectTitle = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.CLAIM, new ClaimPage(getDriver()))
                .isTitleCorrect("Claim");
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToBuzzPage() {
        boolean isCorrectTitle = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.BUZZ, new BuzzPage(getDriver()))
                .isTitleCorrect("Buzz");
        Assert.assertTrue(isCorrectTitle);
    }
}
