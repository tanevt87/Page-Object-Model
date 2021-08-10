package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);
    private By loggedUserSelector = By.cssSelector("div.userpanel-header");
    private final String URL = "/home";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getLoggedUser() {
        return getText(loggedUserSelector);
    }

    public void navigate() {
        LOGGER.info("Navigating to url:" + URL);
        navigateTo(URL);
    }
}
