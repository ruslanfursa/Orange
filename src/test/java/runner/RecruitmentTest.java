package runner;

import model.base.SideBarMenu;
import model.page.LoginPage;
import model.page.RecruitmentPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class RecruitmentTest extends BaseTest {

    @Test
    public void testAddNewVacancy() {
        boolean isVacancyCreated = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.RECRUITMENT, new RecruitmentPage(getDriver()))
                .createNewVacancy()
                .isVacancyCrated();
        Assert.assertTrue(isVacancyCreated);
        new RecruitmentPage(getDriver())
                .clickLogout();
    }

    @Test
    public void testDeleteVacancy() {
        boolean isVacancyPresent = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.RECRUITMENT, new RecruitmentPage(getDriver()))
                .createNewVacancy()
                .deleteVacancy()
                .isVacancyCrated();
        Assert.assertFalse(isVacancyPresent);
        new RecruitmentPage(getDriver())
                .clickLogout();
    }
}
