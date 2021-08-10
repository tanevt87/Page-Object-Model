package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {
    private WebDriver driver;

    public WebDriver startBrowser(String browserType) {
        switch (browserType.toLowerCase()) {
            case "chrome":
                return startChrome();

            case "firefox":
                return startFirefox();

            case "edge":
                return startEdge();
            default:
                throw new IllegalArgumentException("Not supported browser type!");

        }

    }

    private WebDriver startEdge() {
        EdgeOptions options = new EdgeOptions();
//        options.setHeadless(isHeadless());
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }

    private WebDriver startFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(isHeadless());
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }

    private WebDriver startChrome() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(isHeadless());
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }

    public void quit() {
        driver.quit();
    }


    private boolean isHeadless() {
        String headless = System.getProperty("headless");
        return headless.equals("true");
    }

    public static void downloadDriver(String driverType) {
        switch (driverType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                break;
            default:
                throw new IllegalArgumentException("Not supported browser!");
        }
    }
}
