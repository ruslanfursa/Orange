package runner;

import model.base.SideBarMenu;
import model.page.LeavePage;
import model.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import runner.base.BaseTest;
@Listeners(screenshots.Listener.class)
@Ignore
public class LeaveTest extends BaseTest {

    @Test
    public void testRenameHoliday() {
        boolean isHolidayEdited = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.LEAVE, new LeavePage(getDriver()))
                .clickConfigureTab()
                .clickHolidaysInDropDownList()
                .clickEditIcon()
                .editHolidayName()
                .editHolidayName()
                .clickSaveBtn()
                .isHolidayEdited();
        new LeavePage(getDriver())
                .clickLogout();
        Assert.assertTrue(isHolidayEdited);
    }
}
