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
                .clickVacanciesTab(true)
                .clickAddBtn()
                .fillInNewVacancyBlank()
                .clickSaveBtn()
                .clickVacanciesTab(false)
                .isVacancyCrated();
        Assert.assertTrue(isVacancyCreated);
        new RecruitmentPage(getDriver())
                .clickLogout();
    }
}
