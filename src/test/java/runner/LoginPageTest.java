package runner;

import com.github.javafaker.Faker;
import model.page.DashboardPage;
import model.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class LoginPageTest extends BaseTest {
    private final String validUserName = "Admin";
    private final String validPassword = "admin123";
    private final String invalidUserName = new Faker().name().firstName();

    @Test
    public void testLoginWithValidCredentials() {
        new LoginPage(getDriver())
                .fillInUserNameField(validUserName)
                .fillInPasswordField(validPassword)
                .clickLoginBtn();
        String pageTitle = new DashboardPage(getDriver())
                .getPageTitle();
        Assert.assertEquals(pageTitle, "Dashboard");
    }

    @Test
    public void testLoginWithInvalidUsername() {
        boolean isErrorMessageShown = new LoginPage(getDriver())
                .fillInUserNameField(invalidUserName)
                .fillInPasswordField(validPassword)
                .clickLoginBtn()
                .isErrorMessageShown();
        Assert.assertTrue(isErrorMessageShown);
    }
}
