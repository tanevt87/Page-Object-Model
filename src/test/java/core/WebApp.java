package core;

import org.openqa.selenium.WebDriver;
import pages.ClientPage;
import pages.HomePage;
import pages.ItemPage;
import pages.LoginPage;

public class WebApp {
    private WebDriver driver;
    //Pages
    private LoginPage loginPage;
    private HomePage homePage;
    private ItemPage itemPage;
    private ClientPage clientPage;

    public WebApp(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public ItemPage itemPage() {
        if (itemPage == null) {
            itemPage = new ItemPage(driver);
        }
        return itemPage;
    }

    public ClientPage clientPage() {
        if (clientPage == null) {
            clientPage = new ClientPage(driver);
        }
        return clientPage;
    }

}
