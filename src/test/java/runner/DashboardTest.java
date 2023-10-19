package runner;

import model.base.SideBarMenu;
import model.page.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import runner.base.BaseTest;

@Listeners(screenshots.Listener.class)
@Ignore
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
        new AdminPage(getDriver())
                .clickLogout();
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToPIMpage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PIM, new PimPage(getDriver()))
                .isTitleCorrect("PIM");
        new PimPage(getDriver())
                .clickLogout();
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToLeavePage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.LEAVE, new LeavePage(getDriver()))
                .isTitleCorrect("Leave");
        new LeavePage(getDriver())
                .clickLogout();
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToTimePage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.TIME, new TimePage(getDriver()))
                .isTitleCorrect("Time");
        new TimePage(getDriver())
                .clickLogout();
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToRecruitmentPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.RECRUITMENT, new RecruitmentPage(getDriver()))
                .isTitleCorrect("Recruitment");
        new RecruitmentPage(getDriver())
                .clickLogout();
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToMyInfoPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.MY_INFO, new MyInfoPage(getDriver()))
                .isTitleCorrect("PIM");
        new MyInfoPage(getDriver())
                .clickLogout();
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToPerformancePage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PERFORMANCE, new PerformancePage(getDriver()))
                .isTitleCorrect("Performance");
        new PerformancePage(getDriver())
                .clickLogout();
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToDashboardPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.DASHBOARD, new DashboardPage(getDriver()))
                .isTitleCorrect("Dashboard");
        new DashboardPage(getDriver())
                .clickLogout();
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToDirectoryPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.DIRECTORY, new DirectoryPage(getDriver()))
                .isTitleCorrect("Directory");
        new DashboardPage(getDriver())
                .clickLogout();
        Assert.assertTrue(isCorrectTitle);
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
        new MaintenancePage(getDriver())
                .clickLogout();
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToClaimPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.CLAIM, new ClaimPage(getDriver()))
                .isTitleCorrect("Claim");
        new ClaimPage(getDriver())
                .clickLogout();
        Assert.assertTrue(isCorrectTitle);
    }

    @Test
    public void testTransitionToBuzzPage() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.BUZZ, new BuzzPage(getDriver()))
                .isTitleCorrect("Buzz");
        new BuzzPage(getDriver())
                .clickLogout();
        Assert.assertTrue(isCorrectTitle);
    }
}
