package runner;

import model.base.SideBarMenu;
import model.page.DashboardPage;
import model.page.TimePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class TimeTest extends BaseTest {

    @Test
    public void testToggles() {
        boolean isTogglesDisabled = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.TIME, new TimePage(getDriver()))
                .clickAttendanceTab()
                .clickConfigurationInDropdownList()
                .clickToggles()
                .clickSaveBtn()
                .isTogglesDisabled();
        Assert.assertTrue(isTogglesDisabled);
    }
}
