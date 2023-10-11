package runner;

import model.page.LoginPage;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class DashboardPageTest extends BaseTest {
    @Test
    public void testDashboardSheetsNames(){
        new LoginPage(getDriver());
    }
}
