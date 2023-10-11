package runner;

import com.github.javafaker.Faker;
import model.page.DashboardPage;
import model.page.LoginPage;
import model.page.ResetPasswordPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.base.BaseTest;

public class LoginPageTest extends BaseTest {
    private final String validUserName = "Admin";
    private final String validPassword = "admin123";
    private final String invalidUserName = new Faker().name().firstName();
    private final String invalidPassword = new Faker().internet().password();

    @Test
    public void testLoginWithValidCredentials() {
        new LoginPage(getDriver())
                .fillInUserNameField(validUserName)
                .fillInPasswordField(validPassword)
                .clickLoginBtn();
        String pageTitle = new DashboardPage(getDriver())
                .getPageTitle();
        Assert.assertEquals(pageTitle, "Dashboard");
        new DashboardPage(getDriver())
                .clickLogout();
    }

    @Test
    public void testLoginWithInvalidUsername() {
        boolean isErrorMessageShown = new LoginPage(getDriver())
                .fillInUserNameField(invalidUserName)
                .fillInPasswordField(validPassword)
                .clickLoginBtn()
                .isInvalidCredentialsMessageShown();
        Assert.assertTrue(isErrorMessageShown);
    }

    @Test
    public void testLoginWithInvalidPassword() {
        boolean isErrorMessageShown = new LoginPage(getDriver())
                .fillInUserNameField(validUserName)
                .fillInPasswordField(invalidPassword)
                .clickLoginBtn()
                .isInvalidCredentialsMessageShown();
        Assert.assertTrue(isErrorMessageShown);
    }

    @Test
    public void testLoginWithoutFillingInUserNameAndPasswordFields() {
        boolean isRequiredMessagesShown = new LoginPage(getDriver())
                .clickLoginBtn()
                .isRequiredMessagesShown();
        Assert.assertTrue(isRequiredMessagesShown);
    }

    @Test
    public void testForgotYourPassword() {
        Assert.assertEquals(new LoginPage(getDriver())
                .clickForgotYourPassword()
                .getPageTitle(), "Reset Password");
        new ResetPasswordPage(getDriver())
                .clickCancelBtn();
    }

    @Test
    public void testTransitionToLinkedinPage() {
        boolean isLinkedinPageOpened = new LoginPage(getDriver())
                .clickLinkedinIcon()
                .goToNewTab()
                .isPageOpened("linkedin");
        Assert.assertTrue(isLinkedinPageOpened);
    }

    @Test
    public void testTransitionToFacebookPage() {
        boolean isLinkedinPageOpened = new LoginPage(getDriver())
                .clickFacebookIcon()
                .goToNewTab()
                .isPageOpened("facebook");
        Assert.assertTrue(isLinkedinPageOpened);
    }

    @Test
    public void testTransitionToTwitterPage() {
        boolean isTwitterPageOpened = new LoginPage(getDriver())
                .clickTwitterIcon()
                .goToNewTab()
                .isPageOpened("twitter");
        Assert.assertTrue(isTwitterPageOpened);
    }

    @Test
    public void testTransitionToYoutubePage() {
        boolean isYoutubePageOpened = new LoginPage(getDriver())
                .clickYoutubeIcon()
                .goToNewTab()
                .isPageOpened("youtube");
        Assert.assertTrue(isYoutubePageOpened);
    }
}
