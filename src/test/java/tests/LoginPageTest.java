package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.Defaults;

public class LoginPageTest extends BaseTest {


    @Test
    @Tag("smoke")
    @Tag("positive")
    @DisplayName("Can login with valid credentials")
    public void canLoginWithValidCredentials() {
        webApp.loginPage().navigate();
        Assertions.assertEquals("pragmatic", webApp.loginPage().getCompanyName());
        webApp.loginPage()
                .enterEmail(Defaults.EMAIL)
                .enterPassword(Defaults.PASSWORD)
                .clickLoginButton();
        Assertions.assertEquals(Defaults.EMAIL, webApp.homePage().getLoggedUser());
    }

    @Test
    @Tag("smoke")
    @Tag("negative")
    @DisplayName("Cant login with invalid email and invalid password")
    public void cantLoginWithInvalidEmail() {
        webApp.loginPage().navigate();
        Assertions.assertEquals("pragmatic", webApp.loginPage().getCompanyName());
        webApp.loginPage()
                .enterEmail("test@qaground.com")
                .enterPassword("Dummy password")
                .clickLoginButton();
        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", webApp.loginPage().getErrorMessage());
    }

    @Test
    @Tag("smoke")
    @Tag("negative")
    @DisplayName("Cant login with valid email and invalid password")
    public void cantLoginWithInvalidPassword() {
        webApp.loginPage().navigate();
        Assertions.assertEquals("pragmatic", webApp.loginPage().getCompanyName());
        webApp.loginPage()
                .enterEmail(Defaults.EMAIL)
                .enterPassword("Dummy password")
                .clickLoginButton();
        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", webApp.loginPage().getErrorMessage());
    }

    @Test
    @Tag("smoke")
    @Tag("negative")
    @DisplayName("Cant login with invalid email and valid password")
    public void cantLoginWithInvalidEmailAndValidPassword() {
        webApp.loginPage().navigate();
        Assertions.assertEquals("pragmatic", webApp.loginPage().getCompanyName());
        webApp.loginPage()
                .enterEmail("test@gmail.com")
                .enterPassword(Defaults.PASSWORD)
                .clickLoginButton();
        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", webApp.loginPage().getErrorMessage());
    }

    @Test
    @Tag("smoke")
    @Tag("negative")
    @DisplayName("Cant login with blank credentials")
    public void cantLoginWithBlankCredentials() {
        webApp.loginPage().navigate();
        webApp.loginPage()
                .enterEmail("")
                .enterPassword("")
                .clickLoginButton();
        Assertions.assertEquals("pragmatic", webApp.loginPage().getCompanyName());
        Assertions.assertEquals("Моля, попълнете вашия email", webApp.loginPage().getErrorMessage());
    }

    @Test
    @Tag("smoke")
    @Tag("negative")
    @DisplayName("Cant login with blank email and valid password")
    public void cantLoginWithBlankEmailAndValidPassword() {
        webApp.loginPage().navigate();
        Assertions.assertEquals("pragmatic", webApp.loginPage().getCompanyName());
        webApp.loginPage()
                .enterEmail("")
                .enterPassword("123456")
                .clickLoginButton();
        Assertions.assertEquals("Моля, попълнете вашия email", webApp.loginPage().getErrorMessage());
    }

    @Test
    @Tag("smoke")
    @Tag("negative")
    @DisplayName("Cant login with valid email and blank password")
    public void cantLoginWithValidEmailAndBlankPassword() {
        webApp.loginPage().navigate();
        Assertions.assertEquals("pragmatic", webApp.loginPage().getCompanyName());
        webApp.loginPage()
                .enterEmail(Defaults.EMAIL)
                .enterPassword("")
                .clickLoginButton();
        Assertions.assertEquals("Моля, попълнете вашата парола", webApp.loginPage().getErrorMessage());
    }

    @Test
    @Tag("smoke")
    @Tag("positive")
    @DisplayName("forgot password feature test with valid/existing email")
    public void forgotPasswordFeatureTest() {
        webApp.loginPage().navigate()
                .clickForgotPasswordButton()
                .enterForgottenEmail(Defaults.EMAIL)
                .clickRecoveryEmailSubmitButton();
        Assertions.assertEquals("На e-mail адреса Ви беше изпратен линк, чрез който можете да смените паролата си.",
                webApp.loginPage().verifyRecoveryEmailMessage());
    }
}


