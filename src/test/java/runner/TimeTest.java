package runner;

import model.base.SideBarMenu;
import model.page.LoginPage;
import model.page.TimePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import runner.base.BaseTest;
@Listeners(screenshots.Listener.class)
public class TimeTest extends BaseTest {

    @Test
    public void testToggles() {
        boolean isTogglesDisabled = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.TIME, new TimePage(getDriver()))
                .clickAttendanceTab()
                .clickConfigurationInDropdownList()
                .clickToggles()
                .clickSaveBtn()
                .isTogglesDisabled();
        Assert.assertTrue(isTogglesDisabled);
        new TimePage(getDriver())
                .clickLogout();

    }
}
