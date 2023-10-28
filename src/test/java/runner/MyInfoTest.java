package runner;

import model.base.SideBarMenu;
import model.page.DashboardPage;
import model.page.MyInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class MyInfoTest extends BaseTest {

    @Test
    public void testUsernameChange() {
        boolean isNewNameDisplayed = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.MY_INFO, new MyInfoPage(getDriver()))
                .clearFirstNameField()
                .fillInNewName()
                .clickSaveBtn()
                .isUserNameChanged();
        Assert.assertTrue(isNewNameDisplayed);
    }
}
