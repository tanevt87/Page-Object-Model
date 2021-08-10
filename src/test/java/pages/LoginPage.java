package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Defaults;

public class LoginPage extends BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);
    private String URL = "/login";
    private By emailSelector = By.id("loginusername");
    private By passwordSelector = By.id("loginpassword");
    private By loginButtonSelector = By.id("loginsubmit");
    private By errorMessageSelector = By.id("error");
    private By companyNameSelector = By.xpath("//div[@id='wellcome']");
    private By forgotPasswordSelector = By.id("newpass2");
    private By forgotEmailFieldSelector = By.id("email");
    private By recoveryEmailSubmitSelector = By.id("submit");
    private By successfulRecoveryEmailMessage = By.cssSelector(".alert.selenium-message.alert-success.sticky");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void defaultLogin() {
        navigate();
        enterEmail(Defaults.EMAIL);
        enterPassword(Defaults.PASSWORD);
        clickLoginButton();
    }

    public LoginPage enterEmail(String email) {
        LOGGER.info("Entering email:" + email);
        type(emailSelector, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        LOGGER.info("Entering password:" + password);
        type(passwordSelector, password);
        return this;
    }

    public LoginPage clickLoginButton() {
        LOGGER.info("Click Login button");
        click(loginButtonSelector);
        return this;
    }

    public String getErrorMessage() {
        return getText(errorMessageSelector);
    }

    public String getCompanyName() {
        return getText(companyNameSelector);
    }

    public void scrollToLoginButton() {
        scroll(loginButtonSelector);
    }

    public LoginPage navigate() {
        LOGGER.info("Navigating to:" + URL);
        navigateTo(URL);
        return this;
    }

    public LoginPage clickForgotPasswordButton() {
        LOGGER.info("Click forgot password button");
        click(forgotPasswordSelector);
        return this;
    }

    public LoginPage enterForgottenEmail(String forgottenEmail) {
        LOGGER.info("Enter forgotten email" + forgottenEmail);
        type(forgotEmailFieldSelector, forgottenEmail);
        return this;
    }

    public LoginPage clickRecoveryEmailSubmitButton() {
        LOGGER.info("Click recovery submit button");
        click(recoveryEmailSubmitSelector);
        return this;
    }

    public String verifyRecoveryEmailMessage() {
        return getText(successfulRecoveryEmailMessage);
    }

}

