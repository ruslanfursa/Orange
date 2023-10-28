package runner;

import model.base.SideBarMenu;
import model.page.DashboardPage;
import model.page.RecruitmentPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class RecruitmentTest extends BaseTest {

    @Test
    public void testAddNewVacancy() {
        boolean isVacancyCreated = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.RECRUITMENT, new RecruitmentPage(getDriver()))
                .createNewVacancy()
                .isVacancyCrated();
        Assert.assertTrue(isVacancyCreated);
    }

    @Test
    public void testDeleteVacancy() {
        boolean isVacancyPresent = new DashboardPage(getDriver())
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.RECRUITMENT, new RecruitmentPage(getDriver()))
                .createNewVacancy()
                .deleteVacancy()
                .isVacancyCrated();
        Assert.assertFalse(isVacancyPresent);
    }
}
