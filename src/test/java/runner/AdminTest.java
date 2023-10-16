package runner;

import model.base.SideBarMenu;
import model.page.AdminPage;
import model.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class AdminTest extends BaseTest {

    @Test
    public void testSearchUser() {
        boolean isUserFound = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.ADMIN, new AdminPage(getDriver()))
                .fillInUserName()
                .clickSearchBtn()
                .isUserFound();
        new AdminPage(getDriver())
                .clickLogout();
        Assert.assertTrue(isUserFound);
    }
}
