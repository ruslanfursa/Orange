package runner;

import model.base.SideBarMenu;
import model.page.AdminPage;
import model.page.DashboardPage;
import model.page.PimPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

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
}
