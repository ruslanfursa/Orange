package runner;

import model.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class LoginPageTest extends BaseTest {

    @Test
    public void testLoginWithValidCredentials() {
        String pageTitle = new LoginPage(getDriver())
                .fillInUserNameField()
                .fillInPasswordField()
                .clickLoginBtn()
                .getPageTitle();
        Assert.assertEquals(pageTitle, "Dashboard");
    }
}
