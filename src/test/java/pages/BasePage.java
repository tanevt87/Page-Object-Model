package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Defaults;

public class BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    private static final int MIN_WAIT_MILLIS = 5000;
    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void type(By selector, String text) {
        waitForElementToBeClickable(selector);
        driver.findElement(selector).sendKeys(text);
    }

    protected void click(By selector) {
        waitForElementToBeClickable(selector);
        driver.findElement(selector).click();
    }

    protected String getText(By selector) {
        LOGGER.info("Retrieving text");
        waitForVisibilityOf(selector);
        //Sync logic (explicit wait)
        String text = driver.findElement(selector).getText().trim();
        LOGGER.info("Text found:" + text);
        return text;
    }

    private void waitForVisibilityOf(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, MIN_WAIT_MILLIS);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(selector)));
    }


    private void waitForElementToBeClickable(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, MIN_WAIT_MILLIS);
        wait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    protected void scroll(By selector) {
        //To be implemented
    }

    protected void navigateTo(String url) {
        driver.navigate().to(Defaults.BASE_URL + url);
    }
        protected void clearDefaultTextAndType(By selector , String text) {
        waitForElementToBeClickable(selector);
        driver.findElement(selector).clear();
        driver.findElement(selector).sendKeys(text);
    }
}
