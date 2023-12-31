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
                .createBuzz()
                .isNewBuzzCreated();
        Assert.assertTrue(isNewBuzzExist);
    }

    @Test
    public void testEditBuzz() {
        boolean isBuzzEdited = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.BUZZ, new BuzzPage(getDriver()))
                .createBuzz()
                .clickKebabMenu()
                .clickEditPostInKebabMenu()
                .editBuzz()
                .clickPostBtn()
                .isNewBuzzEdited();
        Assert.assertTrue(isBuzzEdited);
    }

    @Test
    public void testDeleteBuzz() {
        boolean isBuzzExist = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.BUZZ, new BuzzPage(getDriver()))
                .createBuzz()
                .clickKebabMenu()
                .clickDeletePostInKebabMenu()
                .clickYesDeleteBtn()
                .isNewBuzzCreated();
        Assert.assertFalse(isBuzzExist);
    }
}
