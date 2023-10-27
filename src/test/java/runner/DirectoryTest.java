package runner;

import model.base.SideBarMenu;
import model.page.DirectoryPage;
import model.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class DirectoryTest extends BaseTest {

    @DataProvider(name = "jobNames")
    public Object[][] providerExpectedNames() {
        return new Object[][]{{"Account Assistant"}, {"Chief Executive Officer"}, {"Chief Financial Officer"},
                {"Chief Technical Officer"}, {"Content Specialist"}, {"Customer Success Manager"},
                {"Database Administrator"}, {"Finance Manager"}, {"Financial Analyst"}, {"Head of Support"},
                {"HR Associate"}, {"HR Manager"}, {"IT Manager"}, {"Network Administrator"}, {"Payroll Administrator"},
                {"Pre-Sales Coordinator"}, {"QA Engineer"}, {"QA Lead"}, {"Sales Representative"}, {"Social Media Marketer"},
                {"Software Architect"}, {"Software Engineer"}, {"Support Specialist"}, {"VP - Client Services"},
                {"VP - Sales & Marketing"}};
    }

    @Test(dataProvider = "jobNames")
    public void testSearchJobTitleFilter(String jobNme) {
        boolean isResultCorrect = new LoginPage(getDriver())
                .login()
                .clickLinkFromSidebarMenu(SideBarMenu.LinkFromSidebarMenu.DIRECTORY, new DirectoryPage(getDriver()))
                .fillInJobTitle(jobNme)
                .clickSearchBtn()
                .isJobPresentInSearchResult(jobNme);
        Assert.assertTrue(isResultCorrect);
        new DirectoryPage(getDriver())
                .clickLogout();
    }
}
