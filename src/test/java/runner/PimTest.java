package runner;

import model.base.SideBarMenu;
import model.page.DashboardPage;
import model.page.PimPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class PimTest extends BaseTest {

    @Test
    public void testAddEmployee() {
        boolean isEmployeeCreated = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PIM, new PimPage(getDriver()))
                .clickAddBtn()
                .fillInFirstName()
                .fillInLastName()
                .clickSaveBtn()
                .isUserCreated();
        Assert.assertTrue(isEmployeeCreated);
    }
}
