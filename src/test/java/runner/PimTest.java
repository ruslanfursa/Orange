package runner;

import model.base.SideBarMenu;
import model.page.LoginPage;
import model.page.PimPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import runner.base.BaseTest;
@Listeners(screenshots.Listener.class)
@Ignore
public class PimTest extends BaseTest {

    @Test
    public void testAddEmployee() {
        boolean isEmployeeCreated = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PIM, new PimPage(getDriver()))
                .clickAddBtn()
                .fillInFirstName()
                .fillInLastName()
                .clickSaveBtn()
                .isUserCreated();
        new PimPage(getDriver())
                .clickLogout();
        Assert.assertTrue(isEmployeeCreated);
    }
}
