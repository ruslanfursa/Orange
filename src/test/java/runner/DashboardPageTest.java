package runner;

import model.base.SideBarMenu;
import model.page.AdminPage;
import model.page.DashboardPage;
import model.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class DashboardPageTest extends BaseTest {

    @DataProvider(name = "expectedNames")
    public Object[][] providerExpectedNames() {
        return new Object[][]{{"Time at Work"}, {"My Actions"}, {"Quick Launch"}, {"Employee Distribution by Sub Unit"},
                {"Buzz Latest Posts"}, {"Employees on Leave Today"}, {"Employee Distribution by Location"}};
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

    @Test
    public void testTransitionToAdminPAge() {
        boolean isCorrectTitle = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.ADMIN, new AdminPage(getDriver()))
                .isTitleCorrect("Admin");
        Assert.assertTrue(isCorrectTitle);
    }
}
