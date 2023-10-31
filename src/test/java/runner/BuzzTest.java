package runner;

import model.base.SideBarMenu;
import model.page.BuzzPage;
import model.page.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class BuzzTest extends BaseTest {

    @Test
    public void testCreateBuzz() {
        boolean isNewBuzzExist = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.BUZZ, new BuzzPage(getDriver()))
                .fillInBuzzField()
                .clickPostBtn()
                .isNewBuzzCreated();
        Assert.assertTrue(isNewBuzzExist);
    }
}
