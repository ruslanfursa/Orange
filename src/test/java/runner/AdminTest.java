package runner;

import model.base.SideBarMenu;
import model.page.AdminPage;
import model.page.DashboardPage;
import model.page.PimPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

import java.util.List;

public class AdminTest extends BaseTest {

    @Test
    public void testCreateNewUser() {
        String employeeName = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PIM, new PimPage(getDriver()))
                .createEmployee();
        boolean isUserCreated = new PimPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.ADMIN, new AdminPage(getDriver()))
                .clickAddBtn()
                .clickUserRoleArrow()
                .chooseUserRole()
                .clickStatusArrow()
                .chooseUserStatus()
                .fillInEmployeeNameField(employeeName)
                .fillInUsername()
                .fillInPassword()
                .confirmPassword()
                .clickSaveBtn()
                .isUserExist();
        Assert.assertTrue(isUserCreated);
    }

    @Test
    public void testLoginNewUser() {
        String employeeName = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PIM, new PimPage(getDriver()))
                .createEmployee();
        List<String> credentials = new PimPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.ADMIN, new AdminPage(getDriver()))
                .clickAddBtn()
                .createNewUser(employeeName);
        String pageName = new AdminPage(getDriver())
                .clickLogout()
                .login(credentials.get(0), credentials.get(1))
                .getPageName();
        Assert.assertEquals(pageName, "Dashboard");
    }

    @Test
    public void testChangePassword() {
        String employeeName = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PIM, new PimPage(getDriver()))
                .createEmployee();
        List<String> credentials = new PimPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.ADMIN, new AdminPage(getDriver()))
                .clickAddBtn()
                .createNewUser(employeeName);
        String newPassword = new AdminPage(getDriver())
                .changePassword(employeeName);
        credentials.set(1, newPassword);
        String pageName = new AdminPage(getDriver())
                .clickLogout()
                .login(credentials.get(0), credentials.get(1))
                .getPageName();
        Assert.assertEquals(pageName, "Dashboard");
    }
}
