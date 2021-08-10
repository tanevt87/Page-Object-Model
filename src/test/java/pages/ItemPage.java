package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemPage extends BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemPage.class);
    private final String URL = "/objects/manage";
    private By headlineSelector = By.xpath("//div[@id='headline']//h2");
    private By nameSelector = By.name("name");
    private By priceSelector = By.name("price");
    private By saveButtonSelector = By.name("do_submit");
    private By createNewButtonSelector = By.cssSelector("a.newbtn.selenium-add-item");
    private By successMessageSelector = By.id("okmsg");
    private By warrantyPeriodSelector = By.id("warranty");
    private By expandSearchButtonSelector = By.id("searchbtn");
    private By searchResultTableSelector = By.id("fakturi_table");
    private By emptyResultListSelector = By.id("emptylist");
    private By searchButtonSelector = By.name("s");
    private By searchNameFieldSelector = By.name("nm");


    public ItemPage(WebDriver driver) {
        super(driver);
    }


    /**
     * Navigates to item page
     */
    public ItemPage navigate() {
        LOGGER.info("Navigating to url:" + URL);
        navigateTo(URL);
        return this;
    }

    /**
     * Returns the headline text
     *
     * @return text
     */
    public String getHeadline() {
        return getText(headlineSelector);
    }

    public ItemPage enterName(String name) {
        LOGGER.info("Entering item name:" + name);
        type(nameSelector, name);
        return this;
    }

    public ItemPage enterPrice(int price) {
        LOGGER.info("Entering item price:" + price);
        type(priceSelector, String.valueOf(price));
        return this;
    }

    public ItemPage enterWarrantyPeriod(int period) {
        LOGGER.info("Entering warranty period:" + period);
        type(warrantyPeriodSelector, String.valueOf(period));
        return this;
    }

    public ItemPage clickSearchButton() {
        LOGGER.info("Clicking Search button");
        click(searchButtonSelector);
        return this;
    }

    public ItemPage expandSearchSection() {
        LOGGER.info("Expanding Search section");
        click(expandSearchButtonSelector);
        return this;
    }

    public ItemPage enterNameToSearchFor(String name) {
        LOGGER.info("Entering name to search for:" + name);
        type(searchNameFieldSelector, name);
        return this;
    }


    public ItemPage clickSaveButton() {
        LOGGER.info("Clicking Save button");
        click(saveButtonSelector);
        return this;
    }

    public ItemPage clickCreateNewItemButton() {
        LOGGER.info("Clicking Create New Item button");
        click(createNewButtonSelector);
        return this;
    }

    public String getSuccessMessage() {
        return getText(successMessageSelector);
    }

    public String getEmptyListMessage() {
        return getText(emptyResultListSelector);
    }

    public String getResultTableInfo() {
        return getText(searchResultTableSelector);
    }
}
