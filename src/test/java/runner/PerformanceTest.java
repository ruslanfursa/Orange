package runner;

import model.base.SideBarMenu;
import model.page.DashboardPage;
import model.page.PerformancePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class PerformanceTest extends BaseTest {

    @Test
    public void testAddLog() {
        boolean isNewLogDisplayed = new DashboardPage(getDriver())
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
    }
}
