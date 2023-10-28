package runner;

import model.base.SideBarMenu;
import model.page.DashboardPage;
import model.page.LeavePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class LeaveTest extends BaseTest {

    @Test
    public void testRenameHoliday() {
        boolean isHolidayEdited = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.LEAVE, new LeavePage(getDriver()))
                .clickConfigureTab()
                .clickHolidaysInDropDownList()
                .clickEditIcon()
                .editHolidayName()
                .editHolidayName()
                .clickSaveBtn()
                .isHolidayEdited();
        Assert.assertTrue(isHolidayEdited);
    }
}
