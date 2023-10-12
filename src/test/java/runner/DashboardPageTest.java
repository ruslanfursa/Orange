package runner;

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
        new LoginPage(getDriver())
                .fillInValidUserName()
                .fillInValidPassword()
                .clickLoginBtn();
        boolean isNamePresent = new DashboardPage(getDriver())
                .isSheetsPresent(expectedName);
        Assert.assertTrue(isNamePresent);
        new DashboardPage(getDriver())
                .clickLogout();
    }
}
