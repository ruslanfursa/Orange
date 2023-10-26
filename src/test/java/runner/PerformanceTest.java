package runner;

import model.base.SideBarMenu;
import model.page.LoginPage;
import model.page.PerformancePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class PerformanceTest extends BaseTest {

    @Test
    public void testAddLog() {
        boolean isNewLogDisplayed = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.PERFORMANCE, new PerformancePage(getDriver()))
                .clickMyTrackersTab()
                .clickViewBtn()
                .clickAddLogBtn()
                .fillInLogName()
                .fillInCommentField()
                .clickDislikeIcon()
                .clickSaveBtn()
                .isNewLogAndCommentSaved();
        Assert.assertTrue(isNewLogDisplayed);
        new PerformancePage(getDriver())
                .clickLogout();
    }
}
