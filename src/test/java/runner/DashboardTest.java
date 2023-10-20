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
        Assert.assertTrue(isHelpPageOpened);
        new DashboardPage(getDriver())
                .clickLogout();

    }

    @Test
    public void testOrangeLinkInFooter() {
        boolean isOrangeProductSiteOpened = new LoginPage(getDriver())
                .login()
                .clickOrangeLinkInFooter()
                .goToNewTab()
                .isPageOpened("https://www.orangehrm.com/");
        Assert.assertTrue(isOrangeProductSiteOpened);
        new DashboardPage(getDriver())
                .clickLogout();

    }

    @Test(dataProvider = "searchSymbols")
    public void testSearchInSidebarMenu(String symbol) {
        boolean isSymbolPresent = new LoginPage(getDriver())
                .login()
                .typeSymbolInSearchField(symbol)
                .isSymbolPresentInSearchResult(symbol);
        Assert.assertTrue(isSymbolPresent);
        new DashboardPage(getDriver())
                .clickLogout();

    }

    @Test
    public void testTransitionToAdminPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.ADMIN, new AdminPage(getDriver()))
                .isTitleCorrect("Admin");
        Assert.assertTrue(isCorrectTitle);
        new AdminPage(getDriver())
                .clickLogout();

    }

    @Test
    public void testTransitionToPIMpage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PIM, new PimPage(getDriver()))
                .isTitleCorrect("PIM");
        Assert.assertTrue(isCorrectTitle);
        new PimPage(getDriver())
                .clickLogout();

    }

    @Test
    public void testTransitionToLeavePage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.LEAVE, new LeavePage(getDriver()))
                .isTitleCorrect("Leave");
        Assert.assertTrue(isCorrectTitle);
        new LeavePage(getDriver())
                .clickLogout();

    }

    @Test
    public void testTransitionToTimePage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.TIME, new TimePage(getDriver()))
                .isTitleCorrect("Time");
        Assert.assertTrue(isCorrectTitle);
        new TimePage(getDriver())
                .clickLogout();

    }

    @Test
    public void testTransitionToRecruitmentPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.RECRUITMENT, new RecruitmentPage(getDriver()))
                .isTitleCorrect("Recruitment");
        Assert.assertTrue(isCorrectTitle);
        new RecruitmentPage(getDriver())
                .clickLogout();

    }

    @Test
    public void testTransitionToMyInfoPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.MY_INFO, new MyInfoPage(getDriver()))
                .isTitleCorrect("PIM");
        Assert.assertTrue(isCorrectTitle);
        new MyInfoPage(getDriver())
                .clickLogout();

    }

    @Test
    public void testTransitionToPerformancePage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PERFORMANCE, new PerformancePage(getDriver()))
                .isTitleCorrect("Performance");
        Assert.assertTrue(isCorrectTitle);
        new PerformancePage(getDriver())
                .clickLogout();

    }

    @Test
    public void testTransitionToDashboardPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.DASHBOARD, new DashboardPage(getDriver()))
                .isTitleCorrect("Dashboard");
        Assert.assertTrue(isCorrectTitle);
        new DashboardPage(getDriver())
                .clickLogout();

    }

    @Test
    public void testTransitionToDirectoryPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.DIRECTORY, new DirectoryPage(getDriver()))
                .isTitleCorrect("Directory");
        Assert.assertTrue(isCorrectTitle);
        new DashboardPage(getDriver())
                .clickLogout();

    }

    @Test
    public void testTransitionToMaintenancePage() {
        new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.MAINTENANCE, new LoginPage(getDriver()))
                .fillInValidPassword()
                .clickLoginBtn();
        boolean isCorrectTitle = new MaintenancePage(getDriver())
                .isTitleCorrect("Maintenance");
        Assert.assertTrue(isCorrectTitle);
        new MaintenancePage(getDriver())
                .clickLogout();

    }

    @Test
    public void testTransitionToClaimPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.CLAIM, new ClaimPage(getDriver()))
                .isTitleCorrect("Claim");
        Assert.assertTrue(isCorrectTitle);
        new ClaimPage(getDriver())
                .clickLogout();

    }

    @Test
    public void testTransitionToBuzzPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.BUZZ, new BuzzPage(getDriver()))
                .isTitleCorrect("Buzz");
        Assert.assertTrue(isCorrectTitle);
        new BuzzPage(getDriver())
                .clickLogout();

    }
}
