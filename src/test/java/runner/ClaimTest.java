package runner;

import model.base.SideBarMenu;
import model.page.ClaimPage;
import model.page.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class ClaimTest extends BaseTest {

    @Test
    public void testCreateClaim() {
        boolean isNewClaimCreated = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.CLAIM, new ClaimPage(getDriver()))
                .clickSubmitClaimTab()
                .clickEventDropdownArrow()
                .chooseEventType()
                .clickCurrencyDropdownArrow()
                .chooseCurrencyType()
                .fillInRemarksField()
                .clickCreateBtn()
                .clickSubmitBtn()
                .clickMyClaimsTab()
                .isClaimCreated();
        Assert.assertTrue(isNewClaimCreated);
    }
}
